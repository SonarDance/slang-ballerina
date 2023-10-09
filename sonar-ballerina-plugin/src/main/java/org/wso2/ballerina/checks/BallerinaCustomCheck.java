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
package org.wso2.ballerina.checks;
import org.sonar.check.Rule;
import org.sonarsource.slang.api.MatchTree;
import org.sonarsource.slang.checks.api.InitContext;
import org.sonarsource.slang.checks.api.SlangCheck;

@Rule(key = "BallerinaCustomCheck")
public class BallerinaCustomCheck implements SlangCheck {
    @Override
    public void initialize(InitContext init) {
        // The MatchTree here is utilized as a placeholder to achieve a check
        // The -> represents an anonymous function call [i.e: () =>{} in JS]
        init.register(MatchTree.class, (ctx, tree) ->{
            ctx.reportFileIssue("This is a Ballerina Custom Check issue =)");
        });
    }
}
