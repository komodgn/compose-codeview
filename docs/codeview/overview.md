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

| PARAMETER    | TYPE           | DESCRIPTION                                                                      | DEFAULT    |
|--------------|----------------|----------------------------------------------------------------------------------|------------|
| `code`       | `String`       | The source code string to be highlighted.                                        | (Required) |
| `language`   | `CodeLanguage` | The programming language to use for syntax analysis (e.g., `KOTLIN`, `JAVA`).    | (Required) |
| `modifier`   | `Modifier`     | The modifier to be applied to the CodeView container.                            | `Modifier` |
| `fontFamily` | `FontFamily?`  | The font family for the code text. If null, it defaults to FontFamily.Monospace. | `null`     |