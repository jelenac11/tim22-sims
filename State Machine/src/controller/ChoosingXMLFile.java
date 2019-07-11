package controller;

import model.State;
import model.Transition;

public class ChoosingXMLFile extends AppState {

	public ChoosingXMLFile(Controller context) {
		super(context);
	}

	@Override
	public void entry() {
		context.chooseFile();
	}

	@Override
	public void wrongFile() {
		context.notifyAboutWrongFile();
		context.changeAppState(this);
	}

	@Override
	public void invalidXML() {
		context.notifyAboutInvalidFile();
		context.changeAppState(this);
	}

	@Override
	public void validFile(State initState) {
		context.setCurrState(initState);
		context.changeAppState(new FillingFields(context));
	}

	@Override
	public void stateChanged(Transition transition, boolean mandatoryFieldsFilled) {
		
	}

	@Override
	public void openingNewDocument() {
		
	}

}
