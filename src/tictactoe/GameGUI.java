package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import tictactoe.MiniBoard.Square_Button;

public class GameGUI extends JFrame
{
  JMenuBar menuBar;
  JMenu file;
  JMenuItem startGame;
  JMenuItem leaveGame;
  JMenuItem pauseGame;
  JMenuItem resetGame;
  
  MainBoard mainBoard;
  JPanel statsPanel;
  JLabel stats;
  JList<String> moves_list;
  DefaultListModel<String>  moves_model; 
  Game_data data;
  
  public GameGUI()
  {
    setVisible(true);
    // Whatever actions we want the user to be able to take:
    startGame = new JMenuItem("Start Game");  
    leaveGame = new JMenuItem("Leave Game");
    pauseGame = new JMenuItem("Pause Game");
    resetGame = new JMenuItem("Reset Game");
    resetGame.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        mainBoard.resetBoard();
      }
    });

    
    file = new JMenu("File");
    file.add(startGame);
    file.add(pauseGame);
    file.add(leaveGame);
    file.add(resetGame);
    
    menuBar = new JMenuBar();
    menuBar.add(file);
    
    // This panel could show the stats of the game, depending on what we want..
    // It is currently set to a blue color just so we can see where it is in the
    // GUI.
    statsPanel = new JPanel();
    statsPanel.setLayout( new BoxLayout( statsPanel , BoxLayout.PAGE_AXIS ) );
    statsPanel.setPreferredSize(new Dimension(250,600));
    Color panelCol = new Color(0, 128, 255);
    statsPanel.setBackground(panelCol);
    
	data = new Game_data();
    
    JPanel top = new JPanel();
    JButton undo = new JButton( "Undo" );
    undo.addActionListener(new ActionListener()
    {
    	  public void actionPerformed(ActionEvent e)
    	  {
    		  String undo_s = data.undo_move(); 
    		  if( undo_s == null )
    		  {
    			  return; 
    		  }
    		  
    		  moves_model.remove( moves_model.size() - 1 );
    		  
    		  
    		  for( MiniBoard b : MainBoard.boards )
    		  {
    			  b.dissable_panel();
    		  }
    		  
    		  MiniBoard board = MainBoard.boards.get( Integer.parseInt( ""+undo_s.charAt( 1 ) ) );
    		  Square_Button temp =  board.buttons.get( Integer.parseInt( ""+undo_s.charAt( 2 ) ) );
    		  temp.set_fill( 'n' );
    		  temp.setDisabledIcon( MiniBoard.disabledImage );
    		  
    		  if( data.get_moves().size() == 0  )
    		  {
    			  MainBoard.first_move = true; 
    			  MainBoard.img = MainBoard.xImage; 
    			  for( MiniBoard b : MainBoard.boards )
    				  b.enable_panel();
    			  return; 
    		  }
    		  
    		  board.enable_panel();
    	  }		
    });
    top.add( undo );
    JButton redo = new JButton( "Redo" );
    redo.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e) 
		{
		  String redo_s = data.redo();
  		  if( redo_s == null )
  		  {
  			  return; 
  		  }
  		  
		  MiniBoard board = MainBoard.boards.get( Integer.parseInt( ""+redo_s.charAt( 1 ) ) );
		  Square_Button temp =  board.buttons.get( Integer.parseInt( ""+redo_s.charAt( 2 ) ) );
  		  temp.set_fill( redo_s.charAt( 0 ) );
  		  temp.setDisabledIcon(redo_s.charAt( 0 ) == 'x' ? MiniBoard.xImage : MiniBoard.oImage );
		  
  		  if( board.CheckWinner( Integer.parseInt( ""+redo_s.charAt( 2 ) ) )
  				  &&  mainBoard.check_winner( Integer.parseInt(""+redo_s.charAt( 1 ) ) ) )
  		  {
  			 mainBoard.winner( redo_s.charAt( 0 ) );
  		  }
		}
    });
    top.add( redo );
    
    statsPanel.add( top ); 
    
    stats = new JLabel() ;
    statsPanel.add( stats ); 
    //add a jlist with a scroable pannel 
    // give member variable of the itator 
    // 
    moves_model = new DefaultListModel<String>();
    moves_list = new JList<String>( moves_model ); 
    JScrollPane move_p = new JScrollPane( moves_list ); 
    //stats.add( moves_list );
    statsPanel.add( move_p );
    
    mainBoard = new MainBoard( stats, this );
    
    setLayout(new FlowLayout());
    setJMenuBar(menuBar);
    add(statsPanel);
    add(mainBoard);
    pack();
  }
  
  public void setGlass(String glass_type)
  {
    
    final JPanel glass = new JPanel();
    JPanel inner = new JPanel();
    JPanel westPanel = new JPanel();
    westPanel.setOpaque(false);
    westPanel.setPreferredSize(new Dimension(250,650));
    JLabel winnerPic = new JLabel();
    assert(glass_type.equals("winner") || glass_type.equals("stalemate"));
    ImageIcon winPic = new ImageIcon(getClass().getClassLoader().getResource("images/" + glass_type + ".jpg"));
    winnerPic.setIcon(winPic);
    inner.add(winnerPic);
    JPanel topBar = new JPanel();
    topBar.setPreferredSize(new Dimension(940,20));
    topBar.setOpaque(false);
    JButton dismiss = new JButton("Dismiss " + glass_type+ " Message");
    dismiss.setPreferredSize(new Dimension(200,70));
    westPanel.add(dismiss);
    dismiss.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        glass.setVisible(false);
      }
    });
    inner.setPreferredSize(new Dimension(660,660));
    glass.setLayout(new FlowLayout());
    glass.add(topBar);
    glass.add(westPanel);
    glass.add(inner);
    glass.addMouseListener(new MouseAdapter()
    {

      @Override
      public void mouseClicked(MouseEvent e)
      {}
    });
    
    setGlassPane(glass);
    glass.setVisible(true);
    glass.setOpaque(false);
  }
  
}
