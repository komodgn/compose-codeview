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

data class HighlightToken(
    val range: IntRange,
    val type: TokenType,
)

enum class TokenType { KEYWORD, STRING, COMMENT, TYPE, FUNCTION, PLAIN }

object SyntaxParser {
    fun parse(code: String, definition: LanguageDefinition): List<HighlightToken> {
        val tokens = mutableListOf<HighlightToken>()

        val multiLineCommentRegex = Regex("""/\*[\s\S]*?\*/""")
        val singleLineCommentRegex = Regex("""//.*""")

        multiLineCommentRegex.findAll(code).forEach {
            tokens.add(HighlightToken(it.range, TokenType.COMMENT))
        }

        singleLineCommentRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.contains(match.range.first) }) {
                tokens.add(HighlightToken(match.range, TokenType.COMMENT))
            }
        }

        val stringRegex = Regex(""""([^"\\]|\\.)*"""")
        stringRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.contains(match.range.first) }) {
                tokens.add(HighlightToken(match.range, TokenType.STRING))
            }
        }

        val typeRegex = Regex("""\b[A-Z]\w*\b""")
        typeRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.contains(match.range.first) }) {
                tokens.add(HighlightToken(match.range, TokenType.TYPE))
            }
        }

        val functionRegex = Regex("""\b\w+(?=\s*\()""")
        functionRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.contains(match.range.first) }) {
                tokens.add(HighlightToken(match.range, TokenType.FUNCTION))
            }
        }

        val wordRegex = Regex("""\b(\w+)\b""")
        wordRegex.findAll(code).forEach { match ->
            if (tokens.none { it.range.contains(match.range.first) }) {
                if (match.value in definition.keywords) {
                    tokens.add(HighlightToken(match.range, TokenType.KEYWORD))
                }
            }
        }

        return tokens.sortedBy { it.range.first }
    }
}
