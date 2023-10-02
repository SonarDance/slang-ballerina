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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// SLang Checks
import org.sonarsource.slang.checks.BadFunctionNameCheck;
import org.sonarsource.slang.checks.BooleanInversionCheck;
import org.sonarsource.slang.checks.BooleanLiteralCheck;
import org.sonarsource.slang.checks.BadClassNameCheck;
import org.sonarsource.slang.checks.AllBranchesIdenticalCheck;
import org.sonarsource.slang.checks.CheckList;
import org.sonarsource.slang.checks.CodeAfterJumpCheck;
import org.sonarsource.slang.checks.CollapsibleIfStatementsCheck;
import org.sonarsource.slang.checks.CommentedCodeCheck;
import org.sonarsource.slang.checks.DuplicateBranchCheck;
import org.sonarsource.slang.checks.DuplicatedFunctionImplementationCheck;
import org.sonarsource.slang.checks.ElseIfWithoutElseCheck;
import org.sonarsource.slang.checks.EmptyBlockCheck;
import org.sonarsource.slang.checks.EmptyCommentCheck;
import org.sonarsource.slang.checks.EmptyFunctionCheck;
import org.sonarsource.slang.checks.FileHeaderCheck;
import org.sonarsource.slang.checks.FixMeCommentCheck;
import org.sonarsource.slang.checks.FunctionCognitiveComplexityCheck;
import org.sonarsource.slang.checks.HardcodedCredentialsCheck;
import org.sonarsource.slang.checks.HardcodedIpCheck;
import org.sonarsource.slang.checks.IdenticalBinaryOperandCheck;
import org.sonarsource.slang.checks.IdenticalConditionsCheck;
import org.sonarsource.slang.checks.IfConditionalAlwaysTrueOrFalseCheck;
import org.sonarsource.slang.checks.MatchCaseTooBigCheck;
import org.sonarsource.slang.checks.MatchWithoutElseCheck;
import org.sonarsource.slang.checks.NestedMatchCheck;
import org.sonarsource.slang.checks.OctalValuesCheck;
import org.sonarsource.slang.checks.OneStatementPerLineCheck;
import org.sonarsource.slang.checks.ParsingErrorCheck;
import org.sonarsource.slang.checks.RedundantParenthesesCheck;
import org.sonarsource.slang.checks.SelfAssignmentCheck;
import org.sonarsource.slang.checks.StringLiteralDuplicatedCheck;
import org.sonarsource.slang.checks.TabsCheck;
import org.sonarsource.slang.checks.TodoCommentCheck;
import org.sonarsource.slang.checks.TooComplexExpressionCheck;
import org.sonarsource.slang.checks.TooDeeplyNestedStatementsCheck;
import org.sonarsource.slang.checks.TooLongFunctionCheck;
import org.sonarsource.slang.checks.TooLongLineCheck;
import org.sonarsource.slang.checks.TooManyCasesCheck;
import org.sonarsource.slang.checks.TooManyLinesOfCodeFileCheck;
import org.sonarsource.slang.checks.TooManyParametersCheck;
import org.sonarsource.slang.checks.UnusedFunctionParameterCheck;
import org.sonarsource.slang.checks.UnusedLocalVariableCheck;
import org.sonarsource.slang.checks.UnusedPrivateMethodCheck;
import org.sonarsource.slang.checks.VariableAndParameterNameCheck;
import org.sonarsource.slang.checks.WrongAssignmentOperatorCheck;

// Ballerina Custom checks
import org.wso2.ballerina.checks.AllBranchesIdenticalScalaCheck;
import org.wso2.ballerina.checks.DuplicateBranchScalaCheck;
import org.wso2.ballerina.checks.UnusedPrivateMethodScalaCheck;

public final class ScalaCheckList {

  private ScalaCheckList() {
    // utility class
  }

  // Add to here all the default rules sonar source SLang provides which should be disabled
  static final Class[] SCALA_CHECK_BLACK_LIST = {
    // Intentionally disabling all default checks for starting out with the Ballerina Plugin
    MatchWithoutElseCheck.class,
    OctalValuesCheck.class,
    RedundantParenthesesCheck.class,
    WrongAssignmentOperatorCheck.class,
    BadClassNameCheck.class,
    BadFunctionNameCheck.class,
    BooleanInversionCheck.class,
    BooleanLiteralCheck.class,
    CheckList.class,
    CodeAfterJumpCheck.class,
    CollapsibleIfStatementsCheck.class,
    CommentedCodeCheck.class,
    DuplicatedFunctionImplementationCheck.class,
    ElseIfWithoutElseCheck.class,
    EmptyBlockCheck.class,
    EmptyCommentCheck.class,
    EmptyFunctionCheck.class,
    FileHeaderCheck.class,
    FixMeCommentCheck.class,
    FunctionCognitiveComplexityCheck.class,
    HardcodedCredentialsCheck.class,
    HardcodedIpCheck.class,
    IdenticalBinaryOperandCheck.class,
    IdenticalConditionsCheck.class,
    IfConditionalAlwaysTrueOrFalseCheck.class,
    MatchCaseTooBigCheck.class,
    MatchWithoutElseCheck.class,
    NestedMatchCheck.class,
    OctalValuesCheck.class,
    OneStatementPerLineCheck.class,
    RedundantParenthesesCheck.class,
    SelfAssignmentCheck.class,
    StringLiteralDuplicatedCheck.class,
    TabsCheck.class,
    TodoCommentCheck.class,
    TooComplexExpressionCheck.class,
    TooDeeplyNestedStatementsCheck.class,
    TooLongFunctionCheck.class,
    TooLongLineCheck.class,
    TooManyCasesCheck.class,
    TooManyLinesOfCodeFileCheck.class,
    TooManyParametersCheck.class,
    UnusedFunctionParameterCheck.class,
    UnusedLocalVariableCheck.class,
    VariableAndParameterNameCheck.class,
    WrongAssignmentOperatorCheck.class,

    // The Parsing error check is there in a main test so it should not be excluded
    // ParsingErrorCheck.class,

    // Language specific implementation is provided.
    UnusedPrivateMethodCheck.class,
    AllBranchesIdenticalCheck.class,
    DuplicateBranchCheck.class
  };

  // Add to here all the customized rules that are created by extending the sonar source SLang provided rules
  static final List<Class<?>> SCALA_LANGUAGE_SPECIFIC_CHECKS = Arrays.asList(
    UnusedPrivateMethodScalaCheck.class,
    AllBranchesIdenticalScalaCheck.class,
    DuplicateBranchScalaCheck.class);

  // Function which decides which rules to include and which rules to exclude
  public static List<Class<?>> checks() {
    List<Class<?>> list = new ArrayList<>(CheckList.excludeChecks(SCALA_CHECK_BLACK_LIST));
    list.addAll(SCALA_LANGUAGE_SPECIFIC_CHECKS);
    return list;
  }
}
