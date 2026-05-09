<div align="center">
    <h1>CodeView</h1>
    <p>🎨 Beautiful Syntax Highlighting for your Compose apps. Display code everywhere with CodeView.</p>
    <img width="700" alt="Group 10 (1)" src="https://github.com/user-attachments/assets/346b3b3a-5d67-4d3c-a003-30fde3d22346" />
    <br>
    <a href="https://jitpack.io/#komodgn/compose-codeview">
        <img src="https://jitpack.io/v/komodgn/compose-codeview.svg" alt="JitPack Version">
    </a>
    <a href="https://opensource.org/licenses/Apache-2.0">
        <img src="https://img.shields.io/badge/License-Apache%202.0-purple.svg" alt="License">
    </a>
</div>

## Quick Start

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

## ⭐ Contributing
You can see the 👉 [Contributing Guide](https://github.com/komodgn/compose-codeview?tab=contributing-ov-file).
