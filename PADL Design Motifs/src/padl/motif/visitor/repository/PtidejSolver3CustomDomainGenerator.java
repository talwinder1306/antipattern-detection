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
package padl.motif.visitor.repository;

import padl.motif.visitor.IMotifWalker;

public class PtidejSolver3CustomDomainGenerator extends
		PtidejSolver2CustomDomainGenerator implements IMotifWalker {

	protected String getListDeclaration() {
		return "list<Entity>";
	}
	protected String getListOfListPrefix() {
		return "list<list<Entity>>(";
	}
	protected String getListOfListSuffix() {
		return ")";
	}
	protected String getListPrefix() {
		return "list<Entity>(";
	}
	protected String getListSuffix() {
		return ")";
	}
	public String getName() {
		return "PtidejSolver 3 Custom Domain";
	}
}
