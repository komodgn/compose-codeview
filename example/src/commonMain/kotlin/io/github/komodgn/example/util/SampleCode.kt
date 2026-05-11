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
package io.github.komodgn.example.util

import io.github.komodgn.codeview.core.CodeLanguage

/**
 * Provides the initial code snippet for the demo application.
 */
fun getInitialCode(language: CodeLanguage, version: String): String = when (language) {
    CodeLanguage.KOTLIN -> """
            package io.github.komodgn.example

            /**
             * Welcome to Compose CodeView v$version Demo!
             *
             * This library provides syntax highlighting for Compose Multiplatform.
             * Feel free to edit the code on the left to see real-time updates.
             */
            @Composable
            fun CodeDisplay() {
                val greeting = getWelcomeMessage()

                // You can apply custom fonts via the 'fontFamily' parameter.
                CodeView(
                    code = greeting,
                    language = CodeLanguage.KOTLIN,
                )
            }

            private fun getWelcomeMessage() = "Hello, CodeView!"
    """.trimIndent()

    CodeLanguage.JAVA -> """
            package io.github.komodgn.example;

            /**
             * Welcome to Compose CodeView v$version!
             */
            public class Main {
                public static void main(String[] args) {
                    String message = "Hello, Java!";
                    System.out.println(message);
                }
            }
    """.trimIndent()

    CodeLanguage.PYTHON -> """
            # Welcome to Compose CodeView v$version!

            def welcome_codeview():
                message = "Hello, Python!"
                print(message)

            if __name__ == "__main__":
                welcome_codeview()
    """.trimIndent()
}
