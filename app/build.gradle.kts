plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.breadcrumnavigation"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.breadcrumnavigation"
        minSdk = 24
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // LiveData (often used with ViewModel)
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Lifecycle extensions
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("com.google.code.gson:gson:2.13.1")
}