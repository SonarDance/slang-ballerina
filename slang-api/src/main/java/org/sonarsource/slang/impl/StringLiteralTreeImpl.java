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
package org.sonarsource.slang.impl;

import org.sonarsource.slang.api.StringLiteralTree;
import org.sonarsource.slang.api.TreeMetaData;

public class StringLiteralTreeImpl extends LiteralTreeImpl implements StringLiteralTree {

  private final String content;

  public StringLiteralTreeImpl(TreeMetaData metaData, String value) {
    super(metaData, value);
    if (value.length() < 2 || value.charAt(0) != '"' || value.charAt(value.length() - 1) != '"') {
      throw new IllegalArgumentException("Invalid string format: expected \"XXX\"");
    }
    content = value().substring(1, value().length() - 1);
  }

  public StringLiteralTreeImpl(TreeMetaData metaData, String value, String content) {
    super(metaData, value);
    this.content = content;
  }

  @Override
  public String content() {
    return content;
  }

}
