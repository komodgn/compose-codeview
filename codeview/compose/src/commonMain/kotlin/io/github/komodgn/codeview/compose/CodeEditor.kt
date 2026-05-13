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
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.komodgn.codeview.core.CodeLanguage
import io.github.komodgn.codeview.core.SyntaxParser
import io.github.komodgn.codeview.core.extension.toDefinition

@Composable
fun CodeEditor(
    value: String,
    onValueChange: (String) -> Unit,
    language: CodeLanguage,
    showLineNumbers: Boolean = true,
    modifier: Modifier = Modifier,
    fontFamily: FontFamily? = null,
) {
    val lineCount = remember(value) { value.lines().size }

    val commonTextStyle = TextStyle(
        fontFamily = fontFamily ?: FontFamily.Monospace,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

    val syntaxTransformation = remember(language) {
        VisualTransformation { text ->
            val tokens = SyntaxParser.parse(text.text, language.toDefinition())
            val annotatedString = tokens.toAnnotatedString(text.text)
            TransformedText(annotatedString, OffsetMapping.Identity)
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFF0D1117), RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                color = Color(0xFF30363D),
                shape = RoundedCornerShape(8.dp),
            )
            .padding(16.dp),
    ) {
        if (showLineNumbers) {
            Column(
                modifier = Modifier.padding(end = 12.dp),
                horizontalAlignment = Alignment.End,
            ) {
                for (i in 1..lineCount) {
                    Text(
                        text = i.toString(),
                        color = Color(0xFF8B949E),
                        fontSize = 14.sp,
                        fontFamily = fontFamily ?: FontFamily.Monospace,
                        lineHeight = 20.sp,
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .horizontalScroll(rememberScrollState()),
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .defaultMinSize(100.dp),
                textStyle = commonTextStyle.copy(color = Color.White),
                cursorBrush = SolidColor(Color.White),
                visualTransformation = syntaxTransformation,
                decorationBox = { innerTextField ->
                    innerTextField()
                },
            )
        }
    }
}
