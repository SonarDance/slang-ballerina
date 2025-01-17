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

import org.sonarsource.slang.api.Comment;
import org.sonarsource.slang.api.TextRange;

public class CommentImpl implements Comment {

  private final String text;
  private final String contentText;
  private final TextRange range;
  private final TextRange contentRange;

  public CommentImpl(String text, String contentText, TextRange range, TextRange contentRange) {
    this.contentText = contentText;
    this.text = text;
    this.range = range;
    this.contentRange = contentRange;
  }

  @Override
  public String contentText() {
    return contentText;
  }

  @Override
  public String text() {
    return text;
  }

  @Override
  public TextRange textRange() {
    return range;
  }

  @Override
  public TextRange contentRange() {
    return contentRange;
  }

}
