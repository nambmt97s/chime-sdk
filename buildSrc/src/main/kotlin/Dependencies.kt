import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Created by ThuanPx on 3/15/20.
 * Source: https://docs.gradle.org/current/userguide/kotlin_dsl.html
 * Versions in project
 */
object Versions {
    // Release notes https://github.com/JetBrains/kotlin/blob/master/ChangeLog.md
    const val kotlin = "1.3.72"

    // Release notes https://developer.android.com/studio/releases/gradle-plugin
    const val android_gradle_plugin = "4.0.0"

    const val compile_sdk_version = 29
    const val build_tools_version = "29.0.2"
    const val min_sdk_version = 21
    const val target_sdk_version = 29

    // Release notes https://developer.android.com/jetpack/androidx/releases/appcompat
    const val appcompat = "1.1.0"

    // Release notes https://developer.android.com/jetpack/androidx/releases/core
    const val core_ktx = "1.1.0"

    // Release notes https://developer.android.com/jetpack/androidx/releases/lifecycle
    const val lifecycle_ktx = "2.2.0"

    // Release notes https://developer.android.com/jetpack/androidx/releases/constraintlayout
    const val constraint_layout = "1.1.3"

    // Release notes https://developer.android.com/jetpack/androidx/releases/compose-material
    const val material = "1.2.0-beta01"

    // Release notes https://developer.android.com/jetpack/androidx/releases/room
    const val room_version = "2.2.5"

    // Release notes https://github.com/pinterest/ktlint/blob/master/CHANGELOG.md
    const val ktlint = "0.36.0"

    // Release notes https://github.com/InsertKoinIO/koin/blob/master/CHANGELOG.md
    const val koin = "2.2.1"

    // Release notes https://github.com/square/retrofit/blob/master/CHANGELOG.md
    const val retrofit = "2.7.1"
    const val retrofit_adapter_rxjava3 = "3.0.0-RC8"

    // Release notes https://github.com/square/okhttp/blob/master/CHANGELOG.md
    const val okhttp = "4.7.2"

    // Release notes https://github.com/bumptech/glide/releases
    const val glide = "4.8.0"

    // Release notes https://github.com/Kotlin/kotlinx.coroutines/blob/master/CHANGES.md
    const val coroutines = "1.3.2"

    // Release notes https://github.com/JakeWharton/timber/blob/master/CHANGELOG.md
    const val timber = "4.7.1"

    // Release notes https://square.github.io/leakcanary/changelog/
    const val leak_canary = "2.0"

    // Release notes https://plugins.gradle.org/plugin/name.remal.check-dependency-updates
    const val check_dependency_updates = "1.0.211"

    const val pusher = "2.0.2"
    const val lottie = "3.3.1"
    const val kt_ext = "1.0.0"
    const val conscrypt_android = "2.4.0"
    const val detekt = "1.9.1"
    const val rxjava = "3.0.0"

    // Release notes https://developer.android.com/jetpack/androidx/releases/recyclerview
    const val recycler_view = "1.1.0"

    // Release notes https://developer.android.com/jetpack/androidx/releases/annotation
    const val annotations = "1.1.0"

    // Release notes https://developer.android.com/jetpack/androidx/releases/legacy
    const val legacy = "1.0.0"

    // Release notes https://github.com/junit-team/junit4/wiki
    const val junit = "4.13"

    // Release notes https://developer.android.com/jetpack/androidx/releases/archive/test
    const val test_core = "1.0.0"
    const val espresso = "3.3.0"
    const val androidx_test_ktx = "1.1.2"
    const val text_rule = "1.3.0"

    // Release notes https://github.com/mockito/mockito/releases
    const val mockito = "3.5.13"

    // Release notes https://github.com/hamcrest/JavaHamcrest/releases
    const val hamcrest = "1.3"

    // Release notes https://developer.android.com/jetpack/androidx/releases/arch-core
    const val arch_core_test = "2.1.0"

    // Release notes https://github.com/robolectric/robolectric/releases
    const val robolectric = "4.4"

    // Release notes https://github.com/google/truth/releases
    const val google_truth = "1.0.1"
}

/**
 * Gradle and kotlin in project(Project: project)
 */
object ClassPaths {
    const val android_gradle_plugin =
        "com.android.tools.build:gradle:${Versions.android_gradle_plugin}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

/**
 * Plugin in project(Module: app)
 */
object Plugins {
    const val androidApp = "com.android.application"
    const val kotlinAndroid = "android"
    const val kotlin = "kotlin"
    const val kotlinExt = "android.extensions"
    const val kotlinApt = "kapt"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val koin = "koin"
    const val checkDependencyUpdates = "name.remal.check-dependency-updates"
}

/**
 * Dependency in project
 */
object Dependencies {

    const val support_app_compat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val support_design = "com.google.android.material:material:${Versions.material}"
    const val support_core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val support_core = "androidx.core:core:${Versions.core_ktx}"
    const val view_model_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_ktx}"
    const val live_data_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_ktx}"

    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"

    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    const val ktlint_core = "com.pinterest:ktlint:ktlint-core:${Versions.ktlint}"

    const val koin_view_model = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koin_ext = "org.koin:koin-androidx-ext:${Versions.koin}"

    const val rx_java = "io.reactivex.rxjava3:rxjava:${Versions.rxjava}"
    const val rx_android = "io.reactivex.rxjava3:rxandroid:${Versions.rxjava}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_adapter_rxjava =
        "com.github.akarnokd:rxjava3-retrofit-adapter:${Versions.retrofit_adapter_rxjava3}"

    const val ok_http = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val ok_http_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val leak_canary = "com.squareup.leakcanary:leakcanary-android:${Versions.leak_canary}"

    const val room_runtime = "androidx.room:room-runtime:${Versions.room_version}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"

    const val pusher = "com.pusher:pusher-java-client:${Versions.pusher}"

    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    const val kt_ext = "com.github.ThuanPx:KtExt:${Versions.kt_ext}"

    const val conscrypt_android = "org.conscrypt:conscrypt-android:${Versions.conscrypt_android}"
}

fun DependencyHandler.implementLocalUnitTest() {
    add("implementation", "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}")
    add("testImplementation", "junit:junit:${Versions.junit}")
    add("testImplementation", "androidx.test.ext:junit-ktx:${Versions.androidx_test_ktx}")
    add("testImplementation", "androidx.test:core:${Versions.test_core}")
    add("testImplementation", "org.mockito:mockito-core:${Versions.mockito}")
    add("testImplementation", "org.hamcrest:hamcrest-all:${Versions.hamcrest}")
    add("testImplementation", "androidx.test:rules:${Versions.text_rule}")
    add(
        "testImplementation",
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    )
    add(
        "testImplementation",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    )
    add("testImplementation", "androidx.arch.core:core-testing:${Versions.arch_core_test}")
    add("testImplementation", "org.robolectric:robolectric:${Versions.robolectric}")
    add("testImplementation", "com.google.truth:truth:${Versions.google_truth}")
    add("testImplementation", "org.koin:koin-test:${Versions.koin}")
}

fun DependencyHandler.implementInstrumentationUnitTest() {
    add("androidTestImplementation", "junit:junit:${Versions.junit}")
    add("androidTestImplementation", "androidx.test.ext:junit-ktx:${Versions.androidx_test_ktx}")
    add("androidTestImplementation", "org.mockito:mockito-core:${Versions.mockito}")
    add("androidTestImplementation", "androidx.test:core:${Versions.test_core}")
    add(
        "androidTestImplementation",
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    )
    add("androidTestImplementation", "androidx.test:rules:${Versions.text_rule}")
    add("androidTestImplementation", "androidx.arch.core:core-testing:${Versions.arch_core_test}")
    add("androidTestImplementation", "androidx.test.espresso:espresso-core:${Versions.espresso}")
    add("androidTestImplementation", "androidx.test.espresso:espresso-contrib:${Versions.espresso}")
    add("androidTestImplementation", "androidx.test.espresso:espresso-intents:${Versions.espresso}")
    add(
        "androidTestImplementation",
        "androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}"
    )
    add("androidTestImplementation", "org.robolectric:annotations:${Versions.robolectric}")
    add("androidTestImplementation", "com.google.truth:truth:${Versions.google_truth}")

    // Resolve conflicts between main and test APK:
    add("androidTestImplementation", "androidx.annotation:annotation:${Versions.annotations}")
    add("androidTestImplementation", "androidx.legacy:legacy-support-v4:${Versions.legacy}")
    add("androidTestImplementation", "androidx.recyclerview:recyclerview:${Versions.recycler_view}")
    add("androidTestImplementation", "androidx.appcompat:appcompat:${Versions.appcompat}")
    add("androidTestImplementation", "com.google.android.material:material:${Versions.material}")
    add("androidTestImplementation", "org.koin:koin-test:${Versions.koin}")
}
