package controller;

import model.State;
import model.Transition;

public abstract class AppState {
	
	protected Controller context;
	
	public abstract void entry();
	public abstract void wrongFile();
	public abstract void invalidXML();
	public abstract void validFile(State initState);
	public abstract void stateChanged(Transition transition, boolean mandatoryFieldsFilled);
	public abstract void openingNewDocument();

	public AppState(Controller context) {
		this.context = context;
	}

}
