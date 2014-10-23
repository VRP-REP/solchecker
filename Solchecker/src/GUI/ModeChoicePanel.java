package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * ModeChoicePanel - JPanel extension to choose the resolution mode and display the adapted panel.
 */
public class ModeChoicePanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JPanel buttonsChoicePanel;
	private JRadioButton individualButton;
	private JRadioButton multipleButton;
	
	private JPanel contentChoicePanel;
	private CardLayout cardLayout;
	private IndividualPanel individualPanel = new IndividualPanel();
	private MultiplePanel multiplePanel = new MultiplePanel();
	
	/**
	 * ModeChoicePanel - Creation and initialization of ModeChoicePanel
	 */
	public ModeChoicePanel(){
		// Initialization of the panel
		super();
		this.setLayout(new BorderLayout());
		
		// Initialization of the buttonsChoicePanel which contain two linked choices buttons
		buttonsChoicePanel = new JPanel(new GridLayout(1,3));
		buttonsChoicePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), "Choose your mode"));
		
		ButtonGroup buttonsGroup = new ButtonGroup();
		
		individualButton = new JRadioButton("Single");
		individualButton.setSelected(true);
		individualButton.addActionListener(this);
		buttonsGroup.add(individualButton);
		buttonsChoicePanel.add(individualButton);
		
		multipleButton = new JRadioButton("Batch");
		multipleButton.addActionListener(this);
		buttonsGroup.add(multipleButton);
		buttonsChoicePanel.add(multipleButton);
		
		buttonsChoicePanel.add(new JLabel());
		
		this.add(buttonsChoicePanel, BorderLayout.NORTH);
		
		// Initialization of the contentChoicePanel which contain the adapted content of the selected mode
		cardLayout = new CardLayout();
		contentChoicePanel = new JPanel(cardLayout);
		contentChoicePanel.add(individualPanel, "individualPanel");
		contentChoicePanel.add(multiplePanel, "multiplePanel");
		this.add(contentChoicePanel);
		
		cardLayout.show(contentChoicePanel, "individualPanel");
	}

	/**
	 * actionPerformed - Event on radio button. If individual is check, the individual panel is displayed. Else, the multiple panel is displayed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == individualButton) {
			// Show individualPanel
			cardLayout.show(contentChoicePanel, "individualPanel");
		} else {
			// Show multiplePanel
			cardLayout.show(contentChoicePanel, "multiplePanel");
		}
	}
}
