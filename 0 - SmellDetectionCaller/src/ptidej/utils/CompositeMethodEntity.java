package ptidej.utils;


import java.util.Objects;

import padl.kernel.IMethod;
import padl.kernel.IFirstClassEntity;

public class CompositeMethodEntity {
	
	private IFirstClassEntity classEntity;
	private IMethod methodEntity;
	
	public CompositeMethodEntity(IFirstClassEntity classEntity, IMethod methodEntity) {
		super();
		this.classEntity = classEntity;
		this.methodEntity = methodEntity;
	}

	public IFirstClassEntity getClassEntity() {
		return classEntity;
	}

	public IMethod getMethodEntity() {
		return methodEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classEntity, methodEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeMethodEntity other = (CompositeMethodEntity) obj;
		return Objects.equals(classEntity, other.classEntity) && Objects.equals(methodEntity, other.methodEntity);
	}

	@Override
	public String toString() {
		return "CompositeEntity [classEntity=" + classEntity + ", methodEntity=" + methodEntity + "]";
	}

}
