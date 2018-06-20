/*
 * SonarSource SLang
 * Copyright (C) 2009-2018 SonarSource SA
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
package com.sonarsource.slang.checks;

import org.junit.Test;

public class MatchCaseTooBigCheckTest {

  private MatchCaseTooBigCheck check = new MatchCaseTooBigCheck();

  @Test
  public void max_5() {
    check.max = 5;
    Verifier.verify("MatchCaseTooBig_5.slang", check);
  }

  @Test
  public void max_3() {
    check.max = 3;
    Verifier.verify("MatchCaseTooBig_3.slang", check);
  }
}
