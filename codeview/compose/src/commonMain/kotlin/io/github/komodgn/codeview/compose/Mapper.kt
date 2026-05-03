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

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import io.github.komodgn.codeview.core.HighlightToken
import io.github.komodgn.codeview.core.TokenType

@Composable
fun List<HighlightToken>.toAnnotatedString(code: String): AnnotatedString = buildAnnotatedString {
    append(code)
    this@toAnnotatedString.forEach { token ->
        val color = when (token.type) {
            TokenType.KEYWORD -> SpanStyle(
                color = Color(0xFFFF7B72),
                fontWeight = FontWeight.Bold,
            )

            TokenType.TYPE -> SpanStyle(color = Color(0xFFFFA657))

            TokenType.FUNCTION -> SpanStyle(color = Color(0xFFD2A8FF))

            TokenType.STRING -> SpanStyle(color = Color(0xFFA5D6FF))

            TokenType.COMMENT -> SpanStyle(color = Color(0xFF8B949E))

            TokenType.PLAIN -> SpanStyle(color = Color(0xFFC9D1D9))
        }

        if (token.range.first >= 0 && token.range.last < code.length) {
            addStyle(color, token.range.first, token.range.last + 1)
        }
    }
}
