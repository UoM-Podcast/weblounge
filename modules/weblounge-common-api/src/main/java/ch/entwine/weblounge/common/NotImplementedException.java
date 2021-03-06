/*
 *  Weblounge: Web Content Management System
 *  Copyright (c) 2014 The Weblounge Team
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

package ch.entwine.weblounge.common;

/**
 * An exception which indicates that a code execution path is not (yet)
 * implemented.
 */
public class NotImplementedException extends RuntimeException {

  private static final long serialVersionUID = 1751016937930545603L;

  private final String message;

  /**
   * Creates an instance of {@code NotImplementedException}.
   */
  public NotImplementedException() {
    this.message = null;
  }

  /**
   * Creates an instance of {@code NotImplementedException} with a detail
   * message.
   * 
   * @param message
   *          the detail message
   */
  public NotImplementedException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
