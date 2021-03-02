buildscript {
    apply(from = "$rootDir/team-props/git-hooks.gradle.kts")
    val kotlin_version by extra("1.3.72")

    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath(ClassPaths.android_gradle_plugin)
        classpath(ClassPaths.kotlin_gradle_plugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
