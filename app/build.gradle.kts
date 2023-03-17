plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

@Suppress("UnstableApiUsage")
android {
    compileSdk = Sdk.compile_sdk_version

    defaultConfig {
        applicationId = AppConfiguration.application_id
        minSdk = Sdk.min_sdk_version
        targetSdk = Sdk.target_sdk_version
        versionCode = AppConfiguration.version_code
        versionName = AppConfiguration.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    flavorDimensions.add("version")

    productFlavors {
        create("dev") {
            dimension = "version"
            versionNameSuffix = "-dev"
            applicationId = AppConfiguration.application_id
            buildConfigField("String", "BASE_URL", "\"https://startup-compose-example/api/\"")
            resValue("string",  "app_name", "DEV-${AppConfiguration.application_name}")
        }

        create("stag") {
            dimension = "version"
            versionNameSuffix = "-stag"
            applicationId = AppConfiguration.application_id
            buildConfigField("String", "BASE_URL", "\"https://startup-compose-example/api/\"")
            resValue("string",  "app_name", "STAG-${AppConfiguration.application_name}")
        }

        create("prod") {
            dimension = "version"
            applicationId = AppConfiguration.application_id
            buildConfigField("String", "BASE_URL", "\"https://startup-compose-example/api/\"")
            resValue("string",  "app_name", AppConfiguration.application_name)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = AppConfiguration.application_id
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(CoreLibs.core_ktx)
    implementation(CoreLibs.appcompat)
    implementation(CoreLibs.activity_compose)
    implementation(CoreLibs.material)

    implementation(LifecycleLibs.runtime_ktx)
    implementation(LifecycleLibs.viewmodel_ktx)
    implementation(LifecycleLibs.runtime_compose)

    implementation(NavigationLibs.navigation_compose)

    implementation(AccompanistLibs.insets)
    implementation(AccompanistLibs.systemuicontroller)

    implementation(CoroutinesLibs.core)
    implementation(CoroutinesLibs.play_services)

    implementation(HiltLibs.android)
    kapt(HiltLibs.android_compiler)
    implementation(HiltLibs.navigation_compose)

    implementation(RetrofitLibs.retrofit)
    implementation(RetrofitLibs.converter_gson)
    implementation(RetrofitLibs.logging_interceptor)

    implementation(ComposeLibs.ui)
    implementation(ComposeLibs.material3)
    implementation(ComposeLibs.material3_window_size)
    implementation(ComposeLibs.foundation)
    implementation(ComposeLibs.runtime)

    implementation(RoomLibs.room_runtime)
    implementation(RoomLibs.room_ktx)
    annotationProcessor(RoomLibs.room_compiler)
    kapt(RoomLibs.room_compiler)

    implementation(DataStoreLibs.datastore_preferences)

    implementation(PagingLibs.accompanist_pager)
    implementation(PagingLibs.accompanist_pager_ind)

    implementation(SplashScreen.splashscreen)

    implementation(platform(FirebaseLibs.firebase_bom))
    implementation(FirebaseLibs.analytics)
    implementation(FirebaseLibs.crashlytics)
    implementation(FirebaseLibs.auth)
    implementation(FirebaseLibs.firestore)
    implementation(FirebaseLibs.perf)
    implementation(FirebaseLibs.config)

    implementation(PlayServicesLibs.auth)

    implementation(Coil.coil)

    debugImplementation(ComposeLibs.ui_tooling)
    debugImplementation(ComposeLibs.ui_tooling_preview)

    implementation(GeoLocation.location)
    implementation(GeoLocation.accompaints_permission)

    testImplementation(TestLibs.junit)
    androidTestImplementation(AndroidXTestLibs.junit)
    androidTestImplementation(ComposeLibs.ui_test_junit4)
}