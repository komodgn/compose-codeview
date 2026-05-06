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
package io.github.komodgn.example.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import compose_codeview.example.generated.resources.Res
import compose_codeview.example.generated.resources.*
import org.jetbrains.compose.resources.Font

@Composable
fun CodeViewTheme(content: @Composable () -> Unit) {
    val codeFont = FontFamily(
        Font(Res.font.NotoSansKR_Regular),
        Font(Res.font.NotoColorEmoji),
    )

    val typography = Typography(
        bodyMedium = TextStyle(
            fontFamily = codeFont,
        ),
        labelLarge = TextStyle(
            fontFamily = codeFont,
        ),
    )

    CompositionLocalProvider(LocalAppFontFamily provides codeFont) {
        MaterialTheme(
            typography = typography,
            content = content,
        )
    }
}
