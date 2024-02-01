import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.application)
    id(Plugins.`kotlin-android`)
    id(Plugins.`dagger-hilt-android`)
    kotlin(Plugins.kapt)
    id(Plugins.`kotlinx-serialization`)
    id(Plugins.`google-gms-google-services`)
}

android {
    namespace = "fitplan.planner.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "fitplan.planner.app"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Core and Kotlin
    implementation(Dependencies.`core-ktx`)
    implementation(Dependencies.`lifecycle-runtime-ktx`)
    implementation(Dependencies.`activity-compose`)

    // Compose
    implementation(platform(Dependencies.`compose-bom`))
    implementation(Dependencies.`compose-ui`)
    implementation(Dependencies.`compose-ui-graphics`)
    implementation(Dependencies.`compose-ui-tooling-preview`)
    implementation(Dependencies.`compose-material3`)

    // Material Extended Icons
    implementation(Dependencies.`material3-icons-extended`)

    // Navigation
    implementation(Dependencies.`navigation-compose`)

    // Lottie
    implementation(Dependencies.`lottie-compose`)

    // Ktor-Client
    implementation(Dependencies.`ktor-client-core`)
    implementation(Dependencies.`ktor-client`)
    implementation(Dependencies.`ktor-client-android`)
    implementation(Dependencies.`ktor-client-cio`)
    implementation(Dependencies.`ktor-client-serialization`)
    implementation(Dependencies.`ktor-client-logging`)
    implementation(Dependencies.`ktor-client-jvm`)
    implementation(Dependencies.`ktor-client-content-negotiation`)
    implementation(Dependencies.`ktor-client-serialization-gson`)
    implementation(Dependencies.`ktor-serialization-kotlinx-json`)
//    implementation(Dependencies.`ktor-client-logging-jvm`)
    implementation(Dependencies.`ktor-client-gson`)

    // Gson
    implementation(Dependencies.gson)

    // Logback Classic
    implementation(Dependencies.`logback-classic`)

    // Preferences DataStore
    implementation(Dependencies.`datastore-preferences`)

    // Dagger Hilt
    implementation(Dependencies.`hilt-android`)
    implementation(Dependencies.`hilt-navigation-compose`)
    kapt(Dependencies.`hilt-compiler`)

    // Room
    implementation(Dependencies.`room-runtime`)
    implementation(Dependencies.`room-ktx`)
    kapt(Dependencies.`room-compiler`)

    // Coil
    implementation(Dependencies.`coil-compose`)

    // Onboarding module
    implementation(project(":feature:onboarding:presentation"))

    // Base UI module
    implementation(project(":baseui"))

    // Core Firebase module
    implementation(project(":core:firebase"))


    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}