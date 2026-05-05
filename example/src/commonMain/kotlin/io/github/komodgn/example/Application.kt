/*
 * Copyright (C) 2026 komodgn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.komodgn.example

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.komodgn.AppConfig
import io.github.komodgn.codeview.compose.CodeView
import io.github.komodgn.codeview.core.CodeLanguage

@Composable
fun App() {
    var userInput by remember { mutableStateOf(getInitialCode(AppConfig.LIBRARY_VERSION)) }
    val scrollState = rememberScrollState()

    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
        ) { innerPadding ->
            BoxWithConstraints(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .statusBarsPadding()
                    .displayCutoutPadding()
                    .navigationBarsPadding()
                    .imePadding(),
            ) {
                val isCompact = maxWidth < 600.dp

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    if (isCompact) {
                        CodeViewSection(userInput, modifier = Modifier.fillMaxWidth())
                        EditorSection(userInput, onValueChange = { userInput = it }, modifier = Modifier.fillMaxWidth())
                    } else {
                        Row(modifier = Modifier.fillMaxWidth().padding(16.dp, 0.dp, 16.dp, 0.dp)) {
                            EditorSection(userInput, onValueChange = { userInput = it }, modifier = Modifier.weight(1f))
                            CodeViewSection(userInput, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EditorSection(code: String, onValueChange: (String) -> Unit, modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text("Edit Code", style = MaterialTheme.typography.labelLarge)
        TextField(
            value = code,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Code") },
            textStyle = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Composable
fun CodeViewSection(code: String, modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text("Preview", style = MaterialTheme.typography.labelLarge)
        CodeView(
            code = code,
            language = CodeLanguage.KOTLIN,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private fun getInitialCode(version: String) = """
package io.github.komodgn.example

@Composable
fun CodeDisplay() {
    val greeting = "Hello, CodeView! 🎉v$version"

    CodeView(
        code = greeting,
        language = CodeLanguage.KOTLIN,
    )
}
""".trimIndent()
