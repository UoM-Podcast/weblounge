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

package ch.o2it.weblounge.common.impl.language;

import ch.o2it.weblounge.common.impl.util.encoding.Encoding;
import ch.o2it.weblounge.common.impl.util.encoding.PlainEncoding;
import ch.o2it.weblounge.common.language.Language;
import ch.o2it.weblounge.common.language.LocalizationListener;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * This class represents a general object container, capable of managing this
 * object in various languages.
 */
public class LocalizableContent<T> extends LocalizableObject implements LocalizationListener, Cloneable {

  /** the content in various languages */
  protected Map<Language, T> content = null;

  /** the encoding used to display the title */
  private Encoding encoding_ = null;

  /**
   * Constructor for a localizable object with a default behavior of
   * {@link LanguageResolution#Original}.
   */
  public LocalizableContent() {
    encoding_ = PlainEncoding.getInstance();
    content = new HashMap<Language, T>();
  }

  /**
   * Constructor for class MultilingualObject with the given language as the
   * default language.
   * 
   * @param language
   *          the default language
   */
  public LocalizableContent(Language language) {
    super(language);
    encoding_ = PlainEncoding.getInstance();
    content = new HashMap<Language, T>();
  }

  /**
   * Creates a new localizable content object that registers with the given
   * <code>localizable</code> to get notified in case of language switches.
   * 
   * @param localizable
   *          the parent localizable
   */
  public LocalizableContent(LocalizableObject localizable) {
    this(localizable.getDefaultLanguage());
    this.setOriginalLanguage(localizable.getOriginalLanguage());
    this.setDefaultLanguage(localizable.getDefaultLanguage());
    this.setLanguageResolution(localizable.getLanguageResolution());
    localizable.addLocalizationListener(this);
  }

  /**
   * Removes all content from the object while keeping the language settings.
   */
  public void clear() {
    content.clear();
    languages.clear();
  }

  /**
   * Returns <code>true</code> if there is no content at all stored in the
   * object.
   * 
   * @return <code>true</code> if the object is empty
   */
  public boolean isEmpty() {
    return content.isEmpty();
  }

  /**
   * Returns the number of language entries.
   * 
   * @return the number of languages
   */
  public int size() {
    return content.size();
  }

  /**
   * Returns the values stored for the various languages.
   * 
   * @return the values
   */
  public Collection<T> values() {
    return  content.values();
  }

  /**
   * Sets the encoding that is used to display the object title. By default,
   * this class uses a <code>HTMLEncoding</code>.
   * 
   * @param encoding
   *          the encoding used to display the object title
   */
  public void setEncoding(Encoding encoding) {
    if (encoding == null) {
      throw new IllegalArgumentException("Null is not allowed as encoding!");
    }
    encoding_ = encoding;
  }

  /**
   * Returns the encoding used by this class to display the title element.
   * 
   * @return the encoding
   */
  public Encoding getEncoding() {
    return encoding_;
  }

  /**
   * @see ch.o2it.weblounge.common.impl.language.LocalizableObject#remove(ch.o2it.weblounge.common.language.Language)
   */
  @Override
  public void remove(Language language) {
    super.remove(language);
    content.remove(language);
  }

  /**
   * Sets the content in the given language.
   * 
   * @param content
   *          the content
   * @param language
   *          the content language
   */
  public void put(T content, Language language) {
    this.content.put(language, content);
    super.enableLanguage(language);
  }

  /**
   * Returns the content in the active language or <code>null</code> if no
   * content was found.
   * 
   * @return the content
   */
  public T get() {
    return get(getLanguage(), false);
  }

  /**
   * Returns the content in the required language. If no content can be found in
   * that language, then it will be looked up in the default language. If that
   * doesn't produce a result as well, <code>null</code> is returned.
   * 
   * @param language
   *          the content language
   * @return the content
   */
  public T get(Language language) {
    return get(language, false);
  }

  /**
   * Returns the content in the required language. If no content can be found in
   * that language, then it will be looked up in the default language (unless
   * <code>force</code> is set to <code>true</code>). If that doesn't produce a
   * result as well, <code>null</code> is returned.
   * 
   * @param language
   *          the content language
   * @param force
   *          <code>true</code> to force the language
   * @return the content
   */
  public T get(Language language, boolean force) {
    T c = content.get(language);
    if (c == null && !force) {
      Language defaultLanguage = getDefaultLanguage();
      if (defaultLanguage != null && (language == null || !language.equals(defaultLanguage))) {
        c = content.get(defaultLanguage);
      } else if (content.size() == 1) {
        c = content.values().iterator().next();
      }
    }
    return c;
  }

  /**
   * {@inheritDoc}
   * 
   * @see ch.o2it.weblounge.common.language.LocalizationListener#switchedTo(ch.o2it.weblounge.common.language.Language)
   */
  public void switchedTo(Language language) {
    switchTo(language);
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#clone()
   */
  public Object clone() {
    LocalizableContent<T> c = new LocalizableContent<T>();
    c.behavior = behavior;
    c.currentLanguage = currentLanguage;
    c.defaultLanguage = defaultLanguage;
    c.encoding_ = encoding_;
    c.originalLanguage = originalLanguage;

    // languages
    c.languages = new HashSet<Language>();
    c.languages.addAll(languages);

    // content
    c.content = new HashMap<Language, T>();
    c.content.putAll(content);

    return c;
  }

  /**
   * Returns the component title in the active language. The title is identified
   * by the name "name".
   * 
   * @return the component title.
   */
  public String toString() {
    return toString(getDefaultLanguage());
  }

  /**
   * Returns the title in the requested language or <code>null</code> if the
   * title doesn't exist in that language.
   * 
   * @param language
   *          the requested language
   * @return the object title
   */
  public String toString(Language language) {
    return toString(language, false);
  }

  /**
   * Returns the object using <code>language</code> as the output language. If
   * no content can be found in that language, then it will be looked up in the
   * default language (unless <code>force</code> is set to <code>true</code>). <br>
   * If this doesn't produce a result as well, <code>null</code> is returned.
   * 
   * @param language
   *          the language
   * @param force
   *          <code>true</code> to force the language
   * @return the object's string representation in the given language
   */
  public String toString(Language language, boolean force) {
    T c = content.get(language);

    // Not found? Try the fall back
    if (c == null && !force)
      c = content.get(getFallbackLanguage());

    return (c != null) ? encoding_.encode(c.toString()) : null;
  }

}