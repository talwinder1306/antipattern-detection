/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
package sad.codesmell.detection.repository.LavaFlow;
   
/**
 * This class represents the detection of the code smell NotClassGlobalVariable
 * 
 * @author Auto generated
 *
 */

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IEntity;
import padl.kernel.IField;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.property.impl.FieldProperty;
import sad.kernel.impl.CodeSmell;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Auto generated
 */

public class UnusedField extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	 
	public String getName() {
		return "UnunsedClass";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		Set classesNotClassGlobalVariable = new HashSet();
		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IEntity entity = (IEntity) iter.next(); 
			if (entity instanceof IClass) {
				final IClass aClass = (IClass) entity;
				final Set setOfVariables = new HashSet();
	   			final Iterator iter2 = aClass.getIteratorOnConstituents(IField.class);
				   while (iter2.hasNext()) {
					   final IField aField = (IField) iter2.next();
					   if (aField.isStatic()  && !(aField.isFinal()) && !(aField.isPrivate())) {
						   setOfVariables.add(new FieldProperty(aField));
					   }
				   }
	   
				   
				boolean presence = true;
	
				if (presence == true) {
					if (!(setOfVariables.isEmpty())) {
						classesNotClassGlobalVariable =
							createCodeSmell(
								classesNotClassGlobalVariable,
								aClass,
								setOfVariables);
					}
				} else {
					if (setOfVariables.isEmpty()) {
						classesNotClassGlobalVariable =
							createCodeSmell(
								classesNotClassGlobalVariable,
								aClass,
								setOfVariables);
					}
				}
			}
		}

		this.setSetOfSmells(classesNotClassGlobalVariable);
	}
	
	private Set createCodeSmell(
		Set classesNotClassGlobalVariable,
		final IClass aClass,
		final Set setOfVariables) {
		try {
			CodeSmell dc =
				new CodeSmell(
					"NotClassGlobalVariable",
					"",
					new ClassProperty(aClass));
			dc.getClassProperty().addProperties(setOfVariables);

			classesNotClassGlobalVariable.add(dc);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return classesNotClassGlobalVariable;
	}
}
