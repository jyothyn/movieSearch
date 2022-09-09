plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    // to use ... by navArgs()
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 32
    buildToolsVersion = "33.0.0"

    defaultConfig {
        applicationId = "com.cleanarch.moviesearch"
        minSdk = 28
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
//    lintOptions {
//        disable("JvmStaticProvidesInObjectDetector","FieldSiteTargetOnQualifierAnnotation", "FieldSiteTargetOnQualifierAnnotation", "ModuleCompanionObjects", "ModuleCompanionObjectsNotInModuleParent")
//    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    // Hilt dependencies
    val hiltVersion = "2.42"
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    // KotlinJsonAdapterFactory
//    implementation 'com.squareup.moshi:moshi-kotlin:1.13.0'
//    implementation "com.squareup.okhttp3:logging-interceptor:4.9.0"
    // MoshiConverterFactory
//    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    // com.google.gson.annotations.SerializedName
    implementation("com.google.code.gson:gson:2.9.0")
    val glideVersion = "4.12.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
//    annotationProcessor("com.github.bumptech.glide:compiler:$glide_version")
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}