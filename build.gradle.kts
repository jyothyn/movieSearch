// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "7.2.1" apply false
////    id("com.android.library") version "7.2.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
//}
buildscript {
//    ext.kotlin_version = "1.7.10"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.2.2")
        // 1.7.10. hilt 2.40 will work only with kotlin 1.6.0?
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.1")
        /*
        classpath 'com.android.tools.build:gradle:7.2.2'
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0"
        classpath "com.google.dagger:hilt-android-gradle-plugin:2.40"
         */

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
// gradlePluginPortal() allows you to resolve any plugin in the buildscript { } block
allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}