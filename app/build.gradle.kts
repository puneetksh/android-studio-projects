plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
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


    implementation(project(":basicviews"))
    implementation(project(":bottomnavigationviews"))
    implementation(project(":empty"))
    implementation(project(":emptyviews"))
    implementation(project(":fragmentviewmodel"))
    implementation(project(":fullscreenviews"))
    implementation(project(":gogleadmobadsviews"))
    implementation(project(":googlemapsviews"))
    implementation(project(":googlepayviews"))
    implementation(project(":googlewallet"))
    implementation(project(":loginviews"))
    implementation(project(":navigationdrawerviews"))
    implementation(project(":primarydetailsviews"))
    implementation(project(":responsiveviews"))
    implementation(project(":scrollingviews"))
    implementation(project(":settingsviews"))
    implementation(project(":settingsviews2"))
    implementation(project(":tabbedviews"))

}

