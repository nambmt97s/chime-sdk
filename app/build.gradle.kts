import java.text.SimpleDateFormat
import java.util.Calendar

plugins {
    id(Plugins.androidApp)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinExt)
    kotlin(Plugins.kotlinApt)
    id(Plugins.checkDependencyUpdates) version (Versions.check_dependency_updates)
}

buildscript {
    apply(from = "../buildSrc/ktlint.gradle.kts")
}

android {
    compileSdkVersion(Versions.compile_sdk_version)
    buildToolsVersion(Versions.build_tools_version)

    flavorDimensions("default")

    defaultConfig {
        applicationId = "com.neolab.mvvm_architecture"
        minSdkVersion(Versions.min_sdk_version)
        targetSdkVersion(Versions.target_sdk_version)
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "com.neolab.mvvm_architecture.app.CustomTestRunner"
    }

    productFlavors {
        create("DEV") {
            applicationIdSuffix = ".dev"
            versionCode = 1
            versionName = "1.0.0"

            resValue("string", "app_name", "Android Team Base")
            buildConfigField("String", "END_POINT", "\"https://api-dev.neo-lab.com/v1/\"")
        }

        create("PROD") {
            versionCode = 1
            versionName = "1.0.0"

            resValue("string", "app_name", "Android Team Base")
            buildConfigField("String", "END_POINT", "\"https://api-dev.neo-lab.com/v1/\"")
        }

        applicationVariants.all {
            outputs.forEach { output ->
                if (output is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                    output.outputFileName =
                        "App-${SimpleDateFormat("HH_mm_dd_MM_yyyy").format(Calendar.getInstance().time)}-v$versionName(${this.versionCode})-$name.${output.outputFile.extension}"
                }
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                file("proguard-rules.pro"),
                file("androidx-proguard-rules.pro")
            )
        }
    }

    dexOptions {
        javaMaxHeapSize = "4g"
        preDexLibraries = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/*.kotlin_module")
    }

    sourceSets {
        sourceSets {
            val sharedTestDir = "src/sharedTest/java"
            getByName("androidTest").java.srcDirs(sharedTestDir)
            getByName("test").java.srcDirs(sharedTestDir)
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

androidExtensions {
    isExperimental = true
}

kapt {
    useBuildCache = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // Kotlin
    implementation(Dependencies.kotlin_stdlib)
    // App compat & design
    implementation(Dependencies.support_app_compat)
    implementation(Dependencies.support_core)
    implementation(Dependencies.support_design)
    implementation(Dependencies.constraint_layout)
    // Rxjava
    implementation(Dependencies.rx_java)
    implementation(Dependencies.rx_android)
    // Coroutines
    implementation(Dependencies.coroutines_core)
    implementation(Dependencies.coroutines_android)
    // Room
    implementation(Dependencies.room_runtime)
    implementation(Dependencies.room_compiler)
    implementation(Dependencies.room_ktx)
    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofit_converter_gson)
    // Okhttp
    implementation(Dependencies.ok_http)
    implementation(Dependencies.ok_http_logging)
    // Koin
    implementation(Dependencies.koin_view_model)
    implementation(Dependencies.koin_ext)
    // Glide
    implementation(Dependencies.glide)
    annotationProcessor(Dependencies.glide_compiler)
    kapt(Dependencies.glide_compiler)
    // Leak canary
    debugImplementation(Dependencies.leak_canary)
    // Timber
    implementation(Dependencies.timber)
    // KTX
    implementation(Dependencies.support_core_ktx)
    implementation(Dependencies.view_model_ktx)
    implementation(Dependencies.live_data_ktx)

    // Unit Test
    implementLocalUnitTest()
    implementInstrumentationUnitTest()
}
