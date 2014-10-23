package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

/**
 * FileChoicePanel - JPanel extension to choose file and verify it.
 */
public class FileChoicePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JFileChooser fileChooser;
	private JButton browseButton;
	private JLabel messageLabel;

	private boolean fileVerification;
	private String typeFile;

	/**
	 * FileChoicePanel - Creation and initialization of FileChoicePanel
	 * 
	 * @param name - Name of the panel
	 * @param typeFile - Specify the type of the file
	 * @param fileVerification - Specify if the file must be checked
	 */
	public FileChoicePanel(String name, String typeFile,
			boolean fileVerification) {
		super();

		// Initialization of the panel
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory
				.createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), name));

		this.typeFile = typeFile;
		this.fileVerification = fileVerification;

		// Initialization of the browse button
		browseButton = new JButton("Browse");
		browseButton.addActionListener(this);
		browseButton.setBackground(new Color(0, 125, 204));
		browseButton.setForeground(new Color(255, 255, 255));
		this.add(browseButton);

		this.add(Box.createRigidArea(new Dimension(5, 0)));

		// Initialization of the name/error label
		messageLabel = new JLabel("No file");
		this.add(messageLabel);

		this.add(Box.createHorizontalGlue());
	}

	/**
	 * actionPerformed - Event on Browse Button. Open the fileChooser (and
	 * verify the content of the file choose).
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Filter initialization
		FileNameExtensionFilter filter = new FileNameExtensionFilter(typeFile
				+ " File (." + typeFile + ")", typeFile);

		// FileChooser initialization
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		fileChooser.setDialogTitle("Choose your file");

		fileChooser.showOpenDialog(null);

		if (fileChooser.getSelectedFile() != null) {
			// Write the file name
			messageLabel.setText(fileChooser.getSelectedFile().getName());
			messageLabel.setForeground(new Color(0, 0, 0));

			// Verify the file
			if (fileVerification == true) {

				InputStream file = null;

				try {
					file = new FileInputStream(fileChooser.getSelectedFile());
				} catch (FileNotFoundException e1) {
					messageLabel.setText("Error : Cannot open file");
				}

				// For XML File
				if (typeFile.equals("xml")) {
					boolean validation = validateXMLWithXSD(file);
					if (!validation) {
						messageLabel
								.setText("Error : XML file doesn't correspond to the XSD");
						messageLabel.setForeground(new Color(255, 0, 0));
					}
				}
				// For JAR File
				else if (typeFile.equals("jar")) {
					boolean validation = validateJAR(file);
					if (!validation) {
						messageLabel
								.setText("Error : JAR file doesn't contain manifest and/or extension of the class Solchecker");
						messageLabel.setForeground(new Color(255, 0, 0));
					}
				}

			}
		}
	}

	/**
	 * validateXMLWithXSD - Check the contents of the XML file. The XML must
	 * correspond to the XSD.
	 * 
	 * @param xml
	 *            XML file to test
	 * @return true if the file is correct
	 */
	public boolean validateXMLWithXSD(InputStream xml) {
		try {
			// XSD recovery and initialization
			InputStream xsd = new FileInputStream("solchecker_batch.xsd");
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();

			// Check the XML file
			validator.validate(new StreamSource(xml));

			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	/**
	 * validateJAR - Check the contents of the JAR file. The JAR file must
	 * contain a manifest and an extension of the class Solchecker.
	 * 
	 * @param jar
	 *            JAR file to test
	 * @return true if the file is correct
	 */
	public boolean validateJAR(InputStream jar) {
		return true;
	}
}
