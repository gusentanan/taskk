plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kapt)
    alias(libs.plugins.squareup.wire)
    jacoco
}

android {

    namespace = "com.bagusmerta.taskk"
    compileSdk = 33

//    signingConfigs {
//        create("release") {
//            keyAlias = project.properties["RELEASE_KEY_ALIAS"].toString()
//            keyPassword = project.properties["RELEASE_KEY_PASSWORD"].toString()
//            storeFile = file(project.properties["RELEASE_STORE_FILE"].toString())
//            storePassword = project.properties["RELEASE_STORE_PASSWORD"].toString()
//        }
//    }

    defaultConfig {
        applicationId = "com.bagusmerta.taskk"
        minSdk = 29
        targetSdk = 33
        versionCode = 95
        versionName = "1.21.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/room-schemas")
            }
        }

    }

    buildTypes {
        getByName("release") {
            manifestPlaceholders["appName"] = "@string/app_name"
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
//            signingConfig = signingConfigs.getByName("release")
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
        }
        getByName("debug") {
            applicationIdSuffix = ".debug"
            manifestPlaceholders["appName"] = "@string/app_name_debug"
            isDebuggable = true
            enableUnitTestCoverage = true
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
}

wire {
    kotlin {
        android = true
    }
}



dependencies {
    // App
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.appcompat)
    implementation(libs.coroutines)
    implementation(libs.timber)
    implementation(libs.androidx.test.espresso.core)
    implementation(libs.androidx.dataStore.core)
    implementation(libs.google.material)
    implementation(libs.google.protobuf)
    implementation(libs.google.accompanist.navigation)
    implementation(libs.google.accompanist.systemuicontroller)

    // Arch
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)

    // Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Jetpack Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.widget)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.compiler)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.lottie.compose)


    // Test
    // JVM tests - Hilt
    testImplementation(libs.hilt.android)
    kaptTest(libs.hilt.compiler)

    debugImplementation(libs.androidx.compose.ui.tooling.core)
    debugImplementation(libs.androidx.compose.ui.testManifest)

    testImplementation(libs.test.turbine)
    testImplementation(libs.robolectric)
    testImplementation(libs.junit4)
    testImplementation(libs.hilt.android.testing)
    testImplementation(libs.androidx.compose.ui.test)

    // AndroidX Test - Hilt testing
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)

    androidTestImplementation(libs.androidx.test.ext)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test)
}

// The Jacoco and Robolectric conflict caused an error during unit testing with coverage
// The solution has not yet been implemented.
tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        isIncludeNoLocationClasses = true
        excludes = listOf("jdk.internal.*", "**/*\$*$*")
    }
}
