# CodeView Overview

🎨 Beautiful Syntax Highlighting for your Compose apps. Display code everywhere with CodeView.

## CodeView

```kotlin
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

### Parameters

| Parameter    | Type           | Default | Description                                                                 |
|--------------|----------------|-------|-----------------------------------------------------------------------------|
| `code`       | `String`       | (Required) | The source code string to be highlighted. |
| `language`   | `CodeLanguage` | (Required) | The programming language to use for syntax analysis (e.g., KOTLIN, JAVA). |
| `modifier`   | `Modifier`     | `Modifier` | The modifier to be applied to the layout (padding, background, etc.). |
| `fontFamily` | `FontFamily`   | `null` | The font family to be applied to the code text. Monospace is recommended. |