package tictactoe;

import java.awt.Dimension;
import static java.lang.Math.*;

public class ArtificialIntelligence
{
  private MainBoard mainBoard;
	private String difficulty;	//one of "EASY, MEDIUM, or HARD"
	private int thinkingDelay;	//"thinking" speed in milliseconds (optional?)
	private int randomness = 0;  //change number if want to change randomness
	
	private char side; //'x' or 'o'
	
	public ArtificialIntelligence(MainBoard mainBoard_In, String difficulty_In, int thinkingDelay_In, char side_In)
	{
	  mainBoard = mainBoard_In;
	  difficulty = difficulty_In;
	  thinkingDelay = thinkingDelay_In;
	  side = side_In;
	}

	public int /*???*/ makeMove()
	{
	  //wait for a bit before making move
    try
    {
      Thread.sleep(thinkingDelay);
    }
    catch(InterruptedException iE)
    {
     //do nothing (is this bad?) 
    }
    int playerMove = mainBoard.getMove(); //need this function
    int move = 0; //default
    
	  if(difficulty == "EASY")
	  {
	    for(/*iterate over miniboard*/)
	    {
	      //find 1st empty box ('n'?)
	      if(/*box == empty*/)
	      {
	        move = button.getIndex();
	      }
	    }
	  }
	  else if(difficulty == "MEDIUM")
	  {
	    
	  }
	  else if(difficulty == "HARD")
	  {
	    
	  }
	  
	  return move;  //not sure if want type <int>
	}
}
