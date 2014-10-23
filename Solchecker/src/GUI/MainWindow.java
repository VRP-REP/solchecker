package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.net.URL;

import javax.swing.*;

/**
 * MainWindow - JFrame extension to display  
 */
public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	private ModeChoicePanel modeChoicePanel;
	private JButton runButton;

	/**
	 * MainWindow - Creation and initialization of MainWindow
	 */
	public MainWindow() {
		super("VRP-REP SolChecker");
		initialize();
	}

	/**
	 * initialize - Initialization of the mainWindow
	 */
	private void initialize() {
		// Initialization of the frame
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 255);
		setVisible(true);
		
		// Change favicon
		URL iconURL = getClass().getResource("favicon.png");
		if (iconURL != null) {
			ImageIcon icon = new ImageIcon(iconURL);
			this.setIconImage(icon.getImage());
		}
		
		// Initialization of the modeChoicePanel
		modeChoicePanel = new ModeChoicePanel();
		this.add(modeChoicePanel, BorderLayout.CENTER);

		// Initialization of the runButton
		runButton = new JButton("Run");
		runButton.setBackground(new Color(0, 125, 204));
		runButton.setForeground(new Color(255, 255, 255));
		this.add(runButton, BorderLayout.SOUTH);
	}
}
