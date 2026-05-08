## Setup
Add the dependency below to your module's build.gradle.kts file.

1. Add Repository
   Add the JitPack repository to your root settings.gradle.kts:

```Kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Add this
    }
}
```

2. Add Dependency
- If you are using libs.versions.toml:

```toml
[versions]
composeCodeview = "$version"

[libraries]
compose-codeview-core = { module = "com.github.komodgn.compose-codeview:core", version.ref = "composeCodeview" }
compose-codeview = { module = "com.github.komodgn.compose-codeview:compose", version.ref = "composeCodeview" }
```

- Then, add the dependency to your build.gradle.kts:

```Kotlin
// For KMP (commonMain)
sourceSets {
    commonMain.dependencies {
        implementation(libs.compose.codeview.core)
        implementation(libs.compose.codeview)
    }
}

// For Android-only projects
dependencies {
    implementation(libs.compose.codeview.core)
    implementation(libs.compose.codeview)
}
```
