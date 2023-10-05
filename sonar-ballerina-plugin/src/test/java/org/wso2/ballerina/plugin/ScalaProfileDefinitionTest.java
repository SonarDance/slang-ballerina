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
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition.BuiltInActiveRule;

import static org.assertj.core.api.Assertions.assertThat;

// This is a direct test done against the plugin implementation, and cannot be deactivated like the SLang tests
class ScalaProfileDefinitionTest {

  @Test
  void profile() {
    BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
    new BallerinaProfileDefinition().define(context);
    BuiltInQualityProfilesDefinition.BuiltInQualityProfile profile = context.profile("ballerina", "Sonar way");

    assertThat(profile.rules()).extracting("repoKey").containsOnly("ballerina");
    assertThat(profile.rules()).isNotEmpty();
    assertThat(profile.rules()).extracting(BuiltInActiveRule::ruleKey).contains("ParsingError");
  }

}