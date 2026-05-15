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
package io.github.komodgn.codeview.core.languages

import io.github.komodgn.codeview.core.TokenType
import io.github.komodgn.codeview.core.languages.base.ScriptLanguageDefinition

object PythonDefinition : ScriptLanguageDefinition() {
    override val name = "python"

    override val keywords = commonScriptKeywords + setOf(
        "def", "class", "elif", "try", "except", "finally", "raise",
        "with", "as", "pass", "import", "from", "lambda", "assert",
        "is", "not", "and", "or", "global", "nonlocal", "del",
    )

    override fun getCustomRules(): Map<TokenType, Regex> {
        return super.getCustomRules() + mapOf(
            TokenType.ANNOTATION to Regex("""@\w+(?:\.\w+)*"""),
        )
    }
}
