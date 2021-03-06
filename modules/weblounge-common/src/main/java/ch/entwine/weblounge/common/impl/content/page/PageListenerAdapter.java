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

package ch.entwine.weblounge.common.impl.content.page;

import ch.entwine.weblounge.common.content.Renderer;
import ch.entwine.weblounge.common.content.ResourceURI;
import ch.entwine.weblounge.common.content.page.PageLayout;
import ch.entwine.weblounge.common.content.page.PageListener;
import ch.entwine.weblounge.common.security.User;

/**
 * Convenience implementation for classes that are interested in callbacks to a
 * <code>PageListener</code>.
 * 
 * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener
 */
public class PageListenerAdapter implements PageListener {

  /**
   * Creates a new adapter for a page listener.
   */
  public PageListenerAdapter() { }

  /**
   * This method is called if the page at location <code>url</code> has been
   * created by user <code>user</code>.
   * 
   * @param uri
   *          the page's location
   * @param user
   *          the creating user
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageCreated(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageCreated(ResourceURI uri, User user) { }

  /**
   * This method is called if the page at location <code>url</code> has been
   * removed by user <code>user</code>.
   * 
   * @param uri
   *          the page's former location
   * @param user
   *          the removing user
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageRemoved(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageRemoved(ResourceURI uri, User user) { }

  /**
   * This method is called if the page at location <code>from</code> has been
   * moved to <code>to</code> by user <code>user</code>.
   * 
   * @param from
   *          the page's former location
   * @param to
   *          the page's new location
   * @param user
   *          the user moving the page
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageMoved(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.api.url.WebUrl, ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageMoved(ResourceURI from, ResourceURI to, User user) {
  }

  /**
   * This method is called if the page at location <code>url</code> has been
   * published by user <code>user</code>.
   * 
   * @param uri
   *          the page's location
   * @param user
   *          the user publishing the page
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pagePublished(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pagePublished(ResourceURI uri, User user) {
  }

  /**
   * This method is called if the page at location <code>url</code> has been
   * unpublished by user <code>user</code>.
   * 
   * @param uri
   *          the page's location
   * @param user
   *          the user unpublishing the page
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageUnpublished(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageUnpublished(ResourceURI uri, User user) {
  }

  /**
   * This method is called if the page at location <code>url</code> has been
   * locked by user <code>user</code>.
   * 
   * @param uri
   *          the page's location
   * @param user
   *          the user locking the page
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageLocked(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageLocked(ResourceURI uri, User user) {
  }

  /**
   * This method is called if the page at location <code>url</code> has been
   * released by user <code>user</code>.
   * 
   * @param uri
   *          the page's location
   * @param user
   *          the user releasing the page lock
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageUnlocked(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageUnlocked(ResourceURI uri, User user) {
  }

  /**
   * Notifies the listener about a new page renderer at url <code>url</code>.
   * 
   * @param url
   *          the page url
   * @param newRenderer
   *          the new renderer
   * @param oldRenderer
   *          the former renderer
   * @param user
   *          the editing user
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageRendererChanged(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.Renderer.renderer.Renderer,
   *      ch.entwine.weblounge.common.Renderer.renderer.Renderer,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageRendererChanged(ResourceURI url, Renderer newRenderer,
      Renderer oldRenderer, User user) {
  }

  /**
   * Notifies the listener about a new page layout at url <code>url</code>.
   * 
   * @param uri
   *          the page url
   * @param newLayout
   *          the new layout
   * @param oldLayout
   *          the former layout
   * @param user
   *          the editing user
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageLayoutChanged(ch.entwine.weblounge.api.url.WebUrl,
   *      ch.entwine.weblounge.common.content.page.PageLayout.content.Layout,
   *      ch.entwine.weblounge.common.content.page.PageLayout.content.Layout,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageLayoutChanged(ResourceURI uri, PageLayout newLayout, PageLayout oldLayout,
      User user) {
  }

  /**
   * Notifies the listener about a new page type at url <code>url</code>.
   * 
   * @param uri
   *          the page url
   * @param newType
   *          the new page type
   * @param oldType
   *          the former page type
   * @param user
   *          the editing user
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageTypeChanged(ch.entwine.weblounge.api.url.WebUrl,
   *      java.lang.String, java.lang.String,
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageTypeChanged(ResourceURI uri, String newType, String oldType, User user) {
  }

  /**
   * Notifies the listener about a change in the list of keywords at url
   * <code>url</code>.
   * 
   * @param uri
   *          the page url
   * @param newKeywords
   *          the new keywords
   * @param oldKeywords
   *          the old keywords
   * @param user
   *          the editing user
   * @see ch.entwine.weblounge.common.content.page.contentrepository.api.content.PageListener#pageKeywordsChanged(ch.entwine.weblounge.api.url.WebUrl,
   *      java.lang.String[], java.lang.String[],
   *      ch.entwine.weblounge.common.security.api.security.User)
   */
  public void pageKeywordsChanged(ResourceURI uri, String[] newKeywords,
      String[] oldKeywords, User user) {
  }

}