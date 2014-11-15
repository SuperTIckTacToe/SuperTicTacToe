package tictactoe;

import java.awt.*;

import javax.imageio.ImageIO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MiniBoard extends JPanel
{
  // Buttons for each square on tic-tac-toe board, labeled left to right
  // from top-left to bottom-right:
  JButton button1, button2, button3, button4, button5, button6;
  JButton button7, button8, button9;
  
  // holds all the buttons 
  ArrayList< Square_Button > buttons = new ArrayList< Square_Button >() ;
  
  // Image that will be toggled to X's or O's depending on the player:
  static ImageIcon img;
  static ImageIcon xImage;
  static ImageIcon oImage;
  
  String xImagePath;
  String oImagePath;
  static ImageIcon blankImage;
  static boolean xWins;
  static BufferedImage bkImage;
  int index; 
  
  // glass pane
  //JPanel glass;
  
  public class Square_Button extends JButton {
	 
	private int index; 
	private int parent; 
	//change to private 
	private char fill = 'n';
	
	  public Square_Button( int _index , int _parent )
	  {
		  super(); 
		  index = _index; 
		  parent = _parent; 
	  }
	  int get_index(){
		  return index; 
	  }
	  int get_parent()
	  {
		  return parent; 
	  }
	  void set_fill( char in )
	  {
		  if( in == 'x' || in == 'o' )
			  fill = in; 
	  }
	  char get_fill()
	  {
		  return fill; 
	  }
  }
  
  
  public MiniBoard( ActionListener button_listener , int _index )
  {
	index = _index;   
	  
    /**Color black = new Color(0, 0, 0);   
    setBackground(black);*/
    setLayout(new GridLayout(3,3, 2, 2));   
    setPreferredSize(new Dimension(210, 210));
   
    // Initialize Image:
    xImagePath = new String("images/X_Image.jpg");
    img = new ImageIcon();
    xImage = new ImageIcon(getClass().getClassLoader().getResource(xImagePath));
    
    oImage = new ImageIcon(getClass().getClassLoader().getResource("images/O_image.jpg"));
    img = xImage;
    blankImage = new ImageIcon(getClass().getClassLoader().getResource("images/blank.jpg"));
    
    // Set a 3 by 3 gridlayout for each Mini board:
    setLayout(new GridLayout( 3, 3, 1, 1 ) );
    
    // Initialize buttons:
    for( int i = 0 ; i < 9 ; ++i )
    {
    	buttons.add( new Square_Button( i , index  ) ) ; 
    	ButtonInitializer( buttons.get( i ) , button_listener );
    	add( buttons.get( i ) );
    }
    
    
    /**this.setVisible(true);
    //Set tic-tac-toe gridlines on board (this currently is not successful)
    
    if(this.getGraphics() == null)
      System.out.println("HAHAH ITS NULL");
    
    Graphics graph = this.getGraphics();
    graph.drawLine(70, 0, 70, 210);
    graph.drawLine(140, 0, 140, 210);
    graph.drawLine(0, 70, 210, 70);
    graph.drawLine(0, 140, 210, 140);*/
    

    
  }
  
  public void dissable_panel()
  {
	  for (Component c : this.getComponents()) 
	  {
		  c.setEnabled( false );
	  }
  }
  
  public boolean enable_panel()
  {

	  for (Component c : this.getComponents()) 
	  {
		  if( c instanceof Square_Button )
		  {
			  Square_Button button = ( Square_Button ) c ;
			  if ( button.get_fill() == 'n' )
			  {
				  System.out.println( "Here ");
				  button.setEnabled( true );
			  }
		  }
	  }
	  return true; 
  }
  
  public void ButtonInitializer( Square_Button b ,  ActionListener button_listener)
  {
    b.setIcon(blankImage);
    b.addActionListener( button_listener );
    
  }
  
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.drawImage(bkImage, 0, 0, getWidth(), getHeight(), this);
  }
  
  public boolean CheckWinner( int in )
  {
	  

	  return false; 
  }
  
  public boolean getWinner()
  {
    return xWins;
  }

}

