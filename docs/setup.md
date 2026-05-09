<a href="https://jitpack.io/#komodgn/compose-codeview">
   <img src="https://jitpack.io/v/komodgn/compose-codeview.svg" alt="JitPack Version">
</a> 
<br>

## JitPack Setup
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

## Version Catalog
If you are using libs.versions.toml:

```toml
[versions]
composeCodeview = "$version"

[libraries]
compose-codeview-core = { module = "com.github.komodgn.compose-codeview:core", version.ref = "composeCodeview" }
compose-codeview = { module = "com.github.komodgn.compose-codeview:compose", version.ref = "composeCodeview" }
```

## Gradle Setup
Add the dependency below to your module's build.gradle.kts file.

### For KMP (commonMain)
```Kotlin
sourceSets {
    commonMain.dependencies {
        implementation(libs.compose.codeview.core)
        implementation(libs.compose.codeview)
    }
}
```
### For Android-only projects
```Kotlin
dependencies {
    implementation(libs.compose.codeview.core)
    implementation(libs.compose.codeview)
}
```