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

import com.ibm.toad.cfparse.ClassFile;
import com.ibm.toad.cfparse.FieldInfo;

/**
 * @version	0.1
 * @author 	Yann-Ga�l Gu�h�neuc
 */
public final class ExtendedFieldInfo {
	private final char[] declaringClassFileName;
	private final char[] fieldName;
	private final char[] fieldType;
	private final int fieldVisibility;

	public ExtendedFieldInfo(
		final ClassFile declaringClassFile,
		final FieldInfo fieldInfo) {

		this.declaringClassFileName =
			declaringClassFile.getName().toCharArray();
		this.fieldName = fieldInfo.getName().toCharArray();
		this.fieldType = fieldInfo.getType().toCharArray();
		this.fieldVisibility = fieldInfo.getAccess();
	}
	public char[] getDeclaringClassName() {
		return this.declaringClassFileName;
	}
	public char[] getName() {
		return this.fieldName;
	}
	public char[] getType() {
		return this.fieldType;
	}
	public int getVisibility() {
		return this.fieldVisibility;
	}
}
