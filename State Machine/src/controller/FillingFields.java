package controller;

import model.Semantic;
import model.State;
import model.Transition;

public class FillingFields extends AppState {

	public FillingFields(Controller context) {
		super(context);
	}

	@Override
	public void entry() {
		context.refreshForm();
	}

	@Override
	public void wrongFile() {

	}

	@Override
	public void invalidXML() {
		
	}

	@Override
	public void validFile(State initState) {

	}

	@Override
	public void stateChanged(Transition transition, boolean mandatoryFieldsFilled) {
		if (mandatoryFieldsFilled) {
			context.setCurrState(transition.getStateTo());
			context.changeAppState(this);
		} else {
			context.setCurrState(transition.getStateFrom());
			context.changeAppState(this);
		}
	}

	@Override
	public void openingNewDocument() {
		context.changeAppState(new ChoosingXMLFile(context));
	}

}
