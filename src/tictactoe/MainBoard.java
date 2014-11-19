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
  static ArrayList< MiniBoard > boards = new ArrayList<MiniBoard>();
  
  static ImageIcon img;
  static ImageIcon xImage;
  static ImageIcon oImage;
  static ImageIcon blankImage;
  String xImagePath;
  String oImagePath;
  static JLabel stats; 
  GameGUI game;
  
  static boolean first_move = true; 
  
  public MainBoard( JLabel output, GameGUI game_in )
  {
    System.out.println("WHATEVS");
    stats = output; 
    xImagePath = new String("images/X_Image.jpg");
    img = new ImageIcon();
    xImage = new ImageIcon(getClass().getClassLoader().getResource(xImagePath));
    blankImage = new ImageIcon(getClass().getClassLoader().getResource("images/blank.jpg"));
    oImage = new ImageIcon(getClass().getClassLoader().getResource("images/O_image.jpg"));
    img = xImage;
    game = game_in;
             
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
  }
  
  public void resetBoard()
  {
    for(MiniBoard mini: boards)
    {
      mini.resetMiniBoard();
      first_move = true;
    }
  }

  boolean check_winner( int in )
  {
	  int row = in / 3; 
	  int col = in % 3;
	  
	  //System.out.println( "row: " + row + " col: " + col );
	  
	  char move = boards.get( in ).get_winner();
	  //need to change this a better name 
	  int i ; 
	  
	  //check horizontal 
	  for ( i = 0 ; i < 3 ; ++i)
	  {
		  if( boards.get( i + row * 3 ).get_winner() != move )
		  {
			  break; 
		  }
	  }
	  
	  if ( i == 3 ) {
		  winner( move ); 
		  return true;
	  }
	  
	  //check vertical 
	  for( i = 0 ; i < 9 ; i += 3 )
	  {
		  if( boards.get( i + col ).get_winner() != move )
		  {
			  break; 
		  }
	  }
	  
	  if ( i >= 9 )
	  {
		  winner( move ); 
		  return true;
	  }
	  
	  //check diag 0, 4 , 8 
	  if ( row == col )
	  {
		  for( i = 0 ; i < 9 ; i += 4 )
		  {
			  if( boards.get( i ).get_winner() != move )
			  {
				  break; 
			  }
		  }
	  }
	  
	  if ( i >= 9 ){
		  winner( move ); 
		  return true;
	  }
	  
	  //check diag  2, 4, 6 
	  for ( i = 2 ; i < 8 ; i += 2 )
	  {
		  if( boards.get( i ).get_winner() != move )
		  {
			  break; 
		  }  
	  }
	  
	  if ( i >= 8 )
	  {
		winner( move );
		return true; 
	  }
	  
	  return false; 
  }
  
  void winner( char in )
  {
	 for( int i = 0 ; i < boards.size() ; ++i )
	 {
		  boards.get( i ).dissable_panel();
	 }
	stats.setText( in + " Wins!!!");
	game.setGlass();
  }
  
  public class ButtonListener implements ActionListener
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
      
      // sets the button icon to xImage, we can change this to set the image of
      // X or O depending on the player...
      button.setDisabledIcon( img );
      if(img == xImage)
      {
        img = oImage;
      	button.set_fill('x') ;
      	stats.setText("Move: O");
      }
      else
      {
        img = xImage;
        button.set_fill('o') ;
        stats.setText("Move: X");
      }
      
      if ( boards.get( button.get_parent() ).CheckWinner( button.get_index()  ) )
      {
    	  //do some check winner stuff 
    	  if ( check_winner( button.get_parent() ))
    		  return ;
      }
      
      boards.get( button.get_parent() ).dissable_panel();
      if(boards.get( button.get_index() ).is_active() )
      {
          boards.get( button.get_index() ).enable_panel();
      }
      else
      {
    	  first_move = true; 
    	  for( int i = 0 ; i < boards.size() ; ++i )
    	  {
    		  if( boards.get( i ).is_active() )
    			  boards.get( i ).enable_panel();
    	  }
      }
      
      // So that the button can't be clicked again. It currently sets the
      // button to grey, but it looks like that can be changed with UIManager..
      button.setEnabled(false);
    }
  }
  

}
