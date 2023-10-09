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

import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

public class BallerinaPlugin implements Plugin {
  public static final String BALLERINA_LANGUAGE_KEY = "ballerina";
  static final String BALLERINA_LANGUAGE_NAME = "Ballerina";

  // Variable that defines file predicates
  static final String BALLERINA_FILE_SUFFIXES_DEFAULT_VALUE = ".bal";

  static final String BALLERINA_FILE_SUFFIXES_KEY = "sonar.ballerina.file.suffixes";
  static final String BALLERINA_REPOSITORY_KEY = "ballerina";
  static final String REPOSITORY_NAME = "SonarAnalyzer";
  static final String PROFILE_NAME = "Sonar way";
  private static final String GENERAL = "General";
  private static final String BALLERINA_CATEGORY = "Ballerina";

  @Override
  public void define(Context context) {
    context.addExtensions(
      // Class required for introducing the language to sonarqube
      BallerinaLanguage.class,
      // Class required for introducing the file parser of the language to sonarqube
      BallerinaSensor.class,
      // Class required for introducing language specific rules to sonarqube (HTML and JSON files)
      BallerinaRulesDefinition.class,
      // Class required for introducing language specific rules to sonarqube (Sonar_way_profile.json)
      BallerinaProfileDefinition.class,
        PropertyDefinition.builder(BALLERINA_FILE_SUFFIXES_KEY)
          .defaultValue(BALLERINA_FILE_SUFFIXES_DEFAULT_VALUE)
          .name("File Suffixes")
          .description("List of suffixes for files to analyze.")
          .subCategory(GENERAL)
          .category(BALLERINA_CATEGORY)
          .multiValues(true)
          .onQualifiers(Qualifiers.PROJECT)
          .build()
    );
  }
}
