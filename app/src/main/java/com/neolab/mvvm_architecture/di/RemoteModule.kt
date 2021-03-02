package com.neolab.mvvm_architecture.di
import android.annotation.SuppressLint
import android.app.Application
import com.google.gson.Gson
import com.neolab.mvvm_architecture.BuildConfig
import com.neolab.mvvm_architecture.data.remote.api.ApiService
import com.neolab.mvvm_architecture.data.remote.api.middleware.ConnectivityInterceptor
import com.neolab.mvvm_architecture.data.remote.api.middleware.InterceptorImpl
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 * Copyright © 2020 Neolab VN.
 * Created by ThuanPx on 8/5/20.
 */

val remoteModule = module {
    single { provideOkHttpCache(androidApplication()) }
    single { provideInterceptor() }
    single { providerX509TrustManager() }
    single { providerSslSocketFactory(get()) }
    single { providerConnectivityInterceptor(androidApplication()) }
    single { provideOkHttpClient(get(), get(), get(), get(), get()) }
    single { provideApi(get(), get()) }
}

fun provideOkHttpCache(app: Application): Cache {
    val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
    return Cache(app.cacheDir, cacheSize)
}

/**
 * Instance of this interface manage which X509 certificates
 * may be used to authenticate the remote side of a secure socket.
 * Decisions may be based on trusted certificate authorities, certificate revocation lists,
 * online status checking or other means.
 */
@SuppressLint("TrustAllX509TrustManager")
fun providerX509TrustManager(): X509TrustManager {
    // Create a trust manager that does not validate certificate chains
    return object : X509TrustManager {
        @Throws(CertificateException::class)
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    }
}

fun providerSslSocketFactory(trust: X509TrustManager): SSLSocketFactory {
    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, arrayOf(trust), java.security.SecureRandom())
    // Create an ssl socket factory with our all-trusting manager
    return sslContext.socketFactory
}

fun providerConnectivityInterceptor(app: Application): ConnectivityInterceptor =
    ConnectivityInterceptor(
        app
    )

fun provideOkHttpClient(
    cache: Cache,
    interceptor: Interceptor,
    sslSocketFactory: SSLSocketFactory,
    trustAllCerts: X509TrustManager,
    connectivityInterceptor: ConnectivityInterceptor
): OkHttpClient {
    val httpClientBuilder = OkHttpClient.Builder()
    httpClientBuilder.cache(cache)
    httpClientBuilder.addInterceptor(interceptor)

    httpClientBuilder.readTimeout(
        RemoteConstants.READ_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.writeTimeout(
        RemoteConstants.WRITE_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.connectTimeout(
        RemoteConstants.CONNECTION_TIMEOUT, TimeUnit.SECONDS
    )
    httpClientBuilder.sslSocketFactory(sslSocketFactory, trustAllCerts)
    httpClientBuilder.addInterceptor(connectivityInterceptor)
    httpClientBuilder.hostnameVerifier(HostnameVerifier { _, _ -> true })

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        httpClientBuilder.addInterceptor(logging)
        logging.level = HttpLoggingInterceptor.Level.BODY
    }

    return httpClientBuilder.build()
}

fun provideInterceptor(): Interceptor {
    return InterceptorImpl()
}

private fun createTemplateRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.END_POINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
}

fun provideApi(gson: Gson, okHttpClient: OkHttpClient): ApiService {
    return createTemplateRetrofit(okHttpClient, gson).create(ApiService::class.java)
}

object RemoteConstants {
    const val READ_TIMEOUT: Long = 30
    const val WRITE_TIMEOUT: Long = 30
    const val CONNECTION_TIMEOUT: Long = 30
}
