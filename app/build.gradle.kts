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
    implementation(Kotlin.core)
    implementation(AndroidX.fragment_ktx)

    //Design
    implementation(AndroidX.appcompat)
    implementation(Design.material)
    implementation(Design.constraint_layout)

    //Navigation
    implementation(Navigation.fragment_ktx)
    implementation(Navigation.ui_ktx)

    //Hilt
    implementation (Hilt.main)
    kapt(Hilt.compiler)

    //Dagger
    implementation(Dagger.main)
    implementation(Dagger.android_support)
    kapt(Dagger.compiler)
}