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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.komodgn.AppConfig
import io.github.komodgn.codeview.core.CodeLanguage
import io.github.komodgn.example.component.DemoTopBar
import io.github.komodgn.example.section.CodeEditorSection
import io.github.komodgn.example.section.CodeViewSection
import io.github.komodgn.example.section.EditorSection
import io.github.komodgn.example.theme.CodeViewTheme
import io.github.komodgn.example.util.getInitialCode

@Composable
fun App() {
    var isDark by rememberSaveable { mutableStateOf(true) }
    var selectedComponent by rememberSaveable { mutableStateOf(DemoComponent.CODE_VIEW) }
    var currentLang by remember { mutableStateOf(CodeLanguage.KOTLIN) }
    var userInput by remember { mutableStateOf(getInitialCode(currentLang, AppConfig.LIBRARY_VERSION)) }

    CodeViewTheme(isDarkTheme = isDark) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {
                DemoTopBar(
                    isDark = isDark,
                    onToggleTheme = { isDark = !isDark },
                    selectedComponent = selectedComponent,
                    onComponentSelect = { selectedComponent = it },
                )
            },
        ) { innerPadding ->
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(MaterialTheme.colorScheme.background)
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                    .imePadding(),
            ) {
                MainContent(
                    isCompact = maxWidth < 600.dp,
                    selectedDemoComponent = selectedComponent,
                    code = userInput,
                    language = currentLang,
                    onCodeChange = { userInput = it },
                    onLanguageChange = { newLang ->
                        currentLang = newLang
                        userInput = getInitialCode(newLang, AppConfig.LIBRARY_VERSION)
                    },
                )
            }
        }
    }
}

@Composable
private fun MainContent(
    isCompact: Boolean,
    selectedDemoComponent: DemoComponent,
    code: String,
    language: CodeLanguage,
    onCodeChange: (String) -> Unit,
    onLanguageChange: (CodeLanguage) -> Unit,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        when (selectedDemoComponent) {
            DemoComponent.CODE_VIEW -> {
                if (isCompact) {
                    CodeViewSection(code, language, Modifier.fillMaxWidth())
                    EditorSection(code, language, onLanguageChange, onCodeChange, Modifier.fillMaxWidth())
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        EditorSection(code, language, onLanguageChange, onCodeChange, Modifier.weight(1f))
                        CodeViewSection(code, language, Modifier.weight(1f))
                    }
                }
            }

            DemoComponent.CODE_EDITOR -> {
                CodeEditorSection(
                    code,
                    language,
                    onCodeChange,
                    selectedLanguage = language,
                    onLanguageChange = onLanguageChange,
                )
            }
        }
    }
}
