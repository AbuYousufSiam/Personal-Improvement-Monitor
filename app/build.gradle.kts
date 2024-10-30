plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.grevocabularyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.grevocabularyapp"
        minSdk = 26    // Set minSdk to 26 or higher
        //noinspection OldTargetApi
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.core.v190)
    implementation(libs.appcompat)
    implementation(libs.material.v170)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation(libs.firebase.inappmessaging)
    implementation(libs.support.annotations)
    implementation(libs.material)
    implementation(libs.drawerlayout)

    // Add JUnit dependency for unit testing
    testImplementation(libs.junit)

    // If you are using AndroidX Test, include these
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)
}
