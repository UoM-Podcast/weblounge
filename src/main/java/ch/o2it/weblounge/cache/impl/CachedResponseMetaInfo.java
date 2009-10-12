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

package ch.o2it.weblounge.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * CachedResponseMetaInfo
 * 
 * @version $Revision: 1.1 $ $Date: 2005/05/11 19:51:58 $
 * @author Daniel Steiner
 */
class CachedResponseMetaInfo {

	/** the list of custom headers */
	Map<String, Object> headers = new HashMap<String, Object>(5);
	
	/** the content type of the message */
	String contentType;
	
	/**
	 * Apply the cached response meta info to a new response.
	 * @param resp the response
	 */
	@SuppressWarnings("unchecked")
	void apply(HttpServletResponse resp) {
		if (resp == null)
			return;
		if (contentType != null)
			resp.setContentType(contentType);
		for (Map.Entry<String, Object> e : headers.entrySet()) {
			Object v = e.getValue();
			if (v instanceof List)
				for (String s : (List<String>)v)
					resp.addHeader(e.getKey(), s);
			else if (v instanceof String)
				resp.setHeader(e.getKey(), (String)v);
		}
	}
	
	/** sets response a header */
	void setHeader(String key, String value) {
		headers.put(key, value);
	}
	
	/** adds a response header */
	@SuppressWarnings("unchecked")
	void addHeader(String key, String value) {
		Object o = headers.get(key);
		if (o == null) {
			headers.put(key, value);
		}
		else if (o instanceof List) {
			((List<String>) o).add(value);
		}
		else if (o instanceof String) {
			List<String> l = new ArrayList<String>(3);
			l.add((String)o);
			l.add(value);
			headers.put(key, l);
		}
	}
}
