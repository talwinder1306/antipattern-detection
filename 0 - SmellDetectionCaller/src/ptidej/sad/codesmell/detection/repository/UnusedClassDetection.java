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
 * This class represents the detection of the code smell UnunsedClass
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
import pom.metrics.IBinaryMetric;
import pom.metrics.IUnaryMetric;
import pom.metrics.MetricsRepository;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.AbstractCodeSmellDetection;
import sad.codesmell.property.impl.ClassProperty;
import sad.codesmell.property.impl.FieldProperty;
import sad.codesmell.property.impl.MetricProperty;
import sad.kernel.impl.CodeSmell;
import util.io.ProxyConsole;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.cdt.core.model.IBinary;
import org.eclipse.cdt.core.model.IMethod;

/**
 * @author Auto generated
 */

public class UnusedClassDetection extends AbstractCodeSmellDetection implements ICodeSmellDetection {

	public String getName() {
		return "UnunsedClass";
	}

	public void detect(final IAbstractLevelModel anAbstractLevelModel) {
		final Set classesIsNotCalled = new HashSet();

		final Iterator iter = anAbstractLevelModel.getIteratorOnTopLevelEntities();
		while (iter.hasNext()) {
			final IEntity entity = (IEntity) iter.next();
			if (entity instanceof IClass) {
				final IClass aClass = (IClass) entity;
				//System.out.println("Class name " + aClass);
				final double CBOin = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("CBOin"))
						.compute(anAbstractLevelModel, aClass);
				//System.out.println("CBOin " + CBOin);
				final double connectivity = ((IUnaryMetric) MetricsRepository.getInstance().getMetric("connectivity"))
						.compute(anAbstractLevelModel, aClass);
				//System.out.println("connectivity " + connectivity);
				final Iterator iter1 = anAbstractLevelModel.getIteratorOnTopLevelEntities();

				if (CBOin == 0.0 && connectivity == 0.0) {
					final String mainClass = "main";
					final IConstituent methodConstituent = aClass.getConstituentFromName(mainClass);
					if (methodConstituent != null 
							&& methodConstituent.isPublic() 
							&& methodConstituent.isStatic()) {
						continue;
					}
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

		this.setSetOfSmells(classesIsNotCalled);
	}
}
