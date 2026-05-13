# CodeEditor Overview

🧩 The easiest way to add code editing to your Compose app.

## CodeEditor

```kotlin
import io.github.komodgn.codeview.compose.CodeEditor
import io.github.komodgn.codeview.core.CodeLanguage

@Composable
fun CodeEditorExample() {
    var code by remember { mutableStateOf("// Write your code here") }

    CodeEditor(
        value = code,
        onValueChange = { code = it },
        language = CodeLanguage.KOTLIN,
    )
}
```

### Parameters

| PARAMETER         | TYPE               | DESCRIPTION                                                                      | DEFAULT    |
|-------------------|--------------------|----------------------------------------------------------------------------------|------------|
| `value`           | `String`           | The source code string to be highlighted.                                        | (Required) |
| `onValueChange`   | `(String) -> Unit` | Callback that is triggered when the input text changes.                          | (Required) |
| `language`        | `CodeLanguage`     | The programming language to use for syntax analysis (e.g., `KOTLIN`, `JAVA`).    | (Required) |
| `showLineNumbers` | `Boolean`          | Whether to display line numbers on the left side of the code.                    | `true`     |
| `modifier`        | `Modifier`         | The modifier to be applied to the CodeView container.                            | `Modifier` |
| `fontFamily`      | `FontFamily?`      | The font family for the code text. If null, it defaults to FontFamily.Monospace. | `null`     |