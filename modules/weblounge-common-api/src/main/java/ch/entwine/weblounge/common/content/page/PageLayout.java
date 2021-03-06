/*
 *  Weblounge: Web Content Management System
 *  Copyright (c) 2003 - 2011 The Weblounge Team
 *  http://entwinemedia.com/weblounge
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

package ch.entwine.weblounge.common.content.page;

import ch.entwine.weblounge.common.language.Localizable;
import ch.entwine.weblounge.common.security.User;

/**
 * A <code>Layout</code> enforces the placement of certain elements on the page.
 */
public interface PageLayout extends Localizable {

  /**
   * Returns the layout identifier.
   * 
   * @return the layout identifier
   */
  String getIdentifier();

  /**
   * Returns <code>true</code> if the specified element may be placed inside
   * composer <code>composer</code> at position <code>position</code> by user
   * <code>user</code>, <code>false</code> otherwise.
   * 
   * @param element
   *          the element to place
   * @param composer
   *          the composer name
   * @param position
   *          the position to place the element
   * @param user
   *          the user
   * @return <code>true</code> if the element may be placed as specified
   */
  boolean accepts(Pagelet element, String composer, int position, User user);

  /**
   * Returns <code>true</code> if the given composer can be edited by
   * <code>user</code>.
   * 
   * @param composer
   *          the composer name
   * @param user
   *          the user
   * @return <code>true</code> if the composer may be edited
   */
  boolean isEditable(String composer, User user);

  /**
   * Returns <code>true</code> if the pagelet at position <code>position</code>
   * in composer <code>composer</code> can be edited by <code>user</code>.
   * <p>
   * Note that the pagelet position is zero-based.
   * 
   * @param composer
   *          the composer name
   * @param position
   *          pagelet position in the composer
   * @param user
   *          the user
   * @return <code>true</code> if the composer may be edited
   */
  boolean isEditable(String composer, int position, User user);

  /**
   * Returns <code>true</code> if the pagelet at position <code>position</code>
   * in composer <code>composer</code> can be move inside the composer by
   * <code>user</code>.
   * <p>
   * Note that the pagelet position is zero-based.
   * 
   * @param composer
   *          the composer name
   * @param position
   *          pagelet position in the composer
   * @param user
   *          the user
   * @return <code>true</code> if the composer may be moved
   */
  boolean isMovable(String composer, int position, User user);

  /**
   * Returns <code>true</code> if the pagelet at position <code>position</code>
   * in composer <code>composer</code> can be removed by <code>user</code>.
   * <p>
   * Note that the pagelet position is zero-based.
   * 
   * @param composer
   *          the composer name
   * @param position
   *          pagelet position in the composer
   * @param user
   *          the user
   * @return <code>true</code> if the composer may be removed
   */
  boolean isRemovable(String composer, int position, User user);

}