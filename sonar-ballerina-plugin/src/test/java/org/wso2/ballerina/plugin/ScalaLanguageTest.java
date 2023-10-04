/*
 * SonarSource SLang
 * Copyright (C) 2018-2023 SonarSource SA
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
package org.wso2.ballerina.plugin;

import org.junit.jupiter.api.Test;
import org.sonar.api.config.internal.MapSettings;

import static org.assertj.core.api.Assertions.assertThat;

class ScalaLanguageTest {

  @Test
  void test_suffixes_default() {
    BallerinaLanguage scalaLanguage = new BallerinaLanguage(new MapSettings().asConfig());
    assertThat(scalaLanguage.getFileSuffixes()).containsExactly(".bal");
  }

  @Test
  void test_suffixes_empty() {
    BallerinaLanguage scalaLanguage = new BallerinaLanguage(new MapSettings().setProperty(BallerinaPlugin.BALLERINA_FILE_SUFFIXES_KEY, "").asConfig());
    assertThat(scalaLanguage.getFileSuffixes()).containsExactly(".bal");
  }

  @Test
  void test_suffixes_custom() {
    BallerinaLanguage scalaLanguage = new BallerinaLanguage(new MapSettings().setProperty(BallerinaPlugin.BALLERINA_FILE_SUFFIXES_KEY, ".custom").asConfig());
    assertThat(scalaLanguage.getFileSuffixes()).containsExactly(".custom");
  }

}
