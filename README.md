# CodeView
[![](https://jitpack.io/v/komodgn/compose-codeview.svg)](https://jitpack.io/#komodgn/compose-codeview) <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-purple.svg"/></a>

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

## Usage
You can easily integrate syntax highlighting into your Compose UI.

```Kotlin
import io.github.komodgn.codeview.compose.CodeView
import io.github.komodgn.codeview.core.CodeLanguage

@Composable
fun CodeViewerExample() {
    val demoCode = """
        /**
         * CodeView enables seamless syntax highlighting.
         */
        fun main() {
            println("Hello, CodeView!")
        }
    """.trimIndent()

    CodeView(
        code = demoCode,
        language = CodeLanguage.KOTLIN,
    )
}
```
