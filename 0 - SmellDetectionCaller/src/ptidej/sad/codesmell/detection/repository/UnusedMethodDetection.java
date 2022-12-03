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
 * This class represents the detection of the code smell UnusedMethods
 * 
 * @author Auto generated
 *
 */

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IConstituent;
import padl.kernel.IEntity;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.impl.Method;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import pom.primitives.ClassPrimitives;
import pom.primitives.MethodPrimitives;
import ptidej.pom.metrics.repository.UUM;
import ptidej.utils.SmellDetectUtils;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.property.impl.FieldProperty;
import sad.codesmell.property.impl.MetricProperty;
import sad.kernel.impl.CodeSmell;
import util.io.ProxyConsole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Auto generated
 */

public class UnusedMethodDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	public String getName() {
		return "UnunsedMethod";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		final Set methodsAreNotCalled = new HashSet();

		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IFirstClassEntity anEntity = (IFirstClassEntity) iter.next();
			if (anEntity instanceof IClass) {
				ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
				List entityList = classPrimitives.listOfAllMethods(anEntity);
				// System.out.println(classPrimitives.listOfAllMethods(anEntity).size());
				MethodPrimitives methodPrimitives = MethodPrimitives.getInstance();
				// System.out.println("Implemented " +
				// classPrimitives.listOfImplementedFields(anEntity).size());

				final Collection methodsOfClass = classPrimitives.listOfOverriddenAndConcreteMethods(anEntity);
				List ancestors = classPrimitives.listOfAncestors(anEntity);
				//System.out.println("CLass " + anEntity.getDisplayName() + " " + methodsOfClass.size());

				for (Iterator iterMethod1 = methodsOfClass.iterator(); iterMethod1.hasNext();) {
					final IMethod method1 = (IMethod) iterMethod1.next();
					//System.out.println(method1.isPublic() + " " + method1.isStatic() + " " + method1.getDisplayName() + " " + new String(method1.getReturnType()));
					if(method1.isPublic() && method1.isStatic() 
							&& "main".equals(method1.getDisplayName())
							&& "void".equals(new String(method1.getReturnType()))) {
						continue;
					}
					final Iterator iterator = anAbstractLevelModel.getIteratorOnTopLevelEntities();
					int listOfMethodCalls = 0;
					//System.out.println("# method1 " + method1.getDisplayName() + " " + method1.getDisplayPath());
					while (iterator.hasNext()) {
						final IFirstClassEntity otherEntity = (IFirstClassEntity) iterator.next();

						if (!otherEntity.equals(anEntity) && otherEntity instanceof IClass) {
							listOfMethodCalls = methodPrimitives.numberOfUsesByFieldsOrMethods(otherEntity, anEntity);
							listOfMethodCalls += methodPrimitives.listOfSisterMethodCalledByMethod(otherEntity, method1)
									.size();
							if (listOfMethodCalls > 0) {
								//System.out.println("numberOfUsesByFieldsOrMethods " + anEntity.getDisplayName()
								//		+ " other " + otherEntity.getDisplayName() + " Number " + listOfMethodCalls);
								CodeSmell dc = new CodeSmell("ClassHasUnusedMethods", "", new ClassProperty((IClass) anEntity));
								methodsAreNotCalled.add(dc);
							}
						}
					}
				}
			}
		}

		this.setSetOfSmells(methodsAreNotCalled);
	}
}
