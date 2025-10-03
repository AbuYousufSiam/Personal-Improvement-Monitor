plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.improvementmonitor"
    compileSdk = 35 // Compile sdk is 1 greater than target sdk

    defaultConfig {
        applicationId = "com.example.improvementmonitor"
        minSdk = 26    // Set minSdk to 26 or higher
        //noinspection OldTargetApi
        targetSdk = 34 //Android 14
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
    implementation(libs.activity)

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.0.0")
    implementation ("com.faendir.rhino:rhino-android:1.5.2")
    implementation ("org.mozilla:rhino:1.7.13")
    implementation ("net.objecthunter:exp4j:0.4.8")
    implementation(libs.firebase.firestore)


    // Add JUnit dependency for unit testing
    testImplementation(libs.junit)

    // If you are using AndroidX Test, include these
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)
}
