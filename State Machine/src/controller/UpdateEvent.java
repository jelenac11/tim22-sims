package controller;

import java.util.EventObject;

public abstract class UpdateEvent extends EventObject {

	public UpdateEvent(Object object) {		
		super(object);
	}
	
}
