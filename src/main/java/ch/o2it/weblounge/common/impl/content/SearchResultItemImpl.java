/*
 *  Weblounge: Web Content Management System
 *  Copyright (c) 2009 The Weblounge Team
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

package ch.o2it.weblounge.common.impl.content;

import ch.o2it.weblounge.common.content.PageURI;
import ch.o2it.weblounge.common.content.Renderer;
import ch.o2it.weblounge.common.content.SearchResultItem;
import ch.o2it.weblounge.common.impl.language.LocalizableObject;
import ch.o2it.weblounge.common.language.Language;
import ch.o2it.weblounge.common.language.Localizable;

/**
 * Default implementation of a {@link SearchResultItem}.
 */
public class SearchResultItemImpl extends LocalizableObject implements SearchResultItem {

  /** The title */
  protected String title = null;

  /** The preview */
  protected String preview = null;

  /** The hit location */
  protected PageURI uri = null;

  /** The renderer used to show the preview */
  protected Renderer previewRenderer = null;

  /** Source of the search result */
  protected Object source = null;

  /** Score within the search result */
  protected double score = 0.0d;

  /**
   * Creates a new search result with the given uri. The <code>source</code> is
   * the object that created the item, usually, this will be the site itself but
   * it could very well be a module that added to a search result.
   * 
   * @param uri
   *          the url to show the hit
   * @param source
   *          the object that produced the result item
   * @param relevance
   *          the score inside the search result
   */
  public SearchResultItemImpl(PageURI uri, Object source, double relevance) {
    this.uri = uri;
    this.source = source;
    this.score = relevance;
  }

  /**
   * Returns the title for this search result.
   * 
   * @see ch.o2it.weblounge.common.content.SearchResultItem#getTitle()
   */
  public String getTitle() {
    return title;
  }

  /**
   * @see ch.o2it.weblounge.common.content.SearchResultItem#getUrl()
   */
  public PageURI getURI() {
    return uri;
  }

  /**
   * @see ch.o2it.weblounge.common.content.SearchResultItem#getPreview()
   */
  public String getPreview() {
    return preview;
  }

  /**
   * Sets the preview renderer.
   * 
   * @param r
   *          the renderer
   */
  public void setPreviewRenderer(Renderer r) {
    previewRenderer = r;
  }

  /**
   * @see ch.o2it.weblounge.common.content.SearchResultItem#getPreviewRenderer()
   */
  public Renderer getPreviewRenderer() {
    return previewRenderer;
  }

  /**
   * @see ch.o2it.weblounge.common.content.SearchResultItem#getRelevance()
   */
  public double getRelevance() {
    return score;
  }

  /**
   * Returns the search result's source.
   * 
   * @see ch.o2it.weblounge.common.content.SearchResultItem#getSource()
   */
  public Object getSource() {
    return source;
  }

  /**
   * {@inheritDoc}
   * 
   * @see ch.o2it.weblounge.common.impl.language.LocalizableObject#switchTo(ch.o2it.weblounge.common.language.Language,
   *      boolean)
   */
  @Override
  public Language switchTo(Language language, boolean force) {
    if (previewRenderer != null)
      previewRenderer.switchTo(language);
    return super.switchTo(language, force);
  }

  /**
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(SearchResultItem sr) {
    if (score < sr.getRelevance())
      return 1;
    else if (score > sr.getRelevance())
      return -1;
    else
      return getTitle().compareTo(sr.getTitle());
  }

  /**
   * {@inheritDoc}
   * 
   * @see ch.o2it.weblounge.common.language.Localizable#compareTo(ch.o2it.weblounge.common.language.Localizable,
   *      ch.o2it.weblounge.common.language.Language)
   */
  public int compareTo(Localizable o, Language l) {
    if (o instanceof SearchResultItem) {
      SearchResultItem r = (SearchResultItem) o;
      if (score > r.getRelevance())
        return 1;
      else if (score < r.getRelevance())
        return -1;
      else {
        // TODO: Return newest entry?
        return 0;
      }
    }
    return 0;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof SearchResultItem) {
      SearchResultItem r = (SearchResultItem) obj;
      return uri.equals(r.getURI()) && score == r.getRelevance();
    }
    return super.equals(obj);
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return Double.toString(score).hashCode();
  }

}