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

import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextPointer;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.sensor.error.AnalysisError;
import org.sonar.api.batch.sensor.issue.Issue;
import org.sonar.api.batch.sensor.issue.internal.DefaultNoSonarFilter;
import org.sonar.api.config.internal.MapSettings;
import org.sonarsource.slang.testing.AbstractSensorTest;

import static org.assertj.core.api.Assertions.assertThat;

// This is a direct test done against the plugin implementation, and cannot be deactivated like the SLang tests
class ScalaSensorTest extends AbstractSensorTest {

  @Test
  void test_fail_parsing() {
    InputFile inputFile = createInputFile("file1.scala", "#define invalid scala source code");
    context.fileSystem().add(inputFile);
    CheckFactory checkFactory = checkFactory("ParsingError");
    sensor(checkFactory).execute(context);
    Collection<AnalysisError> analysisErrors = context.allAnalysisErrors();
    assertThat(analysisErrors).hasSize(1);
    AnalysisError analysisError = analysisErrors.iterator().next();
    assertThat(analysisError.inputFile()).isEqualTo(inputFile);
    assertThat(analysisError.message()).isEqualTo("Unable to parse file: file1.scala");
    TextPointer textPointer = analysisError.location();
    assertThat(textPointer).isNotNull();
    assertThat(textPointer.line()).isEqualTo(1);
    assertThat(textPointer.lineOffset()).isZero();

    assertThat(logTester.logs()).contains(String.format("Unable to parse file: %s. Parse error at position 1:0", inputFile.uri()));
  }

// Purposefully disabling the check for the initial development of the Ballerina SonarQube Plugin
//  @Test
//  void test_one_rule() {
//    InputFile inputFile = createInputFile("file1.scala",
//      "class A { def main() = {\nprint (1 == 1);} }");
//    context.fileSystem().add(inputFile);
//    CheckFactory checkFactory = checkFactory("S1764");
//    sensor(checkFactory).execute(context);
//    Collection<Issue> issues = context.allIssues();
//    assertThat(issues).hasSize(1);
//    Issue issue = issues.iterator().next();
//    assertThat(issue.ruleKey().rule()).isEqualTo("S1764");
//    IssueLocation location = issue.primaryLocation();
//    assertThat(location.inputComponent()).isEqualTo(inputFile);
//    assertThat(location.message()).isEqualTo("Correct one of the identical sub-expressions on both sides this operator");
//    assertTextRange(location.textRange()).hasRange(2, 12, 2, 13);
//  }

  @Test
  void test_issue_suppression() {
    InputFile inputFile = createInputFile("file1.scala",
      "class A { " +
        "@SuppressWarnings(\"scala:S1764\")\n" +
        "def m1() = {\nprint (1 == 1);}\n" +
        "@SuppressWarnings(value = {\"scala:S1764\"})\n" +
        "def m2() = {\nprint (1 == 1);}\n" +
        "}");
    context.fileSystem().add(inputFile);
    CheckFactory checkFactory = checkFactory("S1764");
    sensor(checkFactory).execute(context);
    Collection<Issue> issues = context.allIssues();
    assertThat(issues).isEmpty();
  }

// Purposefully disabling the check for the initial development of the Ballerina SonarQube Plugin
//  @Test
//  void test_issue_not_suppressed() {
//    InputFile inputFile = createInputFile("file1.scala",
//      "class A { " +
//        "@SuppressWarnings(\"kotlin:S1764\")\n" +
//        "def m1() = {\nprint (1 == 1);}\n" +
//        "@SuppressWarnings(value = {\"S1764\"})\n" +
//        "def m2() = {\nprint (1 == 1);}\n" +
//        "}");
//    context.fileSystem().add(inputFile);
//    CheckFactory checkFactory = checkFactory("S1764");
//    sensor(checkFactory).execute(context);
//    Collection<Issue> issues = context.allIssues();
//    assertThat(issues).hasSize(2);
//  }

  @Override
  protected String repositoryKey() {
    return BallerinaPlugin.BALLERINA_REPOSITORY_KEY;
  }

  @Override
  protected BallerinaLanguage language() {
    return new BallerinaLanguage(new MapSettings().asConfig());
  }

  private BallerinaSensor sensor(CheckFactory checkFactory) {
    return new BallerinaSensor(SQ_LTS_RUNTIME, checkFactory, fileLinesContextFactory, new DefaultNoSonarFilter(), language());
  }

}
