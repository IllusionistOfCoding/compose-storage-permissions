object Versions {
    const val compose = "1.3.3"
    const val compose_compiler = "1.4.2"
    const val compose_navigation = "2.5.1"
    const val compose_hilt_navigation = "1.0.0"

    const val kotlin = "1.8.10"
    const val hilt = "2.44"
    const val gradle = "7.4.1"

    const val activity = "1.6.1"

    const val room = "2.4.3"
    const val pager = "0.13.0"

    const val firebase = "2.4.3"
}

object Sdk {
    const val compile_sdk_version = 33
    const val target_sdk_version = 33
    const val min_sdk_version = 26
}

object CoreLibs {
    const val core_ktx =                "androidx.core:core-ktx:1.9.0"
    const val appcompat =               "androidx.appcompat:appcompat:${Versions.activity}"
    const val activity_compose =        "androidx.activity:activity-compose:${Versions.activity}"
    const val material =                "com.google.android.material:material:1.6.1"
}

object LifecycleLibs {
    const val runtime_ktx =             "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    const val viewmodel_ktx =           "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    const val runtime_compose =         "androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha03"
}

object NavigationLibs {
    const val navigation_compose =      "androidx.navigation:navigation-compose:2.5.2"
}

object AccompanistLibs {
    const val insets =                  "com.google.accompanist:accompanist-insets:0.23.0"
    const val systemuicontroller =      "com.google.accompanist:accompanist-systemuicontroller:0.23.0"
}

object CoroutinesLibs {
    const val core =                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    const val play_services =           "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.3"
}

object ComposeLibs {
    const val ui =                      "androidx.compose.ui:ui:${Versions.compose}"
    const val animation =               "androidx.compose.animation:animation:${Versions.compose}"
    const val runtime =                 "androidx.compose.runtime:runtime:${Versions.compose}"
    const val ui_tooling =              "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val ui_tooling_preview =      "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val ui_test_junit4 =          "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val material3 =               "androidx.compose.material3:material3:1.0.1"
    const val material3_window_size =   "androidx.compose.material3:material3-window-size-class:1.0.1"
    const val foundation =              "androidx.compose.foundation:foundation:1.3.1"
    const val material =                "androidx.compose.material:material:1.3.1"
}

object HiltLibs {
    const val android =                 "com.google.dagger:hilt-android:${Versions.hilt}"
    const val core =                    "com.google.dagger:hilt-core:${Versions.hilt}"
    const val compiler =                "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val android_compiler =        "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val navigation_compose =      "androidx.hilt:hilt-navigation-compose:${Versions.compose_hilt_navigation}"
}

object RetrofitLibs {
    const val retrofit =                "com.squareup.retrofit2:retrofit:2.8.1"
    const val converter_gson =          "com.squareup.retrofit2:converter-gson:2.8.1"
    const val logging_interceptor =     "com.squareup.okhttp3:logging-interceptor:4.9.1"
}

object AndroidXTestLibs {
    const val junit =                   "androidx.test.ext:junit:1.1.3"
}

object TestLibs {
    const val junit =                   "junit:junit:4.13.2"
}

object PreferenceLibs {
    const val preference_ktx =          "androidx.preference:preference-ktx:1.2.0"
}

object DataStoreLibs {
    const val datastore_preferences =   "androidx.datastore:datastore-preferences:1.0.0"
}

object RoomLibs {
    const val room_runtime =            "androidx.room:room-runtime:${Versions.room}"
    const val room_compiler =           "androidx.room:room-compiler:${Versions.room}"
    const val room_ktx =                "androidx.room:room-ktx:${Versions.room}"
}

object PagingLibs {
    const val accompanist_pager =       "com.google.accompanist:accompanist-pager:${Versions.pager}"
    const val accompanist_pager_ind =   "com.google.accompanist:accompanist-pager-indicators:${Versions.pager}"
}

object SplashScreen {
    const val splashscreen =            "androidx.core:core-splashscreen:1.0.0"
}

object FirebaseLibs {
    const val firebase_bom =            "com.google.firebase:firebase-bom:31.2.2"
    const val analytics =               "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics =             "com.google.firebase:firebase-crashlytics-ktx"
    const val auth =                    "com.google.firebase:firebase-auth-ktx"
    const val firestore =               "com.google.firebase:firebase-firestore-ktx"
    const val perf =                    "com.google.firebase:firebase-perf-ktx"
    const val config =                  "com.google.firebase:firebase-config-ktx"
}

object PlayServicesLibs {
    const val auth =                    "com.google.android.gms:play-services-auth:20.4.1"
}

object Coil {
    const val coil =                    "io.coil-kt:coil-compose:2.0.0-rc01"
}

object GeoLocation{
    const val location =                "com.google.android.gms:play-services-location:21.0.1"
    const val accompaints_permission =  "com.google.accompanist:accompanist-permissions:0.28.0"
}
