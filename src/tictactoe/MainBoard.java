package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import tictactoe.MiniBoard;
import tictactoe.MiniBoard.Square_Button;

//pat added this
import network.*;
import java.io.IOException;

public class MainBoard extends JPanel
{
	public static ArrayList< MiniBoard > boards = new ArrayList<MiniBoard>();

	static ImageIcon img;
	static ImageIcon xImage;
	static ImageIcon oImage;
	static ImageIcon blankImage;
	String xImagePath;
	String oImagePath;
	static JLabel stats; 
	GameGUI game;
	JLabel output;
	public ButtonListener button_listern = new ButtonListener(); 
	private boolean online; //pat added this
  private ClientSideSocket socket; //pat added this
  //private String serverIP = "127.0.0.1"; //pat added this
  private String serverIP = "54.148.133.158"; //pat added this
  private int portNum = 45000; //pat added this
  private boolean waiting; //pat added this
  boolean gameOver = false; // pat added this
  boolean onlinePlayInitialized = false; //pat added this

	static boolean first_move = true; 

	Boolean AImove = false; 
	ArtificialIntelligence AI = new ArtificialIntelligence(this, "EASY", 3000);

	public MainBoard( JLabel _output, GameGUI game_in )
	{
		output = _output;
		game = game_in;
		setup();
	}

	public void reset( )
	{
		this.removeAll();
		setup();
	}

	private void setup( )
	{
		stats = output; 
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
	}
	
//pat added this function
  public void startOnlinePlay()
  {
    online = true;
    waiting = false;
    try
    {
      socket = new ClientSideSocket(serverIP, portNum);
      socket.connectToServer();
      System.out.println("player has connected");
      NetworkExchange firstPlayerCheck = socket.recvObject();
      System.out.println("player found out if player1 or not");
      if(!firstPlayerCheck.isPlayer1())
      {
        System.out.println("says that this is player2");
        waiting = true;
        NetworkExchange firstMove = socket.recvObject();
        onlinePlayInitialized = true;
        //  I know this line is long
        boards.get(firstMove.getParentIndex()).buttons.get(firstMove.getButtonIndex()).doClick();
      }
      else
      {
        onlinePlayInitialized = true;
      }
    }
    catch(IOException e)
    {
      online = false;
      JOptionPane.showMessageDialog(game, 
          "Opponent has left the game. Switching to Local Play.", 
          "Disconnected", JOptionPane.INFORMATION_MESSAGE);
    }
  }

	public void resetBoard()
	{
		for(MiniBoard mini: boards)
		{
			mini.resetMiniBoard(new ButtonListener());
			first_move = true;
			img = xImage;
		}
	}

	boolean check_winner( int in )
	{
		int row = in / 3; 
		int col = in % 3;

		//System.out.println( "row: " + row + " col: " + col );

		char move = boards.get( in ).get_winner();
		if( move == 'n')
			return false; 
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
		stats.setText(in + "Wins!!!\n");
		game.setGlass("winner");
	}

	public boolean check_stalemate()
	{
		for(MiniBoard mini: boards)
		{
			if(mini.is_active())
				return false;
		}
		return true;
	}


	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
		//pat added this if statement
      if(online && !onlinePlayInitialized)
      {
        return;
      }
			Square_Button button = (Square_Button) e.getSource();
			//pat put this block in this if statement
			if(!online)
			{
			  game.undo.setEnabled( true  );
			  game.redo.setEnabled( false );
			}

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
				System.out.println("Image is xImage");
				img = oImage;
				button.set_fill('x') ;
				stats.setText("Move: O\n"); 
			}
			else if( img == oImage )
			{
				System.out.println("Image is not xImage");
				img = xImage;
				button.set_fill('o') ;
				stats.setText("Move: X\n");
			}

			//linked list of strings x or 0 then index on the main board and then index on the mini
			// eg x00 would be the top left mini bord and the top left box in there 
			// all indexs will be garentited to be less than 10 so only single digests 


			if ( boards.get( button.get_parent() ).CheckWinner( button.get_index()  ) || 
					!boards.get( button.get_parent() ).is_active() )
			{
				//do some check winner stuff 
				if ( check_winner( button.get_parent() ))
				{
				  gameOver = true; //pat added brackets and this line
          //return ;
				}
				else if (check_stalemate())
				{
					stats.setText("There is a stalemate.. duh duh duh\n");
					game.setGlass("stalemate");
				}
			}
			//pat put if statement around this block
			if(!gameOver)
			{
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
			}
			//pat put this block in this if statement
			if(!online)
			{
			  String move = button.get_fill() + Integer.toString( button.get_parent() ) + Integer.toString( button.get_index() ); 
			  game.moves_model.addElement( game.moves_model.size()+": " + move );

			  try 
			  {
			    game.data.add( move );
			  } 
			  catch (InvalidInputException e1) 
			  {
				//  TODO Auto-generated catch block
				  e1.printStackTrace();
				  System.out.println( "you sent a bad move in "); 
			  }
			}

			if( AImove  )
			{
				AImove = false;
				// boards.get(button.get_index())
				AI.makeMove(button.get_index());
				AImove = true;
			}


			// So that the button can't be clicked again. It currently sets the
			// button to grey, but it looks like that can be changed with UIManager..
			button.setEnabled(false);
			
		//pat added following if statement
      if(online && !waiting)
      {
        try
        {
          NetworkExchange moveData = new NetworkExchange("user"); 
          moveData.setButtonIndex(button.get_index());
          moveData.setParentIndex(button.get_parent());
          if(gameOver)
          {
            moveData.setGameOver(gameOver);
          }
          socket.sendToServer(moveData);
          if(!gameOver)
          {
            moveData = socket.recvObject();
            waiting = true;
            boards.get(moveData.getParentIndex()).buttons.get(moveData.getButtonIndex()).doClick();
          }
        }
        catch(IOException exception)
        {
          online = false;
          JOptionPane.showMessageDialog(game, 
              "Opponent has left the game. Switching to Local Play.", 
              "Disconnected", JOptionPane.INFORMATION_MESSAGE);
        }
      }
      waiting = false; //pat added this
		}
	}
	//pat added this function
	public void turnAIOn()
	{
	  AImove = true;
	}


}
