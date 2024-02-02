plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id(Plugins.`dagger-hilt-android`)
}

android {
    namespace = "fitplan.core.room"
    compileSdk = 33

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // Core and Kotlin
    implementation(Dependencies.`core-ktx`)
    implementation(Dependencies.`lifecycle-runtime-ktx`)
    implementation(Dependencies.`activity-compose`)

    // Room
    implementation(Dependencies.`room-runtime`)
    implementation(Dependencies.`room-ktx`)
    kapt(Dependencies.`room-compiler`)

    // Dagger Hilt
    implementation(Dependencies.`hilt-android`)
    implementation(Dependencies.`hilt-navigation-compose`)
    kapt(Dependencies.`hilt-compiler`)

    //Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}