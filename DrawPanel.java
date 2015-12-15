/*********************************************************************
 * Class: CS111B - Object-Oriented Programming Methodologies in Java
 * Description:	This class creates a panel to draw the shape the user
 * 				requests. 
 * Due Date:	December 9
 * Name:      	Thierry Spelle
 * Name of File:  DrawPanel.java
 * Assignment 10
 **********************************************************************/

import java.awt.event.*;						// import from java libraries
import javax.swing.*;
import java.awt.*; 

public class DrawPanel extends JPanel {
  MyShape[] shapes = new MyShape[100];			// array to store shapes
  
  MyShape currentShape = null;
  Color currentColor = Color.black;
  JLabel statusLabel;
  
  int	shapeCount = 0,							// number of shapes in array
		shapeType;			// 0 represents a line, 1 an oval, 2 a rectangle
  
  //-------------
  // constructor
  //-------------
  public DrawPanel(JLabel label) {
    statusLabel = label;						// mouse position coordinates
    setBackground(Color.white);									// panel color
    
    DrawListener listener = new DrawListener();				// set up listener
    addMouseListener(listener);
    addMouseMotionListener(listener);
  }
     
  //-----------------------------------------------------------------
  //  Draws the current shape from the initial mouse-pressed point to
  //  the current position of the mouse.
  //-----------------------------------------------------------------
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);					// call constructor from superclass
    
    for (int i = 0; i < shapeCount; i++) {		// draw each shape in the array
      shapes[i].draw(g);
    }
    
    if (currentShape != null)					// draw current shape
       currentShape.draw(g);      
  } // end of paintComponent()
  
  public void setShapeType(int type) {			// set the shape
    shapeType = type;
  } // end of shapeType()
  
  public void setCurrentColor(Color color) {	// set the color
    currentColor = color;
  } // end of currentColor()
  
  public void clearLastShape() {				// delete last shape drawn
    if (shapeCount > 0) {
      shapeCount--;
      repaint();
    }
  } // end of clearShape()
  
  public void clearDrawing() {					// clear the drawing panel
    shapeCount = 0;
    repaint();	  
  } // end of clearDrawing

  //*****************************************************************
  //  Represents the listener for all mouse events.
  //*****************************************************************
  private class DrawListener implements MouseListener,
                                        MouseMotionListener
  {
     //--------------------------------------------------------------
     //  Captures initial position at which the mouse is pressed.
     //--------------------------------------------------------------
     public void mousePressed(MouseEvent event)
     {
       int x = event.getX();					// get initial coordinates
       int y = event.getY();
       
       if (shapeType == 0)						// shape is a line
          currentShape = new MyLine(x, y, x, y, currentColor);
         
       if (shapeType == 1)						// shape is an oval
          currentShape = new MyOval(x, y, x, y, currentColor);
    	 
       if (shapeType == 2)						// shape is a rectangle
          currentShape = new MyRect(x, y, x, y, currentColor);         
     }
     
   //--------------------------------------------------------------
     //  Captures final position at which the mouse is pressed.
     //--------------------------------------------------------------
     public void mouseReleased(MouseEvent event)
     {
    	int x = event.getX();					// get release coordinates
        int y = event.getY();	 
    	 
        currentShape.setX2(x);					// pass coordinates to shape
        currentShape.setY2(y);
        
        shapes[shapeCount] = currentShape;		// store shape in array
        shapeCount++;
        
        currentShape = null;					// reset currentShape
        
        repaint();								// draw shapes
     }

     //--------------------------------------------------------------
     //  Gets the current position of the mouse as it is dragged and
     //  redraws the shapes
     //--------------------------------------------------------------
     public void mouseDragged(MouseEvent event)
     {
    	 int x = event.getX();					// get release coordinates
         int y = event.getY();	 
     	 
         currentShape.setX2(x);					// pass coordinates to shape
         currentShape.setY2(y);
                  
    	 repaint();								// draw shapes
    	 
    	 statusLabel.setText("(" + x + "," + y + ")");	// display status bar
     }
     
     //------------------------------------------------------------
     // Get the position of the mouse as it is moved and display it
     //------------------------------------------------------------
     public void mouseMoved(MouseEvent event)
     {
    	 int x = event.getX();					// get move coordinates
         int y = event.getY();	 
     	                      	 
    	 statusLabel.setText("(" + x + "," + y + ")");	// display status bar
     }

     //--------------------------------------------------------------
     //  Provide empty definitions for unused event methods.
     //--------------------------------------------------------------
     public void mouseClicked(MouseEvent event) {}
     public void mouseEntered(MouseEvent event) {}
     public void mouseExited(MouseEvent event) {}

     
  } // end of listener
	
} // end of class