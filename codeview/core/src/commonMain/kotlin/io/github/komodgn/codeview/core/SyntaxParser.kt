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
package io.github.komodgn.codeview.core

import io.github.komodgn.codeview.core.languages.base.LanguageDefinition

object SyntaxParser {
    private val typeRegex = Regex("""\b[A-Z]\w*\b""")
    private val functionRegex = Regex("""\b(?!if|while|for|catch|switch|when)\w+(?=\s*\()""")
    private val wordRegex = Regex("""\b(\w+)\b""")

    /**
     * Parses the given [code] snippet based on the rules specified in [definition].
     *
     * @param code The raw source code string to be analyzed.
     * @param definition The language-specific syntax rules and keywords.
     * @return A sorted list of [HighlightToken]s used for rendering styled text.
     */
    fun parse(code: String, definition: LanguageDefinition): List<HighlightToken> {
        if (code.isEmpty()) return emptyList()

        val tokens = mutableListOf<HighlightToken>()

        // Use a tracking array to achieve O(1) overlap checking and prevent re-highlighting
        val visited = BooleanArray(code.length)

        /**
         * Attempts to add a [HighlightToken] if the given [range] does not overlap
         * with already processed/highlighted regions of the code.
         * Higher-priority rules take precedence and lock their ranges first.
         */
        fun tryAddToken(range: IntRange, type: TokenType) {
            if (range.first < 0 || range.last >= code.length) return

            // Check if any character inside the requested range has already been claimed
            var isOverlapped = false
            for (i in range.first..range.last) {
                if (visited[i]) {
                    isOverlapped = true
                    break
                }
            }

            // If the range is clear, register the token and mark the indices as visited
            if (!isOverlapped) {
                tokens.add(HighlightToken(range, type))
                for (i in range.first..range.last) {
                    visited[i] = true
                }
            }
        }

        // High-priority custom rules
        definition.getCustomRules().forEach { (type, regex) ->
            regex.findAll(code).forEach { match ->
                tryAddToken(match.range, type)
            }
        }

        functionRegex.findAll(code).forEach { match ->
            tryAddToken(match.range, TokenType.FUNCTION)
        }

        typeRegex.findAll(code).forEach { match ->
            tryAddToken(match.range, TokenType.TYPE)
        }

        wordRegex.findAll(code).forEach { match ->
            if (match.value in definition.keywords) {
                tryAddToken(match.range, TokenType.KEYWORD)
            }
        }

        return tokens.sortedBy { it.range.first }
    }
}
