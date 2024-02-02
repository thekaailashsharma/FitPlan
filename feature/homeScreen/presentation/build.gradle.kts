plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Plugins.`google-gms-google-services`)
}

android {
    namespace = "fitplan.ui.presentation"
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

    // Navigation
    implementation(Dependencies.`navigation-compose`)

    // Collapsing Toolbar
    implementation(Dependencies.`collapsing-toolbar`)

    // Jet FireStore
    implementation(Dependencies.`jet-firestore`)

    // Swipeable
    implementation("me.saket.swipe:swipe:1.2.0")

    // Datastore module
    implementation(project(":core:datastore"))

    // Theme module
    implementation(project(":theme"))

    // Base UI module
    implementation(project(":baseui"))

    // Room Module
    implementation(project(":core:room"))

    // Firebase module
    implementation(project(":core:firebase"))

    // Firebase
    implementation(platform(Dependencies.`firebase-bom`))
    implementation(Dependencies.`firebase-auth`)
    implementation(Dependencies.`firebase-firestore`)

    // Onboarding module: Data
    implementation(project(":feature:onboarding:data"))
    //Onboarding Presentation Module
    implementation(project(":feature:onboarding:presentation"))

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}