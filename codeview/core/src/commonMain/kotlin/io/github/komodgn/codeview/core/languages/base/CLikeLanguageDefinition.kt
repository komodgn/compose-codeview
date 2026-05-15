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
package io.github.komodgn.codeview.core.languages.base

import io.github.komodgn.codeview.core.TokenType

abstract class CLikeLanguageDefinition : LanguageDefinition {
    protected val commonKeywords = setOf(
        "if", "else", "for", "while", "do", "switch", "case", "default", "break", "continue", "return",
        "try", "catch", "finally", "throw",
        "class", "interface", "package", "import", "public", "private", "protected", "static", "final", "abstract", "sealed",
        "this", "super", "true", "false", "null", "new", "instanceof", "is", "as", "in",
    )

    protected val commonAnnotationRegex = Regex("""@\w+(?:\.\w+)*""")

    override fun getCustomRules(): Map<TokenType, Regex> = mapOf(
        TokenType.COMMENT to Regex("//.*|/\\*[\\s\\S]*?\\*/"),
        TokenType.STRING to Regex("\".*?\""),
    )
}
