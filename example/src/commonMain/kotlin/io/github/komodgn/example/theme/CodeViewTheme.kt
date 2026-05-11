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

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import compose_codeview.example.generated.resources.NotoColorEmoji
import compose_codeview.example.generated.resources.NotoSansKR_Regular
import compose_codeview.example.generated.resources.Res
import io.github.komodgn.example.PlatformColors
import org.jetbrains.compose.resources.Font

private val DarkColors = darkColorScheme(
    primary = CodeAccent,
    onPrimary = Color.Black,
    background = CodeBackground,
    onBackground = CodeTextPrimary,
    surface = CodeSurface,
    onSurface = CodeTextPrimary,
    surfaceVariant = Grey700,
    error = ErrorRed,
    onError = Color.White,
)

private val LightColors = lightColorScheme(
    primary = CodeAccentRed,
    onPrimary = Color.White,
    background = CodeBackgroundLight,
    onBackground = CodeTextPrimaryLight,
    surface = CodeSurfaceLight,
    onSurface = CodeTextPrimaryLight,
    surfaceVariant = LightGrey200,
    error = LightError,
    onError = Color.White,
)

@Composable
fun CodeViewTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (isDarkTheme) DarkColors else LightColors

    PlatformColors(isDarkTheme)

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
            colorScheme = colors,
            content = content,
        )
    }
}
