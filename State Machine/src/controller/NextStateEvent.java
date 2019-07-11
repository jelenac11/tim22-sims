package controller;

import model.State;

public class NextStateEvent extends UpdateEvent {

	private State nextState;
	
	public NextStateEvent(Object object, State nextState) {
		super(object);
		this.nextState = nextState;
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

}
