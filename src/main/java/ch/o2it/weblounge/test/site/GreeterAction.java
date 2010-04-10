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

package ch.o2it.weblounge.test.site;

import ch.o2it.weblounge.common.content.HTMLHeadElement;
import ch.o2it.weblounge.common.content.Page;
import ch.o2it.weblounge.common.content.Pagelet;
import ch.o2it.weblounge.common.content.PageletRenderer;
import ch.o2it.weblounge.common.impl.content.PageImpl;
import ch.o2it.weblounge.common.impl.content.PageURIImpl;
import ch.o2it.weblounge.common.impl.content.PageletImpl;
import ch.o2it.weblounge.common.impl.request.RequestUtils;
import ch.o2it.weblounge.common.impl.site.HTMLActionSupport;
import ch.o2it.weblounge.common.impl.url.WebUrlImpl;
import ch.o2it.weblounge.common.request.RequestFlavor;
import ch.o2it.weblounge.common.request.WebloungeRequest;
import ch.o2it.weblounge.common.request.WebloungeResponse;
import ch.o2it.weblounge.common.site.ActionException;
import ch.o2it.weblounge.test.util.TestSiteUtils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple test action that is able to render a greeting on the site template.
 */
public class GreeterAction extends HTMLActionSupport {

  /** Name of the language parameter */
  public static final String LANGUAGE_PARAM = "language";

  /** The greetings */
  protected Map<String, String> greetings = new HashMap<String, String>();
  
  /**
   * {@inheritDoc}
   * 
   * @see ch.o2it.weblounge.common.impl.site.HTMLActionSupport#configure(ch.o2it.weblounge.common.request.WebloungeRequest,
   *      ch.o2it.weblounge.common.request.WebloungeResponse,
   *      ch.o2it.weblounge.common.request.RequestFlavor)
   */
  @Override
  public void configure(WebloungeRequest request, WebloungeResponse response,
      RequestFlavor flavor) throws ActionException {
    super.configure(request, response, flavor);
    Map<String, String> allGreetings = TestSiteUtils.loadGreetings();
    try {
      String language = RequestUtils.getRequiredParameter(request, LANGUAGE_PARAM);
      String greeting = allGreetings.get(language);
      if (greeting == null)
        // TODO: How do we indicate a 404 instead of 500? Different exceptions?
        // Like this, a json action could not return an empty result set
        throw new ActionException("Unfortunately, we are not fluent in " + language);
      greetings.put(language, greeting);
      
      // Add a fake page, so we can test pagelet renderer includes
      Page page = new PageImpl(new PageURIImpl(new WebUrlImpl(getSite(), "/test")));
      Pagelet pagelet = new PageletImpl(getModule().getIdentifier(), "content");
      page.addPagelet(pagelet, "main");
      setPage(page);
    } catch (IllegalStateException e) {
      throw new ActionException("Language parameter '" + LANGUAGE_PARAM + "' was not specified");
    }
  }
  
  /**
   * {@inheritDoc}
   *
   * @see ch.o2it.weblounge.common.impl.site.HTMLActionSupport#startHeader(ch.o2it.weblounge.common.request.WebloungeRequest, ch.o2it.weblounge.common.request.WebloungeResponse)
   */
  @Override
  public void startHeader(WebloungeRequest request, WebloungeResponse response)
      throws IOException, ActionException {
    PageletRenderer contentPagelet = getModule().getRenderer("greeting");
    for (HTMLHeadElement header : contentPagelet.getHTMLHeaders()) {
      addHTMLHeader(header);
    }
    super.startHeader(request, response);
  }

  /**
   * {@inheritDoc}
   *
   * @see ch.o2it.weblounge.common.impl.site.HTMLActionSupport#startStage(ch.o2it.weblounge.common.request.WebloungeRequest, ch.o2it.weblounge.common.request.WebloungeResponse)
   */
  @Override
  public int startStage(WebloungeRequest request, WebloungeResponse response)
      throws ActionException {
    try {
      String language = greetings.keySet().iterator().next();
      String htmlGreeting = StringEscapeUtils.escapeHtml(greetings.get(language));
      IOUtils.write("<h1>" + htmlGreeting + "</h1>", response.getOutputStream(), "UTF-8");

      // Include a pagelet
      include(request, response, "content", null);

      // Include another pagelet
      include(request, response, "greeting", null);
      return SKIP_COMPOSER;
    } catch (IOException e) {
      throw new ActionException("Unable to send json response", e);
    }
  }
  
  /**
   * {@inheritDoc}
   * 
   * @see ch.o2it.weblounge.common.impl.site.HTMLActionSupport#passivate()
   */
  @Override
  public void passivate() {
    super.passivate();
    greetings.clear();
  }

}
