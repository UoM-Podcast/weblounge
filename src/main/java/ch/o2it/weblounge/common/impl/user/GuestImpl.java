/*
 * Weblounge: Web Content Management System Copyright (c) 2007 The Weblounge
 * Team http://weblounge.o2it.ch
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */

package ch.o2it.weblounge.common.impl.user;

import ch.o2it.weblounge.common.impl.security.SystemRole;
import ch.o2it.weblounge.common.site.Site;
import ch.o2it.weblounge.common.user.Guest;

/**
 * Guest user, which is automatically taken as the user object if no valid user
 * is found in the current session. The guest user normally has the smallest set
 * of rights.
 */
public class GuestImpl extends WebloungeUserImpl implements Guest {

  /**
   * Creates a new guest user for the given site.
   */
  public GuestImpl(Site site) {
    super("guest", site);
    setName("Guest");
    assignRole(SystemRole.GUEST);
  }

}