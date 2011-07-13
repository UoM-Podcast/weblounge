/*
 *  Weblounge: Web Content Management System
 *  Copyright (c) 2011 The Weblounge Team
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

package ch.entwine.weblounge.common.content.page;

import ch.entwine.weblounge.common.content.Resource;
import ch.entwine.weblounge.common.content.image.ImageStyle;
import ch.entwine.weblounge.common.impl.content.image.ImageStyleImpl;
import ch.entwine.weblounge.common.impl.content.page.PageImpl;
import ch.entwine.weblounge.common.impl.content.page.PagePreviewGenerator;
import ch.entwine.weblounge.common.impl.content.page.PageURIImpl;
import ch.entwine.weblounge.common.impl.language.LanguageImpl;
import ch.entwine.weblounge.common.language.Language;
import ch.entwine.weblounge.common.site.Site;

import org.apache.commons.io.IOUtils;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Locale;

/**
 * Test case for {@link PagePreviewGenerator}.
 */
public class PagePreviewGeneratorTest {

  /** The test page */
  private String pagePath = "/page.html";

  /** The test file */
  private InputStream page = null;

  /** The generator */
  private PagePreviewGenerator generator = new PagePreviewGenerator();

  /** The site */
  private Site site = null;

  /** The image style used to create the preview */
  private ImageStyle style = new ImageStyleImpl("preview", 150, 150);

  /** English */
  private Language language = new LanguageImpl(Locale.ENGLISH);

  /** The resource */
  private Resource<?> resource = null;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    File f = new File(PagePreviewGeneratorTest.class.getResource(pagePath).getPath());
    page = new FileInputStream(f);
    site = EasyMock.createNiceMock(Site.class);
    resource = new PageImpl(new PageURIImpl(site));
  }
  
  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
    IOUtils.closeQuietly(page);
  }

  /**
   * Test method for {@link ch.entwine.weblounge.common.impl.content.page.PagePreviewGenerator#createPreview(java.io.InputStream, ch.entwine.weblounge.common.content.image.ImageStyle, java.lang.String)}.
   */
  @Test
  @Ignore
  public void testCreatePreview() throws Exception {
    File f = File.createTempFile("test", ".jpg");
    try {
      FileOutputStream fos = new FileOutputStream(f);
      generator.createPreview(resource, language, style, page, fos);
    } finally {
      f.delete();
    }
  }

}
