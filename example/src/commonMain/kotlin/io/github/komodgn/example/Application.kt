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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.komodgn.AppConfig
import io.github.komodgn.codeview.compose.CodeView
import io.github.komodgn.codeview.core.CodeLanguage

@Composable
fun App() {
    val scrollState = rememberScrollState()

    val demoCode = """
    package io.github.komodgn.example

    /**
     * CodeView enables seamless syntax highlighting within KMP projects.
     * It efficiently handles multi-line documentation and complex annotations.
     */
    @Composable
    fun CodeDisplay(version: String) {
        // You can integrate dynamic string interpolation effortlessly
        val greeting = "Hello, CodeView ${AppConfig.LIBRARY_VERSION} version"

        /*
           Block comment support:
           Developers can verify the accuracy of color rendering
           for improved code readability.
        */
        CodeView(
            code = greeting,
            language = CodeLanguage.KOTLIN,
        )
    }
    """.trimIndent()

    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                CodeView(
                    code = demoCode,
                    language = CodeLanguage.KOTLIN,
                )
            }
        }
    }
}
