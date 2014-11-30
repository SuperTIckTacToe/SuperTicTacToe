package tictactoe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

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
    statsPanel.setPreferredSize(new Dimension(250,600));
    Color panelCol = new Color(0, 128, 255);
    statsPanel.setBackground(panelCol);
    stats = new JLabel() ;
    statsPanel.add( stats ); 
    
    mainBoard = new MainBoard( stats, this );
    
    setLayout(new FlowLayout());
    setJMenuBar(menuBar);
    add(statsPanel);
    add(mainBoard);
    setStartScreen();
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
  
  public void setStartScreen()
  {
	    final JPanel glass = new JPanel();
	    glass.setLayout(new FlowLayout());
	    glass.setBackground(new Color(226,237,135));
	    JLabel pic = new JLabel();
	    ImageIcon startIcon = new ImageIcon(getClass().getClassLoader().getResource("images/front.jpg"));
	    pic.setIcon(startIcon);
	    JLabel gameType = new JLabel("Which type of game would you like to play?");
	    gameType.setFont(new Font("SansSerif", Font.BOLD, 36));
	    glass.setPreferredSize(new Dimension(800,800));
	    JButton local = new JButton("Local 2 Player");
	    local.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		glass.setVisible(false);
	    	}
	    });
	    JButton comp = new JButton("Play the Computer");
	    comp.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		glass.setVisible(false);
	    	}
	    });
	    JButton network = new JButton("Play Online");
	    network.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		glass.setVisible(false);
	    	}
	    });
	    glass.addMouseListener(new MouseAdapter()
	    {

	      @Override
	      public void mouseClicked(MouseEvent e)
	      {}
	    });
	    glass.add(gameType);
	    glass.add(local);
	    glass.add(comp);
	    glass.add(network);
	    glass.add(pic);
	    setGlassPane(glass);
	    glass.setVisible(true);
  }
  
}
