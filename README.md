<div align="center">
    <h1>CodeView</h1>
    <p>👻 <a href="https://komodgn.github.io/compose-codeview/demo">Live Playground</a></p>
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

## Table of contents
1. [Setup](#setup)
2. [Usage](#usage)
3. [Contributing](#-contributing)

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

#### Parameters

| PARAMETER         | TYPE           | DESCRIPTION                                                                      | DEFAULT    |
|-------------------|----------------|----------------------------------------------------------------------------------|------------|
| `code`            | `String`       | The source code string to be highlighted.                                        | (Required) |
| `language`        | `CodeLanguage` | The programming language to use for syntax analysis (e.g., `KOTLIN`, `JAVA`).    | (Required) |
| `showLineNumbers` | `Boolean`      | Whether to display line numbers on the left side of the code.                    | `true`     |
| `modifier`        | `Modifier`     | The modifier to be applied to the CodeView container.                            | `Modifier` |
| `fontFamily`      | `FontFamily?`  | The font family for the code text. If null, it defaults to FontFamily.Monospace. | `null`     |

## ⭐ Contributing
You can see the 👉 [Contributing Guide](https://github.com/komodgn/compose-codeview?tab=contributing-ov-file).
