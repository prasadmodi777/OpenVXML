/*--------------------------------------------------------------------------
 * Copyright (c) 2004, 2006-2007 OpenMethods, LLC
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Trip Gilman (OpenMethods), Lonnie G. Pryor (OpenMethods)
 *    - initial API and implementation
 -------------------------------------------------------------------------*/
package org.eclipse.vtp.framework.interactions.core.media;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

public class NumberContent extends FormattableContent
{
	public static final String ELEMENT_NAME = "number-content"; //$NON-NLS-1$

	public NumberContent()
	{
	}

	public NumberContent(Element element)
	{
		super(element);
	}

	public String getContentTypeName()
	{
		return "NUMBER"; //$NON-NLS-1$
	}

	public List format(IFormatter formatter, IMediaProvider mediaProvider)
	{
		List ret = new LinkedList();
		
		try {
			if (getValueType() != VARIABLE_VALUE)
				ret.addAll(formatter.formatNumber(Integer.parseInt(getValue()),
						mediaProvider.getFormatManager().getFormat(this, getFormatName()),
						getFormatOptions(), mediaProvider.getResourceManager()));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	public Element store(Element element)
	{
		Element thisElement = element.getOwnerDocument().createElementNS(
				ELEMENT_NAMESPACE, ELEMENT_NAME);
		element.appendChild(thisElement);
		super.storeBaseInfo(thisElement);
		return thisElement;
	}

	public String getContentType()
	{
		return "org.eclipse.vtp.framework.interactions.core.media.content.number"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.vtp.framework.interactions.core.media.Content#createCopy()
	 */
	public Content createCopy()
	{
		return configureCopy(new NumberContent());
	}

}
