## Android App with Room and Hilt

---

libs.version.toml

```toml
[versions]
hiltAndroid = "2.49"
google-devtools-ksp-version = "1.9.10-1.0.13"
roomRuntime = "2.6.1"
hiltNavigationCompose = "1.2.0"
[libraries]
androidx-room-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomRuntime" }
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "roomRuntime" }
androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "roomRuntime" }
hilt-android = { module = "com.google.dagger:hilt-android", version.ref = "hiltAndroid" }
hilt-android-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hiltAndroid" }
androidx-hilt-navigation-compose = { module = "androidx.hilt:hilt-navigation-compose", version.ref = "hiltNavigationCompose" }
[plugins]
hilt-android = { id = "com.google.dagger.hilt.android", version.ref = "hiltAndroid" }
google-devtools-ksp = { id = "com.google.devtools.ksp", version.ref = "google-devtools-ksp-version" }
room-android = { id = "androidx.room", version.ref = "roomRuntime" }
```

---

build.gradle.kts (Project Level)

```kotlin
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias { libs.plugins.hilt.android } apply false
    alias { libs.plugins.google.devtools.ksp } apply false
    alias { libs.plugins.room.android } apply false
}
```

---

build.gradle.kts (Module Level)

```kotlin
plugins {
    alias { libs.plugins.google.devtools.ksp }
    alias { libs.plugins.hilt.android }
    alias { libs.plugins.room.android }
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}
```

```kotlin
android {
    room {
        schemaDirectory("$projectDir/schemas")
    }
    kapt {
        correctErrorTypes = true
    }
}
```

```kotlin
dependencies {
    // Room Database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.room.compiler)

    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
}
```
