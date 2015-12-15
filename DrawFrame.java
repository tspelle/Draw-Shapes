/*********************************************************************
 * Class: CS111B - Object-Oriented Programming Methodologies in Java
 * Description:	This class creates a frame with border layout, and
 * 				places three panels on it. The center panel draws
 * 				shapes, the top panel has button to select shapes and
 * 				color, and the bottom color display the coordinates
 * 				of the mouse
 * Due Date:	December 9
 * Name:      	Thierry Spelle
 * Name of File:  DrawFrame.java
 * Assignment 10
 **********************************************************************/

import java.awt.event.*;					// import from java libraries
import javax.swing.*;
import java.awt.*;

public class DrawFrame extends JFrame {

  final int WINDOW_WIDTH = 750,     		// width of window in pixels
           WINDOW_HEIGHT = 600;     		// height of window in pixels
  
  JPanel	topPanel,						// create panels 
  			bottomPanel;
  
  DrawPanel drawingPanel;	// create a panel that is an instance of DrawPanel()
  
  JButton	undoButton,						// create buttons for top panel
  			clearButton;
  
  JComboBox	colorBox,						// create combo boxes for
  			shapeBox;						// shapes and colors
  
  JLabel label = new JLabel("(0,0)");		// create label for mouse coordinates
  
  // declare enums for shapes and colors
  private String[] shapes = { "Line", "Oval", "Rectangle"};
  private String[] colors = {"Black", "Blue", "Cyan", "Dark Gray", "Gray",
		  					 "Green", "Light Gray", "Magenta", "Orange",
		  					 "Pink", "Red", "White", "Yellow"}; 
  			
  // create listener objects
  ButtonListener listener = new ButtonListener();
  ShapeComboBoxListener shapeChooser = new ShapeComboBoxListener();
  ColorComboBoxListener colorChooser = new ColorComboBoxListener();  
  
  //************
  // constructor
  //************
  public DrawFrame()
  {
	// set up frame
	super("Shape Drawings");
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
    setLayout(new BorderLayout());

    // specify what happens when the close X is clicked.
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    // call method to build top panel
    buildTopPanel();
        
    // create drawing and bottom panels
    bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout());
 
    drawingPanel = new DrawPanel(label);
    
    // add panels to the frame
    add(topPanel, BorderLayout.NORTH);
    add(drawingPanel, BorderLayout.CENTER);
    bottomPanel.add(label, BorderLayout.WEST);
    add(bottomPanel, BorderLayout.SOUTH);
   }

  //**************************************************
  // create the top panel with buttons and combo boxes
  //**************************************************
  public void buildTopPanel() {
    topPanel = new JPanel();						// create top panel

	undoButton = new JButton("Undo");				// set up the undo button
	undoButton.addActionListener(listener);
	topPanel.add(undoButton);
	    
	clearButton = new JButton("Clear");				// set up the clear button
	clearButton.addActionListener(listener);
	topPanel.add(clearButton);
	
    shapeBox = new JComboBox(shapes);				// set up combo box for shape
    shapeBox.addActionListener(shapeChooser);
    topPanel.add(shapeBox);
    
    colorBox = new JComboBox(colors);				// set up combo box for shape
    colorBox.addActionListener(colorChooser);
    topPanel.add(colorBox);
  }
  
  //**********************************************
  //  Represents a listener for button push events.
  //**********************************************
  private class ButtonListener implements ActionListener
  {
    //------------------------------------------------------
    // Determines button pressed and sets color accordingly.
    //------------------------------------------------------
    public void actionPerformed(ActionEvent event)
    {
      // if Undo button is pressed, call clearLastShape
      if (event.getSource() == undoButton)
         drawingPanel.clearLastShape();
      // if Clear button is pressed, call clearLastShape
      if (event.getSource() == clearButton)
         drawingPanel.clearDrawing();
    }
  }
  
  //*****************************************
  //  Represents a listener for shape choices
  //*****************************************
  private class ShapeComboBoxListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      // assign chosen shape to variable shapeType
      drawingPanel.setShapeType(shapeBox.getSelectedIndex());        
    }
  }

//*******************************************
  //  Represents a listener for color choices
  //*****************************************
  private class ColorComboBoxListener implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {  
      // get color chosen and assign it to variable currentColor
      String selection = (String) colorBox.getSelectedItem();
     
      switch (selection) {
      case "Black": 	drawingPanel.setCurrentColor(Color.black);
      				break;
      case "Blue": 	drawingPanel.setCurrentColor(Color.blue);
	  				break;
      case "Cyan": 	drawingPanel.setCurrentColor(Color.cyan);
	  				break;
      case "Dark Gray": 	drawingPanel.setCurrentColor(Color.darkGray);
      				break;
      case "Gray": 	drawingPanel.setCurrentColor(Color.gray);
					break;
      case "Green": 	drawingPanel.setCurrentColor(Color.green);
					break;
      case "Light Gray": 	drawingPanel.setCurrentColor(Color.lightGray);
					break;
      case "Magenta": 	drawingPanel.setCurrentColor(Color.magenta);
					break;
      case "Orange": 	drawingPanel.setCurrentColor(Color.orange);
					break;
      case "Pink": 	drawingPanel.setCurrentColor(Color.pink);
					break;
      case "Red": 	drawingPanel.setCurrentColor(Color.red);
					break;
      case "White": 	drawingPanel.setCurrentColor(Color.white);
					break;
      case "Yellow": 	drawingPanel.setCurrentColor(Color.yellow);
					break;
      }
    }
  }
  
} // end of class