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
package io.github.komodgn.codeview.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.komodgn.codeview.core.CodeLanguage
import io.github.komodgn.codeview.core.SyntaxParser
import io.github.komodgn.codeview.core.extension.toDefinition

@Composable
fun CodeView(
    code: String,
    language: CodeLanguage,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily? = null,
) {
    val tokens = SyntaxParser.parse(code, language.toDefinition())

    val annotatedString = tokens.toAnnotatedString(code)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0D1117), RoundedCornerShape(8.dp))
            .padding(12.dp),
    ) {
        Text(
            text = language.name.lowercase(),
            color = Color.LightGray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 8.dp),
        )

        Box(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            Text(
                text = annotatedString,
                color = Color.White,
                fontSize = 14.sp,
                fontFamily = fontFamily ?: FontFamily.Monospace,
                lineHeight = 20.sp,
            )
        }
    }
}
