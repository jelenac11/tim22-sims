package controller;

import java.util.ArrayList;
import java.util.List;

import model.State;
import model.Transition;

public class Controller {
	
	private State currState;
	private AppState currAppState;
	
	public Controller() {
		
	}
	
	public void changeAppState(AppState newState) {
		currAppState = newState;
		newState.entry();
	}
	
	public State getCurrState() {
		return currState;
	}

	public void setCurrState(State currState) {
		this.currState = currState;
	}

	public void wrongFile() {
		currAppState.wrongFile();
	}
	
	public void invalidXML() {
		currAppState.invalidXML();
	}
	
	public void validFile(State initState) {
		currAppState.validFile(initState);
	}
	
	public void stateChanged(Transition transition, boolean mandatoryFieldsFilled) {
		currAppState.stateChanged(transition, mandatoryFieldsFilled);
	}
	
	public void openingNewDocument() {
		currAppState.openingNewDocument();
	}
	
	public void start() {
		currAppState = new ChoosingXMLFile(this);
		currAppState.entry();
	}

	public void notifyAboutWrongFile() {
		WrongFileEvent e = new WrongFileEvent(this);
		
		for (UpdateListener updateListener : listeners) {
			updateListener.updatePerformed(e);
		}
	}

	public void notifyAboutInvalidFile() {
		InvalidFileEvent e = new InvalidFileEvent(this);
		
		for (UpdateListener updateListener : listeners) {
			updateListener.updatePerformed(e);
		}
	}

	public void chooseFile() {
		ChooseFileEvent e = new ChooseFileEvent(this);
		for (UpdateListener updateListener : listeners) {
			updateListener.updatePerformed(e);
		}
	}

	public void refreshForm() {
		NextStateEvent e = new NextStateEvent(this, currState);
		
		for (UpdateListener updateListener : listeners) {
			updateListener.updatePerformed(e);
		}
	}
	
	private List<UpdateListener> listeners = new ArrayList<UpdateListener>();
	
	public void addListener(UpdateListener listener) {
		listeners.add(listener);
	}
		
	public void removeListener(UpdateListener listener) {
		listeners.remove(listener);
	}
	
}
