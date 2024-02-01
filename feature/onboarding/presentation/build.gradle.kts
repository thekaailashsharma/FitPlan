plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "fitplan.ui.onboarding"
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

    // Theme module
    implementation(project(":theme"))

    // Base UI module
    implementation(project(":baseui"))

    // SignIn module
    implementation(project(":feature:signin:presentation"))

    // Onboarding module:data
    implementation(project(":feature:onboarding:data"))
}