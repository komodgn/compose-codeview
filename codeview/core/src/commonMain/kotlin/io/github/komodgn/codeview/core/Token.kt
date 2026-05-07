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

data class HighlightToken(
    val range: IntRange,
    val type: TokenType,
)

enum class TokenType { KEYWORD, STRING, COMMENT, TYPE, FUNCTION, PLAIN }

object SyntaxParser {
    fun parse(code: String, definition: LanguageDefinition): List<HighlightToken> {
        val tokens = mutableListOf<HighlightToken>()

        definition.getCustomRules().forEach { (type, regex) ->
            regex.findAll(code).forEach { match ->
                if (tokens.none { it.range.overlaps(match.range) }) {
                    tokens.add(HighlightToken(match.range, type))
                }
            }
        }

        val typeRegex = Regex("""\b[A-Z]\w*\b""")
        typeRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.overlaps(match.range) }) {
                tokens.add(HighlightToken(match.range, TokenType.TYPE))
            }
        }

        val functionRegex = Regex("""\b\w+(?=\s*\()""")
        functionRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.overlaps(match.range) }) {
                tokens.add(HighlightToken(match.range, TokenType.FUNCTION))
            }
        }

        val wordRegex = Regex("""\b(\w+)\b""")
        wordRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.overlaps(match.range) }) {
                if (match.value in definition.keywords) {
                    tokens.add(HighlightToken(match.range, TokenType.KEYWORD))
                }
            }
        }

        return tokens.sortedBy { it.range.first }
    }

    /**
     * Checks if this range overlaps with the [other] range.
     * @return true if there is at least one common element between the two ranges.
     */
    private fun IntRange.overlaps(other: IntRange): Boolean = this.first <= other.last && other.first <= this.last
}
