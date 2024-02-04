object Versions {
    //Kotlin
    const val core_ktx = "1.12.0"

    //AndroidX
    const val appcompat = "1.6.1"
    const val andrx_fragment_ktx = "1.6.2"

    //Design
    const val material = "1.11.0"
    const val constraint_layout = "2.1.4"

    //Retrofit
    const val retrofit_main = "2.9.0"

    //Navigation
    const val navigation = "2.7.6"

    //Room
    const val room_runtime = "2.6.1"

    //Dagger
    const val dagger = "2.49"

    //Hilt
    const val hilt = "2.49"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core_ktx}"
}
object AndroidX {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.andrx_fragment_ktx}"
}

object Design {
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
}

object Retrofit {
    const val main = "com.squareup.retrofit2:retrofit:${Versions.retrofit_main}"
    const val gson_convertor = "com.squareup.retrofit2:converter-gson:${Versions.retrofit_main}"
}

object Navigation {
    const val fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.room_runtime}"
    const val ktx = "androidx.room:room-ktx:${Versions.room_runtime}"
    const val compiler = "androidx.room:room-compiler:${Versions.room_runtime}"
}

object Dagger {
    const val main = "com.google.dagger:dagger:${Versions.dagger}"
    const val android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Hilt {
    const val main = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}