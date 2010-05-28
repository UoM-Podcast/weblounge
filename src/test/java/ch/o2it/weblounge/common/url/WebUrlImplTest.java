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

package ch.o2it.weblounge.common.url;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import ch.o2it.weblounge.common.impl.language.LanguageSupport;
import ch.o2it.weblounge.common.impl.url.UrlSupport;
import ch.o2it.weblounge.common.impl.url.WebUrlImpl;
import ch.o2it.weblounge.common.language.Language;
import ch.o2it.weblounge.common.request.RequestFlavor;
import ch.o2it.weblounge.common.site.Site;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the web url implementation.
 */
public class WebUrlImplTest {

  /** Url instance pointing to JSON output of the document's live version */
  protected WebUrlImpl liveUrl = null;

  /** Url instance pointing to JSON output of the document's live version */
  protected WebUrlImpl flavoredLiveUrl = null;

  /** The localized live url */
  protected WebUrlImpl localizedLiveUrl = null;

  /** Url instance pointing to JSON output of the document's live version */
  protected WebUrlImpl flavoredLocalizedLiveUrl = null;

  /** The localized segmented live url */
  protected WebUrlImpl localizedSegmentedLiveUrl = null;

  /** Url instance pointing to JSON output of the document's live version */
  protected WebUrlImpl flavoredSegmentedLiveUrl = null;

  /** Url instance pointing to JSON output of the document's live version */
  protected WebUrlImpl flavoredLocalizedSegmentedLiveUrl = null;

  /** Url instance pointing to JSON output of the document's version 17 */
  protected WebUrlImpl versionedUrl = null;

  /** Url instance pointing to JSON output of the document's version 17 */
  protected WebUrlImpl flavoredVersionedUrl = null;

  /** Url instance pointing to JSON output of the document's version 17 */
  protected WebUrlImpl localizedVersionedUrl = null;

  /** Url instance pointing to JSON output of the document's version 17 */
  protected WebUrlImpl flavoredLocalizedVersionedUrl = null;

  /** Site hostname */
  protected String hostname = "www.test.com";

  /** Default url path */
  protected String livePath = "/test/";

  /** Segmented live path */
  protected String segmentedPath = "/test/";

  /** Localized live path */
  protected String localizedPath = "/test/index_de.html";

  /** JSON path to live version */
  protected String flavoredPath = "/test/index.json";

  /** JSON path to live version */
  protected String flavoredLocalizedPath = "/test/index_de.json";

  /** JSON path to live version */
  protected String flavoredSegmentedPath = "/test/json/";

  /** Localized live path */
  protected String localizedSegmentedPath = "/test/de/";

  /** JSON path to live version */
  protected String flavoredLocalizedSegmentedPath = "/test/de/json/";

  /** JSON path to version 17 */
  protected String versionedPath = "/test/17.html";

  /** JSON path to version 17 */
  protected String flavoredVersionedPath = "/test/17.json";

  /** JSON path to version 17 */
  protected String localizedVersionedPath = "/test/17_de.html";

  /** JSON path to version 17 */
  protected String flavoredLocalizedVersionedPath = "/test/17_de.json";

  /** The mock site */
  protected Site siteMock = null;

  /** The mock site */
  protected Site otherSiteMock = null;

  /** The English language */
  protected Language english = LanguageSupport.getLanguage("en");

  /** The German language */
  protected Language german = LanguageSupport.getLanguage("de");

  /** The French language */
  protected Language french = LanguageSupport.getLanguage("fr");

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    siteMock = EasyMock.createNiceMock(Site.class);
    EasyMock.expect(siteMock.getLanguage("de")).andReturn(german).anyTimes();
    EasyMock.expect(siteMock.getDefaultLanguage()).andReturn(english);
    EasyMock.expect(siteMock.getHostName()).andReturn(hostname).anyTimes();
    EasyMock.replay(siteMock);
    otherSiteMock = EasyMock.createNiceMock(Site.class);

    liveUrl = new WebUrlImpl(siteMock, livePath);
    localizedLiveUrl = new WebUrlImpl(siteMock, localizedPath);
    flavoredLiveUrl = new WebUrlImpl(siteMock, flavoredPath);
    flavoredLocalizedLiveUrl = new WebUrlImpl(siteMock, flavoredLocalizedPath);
    localizedSegmentedLiveUrl = new WebUrlImpl(siteMock, localizedSegmentedPath);
    flavoredSegmentedLiveUrl = new WebUrlImpl(siteMock, flavoredSegmentedPath);
    flavoredLocalizedSegmentedLiveUrl = new WebUrlImpl(siteMock, flavoredLocalizedSegmentedPath);
    versionedUrl = new WebUrlImpl(siteMock, versionedPath);
    flavoredVersionedUrl = new WebUrlImpl(siteMock, flavoredVersionedPath);
    localizedVersionedUrl = new WebUrlImpl(siteMock, localizedVersionedPath);
    flavoredLocalizedVersionedUrl = new WebUrlImpl(siteMock, flavoredLocalizedVersionedPath);
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#equals(java.lang.Object)}
   * .
   */
  @Test
  public void testEqualsObject() {
    assertTrue(liveUrl.equals(liveUrl));
    assertTrue(flavoredLiveUrl.equals(flavoredLiveUrl));
    assertTrue(flavoredLocalizedLiveUrl.equals(flavoredLocalizedLiveUrl));
    assertFalse(liveUrl.equals(flavoredLiveUrl));
    assertFalse(liveUrl.equals(flavoredLocalizedLiveUrl));
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getSite()}.
   */
  @Test
  public void testGetSite() {
    assertEquals(siteMock, liveUrl.getSite());
    assertEquals(siteMock, flavoredLiveUrl.getSite());
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getLink()}.
   */
  @Test
  public void testGetPath() {
    assertEquals(livePath, liveUrl.getPath());
    assertEquals(livePath, flavoredLiveUrl.getPath());
    assertEquals(livePath, localizedLiveUrl.getPath());
    assertEquals(livePath, flavoredLocalizedLiveUrl.getPath());
    assertEquals(livePath, flavoredSegmentedLiveUrl.getPath());
    assertEquals(livePath, localizedSegmentedLiveUrl.getPath());
    assertEquals(livePath, flavoredLocalizedSegmentedLiveUrl.getPath());
    assertEquals(livePath, versionedUrl.getPath());
    assertEquals(livePath, flavoredVersionedUrl.getPath());
    assertEquals(livePath, localizedVersionedUrl.getPath());
    assertEquals(livePath, flavoredLocalizedVersionedUrl.getPath());
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getLink()}.
   */
  @Test
  public void testGetLink() {
    assertEquals(livePath, liveUrl.getLink());
    assertTrue(flavoredLiveUrl.getLink().endsWith(flavoredPath));
    assertTrue(localizedLiveUrl.getLink().endsWith(localizedPath));
    assertTrue(flavoredLocalizedLiveUrl.getLink().endsWith(flavoredLocalizedPath));
    assertTrue(flavoredSegmentedLiveUrl.getLink().endsWith(flavoredPath));
    assertTrue(localizedSegmentedLiveUrl.getLink().endsWith(localizedPath));
    assertTrue(flavoredLocalizedSegmentedLiveUrl.getLink().endsWith(flavoredLocalizedPath));
    assertEquals(versionedPath, versionedUrl.getLink());
    assertTrue(flavoredVersionedUrl.getLink().endsWith(flavoredVersionedPath));
    assertTrue(localizedVersionedUrl.getLink().endsWith(localizedVersionedPath));
    assertTrue(flavoredLocalizedVersionedUrl.getLink().endsWith(flavoredLocalizedVersionedPath));
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getLink(ch.o2it.weblounge.common.language.Language)}
   * .
   */
  @Test
  public void testGetLinkLanguage() {
    String l = "_fr";
    assertEquals(localizedPath.replaceAll("_de", l), liveUrl.getLink(french));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l), flavoredLiveUrl.getLink(french));
    assertEquals(localizedPath.replaceAll("_de", l), localizedLiveUrl.getLink(french));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l), flavoredSegmentedLiveUrl.getLink(french));
    assertEquals(localizedPath.replaceAll("_de", l), localizedSegmentedLiveUrl.getLink(french));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l), flavoredLocalizedLiveUrl.getLink(french));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l), flavoredLocalizedSegmentedLiveUrl.getLink(french));
    assertEquals(localizedVersionedPath.replaceAll("_de", l), versionedUrl.getLink(french));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("_de", l), flavoredVersionedUrl.getLink(french));
    assertEquals(localizedVersionedPath.replaceAll("_de", l), localizedVersionedUrl.getLink(french));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("_de", l), flavoredLocalizedVersionedUrl.getLink(french));
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getLink(java.lang.String)}
   * .
   */
  @Test
  public void testGetLinkString() {
    String flavor = "pdf";
    assertEquals(flavoredPath.replaceAll("json", flavor), liveUrl.getLink(flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("json", flavor), localizedLiveUrl.getLink(flavor));
    assertEquals(flavoredPath.replaceAll("json", flavor), flavoredLiveUrl.getLink(flavor));
    assertEquals(flavoredPath.replaceAll("json", flavor), flavoredSegmentedLiveUrl.getLink(flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("json", flavor), localizedSegmentedLiveUrl.getLink(flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("json", flavor), flavoredLocalizedLiveUrl.getLink(flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("json", flavor), flavoredLocalizedSegmentedLiveUrl.getLink(flavor));
    assertEquals(flavoredVersionedPath.replaceAll("json", flavor), versionedUrl.getLink(flavor));
    assertEquals(flavoredVersionedPath.replaceAll("json", flavor), flavoredVersionedUrl.getLink(flavor));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("json", flavor), localizedVersionedUrl.getLink(flavor));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("json", flavor), flavoredLocalizedVersionedUrl.getLink(flavor));
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getLink(long)}.
   */
  @Test
  public void testGetLinkLong() {
    long version = 13;
    String v = Long.toString(version);
    assertEquals(flavoredPath.replaceAll("index", v), flavoredLiveUrl.getLink(version));
    assertEquals(localizedPath.replaceAll("index", v), localizedLiveUrl.getLink(version));
    assertEquals(flavoredPath.replaceAll("index", v), flavoredSegmentedLiveUrl.getLink(version));
    assertEquals(localizedPath.replaceAll("index", v), localizedSegmentedLiveUrl.getLink(version));
    assertEquals(flavoredLocalizedPath.replaceAll("index", v), flavoredLocalizedLiveUrl.getLink(version));
    assertEquals(flavoredLocalizedPath.replaceAll("index", v), flavoredLocalizedSegmentedLiveUrl.getLink(version));
    assertEquals(versionedPath.replaceAll("17", v), versionedUrl.getLink(version));
    assertEquals(flavoredVersionedPath.replaceAll("17", v), flavoredVersionedUrl.getLink(version));
    assertEquals(localizedVersionedPath.replaceAll("17", v), localizedVersionedUrl.getLink(version));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("17", v), flavoredLocalizedVersionedUrl.getLink(version));
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getLink(long, ch.o2it.weblounge.common.language.Language, java.lang.String)}
   * .
   */
  @Test
  public void testGetLinkStringLanguageLong() {
    String flavor = "pdf";
    long version = 13;
    String v = Long.toString(version);
    String l = "_fr";
    assertEquals(localizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("html", flavor), liveUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("json", flavor), flavoredLiveUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("json", flavor), localizedLiveUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("json", flavor), flavoredSegmentedLiveUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("json", flavor), localizedSegmentedLiveUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("json", flavor), flavoredLocalizedLiveUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedPath.replaceAll("_de", l).replaceAll("index", v).replaceAll("json", flavor), flavoredLocalizedSegmentedLiveUrl.getLink(version, french, flavor));
    assertEquals(localizedVersionedPath.replaceAll("_de", l).replaceAll("17", v).replaceAll("html", flavor), versionedUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("_de", l).replaceAll("17", v).replaceAll("json", flavor), flavoredVersionedUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("_de", l).replaceAll("17", v).replaceAll("json", flavor), localizedVersionedUrl.getLink(version, french, flavor));
    assertEquals(flavoredLocalizedVersionedPath.replaceAll("_de", l).replaceAll("17", v).replaceAll("json", flavor), flavoredLocalizedVersionedUrl.getLink(version, french, flavor));
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#getFlavor()}.
   */
  @Test
  public void testGetFlavor() {
    assertEquals(RequestFlavor.HTML, liveUrl.getFlavor());
    assertEquals(RequestFlavor.JSON, flavoredSegmentedLiveUrl.getFlavor());
    assertEquals(RequestFlavor.JSON, new WebUrlImpl(siteMock, "/test/json").getFlavor());
    assertEquals(RequestFlavor.JSON, new WebUrlImpl(siteMock, "/test/work/json").getFlavor());
    assertEquals(RequestFlavor.JSON, new WebUrlImpl(siteMock, "/test/de/json").getFlavor());
    assertEquals(RequestFlavor.JSON, new WebUrlImpl(siteMock, "/test/work/de/json").getFlavor());
    assertEquals(RequestFlavor.JSON, new WebUrlImpl(siteMock, "/test/JSON").getFlavor());
    assertEquals(RequestFlavor.JSON, new WebUrlImpl(siteMock, "/test/json/").getFlavor());
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#normalize(boolean, boolean, boolean)}
   * .
   */
  @Test
  public void testNormalize() {
    assertEquals(UrlSupport.concat(hostname, segmentedPath), liveUrl.normalize());
    assertEquals(UrlSupport.concat(hostname, flavoredSegmentedPath), flavoredSegmentedLiveUrl.normalize());
    assertEquals(UrlSupport.concat(hostname, localizedSegmentedPath), localizedSegmentedLiveUrl.normalize());
    assertEquals(UrlSupport.concat(hostname, flavoredLocalizedSegmentedPath), flavoredLocalizedSegmentedLiveUrl.normalize());
  }

  /**
   * Test method for
   * {@link ch.o2it.weblounge.common.impl.url.WebUrlImpl#normalize(boolean, boolean, boolean)}
   * .
   */
  @Test
  public void testNormalizeBooleanBooleanBoolean() {
    WebUrl url = flavoredLocalizedVersionedUrl;
    String fullUrl = UrlSupport.concat(hostname, flavoredLocalizedVersionedPath);
    assertEquals(fullUrl, url.normalize(true, true, true));

    // Everything
    assertTrue(url.normalize(true, true, true).indexOf(Long.toString(url.getVersion())) > 0);
    assertTrue(url.normalize(true, true, true).indexOf(url.getLanguage().getIdentifier().toString()) > 0);
    assertTrue(url.normalize(true, true, true).indexOf(url.getFlavor().toString().toLowerCase()) > 0);

    // Everything but the version
    assertTrue(url.normalize(false, true, true).indexOf(Long.toString(url.getVersion())) < 0);
    assertTrue(url.normalize(false, true, true).indexOf(url.getLanguage().getIdentifier().toString()) > 0);
    assertTrue(url.normalize(false, true, true).indexOf(url.getFlavor().toString().toLowerCase()) > 0);

    // Everything but the language
    assertTrue(url.normalize(true, false, true).indexOf(Long.toString(url.getVersion())) > 0);
    assertTrue(url.normalize(true, false, true).indexOf(url.getLanguage().getIdentifier().toString()) == -1);
    assertTrue(url.normalize(true, false, true).indexOf(url.getFlavor().toString().toLowerCase()) > 0);

    // Everything but the flavor
    assertTrue(url.normalize(true, true, false).indexOf(Long.toString(url.getVersion())) > 0);
    assertTrue(url.normalize(true, true, false).indexOf(url.getLanguage().getIdentifier().toString()) > 0);
    assertTrue(url.normalize(true, true, false).indexOf(url.getFlavor().toString().toLowerCase()) == -1);
  }

}
