/*
 *  Weblounge: Web Content Management System
 *  Copyright (c) 2010 The Weblounge Team
 *  http://weblounge.o2it.ch
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software Foundation
 *  Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package ch.o2it.weblounge.taglib.content;

import ch.o2it.weblounge.common.site.Action;

/**
 * The tag body of this tag is executed if an action handler (the one that is
 * specified) is currently present.
 */
public class IfActionTag extends ActionCheckTag {

  /** Serial vesion uid */
  private static final long serialVersionUID = 9001981722859780524L;

  /**
   * Returns <code>true</code> if an action is currently being executed.
   * 
   * @see ch.o2it.weblounge.taglib.content.ActionCheckTag#skip(Action)
   */
  protected boolean skip(Action handler) {
    return handler == null || !matches(handler);
  }

}
