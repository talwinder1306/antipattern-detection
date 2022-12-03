/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Ga�l Gu�h�neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Ga�l Gu�h�neuc and others, see in file; API and its implementation
 ******************************************************************************/
package padl.creator.classfile.util;

import padl.kernel.Constants;

import com.ibm.toad.cfparse.utils.Access;

/**
 * @author Yann-Ga�l Gu�h�neuc
 * @since  2004/08/01
 */
public final class CadinalityOneAccessorsData implements AccessorsData {
	public int getCardinality() {
		return Constants.CARDINALITY_ONE;
	}
	public boolean matches(
		final char[] targetName,
		final ExtendedFieldInfo fieldInfo) {

		return (
			Access.isPrivate(fieldInfo.getVisibility())
				|| Access.isProtected(fieldInfo.getVisibility()))
			&& fieldInfo.getType().equals(targetName);
	}
}
