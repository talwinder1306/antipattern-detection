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
import padl.kernel.IConstructor;
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
import ptidej.utils.CompositeMethodEntity;
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @author Auto generated
 */

public class UnusedMethodDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	public String getName() {
		return "UnunsedMethod";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
		MethodPrimitives methodPrimitives = MethodPrimitives.getInstance();

		final Set methodsAreNotCalled = new HashSet();
		final Map<CompositeMethodEntity, Integer> methodCalledFrequency = new HashMap<>();
		final Set allMethodsInProject = getAllMethodsInProject(anAbstractLevelModel);
		initialiseMethodCalledFrequencyForAllMethods(anAbstractLevelModel, methodCalledFrequency);
		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IFirstClassEntity anEntity = (IFirstClassEntity) iter.next();
			if (anEntity instanceof IClass) {
				List methodsOfClass = classPrimitives.listOfAllMethods(anEntity);
				for (Iterator iterMethod1 = methodsOfClass.iterator(); iterMethod1.hasNext();) {
					final IMethod method1 = (IMethod) iterMethod1.next();
					/*
					 * Check if the method is main method it should be marked as used
					 */
					if (method1.isPublic() && method1.isStatic() && "main".equals(method1.getDisplayName())
							&& "void".equals(new String(method1.getReturnType()))) {
						methodCalledFrequency.put(new CompositeMethodEntity(anEntity, method1), 1);
					}

					countMethodInvocations(methodCalledFrequency, allMethodsInProject, method1);
				}
			}
		}

		for (Entry<CompositeMethodEntity, Integer> methodCalledFreqEntry : methodCalledFrequency.entrySet()) {
			//System.out.println("## Freq " + methodCalledFreqEntry.getKey().getClassEntity().getDisplayName() + " method "
			//				+ methodCalledFreqEntry.getKey().getMethodEntity().getDisplayName() + " " + methodCalledFreqEntry.getValue());
			if (methodCalledFreqEntry.getValue() == 0) {
				System.out.println("ClassHasUnusedMethods " + methodCalledFreqEntry.getKey().getClassEntity().getDisplayName() + " method "
						+ methodCalledFreqEntry.getKey().getMethodEntity().getDisplayName() );
				CodeSmell dc = new CodeSmell("ClassHasUnusedMethods", "",
						new ClassProperty((IClass) methodCalledFreqEntry.getKey().getClassEntity()));
				methodsAreNotCalled.add(dc);
			}
		}

		this.setSetOfSmells(methodsAreNotCalled);
	}

	private void countMethodInvocations(final Map<CompositeMethodEntity, Integer> methodCalledFrequency,
			final Set allMethodsInProject, final IMethod method1) {
		final Iterator iteratorOnMethodInvocations = method1.getIteratorOnConstituents(IMethodInvocation.class);
		while (iteratorOnMethodInvocations.hasNext()) {
			final IMethodInvocation mi = (IMethodInvocation) iteratorOnMethodInvocations.next();
			if (mi.getTargetEntity() != null && mi.getCalledMethod() != null
					&& mi.getCalledMethod() instanceof IMethod) {
				System.out.println("  > Method invocation " + mi.getCalledMethod().getDisplayName() + "  "
						+ mi.getTargetEntity().getDisplayName());
				if (allMethodsInProject.contains(mi.getCalledMethod())) {
					updateFrequencyMap(methodCalledFrequency, 
							new CompositeMethodEntity(mi.getTargetEntity(),(IMethod) mi.getCalledMethod()), 1);
				}
			}
		}
	}

	private void updateFrequencyMap(final Map<CompositeMethodEntity, Integer> methodCalledFrequency, 
			final CompositeMethodEntity compositeMethodEntity,
			int initialValue) {
		methodCalledFrequency.merge(compositeMethodEntity, initialValue, (a, b) -> a + b);
	}

	private Set getAllMethodsInProject(final IAbstractLevelModel anAbstractLevelModel) {
		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		Set allMethods = new HashSet();
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
		while (iter.hasNext()) {
			final IFirstClassEntity anEntity = (IFirstClassEntity) iter.next();
			if (anEntity instanceof IClass) {
				List allMethodList = classPrimitives.listOfAllMethods(anEntity);
				allMethods.addAll(allMethodList);
			}
		}

		return allMethods;
	}
	
	private void initialiseMethodCalledFrequencyForAllMethods(
			final IAbstractLevelModel anAbstractLevelModel,
			Map<CompositeMethodEntity, Integer> map) {
		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
		
		while (iter.hasNext()) {
			final IFirstClassEntity anEntity = (IFirstClassEntity) iter.next();
			List<IMethod> overiddenAndConcreteMethodsList = new ArrayList<>();
			if (anEntity instanceof IClass) {
				Collection oAndC = classPrimitives.listOfOverriddenAndConcreteMethods(anEntity);
				overiddenAndConcreteMethodsList.addAll(oAndC);
				for(IMethod method: overiddenAndConcreteMethodsList) {
					map.put(new CompositeMethodEntity(anEntity, method), 0);
				}
				
				List<IFirstClassEntity> listOfAncestors = classPrimitives.listOfAncestors(anEntity);
				for(IFirstClassEntity ancestor: listOfAncestors) {
					if(ancestor instanceof IClass) {
						List<IMethod> ancestorMethods = classPrimitives.listOfAllMethods(ancestor);
						for(IMethod ancestorMethod: ancestorMethods) {
							map.put(new CompositeMethodEntity(ancestor, ancestorMethod), 0);
						}
					}
				}
			}
		}
	}
}
