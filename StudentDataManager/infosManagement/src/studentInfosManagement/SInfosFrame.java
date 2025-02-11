package studentInfosManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class SInfosFrame extends JFrame implements ActionListener{
	
	String ID;
	
	JButton buttonAdd;
	JButton buttonChange;
	JButton buttonDelete;
	JButton buttonSearch;
	
	JButton buttonReturn;
	JButton buttonReturn2;
	JButton buttonReturn3;
	JButton buttonReturn4;
	JButton buttonConfirm;
	JButton buttonConfirm2;
	JButton buttonSubmit;
	JButton buttonUpdate;
	
	JLabel labelAddL1_Base;
	JLabel labelAddL1_Panel;
	JLabel labelAddL1_Name;
	JLabel labelAddL1_DoB;
	JLabel labelAddL1_ID;
	JLabel labelAddL1_Gender;
	JLabel labelListL1_Panel;
	JLabel labelListL1_Base;
	
	JComboBox comboBox;
	
	JTextField textFieldAdd_fName;
	JTextField textFieldAdd_mName;
	JTextField textFieldAdd_lName;
	JTextField textFieldAdd_dateOfBirth;
	JTextField textFieldAdd_ID;
	JTextField textField_IDForDelete;
	JTextField textField_IDForUpdate;
	
	ImageIcon image;
	
	JLayeredPane layeredPane;
	JLayeredPane layeredPaneAdd;
	
	JPanel panel;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	
	MySQLConnector mySQLConnector;
	
	Object[][] data;
	
	JLabel error;
	//function for creating button, return JButton type
	static JButton createButton(JButton button, int px, int py, int dx, int dy, String text, ImageIcon img, SInfosFrame instance) {
		
		button = new JButton();
		button.setBounds(px, py, dx, dy);
		button.addActionListener(instance); // can be simplified to lambda expression as the input type is an 
		//interface, initializing the interface data type directly without implementing it.
		button.setText(text);
		button.setFocusable(false);
		button.setIcon(img);
		button.setFont(new Font("Century Gothic", Font.BOLD, 16));
		button.setIconTextGap(15);
		button.setForeground(new Color(62, 81, 130));
		button.setBackground(new Color(255, 255, 255));
		button.setBorder(BorderFactory.createEtchedBorder());
		return button;
	}
	
	//function for creating textField, return JTextField type
	static JTextField createTextField(JTextField textField, int px, int py, int dx, int dy, String text) {
		textField = new JTextField();
		textField.setLayout(new BorderLayout());
		//textField.setPreferredSize(new Dimension(250, 40));
		textField.setBounds(px, py, dx, dy);
		textField.setFont(new Font("Century Gothic", Font.BOLD, 16));
		textField.setForeground(new Color(62, 81, 130));
		textField.setBackground(new Color(255, 255, 255));
		textField.setCaretColor(Color.BLACK);
		textField.setText(text);
		textField.setBorder(BorderFactory.createEtchedBorder());
		return textField;
	}
	
	//function for creating labels, return JLabel type
	static JLabel createLabel(JLabel label, int px, int py, int dx, int dy, String text, ImageIcon img) {
		label = new JLabel();
		label.setBounds(px, py, dx, dy);
		label.setText(text);
		label.setHorizontalTextPosition(JLabel.CENTER);
		label.setFont(new Font("Century Gothic", Font.BOLD, 18));
		label.setForeground(/* new Color(62, 81, 130) */Color.WHITE);
		return label;
	}
	
	SInfosFrame() {
		
		//MySQLConnector class for MySQL connection
		mySQLConnector = new MySQLConnector();
		
		//Pane for containing and organize panels
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 1000, 1000);
		
		//panel for containing and organize labels, buttons
		panel = new JPanel();
		panel.setBounds(0, 0, 1000, 1000);
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
		
		//images for application
		ImageIcon mainBG_img = new ImageIcon("images\\mainBG3.png");
		image = new ImageIcon("images\\gear.png");
		
		//custom sized images
		ImageIcon plus = new ImageIcon("images\\plus.png");
		Image newPlus = plus.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		plus = new ImageIcon(newPlus);	
		ImageIcon wrench = new ImageIcon(new ImageIcon("images\\wrench.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		ImageIcon bin = new ImageIcon(new ImageIcon("images\\bin.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		ImageIcon lookup = new ImageIcon(new ImageIcon("images\\lookup.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		//label containing BG image for menu
		JLabel label = new JLabel();
		label.setOpaque(true);
		label.setIcon(mainBG_img);
		label.setBounds(0, 0, 1000, 1000);
		
		//menu button
		buttonAdd = createButton(buttonAdd, 380, 375, 250, 150, "Add a student", plus, this);
		buttonSearch = createButton(buttonSearch, 380, 535, 250, 150, "Student list", lookup, this);
		
		//add button and label to panel
		panel.add(buttonAdd);
		panel.add(buttonSearch);
		panel.add(label);
		
		//1st page of Add Students;
		//panel for Add student page
		panel2 = new JPanel();
		panel2.setBounds(0, 0, 1000, 1000);
		panel2.setLayout(new BorderLayout());
		panel2.setOpaque(false);
		
		//BG and panel images for Add student page
		ImageIcon mainBG_img2 = new ImageIcon("images\\mainBG3B.png");
		ImageIcon bGPanel = new ImageIcon(new ImageIcon("images\\BGPanel.png").getImage().getScaledInstance(800, 700, Image.SCALE_DEFAULT));
		
		//simple labels
		labelAddL1_Base = new JLabel();
		labelAddL1_Base.setBounds(0, 0, 1000, 1000);
		labelAddL1_Base.setIcon(mainBG_img2);
		
		//simple labels
		labelAddL1_Panel = new JLabel();
		labelAddL1_Panel.setBounds(125, 150, 800, 700);
		labelAddL1_Panel.setIcon(bGPanel);
		
		//create labels with createLabel function
		labelAddL1_Name = createLabel(labelAddL1_Name, 150, 200, 150, 50, "Student's name:", null);
		labelAddL1_DoB = createLabel(labelAddL1_DoB, 150, 300, 150, 50, "Birthday:", null);
		labelAddL1_ID = createLabel(labelAddL1_ID, 150, 400, 150, 50, "Student's ID:", null);
		labelAddL1_Gender = createLabel(labelAddL1_Gender, 150, 500, 150, 50, "Gender:", null);
		
		//create button with createButton function
		buttonSubmit = createButton(buttonSubmit, 380, 600, 250, 75, "Submit", null, this);
		buttonReturn = createButton(buttonReturn, 380, 695, 250, 75, "Return", null, this);
		
		//create textField with createTextField functon
		textFieldAdd_fName = createTextField(textFieldAdd_fName, 350, 200, 125, 50, "First name");
		textFieldAdd_mName = createTextField(textFieldAdd_mName, 500, 200, 125, 50, "Middle name");
		textFieldAdd_lName = createTextField(textFieldAdd_lName, 650, 200, 125, 50, "First name");
		textFieldAdd_dateOfBirth = createTextField(textFieldAdd_dateOfBirth, 350, 300, 175, 50, null);
		textFieldAdd_ID = createTextField(textFieldAdd_ID, 350, 400, 125, 50, null);
		
		//making combobox
		String[] str = {"Male", "Female"};
		comboBox = new JComboBox(str);
		comboBox.setBounds(350, 500, 150, 50);
		
		//adding components above for panel2 of Add student
		panel2.add(textFieldAdd_fName);
		panel2.add(textFieldAdd_mName);
		panel2.add(textFieldAdd_lName);
		panel2.add(textFieldAdd_dateOfBirth);
		panel2.add(textFieldAdd_ID);
		panel2.add(labelAddL1_Name);
		panel2.add(labelAddL1_DoB);
		panel2.add(labelAddL1_ID);
		panel2.add(labelAddL1_Gender);
		panel2.add(buttonReturn);
		panel2.add(buttonSubmit);
		panel2.add(comboBox);
		panel2.add(labelAddL1_Panel);
		panel2.add(labelAddL1_Base);
		
		//end 1st page of Student Add
		
		//1st page of Student List
		//create panels
		panel3 = new JPanel();
		panel3.setBounds(0, 0, 1000, 1000);
		panel3.setLayout(new BorderLayout());
		panel3.setOpaque(false);
		
		//create labels
		labelListL1_Base = new JLabel();
		labelListL1_Base.setBounds(0, 0, 1000, 1000);
		labelListL1_Base.setIcon(mainBG_img2);
		
		labelListL1_Panel = new JLabel();
		labelListL1_Panel.setBounds(125, 150, 800, 700);
		labelListL1_Panel.setIcon(bGPanel);
		
		//create buttons
		buttonReturn2 = createButton(buttonReturn, 345, 720, 250, 50, "Return", null, this);
		buttonChange = createButton(buttonChange, 220, 660, 250, 50, "Update student data", wrench, this);
		buttonDelete = createButton(buttonDelete, 470, 660, 250, 50, "Delete student data", bin, this);
		
		//heading rows and rows for table data as global within the class for later assignment of action listener function
		String[] head = {"ID", "Fist Name", "Middle Name", "Last Name","Date of Birth", "Gender"};
		data = new Object[40][6];
		
		//create a table
		JTable table = new JTable(data, head);
		table.setBounds(175, 250, 600, 400);
		table.getTableHeader().setBounds(175, 200, 600, 50);
		table.setRowHeight(25);
		table.setFont(new Font("Century Gothic", Font.BOLD, 14));
		table.setForeground(new Color(62, 81, 130));
		table.setBackground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
		table.getTableHeader().setBackground(new Color(62, 81, 130));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		
		//create a scroll function for table
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(175, 250, 600, 400);
		
		//add components above to panel3
		panel3.add(scrollPane);
		panel3.add(buttonReturn2);
		panel3.add(buttonChange);
		panel3.add(buttonDelete);
		panel3.add(labelListL1_Panel);
		panel3.add(labelListL1_Base);
		
		//end 1st page of StudentList
		
		//2nd page of StudentList, triggered by Delete student button
		//create images
		ImageIcon bGPanel2 = new ImageIcon(new ImageIcon("images\\BGPanelAsk.png").getImage().getScaledInstance(1000, 1000, Image.SCALE_DEFAULT));
		
		//create panels
		panel4 = new JPanel();
		panel4.setBounds(0, 0, 1000, 1000);
		panel4.setLayout(new BorderLayout());
		panel4.setOpaque(false);
		
		//create labels
		JLabel labelDeleteL1_Base = new JLabel();
		labelDeleteL1_Base.setBounds(0, 0, 1000, 1000);
		labelDeleteL1_Base.setIcon(bGPanel2);
		JLabel AskForID = new JLabel(); 
		AskForID = createLabel(AskForID, 380, 350, 300, 50, "Enter Student's ID", null);
		
		
		//create buttons
		buttonReturn3 = createButton(buttonReturn3, 200, 500, 250, 50, "Return", null, this);
		buttonConfirm = createButton(buttonConfirm, 460, 500, 250, 50, "Confirm", null, this);
		
		//create textField
		textField_IDForDelete = createTextField(textField_IDForDelete, 390, 400, 125, 50, "Enter student ID");
		
		//add components to panel4
		panel4.add(AskForID);
		panel4.add(textField_IDForDelete);
		panel4.add(buttonReturn3);
		panel4.add(buttonConfirm);
		panel4.add(labelDeleteL1_Base);
		//end 2nd page of StudentList, triggered by Delete student button
		
		//2nd page of StudentList, triggered by Update student button
		panel5 = new JPanel();
		panel5.setBounds(0, 0, 1000, 1000);
		panel5.setLayout(new BorderLayout());
		
		//create labels
		panel5.setOpaque(false);
		JLabel labelUpdateL1_Base = new JLabel();
		labelUpdateL1_Base.setBounds(0, 0, 1000, 1000);
		labelUpdateL1_Base.setIcon(bGPanel2);
		JLabel AskForID2 = new JLabel(); 
		AskForID2 = createLabel(AskForID2, 380, 350, 300, 50, "Enter Student's ID", null);
		
		//create buttons
		buttonReturn4 = createButton(buttonReturn4, 200, 500, 250, 50, "Return", null, this);
		buttonConfirm2 = createButton(buttonConfirm2, 460, 500, 250, 50, "Confirm", null, this);
		//this button used for 2nd page of updating student (reusing 1st page of adding student)
		buttonUpdate = createButton(buttonUpdate, 380, 600, 250, 75, "Update", null, this);
		
		//create textField
		textField_IDForUpdate = createTextField(textField_IDForUpdate, 390, 400, 125, 50, "Enter student ID");
		
		//add components above to panel5
		panel5.add(AskForID2);
		panel5.add(textField_IDForUpdate);
		panel5.add(buttonReturn4);
		panel5.add(buttonConfirm2);
		panel5.add(labelUpdateL1_Base);
		
		
		
		layeredPane.add(panel, Integer.valueOf(1));
		//layeredPane.add(panel2, Integer.valueOf(2));
		
		//adding layeredPane to JFrame and some configuration
		this.add(layeredPane);
		this.setTitle("Student Data Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setSize(1000, 1000);
		this.setLayout(null);
		this.setVisible(true);
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(Color.green);
		
		//error checking
		error = new JLabel();
		error = createLabel(error, 520, 400, 300, 50, "Possible duplicate entry", null);
		error.setForeground(/* new Color(62, 81, 130) */Color.RED);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//whether to trigger visibility of buttonAdd and buttonSearch when reusing the student Add 1st page
		boolean isDataUpdate = false;
		
		//buttonAdd trigger
		if(e.getSource() == buttonAdd) {
			
			buttonSubmit.setVisible(true);
			buttonAdd.setVisible(false);
			buttonSearch.setVisible(false);
			
			layeredPane.add(panel2, Integer.valueOf(2));
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonReturn trigger
		if(e.getSource() == buttonReturn) {
			
			System.out.println("check");
			layeredPane.remove(error);
			layeredPane.remove(panel2);
			
			if(isDataUpdate == false) {
				buttonAdd.setVisible(true);
				buttonSearch.setVisible(true);
			}
			
			isDataUpdate = false;
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonReturn trigger for 1st page of student list
		if(e.getSource() == buttonReturn2) {
			
			layeredPane.remove(panel3);
			buttonSearch.setVisible(true);
			buttonAdd.setVisible(true);
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonReturn trigger for 2nd page of student list (triggered by delete button)
		if(e.getSource() == buttonReturn3) {
			
			layeredPane.remove(panel4);
			buttonChange.setVisible(true);
			buttonDelete.setVisible(true);
			buttonReturn2.setVisible(true);
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonReturn trigger for 2nd page of student list (triggered by update button)
		if(e.getSource() == buttonReturn4) {
			
			layeredPane.remove(panel5);
			buttonChange.setVisible(true);
			buttonDelete.setVisible(true);
			buttonReturn2.setVisible(true);
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonConfirm trigger for 2nd page of student list (triggered by delete button)
		if(e.getSource() == buttonConfirm) {
			
			
			mySQLConnector.request("DELETE FROM Students WHERE student_id = '" + textField_IDForDelete.getText() + "'", null);
			
			layeredPane.remove(panel4);
			layeredPane.remove(panel3);
			
			buttonSearch.setVisible(true);
			buttonAdd.setVisible(true);
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonConfirm trigger for 2nd page of student list (triggered by update button)
		if(e.getSource() == buttonConfirm2) {
			
			isDataUpdate = true; //set true to prevent modifying visibility of  buttonSearch and Add
		
			buttonSubmit.setVisible(false);
			
			layeredPane.add(buttonUpdate, Integer.valueOf(7));
			
			String[][] strArr = new String[1][6];
			
			mySQLConnector.query(strArr, "SELECT * FROM Students WHERE Student_ID = '" + textField_IDForUpdate.getText() + "'");
			
			if(strArr[0][0] != "") {

				ID = strArr[0][0];
				layeredPane.add(panel2, Integer.valueOf(6));
				textFieldAdd_ID.setText(strArr[0][0]);
				textFieldAdd_fName.setText(strArr[0][1]);
				textFieldAdd_mName.setText(strArr[0][2]);
				textFieldAdd_lName.setText(strArr[0][3]);
				textFieldAdd_dateOfBirth.setText(strArr[0][4]);
			
			}
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		boolean[] isError = {false};
		//buttonSubmit trigger, 1st page of student add
		if(e.getSource() == buttonSubmit) {
			
			
			String gender = (String)comboBox.getSelectedItem();
			mySQLConnector.request("INSERT INTO Students VALUES ('" + textFieldAdd_ID.getText() + 
					"', '" + textFieldAdd_fName.getText() + 
					"', '" + textFieldAdd_mName.getText() + 
					"', '" + textFieldAdd_lName.getText() + 
					"', '" + textFieldAdd_dateOfBirth.getText() + 
					"', '" + gender + 
					"')", isError);
			System.out.println(isError[0]);
			if(isError[0]) {
				layeredPane.add(error, Integer.valueOf(8));
				isError[0] = false;
			}
			
			
		}
		
		//buttonUpdate trigger, 3rd page of student list, 
		if(e.getSource() == buttonUpdate) {
			
			String gender = (String)comboBox.getSelectedItem();
			
			mySQLConnector.request("UPDATE Students SET Student_ID ='" + textFieldAdd_ID.getText() + "'," +
					"Student_firstName = '" + textFieldAdd_fName.getText() + "'," +
					"Student_middleName = '" + textFieldAdd_mName.getText() + "'," +
					"Student_lastName = '" + textFieldAdd_lName.getText() + "'," +
					"Student_dateOfBirth = '" + textFieldAdd_dateOfBirth.getText() + "'," +
					"Student_gender = '" + gender + "' WHERE Student_ID ='" + ID + "'", null);
			
			layeredPane.remove(buttonUpdate);
			layeredPane.remove(panel2);
			layeredPane.remove(panel3);
			layeredPane.remove(panel5);
			buttonAdd.setVisible(true);
			buttonSearch.setVisible(true);
			
			this.invalidate();
			this.validate();
			this.repaint();
			
			
		}
		//buttonChange trigger, 1st page of studentlist
		if(e.getSource() == buttonChange) {
			
			buttonChange.setVisible(false);
			buttonDelete.setVisible(false);
			buttonReturn2.setVisible(false);
			
			layeredPane.add(panel5, Integer.valueOf(4));
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonSearch trigger, menu
		if(e.getSource() == buttonSearch) {
			buttonSearch.setVisible(false);
			buttonAdd.setVisible(false);
			buttonChange.setVisible(true);
			buttonDelete.setVisible(true);
			buttonReturn2.setVisible(true);
			
			layeredPane.add(panel3, Integer.valueOf(3));
			mySQLConnector.query(data, "SELECT * FROM Students ORDER BY Student_ID");
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
		//buttonDelete 1st page of student list
		if(e.getSource() == buttonDelete) {
			buttonChange.setVisible(false);
			buttonDelete.setVisible(false);
			buttonReturn2.setVisible(false);
			
			layeredPane.add(panel4, Integer.valueOf(4));
			
			this.invalidate();
			this.validate();
			this.repaint();
		}
		
	}

}
