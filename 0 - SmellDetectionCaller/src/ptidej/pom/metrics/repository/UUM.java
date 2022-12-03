package ptidej.pom.metrics.repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import padl.kernel.IAbstractModel;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;
import pom.primitives.ClassPrimitives;
import pom.primitives.MethodPrimitives;
import ptidej.utils.SmellDetectUtils;

public class UUM {

	public String getDefinition() {
		final String def = "Ununsed fields in an entity";
		return def;
	}

	public double concretelyCompute(IAbstractModel anAbstractModel, IFirstClassEntity anEntity) {
		//Skip checking usage for Java system classes
		if(SmellDetectUtils.isJavaClass(anEntity)) {
			return 0;
		}
		
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
		List entityList = classPrimitives.listOfAllMethods(anEntity);
		// System.out.println(classPrimitives.listOfAllMethods(anEntity).size());
		MethodPrimitives methodPrimitives = MethodPrimitives.getInstance();
		// System.out.println("Implemented " +
		// classPrimitives.listOfImplementedFields(anEntity).size());

		int result = 0;
		for (int i = 0; i < entityList.size(); i++) {
			final IMethod method = (IMethod) entityList.get(i);
			final Iterator iterator = anAbstractModel.getIteratorOnTopLevelEntities();
			while (iterator.hasNext()) {
				final IFirstClassEntity otherEntity = (IFirstClassEntity) iterator.next();
				// System.out.println("method " + method.getDisplayName()
				// + " otherEntity " + otherEntity.getDisplayName());
				if (!otherEntity.equals(anEntity)) {
					List listOfMethodCalls = methodPrimitives.listOfSisterMethodCalledByMethod(otherEntity, method);
					// System.out.println("listOfSisterMethodCalledByMethod method " +
					// method.getDisplayName() +
					// " Number " + listOfMethodCalls.size());
				}
			}
		}

		return 0;
	}

}
