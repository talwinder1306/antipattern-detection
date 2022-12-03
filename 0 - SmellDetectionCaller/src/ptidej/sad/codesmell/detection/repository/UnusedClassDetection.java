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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * This class represents the detection of the code smell UnunsedClass
 * 
 * @author Auto generated
 *
 */

import padl.kernel.IAbstractLevelModel;
import padl.kernel.IClass;
import padl.kernel.IEntity;
import padl.kernel.IMethod;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import pom.primitives.ClassPrimitives;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.codesmell.property.impl.ClassProperty;
import sad.kernel.impl.CodeSmell;
import util.io.ProxyConsole;

/**
 * @author Auto generated
 */

public class UnusedClassDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	public String getName() {
		return "UnunsedClass";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		final Set classesIsNotCalled = new HashSet();
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();

		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IEntity entity = (IEntity) iter.next();
			if (entity instanceof IClass) {
				final IClass aClass = (IClass) entity;
				// System.out.println("Class name " + aClass);
				final double CBOin = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("CBOin"))
						.compute(anAbstractLevelModel, aClass);
				// System.out.println("CBOin " + CBOin);
				final double connectivity = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("connectivity"))
						.compute(anAbstractLevelModel, aClass);
				// System.out.println("connectivity " + connectivity);
				final Iterator iter1 = anAbstractLevelModel.getIteratorOnTopLevelEntities();

				if (CBOin == 0.0 && connectivity == 0.0) {
					final Collection methodsOfClass = classPrimitives.listOfOverriddenAndConcreteMethods(aClass);
					// System.out.println("CLass " + anEntity.getDisplayName() + " " +
					// methodsOfClass.size());
					boolean skip = false;
					for (Iterator iterMethod1 = methodsOfClass.iterator(); iterMethod1.hasNext();) {
						final IMethod method1 = (IMethod) iterMethod1.next();
						// System.out.println(method1.isPublic() + " " + method1.isStatic() + " " +
						// method1.getDisplayName() + " " + new String(method1.getReturnType()));
						if (method1.isPublic() && method1.isStatic() && "main".equals(method1.getDisplayName())
								&& "void".equals(new String(method1.getReturnType()))) {
							skip = true;
							continue;
						}
					}
					if (!skip) {
						try {
							CodeSmell dc = new CodeSmell("ClassIsUnused", "", new ClassProperty(aClass));
							classesIsNotCalled.add(dc);
						} catch (final Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace(ProxyConsole.getInstance().errorOutput());
						}
					}
				}
			}
		}

		this.setSetOfSmells(classesIsNotCalled);
	}
}
