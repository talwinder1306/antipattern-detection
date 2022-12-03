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

import java.lang.reflect.Modifier;
import java.util.List;
import padl.cpp.kernel.IGlobalField;
import padl.kernel.Constants;
import padl.kernel.IElement;
import padl.kernel.IElementMarker;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.ModelDeclarationException;
import padl.kernel.impl.FirstClassEntity;
import padl.util.Util;
import util.io.ProxyConsole;

/**
 * @author Ward Flores
 */
// Ward 2004/08/19: Hierarchy level
// Global fields are created at the same level as classes.
// GlobalField has all the properties of Class.
// Implementation to be checked...

class GlobalField extends FirstClassEntity implements IElementMarker,
		IGlobalField {

	private static final long serialVersionUID = 739874953581348926L;

	// Yann 2009/05/01: Duplicate of Element!
	private IElement attachedElement;
	private int cardinality;
	private char[] globalFieldType;

	public GlobalField(final char[] anID) {
		this(anID, null, 1);
	}

	public GlobalField(
		final char[] aID,
		final char[] aName,
		final char[] aType,
		final int aCardinality) {

		super(aID);
		this.setName(aName);
		this.setType(aType);
		this.setCardinality(aCardinality);
	}

	public GlobalField(
		final char[] aName,
		final char[] aType,
		final int aCardinality) {

		super((String.valueOf(aType) + " " + String.valueOf(aName))
			.toCharArray());
		this.setName(aName);
		this.setType(aType);
		this.setCardinality(aCardinality);
	}

	public void addConstituent(final IElement anElement) {
	}

	public void addInheritedEntity(final IFirstClassEntity anEntity) {
	}

	// Yann 2009/05/01: Duplicate of Element!
	public void attachTo(final IElement anElement) {
		if (anElement != null) {
			if (anElement == this) {
				throw new ModelDeclarationException("Cannot attach to itself");
			}
			if (!anElement.getClass().isInstance(this)) {
				throw new ModelDeclarationException(
					"Cannot attach to a different type of element");
			}

			this.detach();
			this.attachedElement = anElement;
		}
	}

	// Yann 2009/05/01: Duplicate of Element!
	public void detach() {
		final IElement oldAttachedElement = this.getAttachedElement();

		if (oldAttachedElement == null) {
			return;
		}

		this.attachedElement = null;
	}

	// Yann 2009/05/01: Duplicate of Element!
	public IElement getAttachedElement() {
		return this.attachedElement;
	}

	public int getCardinality() {
		return this.cardinality;
	}

	public String getDisplayTypeName() {
		return String.valueOf(this.getType());
	}

	public IFirstClassEntity getInheritedEntity(final String anEntityName) {
		return null;
	}

	public String getPurpose() {
		return null;
	}

	public char[] getType() {
		return this.globalFieldType;
	}

	public boolean isAboveInHierarchy(final IFirstClassEntity anEntity) {
		return false;
	}

	public boolean isForceAbstract() {
		return false;
	}

	public List<?> listOfInheritedEntities() {
		return null;
	}

	public List<?> listOfInheritingEntities() {
		return null;
	}

	public void removeInheritedEntity(final IFirstClassEntity anEntity) {
	}

	public void setCardinality(final int cardinality) {
		if (cardinality < 1) {
			throw new ModelDeclarationException(
				"Cardinality must be 1 or greater");
		}

		this.cardinality = cardinality;
	}

	public void setPurpose(final String purpose) {
	}

	public void setType(final char[] globalFieldType) {
		this.globalFieldType = globalFieldType;
	}

	public void setVisibility(final int visibility) {
		super.setVisibility(visibility & ~Modifier.ABSTRACT);
	}

	public String toString() {
		if (Constants.DEBUG) {
			ProxyConsole
				.getInstance()
				.debugOutput()
				.println("// GlobalField.toString()");
		}
		return this.toString(0);
	}

	public String toString(final int tab) {
		if (Constants.DEBUG) {
			ProxyConsole
				.getInstance()
				.debugOutput()
				.println("// GlobalField.toString(int)");
		}
		final StringBuffer codeEq = new StringBuffer();
		codeEq.append(super.toString(tab));
		codeEq.append(' ');
		codeEq.append(this.getType());
		codeEq.append(' ');
		codeEq.append(this.getName());
		final String[] codeLines = this.getCodeLines();
		// Yann: Can be null. The case "empty array" is dealt with in the loop. 
		if (codeLines != null) {
			codeEq.append(" = ");
			for (int i = 0; i < codeLines.length; i++) {
				codeEq.append('\n');
				Util.addTabs(tab + 1, codeEq);
				codeEq.append(codeLines[i]);
			}
		}
		codeEq.append(';');
		return codeEq.toString();
	}
}
