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

package ch.entwine.weblounge.test.site;

import ch.entwine.weblounge.common.scheduler.JobException;
import ch.entwine.weblounge.common.scheduler.JobWorker;
import ch.entwine.weblounge.common.site.Site;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Dictionary;

/**
 * Test job that will print a friendly greeting to <code>System.out</code> at
 * site startup.
 */
public class SiteStartupJob implements JobWorker {

  /** Logging facility */
  protected static final Logger logger = LoggerFactory.getLogger(SiteStartupJob.class);

  /**
   * {@inheritDoc}
   * 
   * @see ch.entwine.weblounge.common.scheduler.JobWorker#execute(java.lang.String,
   *      java.util.Dictionary)
   */
  public void execute(String name, Dictionary<String, Serializable> ctx)
      throws JobException {
    Site site = (Site)ctx.get(Site.class.getName());
    if (site != null)
      logger.info("Site '" + site + "' started");
    else
      logger.warn("Site not found in context");
  }

}
