package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.NextStateEvent;
import controller.UpdateEvent;
import controller.UpdateListener;
import model.Document;
import model.Field;
import model.FieldType;
import model.State;
import model.Status;

public class DocPanel extends JPanel implements UpdateListener {
	
	private static final long serialVersionUID = 7049238128802339292L;

	private HashMap<FieldType, JTextField> textFields = new HashMap<FieldType, JTextField>();

	@Override
	public void updatePerformed(UpdateEvent e) {
		if (e instanceof NextStateEvent) {
			removeAll();
			revalidate();
			repaint();
			textFields = new HashMap<FieldType, JTextField>();
			
			boolean redBorder = false;
			
			State nextState = ((NextStateEvent) e).getNextState();
			Document doc = ((Application) getTopLevelAncestor()).getDocument();
			if (doc.getCurrentState() == nextState) {
				redBorder = true;
			}
			doc.setCurrentState(nextState);
			
			setLayout(new GridLayout(doc.getFields().size(), 2));
			for (Field field : doc.getFields()) {
				addLabel(field, findStatus(nextState, field.getType()), nextState);	
			}
			
			if (redBorder) {
				markFields(nextState);
			}
		}
	}
	
	private Status findStatus(State st, FieldType ft) {
		for(FieldType polje : st.getStateDenyModifyingFields()) {
			if (ft == polje) {
				return Status.DENY;
			}
		}
		
		for(FieldType polje : st.getStateHideFields()) {
			if (ft == polje) {
				return Status.HIDE;
			}
		}
		return null;
	}
	
	private void addLabel(Field field, Status st, State currState) {
		JLabel jl = new JLabel(asterisk(field.getType(), currState) + field.getFieldName());
		JTextField userText = new JTextField();
		userText.setText(field.getValue());
		userText.setColumns(2);
		if (st == Status.DENY) {
			userText.setEditable(false);
		} else if (st == Status.HIDE) {
			jl.setVisible(false);
			userText.setVisible(false);
		}
		add(jl);
		add(userText);
		textFields.put(field.getType(), userText);
	}
	
	private void markFields(State st) {
		for(FieldType ft : st.getStateMandatoryFields()) {
			if(textFields.get(ft).getText().equals("")) {
				textFields.get(ft).setBorder(BorderFactory.createLineBorder(Color.red));
			}
		}
	}
	
	private String asterisk(FieldType ft, State currState) {
		String ast = "";
		for(FieldType polje : currState.getStateMandatoryFields()) {
			if (ft == polje) {
				return "*";
			}
		}
		return ast;
	}

	public HashMap<FieldType, JTextField> getTextFields() {
		return textFields;
	}
	
}
