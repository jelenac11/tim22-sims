package model;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Data")
public class Data {
	
	@XStreamAlias("Transitions")
	private List<Transition> transitions;
	@XStreamAlias("States")
	private List<State> states;
	@XStreamAlias("Document")
	private Document document;
	
	public Data() {
		transitions = new ArrayList<Transition>();
		states = new ArrayList<State>();
	}
	
	public Data(List<Transition> transitions, List<State> states, Document document) {
		this.transitions = transitions;
		this.states = states;
		this.document = document;
	}

	public void setConnections() {
		for (State state : this.states) {
			ArrayList<Transition> trans = new ArrayList<Transition>();
			state.setTransitions(trans);
			if (state.getStateTransitions() == null) {
				state.setStateTransitions(new ArrayList<Integer>());
			}
			for (int tranId : state.getStateTransitions()) {
				for (Transition tran : this.transitions) {
					if (tran.getTransitionId() == tranId) {
						state.getTransitions().add(tran);
						break;
					}
				}
			}
		}
		
		for (Transition tran : this.transitions) {
			for (State state : this.states) {
				if (state.getStateId() == tran.getTransitionOnSucceed()) {
					tran.setStateTo(state);
				}
				if (state.getStateId() == tran.getTransitionOnFail()) {
					tran.setStateFrom(state);
				}
			}
		}
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}

	public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		return "Data [transitions=" + transitions + ", states=" + states + "]";
	}
}
