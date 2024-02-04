plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.test.application.onlinestore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.test.application.onlinestore"
        minSdk = 29
        targetSdk = 34
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":account_profile_screen"))
    implementation(project(":auth_screen"))
    implementation(project(":catalogue_screen"))
    implementation(project(":favourite_screen"))
    implementation(project(":product_card_screen"))
    implementation(project(":local_data"))
    implementation(project(":remote_data"))

    //Kotlin
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    //AndroidX
    implementation("androidx.appcompat:appcompat:1.6.1")

    //Design
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    //Hilt
    implementation ("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    //Dagger
    implementation("com.google.dagger:dagger:2.49")
    implementation("com.google.dagger:dagger-android-support:2.49")
    kapt("com.google.dagger:dagger-compiler:2.49")
}