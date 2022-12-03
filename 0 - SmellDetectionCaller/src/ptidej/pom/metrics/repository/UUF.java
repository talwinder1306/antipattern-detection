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

public class UUF {

	public String getDefinition() {
		final String def = "Ununsed fields in an entity";
		return def;
	}

	public double concretelyCompute(IAbstractModel anAbstractModel) {
		Set<IField> allFields = new HashSet();
		Iterator entityIterator = anAbstractModel.getIteratorOnTopLevelEntities();
		while (entityIterator.hasNext()) {
			final IFirstClassEntity firstClassEntity =
					(IFirstClassEntity) entityIterator.next();
			//System.out.println("Entity name " + firstClassEntity.getDisplayName());
			//Skip checking usage for Java system classes
			if(SmellDetectUtils.isJavaClass(firstClassEntity)) {
				continue;
			}
			final Iterator iterator1 = firstClassEntity.getIteratorOnConstituents(IField.class);
			while (iterator1.hasNext()) {
				IField field = (IField) iterator1.next();
				allFields.add(field);
				//System.out.println("Field - " + field);
			}
		}
		Iterator entityIterator2 = anAbstractModel.getIteratorOnTopLevelEntities();
		ClassPrimitives classPrimitives = ClassPrimitives.getInstance();
		Set usedFields = new HashSet();
		while (entityIterator2.hasNext()) {
			final IFirstClassEntity anEntity =
					(IFirstClassEntity) entityIterator2.next();
			List entityList = classPrimitives.listOfAllMethods(anEntity);
			//System.out.println(classPrimitives.listOfAllMethods(anEntity).size());
			MethodPrimitives methodPrimitives = MethodPrimitives.getInstance();
			//System.out.println("Implemented " + classPrimitives.listOfImplementedFields(anEntity).size());
			
			int result = 0;
			for (int i = 0; i < entityList.size(); i++) {
				final IMethod method = (IMethod) entityList.get(i);
				List usedFieldsByMethod = methodPrimitives.listOfFieldsUsedByMethod(anEntity, method);
				usedFields.addAll(usedFieldsByMethod);
				//System.out.println("listOfFieldsUsedByMethod method "
				//		+ usedFieldsByMethod.size() + " " + usedFields);
			}
		}
		
		allFields.removeAll(usedFields);
		System.out.println("Final set " + allFields);
		

		return 0;
	}

}
