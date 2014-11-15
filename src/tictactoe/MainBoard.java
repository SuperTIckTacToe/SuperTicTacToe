package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import tictactoe.MiniBoard;
import tictactoe.MiniBoard.Square_Button;

public class MainBoard extends JPanel
{
  MiniBoard mini1, mini2, mini3, mini4, mini5, mini6, mini7, mini8, mini9;
  static ArrayList< MiniBoard > boards = new ArrayList<MiniBoard>();
  
  static ImageIcon img;
  static ImageIcon xImage;
  static ImageIcon oImage;
  static ImageIcon blankImage;
  String xImagePath;
  String oImagePath;
  
  static boolean first_move = true; 
  
  public MainBoard()
  {
	 
	 xImagePath = new String("images/X_Image.jpg");
	 img = new ImageIcon();
	 xImage = new ImageIcon(getClass().getClassLoader().getResource(xImagePath));
	 blankImage = new ImageIcon(getClass().getClassLoader().getResource("images/blank.jpg"));
	 oImage = new ImageIcon(getClass().getClassLoader().getResource("images/O_image.jpg"));
	 img = xImage;
	      
	 
	  
    Color black = new Color(0, 0, 0);
    setBackground(black);
    setVisible(true);
    setLayout(new GridLayout(3,3,15,15));
    ButtonListener b_list = new ButtonListener(); 
    
    for( int i = 0 ; i < 9 ; ++i )
    {
    	boards.add( new MiniBoard( b_list , i ) );
    	add( boards.get( i ) );
    }
    
    /*mini1 = new MiniBoard();
    mini2 = new MiniBoard();
    mini3 = new MiniBoard();
    mini4 = new MiniBoard();
    mini5 = new MiniBoard();
    mini6 = new MiniBoard();
    mini7 = new MiniBoard();
    mini8 = new MiniBoard();
    mini9 = new MiniBoard();
    
    add(mini1);
    add(mini2);
    add(mini3);
    add(mini4);
    add(mini5);
    add(mini6);
    add(mini7);
    add(mini8);
    add(mini9);*/
  }
  

  
  
  public static class ButtonListener implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      Square_Button button = (Square_Button) e.getSource();
      
      if( first_move  )
      {
    	  for( int i = 0 ; i < boards.size() ; ++i )
    	  {
    		  boards.get( i ).dissable_panel();
    	  }
    	  first_move = false ; 
      }
      
      
      boards.get( button.get_parent() ).dissable_panel();;
      boards.get( button.get_index() ).enable_panel();
      
      // sets the button icon to xImage, we can change this to set the image of
      // X or O depending on the player...
      button.setDisabledIcon( img );
      if(img == xImage)
      {
        img = oImage;
      	button.set_fill('x') ;
      }
      else
      {
        img = xImage;
        button.set_fill('o') ;
      }
      
      
      // So that the button can't be clicked again. It currently sets the
      // button to grey, but it looks like that can be changed with UIManager..
      button.setEnabled(false);
    }
  }
}
