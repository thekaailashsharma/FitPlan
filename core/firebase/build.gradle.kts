plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Plugins.`google-gms-google-services`)
    id(Plugins.`dagger-hilt-android`)
    kotlin(Plugins.kapt)
}

android {
    namespace = "fitplan.core.firebase"
    compileSdk = 34

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
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    // Core and Kotlin
    implementation(Dependencies.`core-ktx`)
    implementation(Dependencies.`lifecycle-runtime-ktx`)
    implementation(Dependencies.`activity-compose`)

    // Firebase
    implementation(platform(Dependencies.`firebase-bom`))
    implementation(Dependencies.`firebase-auth`)
    implementation(Dependencies.`firebase-firestore`)

    // Dagger Hilt
    implementation(Dependencies.`hilt-android`)
    implementation(Dependencies.`hilt-navigation-compose`)
    kapt(Dependencies.`hilt-compiler`)

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}