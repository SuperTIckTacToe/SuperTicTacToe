package tictactoe;

import java.awt.*;

import javax.swing.*;

public class GameGUI extends JFrame
{
  JMenuBar menuBar;
  JMenu file;
  JMenuItem startGame;
  JMenuItem leaveGame;
  JMenuItem pauseGame;
  
  MainBoard mainBoard;
  JPanel statsPanel;
  
  public GameGUI()
  {
    setVisible(true);
    // Whatever actions we want the user to be able to take:
    startGame = new JMenuItem("Start Game");
    leaveGame = new JMenuItem("Leave Game");
    pauseGame = new JMenuItem("Pause Game");
    
    file = new JMenu("File");
    file.add(startGame);
    file.add(pauseGame);
    file.add(leaveGame);
    
    menuBar = new JMenuBar();
    menuBar.add(file);
    
    // This panel could show the stats of the game, depending on what we want..
    // It is currently set to a blue color just so we can see where it is in the
    // GUI.
    statsPanel = new JPanel();
    statsPanel.setPreferredSize(new Dimension(250,600));
    Color panelCol = new Color(0, 128, 255);
    statsPanel.setBackground(panelCol);
    JLabel stats = new JLabel() ;
    statsPanel.add( stats ); 
    
    mainBoard = new MainBoard( stats );
    
    setLayout(new FlowLayout());
    setJMenuBar(menuBar);
    add(statsPanel);
    add(mainBoard);
    pack();
    
  }
}
