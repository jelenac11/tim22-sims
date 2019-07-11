package model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Field")
public class Field {
	
	private FieldType type;
	private String fieldName;
	private String value;
	
	public Field() {
		
	}

	public Field(FieldType type, String fieldName, String value) {
		this.type = type;
		this.fieldName = fieldName;
		this.value = value;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Field [type=" + type + ", fieldName=" + fieldName + ", value=" + value + "]";
	}
	
}
