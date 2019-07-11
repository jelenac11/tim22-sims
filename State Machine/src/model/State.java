package model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("State")
public class State {

	@XStreamAsAttribute
	private int stateId;
	private String lifecycleName;
	private String displayName;
	private List<Semantic> stateSemantic = new ArrayList<Semantic>();
	@XStreamOmitField
	private List<Transition> transitions = new ArrayList<Transition>();
	private List<Integer> stateTransitions = new ArrayList<Integer>();
	private List<FieldType> stateDenyModifyingFields = new ArrayList<FieldType>();
	private List<FieldType> stateHideFields = new ArrayList<FieldType>();
	private List<FieldType> stateMandatoryFields = new ArrayList<FieldType>();

	public State() {
		stateSemantic = new ArrayList<Semantic>();
		transitions = new ArrayList<Transition>();
		stateTransitions = new ArrayList<Integer>();
		stateDenyModifyingFields = new ArrayList<FieldType>();
		stateHideFields = new ArrayList<FieldType>();
		stateMandatoryFields = new ArrayList<FieldType>();
	}

	public State(int stateId, String lifecycleName, String displayName, List<Semantic> stateSemantic,
			List<Transition> transitions, List<FieldType> stateDenyModifyingFields, List<FieldType> stateHideFields,
			List<FieldType> stateMandatoryFields) {
		this.stateId = stateId;
		this.lifecycleName = lifecycleName;
		this.displayName = displayName;
		this.stateSemantic = stateSemantic;
		this.transitions = transitions;
		this.stateDenyModifyingFields = stateDenyModifyingFields;
		this.stateHideFields = stateHideFields;
		this.stateMandatoryFields = stateMandatoryFields;
	}

	public State(int stateId, String lifecycleName, String displayName, List<Semantic> stateSemantic,
			List<Transition> transitions, List<Integer> stateTransitions, List<FieldType> stateDenyModifyingFields,
			List<FieldType> stateHideFields, List<FieldType> stateMandatoryFields) {
		this.stateId = stateId;
		this.lifecycleName = lifecycleName;
		this.displayName = displayName;
		this.stateSemantic = stateSemantic;
		this.transitions = transitions;
		this.stateTransitions = stateTransitions;
		this.stateDenyModifyingFields = stateDenyModifyingFields;
		this.stateHideFields = stateHideFields;
		this.stateMandatoryFields = stateMandatoryFields;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getLifecycleName() {
		return lifecycleName;
	}

	public void setLifecycleName(String lifecycleName) {
		this.lifecycleName = lifecycleName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<Semantic> getStateSemantic() {
		return stateSemantic;
	}

	public void setStateSemantic(List<Semantic> stateSemantic) {
		this.stateSemantic = stateSemantic;
	}

	public List<Integer> getStateTransitions() {
		return stateTransitions;
	}

	public void setStateTransitions(List<Integer> stateTransitions) {
		this.stateTransitions = stateTransitions;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public List<FieldType> getStateDenyModifyingFields() {
		return stateDenyModifyingFields;
	}

	public void setStateDenyModifyingFields(List<FieldType> stateDenyModifyingFields) {
		this.stateDenyModifyingFields = stateDenyModifyingFields;
	}

	public List<FieldType> getStateHideFields() {
		return stateHideFields;
	}

	public void setStateHideFields(List<FieldType> stateHideFields) {
		this.stateHideFields = stateHideFields;
	}

	public List<FieldType> getStateMandatoryFields() {
		return stateMandatoryFields;
	}

	public void setStateMandatoryFields(List<FieldType> stateMandatoryFields) {
		this.stateMandatoryFields = stateMandatoryFields;
	}

}
