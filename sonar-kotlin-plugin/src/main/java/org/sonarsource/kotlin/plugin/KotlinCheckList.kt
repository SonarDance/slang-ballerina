/*
 * SonarSource SLang
 * Copyright (C) 2018-2021 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonarsource.kotlin.plugin

import org.sonarsource.kotlin.api.AbstractCheck
import org.sonarsource.kotlin.checks.ServerCertificateCheck
import org.sonarsource.kotlin.checks.TooManyParametersKotlinCheck
import org.sonarsource.kotlin.checks.UnusedPrivateMethodKotlinCheck
import org.sonarsource.kotlin.checks.WeakSSLContextCheck
import org.sonarsource.slang.checks.CheckList
import org.sonarsource.slang.checks.MatchWithoutElseCheck
import org.sonarsource.slang.checks.OctalValuesCheck
import org.sonarsource.slang.checks.TooManyParametersCheck
import org.sonarsource.slang.checks.UnusedPrivateMethodCheck

object KotlinCheckList {
    val SLANG_EXCLUDED_CHECKS =
        arrayOf<Class<*>>(
            // FP rate too high for now in Kotlin on 'when' statements due to enum/sealed class that have all branches covered
            MatchWithoutElseCheck::class.java,  // Rule does not apply here as octal values do not exist in Kotlin
            OctalValuesCheck::class.java,  // Language specific implementation is provided.
            UnusedPrivateMethodCheck::class.java,
            TooManyParametersCheck::class.java,
        )

    val SLANG_CHECKS = listOf(
        UnusedPrivateMethodKotlinCheck::class.java,
        TooManyParametersKotlinCheck::class.java,
    )

    private val KOTLIN_CHECKS = listOf<Class<out AbstractCheck>>(
        WeakSSLContextCheck::class.java,
        ServerCertificateCheck::class.java,
    )

    @Deprecated("Use Kotlin-native checks instead")
    fun legacyChecks() = CheckList.excludeChecks(SLANG_EXCLUDED_CHECKS) + SLANG_CHECKS

    fun checks() = KOTLIN_CHECKS
}