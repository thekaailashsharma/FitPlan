object Versions {
    // Core and Kotlin
    const val `core-ktx` = "1.9.0"
    const val `lifecycle-runtime-ktx` = "2.6.2"
    const val `activity-compose` = "1.8.2"
    const val `compose-bom` = "2023.03.00"

    // Material Extended Icons
    const val `material3-icons-extended` = "1.5.4"

    // Navigation
    const val `navigation-compose` = "2.7.5"

    // Lottie
    const val `lottie-compose` = "5.2.0"

    // Ktor-Client
    const val `ktor-client-core` = "2.3.5"
    const val `ktor-client` = "2.3.2"
    const val `ktor-client-android` = "1.6.4"

    // Gson
    const val gson = "2.8.9"

    // Logback Classic
    const val `logback-classic` = "1.2.11"

    // Preferences DataStore
    const val `datastore-preferences` = "1.0.0"

    // Dagger Hilt
    const val `hilt-android` = "2.48"
    const val `hilt-navigation-compose` = "1.1.0"

    // Room
    const val `room-runtime` = "2.6.1"

    // Coil
    const val `coil-compose` = "2.2.2"

    // Firebase
    const val `firebase-bom` = "32.6.0"

    // JetFirestore
    const val `jet-firestore` = "1.0.2"

}


object Dependencies {
    // Core and Kotlin
    const val `core-ktx` = "androidx.core:core-ktx:${Versions.`core-ktx`}"
    const val `lifecycle-runtime-ktx` = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.`lifecycle-runtime-ktx`}"
    const val `activity-compose` = "androidx.activity:activity-compose:${Versions.`activity-compose`}"
    const val `compose-bom` = "androidx.compose:compose-bom:${Versions.`compose-bom`}"

    // Material Extended Icons
    const val `material3-icons-extended` = "androidx.compose.material:material-icons-extended:${Versions.`material3-icons-extended`}"

    // Navigation
    const val `navigation-compose` = "androidx.navigation:navigation-compose:${Versions.`navigation-compose`}"

    // Lottie
    const val `lottie-compose` = "com.airbnb.android:lottie-compose:${Versions.`lottie-compose`}"

    // Ktor-Client
    const val `ktor-client-core` = "io.ktor:ktor-client-core:${Versions.`ktor-client-core`}"
    const val `ktor-client` = "io.ktor:ktor-client:${Versions.`ktor-client`}"
    const val `ktor-client-android` = "io.ktor:ktor-client-android:${Versions.`ktor-client-android`}"
    const val `ktor-client-cio` = "io.ktor:ktor-client-cio:${Versions.`ktor-client`}"
    const val `ktor-client-serialization` = "io.ktor:ktor-client-serialization:${Versions.`ktor-client`}"
    const val `ktor-client-logging` = "io.ktor:ktor-client-logging:${Versions.`ktor-client`}"
    const val `ktor-client-jvm` = "io.ktor:ktor-client-cio-jvm:${Versions.`ktor-client`}"
    const val `ktor-client-content-negotiation` = "io.ktor:ktor-client-content-negotiation:${Versions.`ktor-client`}"
    const val `ktor-client-serialization-gson` = "io.ktor:ktor-serialization-gson:${Versions.`ktor-client`}"
    const val `ktor-serialization-kotlinx-json` = "io.ktor:ktor-serialization-kotlinx-json:${Versions.`ktor-client`}"
    const val `ktor-client-logging-jvm` = "io.ktor:ktor-client-logging-jvm::${Versions.`ktor-client`}"
    const val `ktor-client-gson` = "io.ktor:ktor-client-gson:${Versions.`ktor-client`}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    // Logback Classic
    const val `logback-classic` = "ch.qos.logback:logback-classic:${Versions.`logback-classic`}"

    // Preferences DataStore
    const val `datastore-preferences` = "androidx.datastore:datastore-preferences:${Versions.`datastore-preferences`}"

    // Dagger Hilt
    const val `hilt-android` = "com.google.dagger:hilt-android:${Versions.`hilt-android`}"
    const val `hilt-navigation-compose` = "androidx.hilt:hilt-navigation-compose:${Versions.`hilt-navigation-compose`}"
    const val `hilt-compiler` = "com.google.dagger:hilt-compiler:${Versions.`hilt-android`}"

    // Room
    const val `room-runtime` = "androidx.room:room-runtime:${Versions.`room-runtime`}"
    const val `room-compiler` = "androidx.room:room-compiler:${Versions.`room-runtime`}"
    const val `room-ktx` = "androidx.room:room-ktx:${Versions.`room-runtime`}"

    // Coil
    const val `coil-compose` = "io.coil-kt:coil-compose:${Versions.`coil-compose`}"

    // Compose
    const val `compose-ui` = "androidx.compose.ui:ui"
    const val `compose-ui-graphics` = "androidx.compose.ui:ui-graphics"
    const val `compose-ui-tooling-preview` = "androidx.compose.ui:ui-tooling-preview"
    const val `compose-material3` = "androidx.compose.material3:material3"

    // Firebase
    const val `firebase-bom` = "com.google.firebase:firebase-bom:${Versions.`firebase-bom`}"
    const val `firebase-auth` = "com.google.firebase:firebase-auth-ktx"
    const val `firebase-firestore` = "com.google.firebase:firebase-firestore-ktx"
    const val `jet-firestore` = "com.github.raipankaj:JetFirestore:${Versions.`jet-firestore`}"

}


object Plugins {
    const val application = "com.android.application"
    const val `kotlin-android` ="org.jetbrains.kotlin.android"
    const val `dagger-hilt-android` = "dagger.hilt.android.plugin"
    const val kapt = "kapt"
    const val `kotlinx-serialization` = "kotlinx-serialization"
    const val `google-gms-google-services` = "com.google.gms.google-services"
}
