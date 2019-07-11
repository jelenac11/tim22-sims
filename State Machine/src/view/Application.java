package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import controller.ChooseFileEvent;
import controller.Controller;
import controller.InvalidFileEvent;
import controller.UpdateEvent;
import controller.UpdateListener;
import controller.WrongFileEvent;
import model.Data;
import model.Document;
import model.Field;
import model.FieldType;
import model.Semantic;
import model.State;
import model.Transition;

public class Application extends JFrame implements UpdateListener {

	private static final long serialVersionUID = 8087603199136216708L;
	private Document document;
	private ArrayList<State> states;
	private ArrayList<Transition> transitions;
	private JMenuBar menuBar;
	private DocPanel panel;
	private ToolBar toolBar;
	private Controller controller;
	
	private static Application instance = null;

	public static Application getInstance() {
		if (instance == null) {
			instance = new Application();
		}
		return instance;
	}

	private Application() {

		panel = new DocPanel();
		toolBar = new ToolBar();
		add(panel, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
		pack();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		Dimension windowSize = new Dimension(640, 480);

		setSize(windowSize);
		setLocation((screenWidth - windowSize.width) / 2, (screenHeight - windowSize.height) / 2);
		
		createMenuBar();
		
		setStates(new ArrayList<State>());
		setTransitions(new ArrayList<Transition>());
		
		controller = new Controller();
		
		controller.addListener(this);
		controller.addListener(panel);
		controller.addListener(toolBar);
		
		setTitle("State Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}
	
	private void createMenuBar() {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		newMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.openingNewDocument();
			}
			
		});
		
		menuBar.setPreferredSize(new Dimension(200, 40));
		setJMenuBar(menuBar);
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	public Controller getController() {
		return controller;
	}

	public DocPanel getPanel() {
		return panel;
	}

	public void setPanel(DocPanel panel) {
		this.panel = panel;
	}

	public ToolBar getTb() {
		return toolBar;
	}

	public void setTb(ToolBar toolBar) {
		this.toolBar = toolBar;
	}
	
	private String readFile(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		try {
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}
			return stringBuilder.toString();
		} finally {
			reader.close();
		}
	}

	@Override
	public void updatePerformed(UpdateEvent e) {
		if (e instanceof ChooseFileEvent) {
			
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.addChoosableFileFilter(new FileFilter() {
	
				@Override
				public String getDescription() {
					return "XML Documents (*.xml)";
				}
	
				@Override
				public boolean accept(File f) {
					if (f.isDirectory()) {
						return true;
					} else {
						return f.getName().toLowerCase().endsWith(".xml");
					}
				}
			});
	
			fileChooser.setAcceptAllFileFilterUsed(false);
			int result = fileChooser.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					
					String xml = readFile(fileChooser.getSelectedFile().getAbsolutePath());
					XStream xstream = new XStream(new StaxDriver());
					XStream.setupDefaultSecurity(xstream);
					Class<?>[] classes = new Class[] { Data.class, Transition.class, State.class, Field.class };
					xstream.allowTypes(classes);

					xstream.processAnnotations(Transition.class);
					xstream.processAnnotations(State.class);
					xstream.processAnnotations(FieldType.class);
					xstream.processAnnotations(Data.class);
					xstream.processAnnotations(Semantic.class);
					
					Data dataFromXml = new Data();
					
					try {
						dataFromXml = (Data) xstream.fromXML(xml);
					} catch (Exception ex) {
						controller.wrongFile();
						return;
					}

					int numberOfInitStates = 0;
					int numberOfFinalStates = 0;
					for (State state : dataFromXml.getStates()) {
						for (Semantic semantic : state.getStateSemantic()) {
							if (semantic == Semantic.Init) {
								numberOfInitStates++;
							} else if (semantic == Semantic.Final) {
								numberOfFinalStates++;
							}
						}
					}
					if (numberOfInitStates == 1 && numberOfFinalStates == 1) {
						dataFromXml.setConnections();
						this.document = dataFromXml.getDocument();
						this.states = (ArrayList<State>) dataFromXml.getStates();
						this.transitions = (ArrayList<Transition>) dataFromXml.getTransitions();
						for (State state : this.states) {
							for (Semantic sem : state.getStateSemantic()) {
								if (sem == Semantic.Init) {
									controller.validFile(state);
									return;
								}
							}
						}
					} else {
						controller.invalidXML();
					}
				} catch (IOException ex) {
					controller.wrongFile();
				}
			} else if (result == JFileChooser.CANCEL_OPTION) {
				System.exit(0);
			}
		} else if (e instanceof WrongFileEvent) {
			JOptionPane.showMessageDialog(this, "Wrong file!");
		} else if (e instanceof InvalidFileEvent) {
			JOptionPane.showMessageDialog(Application.getInstance(), "File not valid! There is no init or final"
					+ "\nstates (or there is more than one).");
		}
	}

	public static void main(String[] args) {
		Application app = getInstance();
		app.getController().start();
	}

}
