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

package ch.o2it.weblounge.taglib.security;

import ch.o2it.weblounge.common.site.Site;
import ch.o2it.weblounge.common.user.User;
import ch.o2it.weblounge.taglib.WebloungeTag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

/**
 * The body of this tag is only evaluated if the user has a certain role.
 */
public class RoleCheckTag extends WebloungeTag {

  /** serial uid */
  private static final long serialVersionUID = 8899627757239254637L;

  /** the roles to be checked */
  private List<String> oneOf = new ArrayList<String>();

  /** the roles to be checked */
  private List<String> allOf = new ArrayList<String>();

  /** the role context */
  private String context = null;

  /** the role identifier */
  private String id = null;

  /**
   * Sets the role context, e. g. <code>system</code>. Use this setter in
   * conjunction with {@link #setRoleId(String)}.
   * 
   * @param value
   *          the role context
   */
  public void setContext(String value) {
    context = value;
  }

  /**
   * Sets the role identifier. Use this setter in conjunction with
   * {@link #setContext(String)}.
   * 
   * @param value
   *          the role identifier
   */
  public void setRoleid(String value) {
    id = value;
  }

  /**
   * Specifies which role has to be acquired for the tag body to be displayed.
   * The role definition must consist of the form <code>context:id</code>.
   * 
   * @param value
   *          the role
   */
  public void setRole(String value) throws JspTagException {
    if (value.split(":").length != 2) {
      throw new JspTagException("The role must be of the form 'context:id'!");
    }
    oneOf.add(value);
    allOf.add(value);
  }

  /**
   * Specifies a roleset. The user must own one of these roles for the tag body
   * to be displayed. The roleset must be provided as a coma separated list of
   * role definitions, e.g. <code>system:admin, system:editor</code>.
   * 
   * @param value
   *          the roleset
   */
  public void setOneof(String value) throws JspTagException {
    StringTokenizer tok = new StringTokenizer(value, ",; ");
    String role;
    while (tok.hasMoreTokens()) {
      role = tok.nextToken();
      if (role.split(":").length != 2) {
        throw new JspTagException("The role must be of the form 'context:id'!");
      }
      oneOf.add(role);
    }
  }

  /**
   * Specifies a roleset. The user must own all of these roles for the tag body
   * to be displayed. The roleset must be provided as a coma separated list of
   * role definitions, e.g. <code>system:admin, system:editor</code>.
   * 
   * @param value
   *          the roleset
   */
  public void setAllof(String value) throws JspTagException {
    StringTokenizer tok = new StringTokenizer(value, ",");
    String role;
    while (tok.hasMoreTokens()) {
      role = tok.nextToken();
      if (role.split(":").length != 2) {
        throw new JspTagException("The role must be of the form 'context:id'!");
      }
      allOf.add(role);
    }
  }

  /**
   * @see javax.servlet.jsp.tagext.Tag#doStartTag()
   */
  @Override
  public int doStartTag() throws JspException {
    if (context != null && id != null) {
      allOf.add(context + ":" + id);
      oneOf.add(context + ":" + id);
    }
    return super.doStartTag();
  }

  /**
   * @see javax.servlet.jsp.tagext.Tag#doEndTag()
   */
  @Override
  public int doEndTag() throws JspException {
    reset();
    return super.doEndTag();
  }

  /**
   * Called when this tag instance is released.
   */
  public void reset() {
    allOf = new ArrayList<String>();
    oneOf = new ArrayList<String>();
    super.release();
  }

  /**
   * Returns <code>true</code> if the user has one out of the "oneof" roleset.
   * 
   * @param user
   *          the user to check
   * @param site
   *          the site context
   * @return <code>true</code> if the user has one of the roles
   */
  protected boolean hasOneOf(User user, Site site) {
    if (oneOf.size() == 0) {
      return true;
    } else {
      Iterator<String> roles = oneOf.iterator();
      while (roles.hasNext()) {
        String[] parts = roles.next().split(":");
        if (user.hasRole(parts[0], parts[1]))
          return true;
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code> if the user has all out of the "allof" roleset.
   * 
   * @param user
   *          the user to check
   * @param site
   *          the site context
   * @return <code>true</code> if the user has all of the roles
   */
  protected boolean hasAllOf(User user, Site site) {
    if (allOf.size() == 0) {
      return true;
    } else {
      Iterator<String> roles = allOf.iterator();
      while (roles.hasNext()) {
        String[] parts = roles.next().split(":");
        if (!user.hasRole(parts[0], parts[1])) {
          return false;
        }
      }
    }
    return true;
  }

}
