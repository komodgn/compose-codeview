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
package io.github.komodgn.example.component

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import io.github.komodgn.example.DemoComponent

@Suppress("FrequentlyChangingValue")
@Composable
fun DemoTopBar(
    scrollState: ScrollState,
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    selectedComponent: DemoComponent,
    onComponentSelect: (DemoComponent) -> Unit,
) {
    val scrollThreshold = 120f
    val collapseFraction = (scrollState.value / scrollThreshold).coerceIn(0f, 1f)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Top))
            .padding(bottom = lerp(8.dp, 0.dp, collapseFraction)),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(lerp(50.dp, 48.dp, collapseFraction))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Compose CodeView Demo",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .graphicsLayer {
                        alpha = 1f - collapseFraction
                        translationY = -20f * collapseFraction
                    },
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = if (selectedComponent == DemoComponent.CODE_VIEW) " CodeView" else "CodeEditor",
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .graphicsLayer {
                        alpha = collapseFraction
                        translationY = 20f * (1f - collapseFraction)
                    },
                style = MaterialTheme.typography.bodyMedium,
            )

            IconButton(
                onClick = onToggleTheme,
                modifier = Modifier.align(Alignment.CenterEnd),
            ) {
                Icon(
                    imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                    contentDescription = "Toggle Theme",
                    tint = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }

        val chipAreaHeight = lerp(48.dp, 0.dp, collapseFraction)

        if (collapseFraction < 0.9f) {
            DemoComponentFilterRow(
                selectedComponent = selectedComponent,
                onComponentSelect = onComponentSelect,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(chipAreaHeight)
                    .padding(horizontal = 16.dp)
                    .graphicsLayer {
                        alpha = (1f - collapseFraction * 2f).coerceIn(0f, 1f)
                        scaleY = 1f - collapseFraction
                        translationY = -10f * collapseFraction
                    },
            )
        }
    }
}
