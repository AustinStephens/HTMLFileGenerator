// This program takes input from a user an creates a new html/php file based on what they need.
// Uses Swing. Opens the new file in Notepad.
 
import javax.swing.*;  
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;    

public class webpage{
	public static boolean exssCheck;   //external style sheet check
	public static boolean emssCheck;   //embedded style sheet check
	public static boolean exjsCheck;   //external javascript check
	public static boolean stjsCheck;   //script tag javascript check
	public static boolean phpCheck;    //php file check
	static String lead;                //file name without extension
	static String exssText;            //external stylesheet file name w/o extension
	static String exjsText;            //external javascript file name w/o extension
	
	public static void createAndShowGUI()
	{
		/*Create and set up the window.
		Create the frame.
		Set the layout to Spring Layout.*/
		JFrame frame = new JFrame("Web Page Creator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SpringLayout layout = new SpringLayout();
		frame.setLayout(layout);
		
		//setting up the frame
		frame.setPreferredSize(new Dimension(500, 450));
		
		//setting up elements
		JLabel label1 = new JLabel("Pick what your want your html template to include. DO NOT USE FILE EXTENSIONS!");
		frame.add(label1);
		
		JCheckBox exss = new JCheckBox("External Style Sheet");
		exss.setEnabled(true);
		frame.add(exss);
		
		JCheckBox emss = new JCheckBox("Embedded Style Sheet");
		emss.setEnabled(true);
		frame.add(emss);
		
		JCheckBox exjs = new JCheckBox("External Javascript file");
		exjs.setEnabled(true);
		frame.add(exjs);
		
		JCheckBox stjs = new JCheckBox("Script tags in head");
		stjs.setEnabled(true);
		frame.add(stjs);
		
		JCheckBox php = new JCheckBox("PHP file");
		php.setEnabled(true);
		frame.add(php);
		
		JTextField ssText = new JTextField(20);
		ssText.setEnabled(false);
		frame.add(ssText);
		
		JTextField jsText = new JTextField(20);
		jsText.setEnabled(false);
		frame.add(jsText);
		
		JTextField fileName = new JTextField(20);
		fileName.setEnabled(true);
		frame.add(fileName);
		
		JButton button1 = new JButton("Create File");
		button1.setEnabled(true);
		frame.add(button1);
		
		//listens for the checkboxes to be licked to enabled the textfields below them
		exss.addItemListener(new ItemListener() 
		{
         public void itemStateChanged(ItemEvent e) 
		 {             
            if (e.getStateChange()==1)
			{
				ssText.setEnabled(true);

			}
			else 
			{
				ssText.setEnabled(false);
				ssText.setText("");
			}
         }           
		});
		
		exjs.addItemListener(new ItemListener() 
		{
         public void itemStateChanged(ItemEvent e) 
		 {             
            if (e.getStateChange()==1)
			{
				jsText.setEnabled(true);
			}
			else
			{
				jsText.setEnabled(false);
				jsText.setText("");
			}
         }           
		});
		
		// add the listener to the button to create webpage
			button1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					exssCheck = exss.isSelected();
					emssCheck = emss.isSelected();
					exjsCheck = exjs.isSelected();
					stjsCheck = stjs.isSelected();
					phpCheck = php.isSelected();
					lead = fileName.getText();
					exssText = ssText.getText();
					exjsText = jsText.getText();
					createPage();
				}
			});	
			
		//making constraints for label1
		layout.putConstraint(SpringLayout.WEST, label1, 10, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, label1, 20, SpringLayout.NORTH, frame);
		
		//making constraints for exss
		layout.putConstraint(SpringLayout.WEST, exss, 60, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, exss, 30, SpringLayout.SOUTH, label1);
		
		//making constraints for sstext
		layout.putConstraint(SpringLayout.WEST, ssText, 80, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, ssText, 10, SpringLayout.SOUTH, exss);
		
		//making constraints for emss
		layout.putConstraint(SpringLayout.WEST, emss, 60, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, emss, 10, SpringLayout.SOUTH, ssText);
		
		//making constraints for exjs
		layout.putConstraint(SpringLayout.WEST, exjs, 60, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, exjs, 10, SpringLayout.SOUTH, emss);
		
		//making constraints for jstext
		layout.putConstraint(SpringLayout.WEST, jsText, 80, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, jsText, 10, SpringLayout.SOUTH, exjs);
		
		//making constraints for stjs
		layout.putConstraint(SpringLayout.WEST, stjs, 60, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, stjs, 10, SpringLayout.SOUTH, jsText);
		
		//making constraints for php
		layout.putConstraint(SpringLayout.WEST, php, 60, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, php, 10, SpringLayout.SOUTH, stjs);
		
		//making constraints for fileName
		layout.putConstraint(SpringLayout.WEST, fileName, 120, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, fileName, 20, SpringLayout.SOUTH, php);
		
		//making constraints for button1
		layout.putConstraint(SpringLayout.WEST, button1, 165, SpringLayout.WEST, frame);
		layout.putConstraint(SpringLayout.NORTH, button1, 10, SpringLayout.SOUTH, fileName);



		//Display the window.
        frame.pack();
        frame.setVisible(true);	
	}
	
	public static void createPage()
	{
		String extension = ".htm";
		String file;
		
		Runtime rs = Runtime.getRuntime();
		
		new File("C:\\HTML").mkdirs();  // makes a folder to put all of the files in the C drive
		
		if (phpCheck)
		{
			extension = ".php";
		}
		
		file = lead + extension;
	
		try {
			//fill out file based on selections
			File f = new File("C:\\HTML\\" + file);
			OutputStream fos = new FileOutputStream(f);
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			
			// Writing out and formatting the html/php file
			out.write("<!DOCTYPE>");
			out.newLine();            // I could use /n but it looks horrible all put together and it doesn't work everywhere
			out.write("<html>");
			out.newLine();
			out.write("<head>");
			out.newLine();
			out.write("\t<meta charset=\"utf-8\">");
			out.newLine();
			out.write("\t<title></title>");
			out.newLine();
			//out.write(openPage.getBytes());
	
			// Selecting the external files will not create them, just will write them as the source file.
			if (exssCheck)
			{
				out.write("\t<link rel=\"stylesheet\" href=\"" + exssText + ".css\">");
				out.newLine();
			}
			if (emssCheck)
			{
				out.write("\t<style>");
				out.newLine();
				out.write("\t</style>");
				out.newLine();
			}
			if (exjsCheck)
			{
				out.write("\t<script src=\"" + exjsText + ".js\">");
				out.newLine();
				out.write("\t</script>");
				out.newLine();
			}
			if (stjsCheck)
			{
				out.write("\t<script>");
				out.newLine();
				out.write("\t</script>");
				out.newLine();
			}
			
			out.write("</head>");
			out.newLine();
			out.write("<body>");
			out.newLine();
			out.write("</body>");
			out.newLine();
			out.write("</html>");
			out.close();
			
			
			
			// open document in notepad++, might be a different location for a different computer.
			rs.exec("C:\\Program Files (x86)\\Notepad++\\notepad++.exe C:\\HTML\\" + file);
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
		
	}
	
	
	public static void main(String[] args) {
       		 //creating and showing this application's GUI.
       		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
          			 public void run() {
               		 		createAndShowGUI();
            			}
       		 });
    	}
}