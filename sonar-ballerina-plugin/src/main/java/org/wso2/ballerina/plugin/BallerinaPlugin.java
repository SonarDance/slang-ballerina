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
import org.sonar.api.SonarProduct;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

public class BallerinaPlugin implements Plugin {
  public static final String BALLERINA_LANGUAGE_KEY = "ballerina";
  static final String BALLERINA_LANGUAGE_NAME = "Ballerina";

  // Variable that defines file predicates
  static final String BALLERINA_FILE_SUFFIXES_DEFAULT_VALUE = ".bal";
  static final String BALLERINA_FILE_SUFFIXES_KEY = "sonar.ballerina.file.suffixes";

  // We do not require coverage report paths for the initial MVP
//  static final String COVERAGE_REPORT_PATHS_KEY = "sonar.scala.coverage.reportPaths";

  static final String BALLERINA_REPOSITORY_KEY = "ballerina";
  static final String REPOSITORY_NAME = "SonarAnalyzer";
  static final String PROFILE_NAME = "Sonar way";

  private static final String GENERAL = "General";
  private static final String BALLERINA_CATEGORY = "Ballerina";

  // We do not require test coverage for the initial MVP
//  private static final String TEST_COVERAGE_SUBCATEGORY = "Test and Coverage";

  // We do not require External analyzers to be produced and fed to SonarQube in the initial MVP
//  private static final String EXTERNAL_ANALYZERS_CATEGORY = "External Analyzers";

  @Override
  public void define(Context context) {

    context.addExtensions(
      // Class required for introducing the language to sonarqube
      BallerinaLanguage.class,
      // Class required for introducing the file parser of the language to sonarqube
      BallerinaSensor.class,
      // Class required for introducing language specific rules to sonarqube (HTML and JSON files)
      BallerinaRulesDefinition.class);

    if (context.getRuntime().getProduct() != SonarProduct.SONARLINT) {

      context.addExtensions(
        // Class required for introducing language specific rules to sonarqube (Sonar_way_profile.json)
        BallerinaProfileDefinition.class,

        // We do not require these additional sensors for the initial MVP
//        ScoverageSensor.class,
//        ScalastyleSensor.class,
//        ScapegoatSensor.class,
//        ScalastyleRulesDefinition.class,
//        ScapegoatRulesDefinition.class,

        // Property required for analysing file predicates of a project
        PropertyDefinition.builder(BALLERINA_FILE_SUFFIXES_KEY)
          .defaultValue(BALLERINA_FILE_SUFFIXES_DEFAULT_VALUE)
          .name("File Suffixes")
          .description("List of suffixes for files to analyze.")
          .subCategory(GENERAL)
          .category(BALLERINA_CATEGORY)
          .multiValues(true)
          .onQualifiers(Qualifiers.PROJECT)
          .build()

        // We do not require Coverage reports for the initial MVP
//        PropertyDefinition.builder(COVERAGE_REPORT_PATHS_KEY)
//          .name("Path to Scoverage report")
//          .description("Path to Scoverage report file(s) (scoverage.xml). Usually in target\\scala-X.X\\scoverage-report")
//          .category(BALLERINA_CATEGORY)
//          .subCategory(TEST_COVERAGE_SUBCATEGORY)
//          .onQualifiers(Qualifiers.PROJECT)
//          .multiValues(true)
//          .build(),

          // Not required for the initial MVP
//          PropertyDefinition.builder(ScalastyleSensor.REPORT_PROPERTY_KEY)
//            .name("Scalastyle Report Files")
//            .description("Paths (absolute or relative) to scalastyle xml files with Scalastyle issues.")
//            .category(EXTERNAL_ANALYZERS_CATEGORY)
//            .subCategory(BALLERINA_CATEGORY)
//            .onQualifiers(Qualifiers.PROJECT)
//            .multiValues(true)
//            .build(),

          // Not required for the initial MVP
//          PropertyDefinition.builder(ScapegoatSensor.REPORT_PROPERTY_KEY)
//            .name("Scapegoat Report Files")
//            .description("Paths (absolute or relative) to scapegoat xml files using scalastyle format. For example: scapegoat-scalastyle.xml")
//            .category(EXTERNAL_ANALYZERS_CATEGORY)
//            .subCategory(BALLERINA_CATEGORY)
//            .onQualifiers(Qualifiers.PROJECT)
//            .multiValues(true)
//            .build()
          );
      }
  }
}
