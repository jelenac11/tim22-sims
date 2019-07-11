package model;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Document")
public class Document {
	
	@XStreamAlias("Fields")
	private List<Field> fields;
	private State currentState;

	public Document() {
		this.fields = new ArrayList<Field>();
	}
	
	public Document(List<Field> fields) {
		this.fields = fields;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void setFieldValue(FieldType key, String text) {
		for (Field f : this.fields) {
			if (f.getType() == key) {
				f.setValue(text);
				break;
			}
		}
	}

	public boolean checkMandatoryFields(State state) {
		for(FieldType tp : state.getStateMandatoryFields()) {
			for (Field field : this.fields) {
				if (field.getType() == tp) {
					if(field.getValue().equals("")) {
						return false;
					}
					break;
				}
			}
		}
		return true;
	}
	
}
