package ptidej.utils;


import java.util.Objects;

import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;

public class CompositeFieldEntity {
	
	private IFirstClassEntity classEntity;
	private IField fieldEntity;
	
	public CompositeFieldEntity(IFirstClassEntity classEntity, IField fieldEntity) {
		super();
		this.classEntity = classEntity;
		this.fieldEntity = fieldEntity;
	}

	public IFirstClassEntity getClassEntity() {
		return classEntity;
	}

	public IField getFieldEntity() {
		return fieldEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classEntity, fieldEntity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeFieldEntity other = (CompositeFieldEntity) obj;
		return Objects.equals(classEntity, other.classEntity) && Objects.equals(fieldEntity, other.fieldEntity);
	}

	@Override
	public String toString() {
		return "CompositeEntity [classEntity=" + classEntity + ", fieldEntity=" + fieldEntity + "]";
	}

}
