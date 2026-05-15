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

import io.github.komodgn.codeview.core.CodeLanguage
import io.github.komodgn.codeview.core.SyntaxParser
import io.github.komodgn.codeview.core.extension.toDefinition
import kotlin.test.Test

class CodeViewPerformanceTest {

    @Test
    fun measureParsingSpeed() {
        val dummyCode = """
            package com.example.demo

            import androidx.compose.runtime.Composable
            import androidx.compose.ui.Modifier

            /**
             * This is a sample class for benchmark
             */
            class UserRepository(private val apiService: ApiService) {
                private val cache = mutableMapOf<String, User>()

                suspend fun getUser(id: String): Result<User> {
                    if (cache.containsKey(id)) {
                        return Result.success(cache[id]!!)
                    }
                    return try {
                        val user = apiService.fetchUser(id)
                        cache[id] = user
                        Result.success(user)
                    } catch (e: Exception) {
                        Result.failure(e)
                    }
                }
            }

            @Composable
            fun UserProfile(user: User, modifier: Modifier = Modifier) {
                val greeting = "Hello, ${'$'}{user.name}"
                println(greeting)
            }
        """.trimIndent().repeat(5)

        val definition = CodeLanguage.KOTLIN.toDefinition()

        repeat(20) {
            val tokens = SyntaxParser.parse(dummyCode, definition)
            tokens.toAnnotatedString(dummyCode)
        }

        val iterations = 100
        val startTime = System.nanoTime()

        repeat(iterations) {
            val tokens = SyntaxParser.parse(dummyCode, definition)
            tokens.toAnnotatedString(dummyCode)
        }

        val endTime = System.nanoTime()

        val totalDurationMs = (endTime - startTime) / 1_000_000.0
        val averageDurationMs = totalDurationMs / iterations

        println("⏱️ Total Duration for 100 iterations: $totalDurationMs ms")
        println("🚀 [Benchmark Summary] Mean parsing time per iteration: $averageDurationMs ms")
    }
}
