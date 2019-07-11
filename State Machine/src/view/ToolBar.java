package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.NextStateEvent;
import controller.UpdateEvent;
import controller.UpdateListener;
import model.Document;
import model.FieldType;
import model.State;
import model.Transition;

public class ToolBar extends JToolBar implements UpdateListener {

	private static final long serialVersionUID = -5487724032759176456L;

	@Override
	public void updatePerformed(UpdateEvent e) {
		if (e instanceof NextStateEvent) {
			removeAll();
			revalidate();
			repaint();

			State nextState = ((NextStateEvent) e).getNextState();
			Document doc = ((Application) getTopLevelAncestor()).getDocument();
			doc.setCurrentState(nextState);
			
			for (Transition tr : nextState.getTransitions()) {
				JButton jb = new JButton(tr.getLifecycleName().split(" ")[2]);
				jb.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent event)
					{
						for (Entry<FieldType, JTextField> pair : ((Application)
								getTopLevelAncestor()).getPanel().getTextFields().entrySet()) {
					    	doc.setFieldValue(pair.getKey(), pair.getValue().getText());
						}

						for (Transition tr : nextState.getTransitions()) {
							if (tr.getLifecycleName().split(" ")[2].equals(event.getActionCommand())) {
								((Application) getTopLevelAncestor()).getController().stateChanged(tr, doc.checkMandatoryFields(nextState));
							}
						}
					}
					
				});
				
				add(jb, BorderLayout.EAST);
			}
			setFloatable(false);
		}
	}
	
}
