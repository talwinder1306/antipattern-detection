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
package ptidej.sad.codesmell.detection.repository;

/**
 * This class represents the detection of the code smell UnunsedField
 * 
 * @author Auto generated
 *
 */

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IEntity;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;
import pom.primitives.ClassPrimitives;
import pom.primitives.MethodPrimitives;
import ptidej.pom.metrics.repository.UUF;
import ptidej.pom.metrics.repository.UUM;
import ptidej.utils.CompositeEntity;
import ptidej.utils.SmellDetectUtils;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.property.impl.FieldProperty;
import sad.kernel.impl.CodeSmell;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Auto generated
 */

public class UnusedFieldDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	public String getName() {
		return "UnunsedField";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		final Set fieldsNotUsed = new HashSet();

		Set<CompositeEntity> allFields = new HashSet();
		Iterator entityIterator = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (entityIterator.hasNext()) {
			final IFirstClassEntity firstClassEntity = (IFirstClassEntity) entityIterator.next();
			// System.out.println("Entity name " + firstClassEntity.getDisplayName());
			if (firstClassEntity instanceof IClass) {
				final Iterator iterator1 = firstClassEntity.getIteratorOnConstituents(IField.class);
				while (iterator1.hasNext()) {
					IField field = (IField) iterator1.next();

					allFields.add(new CompositeEntity(firstClassEntity, field));
					// System.out.println("Field - " + field);
				}
			}
		}
		Iterator entityIterator2 = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
		Set usedFields = new HashSet();
		while (entityIterator2.hasNext()) {
			final IFirstClassEntity anEntity = (IFirstClassEntity) entityIterator2.next();
			if (anEntity instanceof IClass) {
				List entityList = classPrimitives.listOfAllMethods(anEntity);
				// System.out.println(classPrimitives.listOfAllMethods(anEntity).size());
				MethodPrimitives methodPrimitives = MethodPrimitives.getInstance();
				// System.out.println("Implemented " +
				// classPrimitives.listOfImplementedFields(anEntity).size());

				for (int i = 0; i < entityList.size(); i++) {
					final IMethod method = (IMethod) entityList.get(i);
					List<IField> usedFieldsByMethod = methodPrimitives.listOfFieldsUsedByMethod(anEntity, method);
					for (IField field : usedFieldsByMethod) {
						usedFields.add(new CompositeEntity(anEntity, field));
					}
					// System.out.println("listOfFieldsUsedByMethod method "
					// + usedFieldsByMethod.size() + " " + usedFields);
				}
			}
		}

		allFields.removeAll(usedFields);
		//System.out.println("Final set " + allFields);
		
		for(CompositeEntity ce : allFields) {
			CodeSmell dc = new CodeSmell("ClassHasUnusedFields", "", new ClassProperty((IClass) ce.getClassEntity()));
			fieldsNotUsed.add(dc);
		}

		this.setSetOfSmells(fieldsNotUsed);
	}

}
