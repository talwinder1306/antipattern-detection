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
package padl.cpp.kernel.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import padl.cpp.kernel.ICPPClass;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IOperation;
import padl.kernel.impl.Class;

public class CPPClass extends Class implements ICPPClass {
	private static final long serialVersionUID = -2096273699564970518L;

	private List<IOperation> friendFunctionList = new ArrayList<IOperation>();
	private List<IFirstClassEntity> friendClassList =
		new ArrayList<IFirstClassEntity>();

	public CPPClass(final char[] anID, final char[] aName) {
		super(anID, aName);
	}
	public void addFriendClass(final IFirstClassEntity aClass) {
		if (aClass != null) {
			this.friendClassList.add(aClass);
		}
	}
	public void addFriendFunction(final IOperation aFunction) {
		if (aFunction != null) {
			this.friendFunctionList.add(aFunction);
		}
	}
	public String toString() {
		final StringBuffer codeEq = new StringBuffer();
		codeEq.append(super.toString());

		codeEq.append("\n\tFriends with functions:");
		final Iterator<IOperation> iteratorOnFriendFunctions =
			this.friendFunctionList.iterator();
		while (iteratorOnFriendFunctions.hasNext()) {
			final IOperation iOperation = iteratorOnFriendFunctions.next();
			codeEq.append('\t');
			codeEq.append(iOperation.getName());
			codeEq.append('\n');
		}

		codeEq.append("\n\tFriends with first-class entities:");
		final Iterator<IFirstClassEntity> iteratorOnFriendClasses =
			this.friendClassList.iterator();
		while (iteratorOnFriendClasses.hasNext()) {
			final IFirstClassEntity iOperation = iteratorOnFriendClasses.next();
			codeEq.append("\t\t");
			codeEq.append(iOperation.getName());
			codeEq.append('\n');
		}

		return codeEq.toString();
	}
}
