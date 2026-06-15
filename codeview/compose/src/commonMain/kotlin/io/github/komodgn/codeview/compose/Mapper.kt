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

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import io.github.komodgn.codeview.compose.theme.Gray
import io.github.komodgn.codeview.compose.theme.LightOrange
import io.github.komodgn.codeview.compose.theme.Orange
import io.github.komodgn.codeview.compose.theme.Red
import io.github.komodgn.codeview.compose.theme.SkyBlue
import io.github.komodgn.codeview.compose.theme.White
import io.github.komodgn.codeview.compose.theme.Yellow
import io.github.komodgn.codeview.core.HighlightToken
import io.github.komodgn.codeview.core.TokenType

fun List<HighlightToken>.toAnnotatedString(code: String): AnnotatedString = buildAnnotatedString {
    append(code)
    this@toAnnotatedString.forEach { token ->
        val color = when (token.type) {
            TokenType.KEYWORD -> SpanStyle(
                color = Red,
                fontWeight = FontWeight.Bold,
            )

            TokenType.ANNOTATION -> SpanStyle(color = Yellow)

            TokenType.TYPE -> SpanStyle(color = LightOrange)

            TokenType.FUNCTION -> SpanStyle(color = Orange)

            TokenType.STRING -> SpanStyle(color = SkyBlue)

            TokenType.COMMENT -> SpanStyle(color = Gray)

            TokenType.PLAIN -> SpanStyle(color = White)
        }

        if (token.range.first >= 0 && token.range.last < code.length) {
            addStyle(color, token.range.first, token.range.last + 1)
        }
    }
}
