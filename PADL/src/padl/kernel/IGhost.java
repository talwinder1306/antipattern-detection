/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-GaÃƒÂ«l GuÃƒÂ©hÃƒÂ©neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-GaÃƒÂ«l GuÃƒÂ©hÃƒÂ©neuc and others, see in file; API and its implementation
 ******************************************************************************/
package padl.kernel;

/**
 * @author Yann-GaÃƒÂ«l GuÃƒÂ©hÃƒÂ©neuc
 */
// TODO: Should be renamed IEntityGhost for consistency with IMembreEntityGhost and IPackageGhost. 
public interface IGhost extends IFirstClassEntity, IInterfaceActor,
		IInterfaceImplementer {

	String LOGO = "\"G\"";
}
