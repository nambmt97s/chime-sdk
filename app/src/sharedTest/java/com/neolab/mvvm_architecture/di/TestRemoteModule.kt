package com.neolab.mvvm_architecture.di
import com.neolab.mvvm_architecture.BuildConfig
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import javax.net.ssl.HostnameVerifier

val testRemoteModule = module {
    single { provideOkHttpCache(androidApplication()) }
    single { provideInterceptor() }
    single { providerX509TrustManager() }
    single { providerSslSocketFactory(get()) }
    single { provideOkHttpClient(get(), get(), get(), get()) }
    single { provideApi(get(), get()) }
}

fun provideOkHttpClient(
    cache: Cache,
    interceptor: Interceptor,
    sslSocketFactory: SSLSocketFactory,
    trustAllCerts: X509TrustManager
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
    httpClientBuilder.hostnameVerifier(HostnameVerifier { _, _ -> true })

    if (BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        httpClientBuilder.addInterceptor(logging)
        logging.level = HttpLoggingInterceptor.Level.BASIC
    }

    return httpClientBuilder.build()
}

object RemoteConstants {
    const val READ_TIMEOUT: Long = 5
    const val WRITE_TIMEOUT: Long = 5
    const val CONNECTION_TIMEOUT: Long = 5
}
