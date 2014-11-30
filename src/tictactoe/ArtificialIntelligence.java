package tictactoe;

import static java.lang.Math.*;
import java.util.Random;

public class ArtificialIntelligence
{
  private MainBoard mainBoard;
	private String difficulty;	//one of "EASY, MEDIUM, or HARD"
	private int thinkingDelay;	//"thinking" speed in milliseconds (optional?)
	private int randomness = 0;  //change number if want to change randomness
	
	private char side = 'o'; //'x' or 'o'
	
	public ArtificialIntelligence(MainBoard mainBoard_In, String difficulty_In, int thinkingDelay_In)//, char side_In)
	{
	  mainBoard = mainBoard_In;
	  difficulty = difficulty_In;
	  if(difficulty_In == "RANDOM")
	  {
	    thinkingDelay = 500;
	  }
	  else if(difficulty_In == "EASY")
    {
      thinkingDelay = 1500;
    }
	  else if(difficulty_In == "MEDIUM")
    {
      thinkingDelay = 2000;
    }
	  else if(difficulty_In == "HARD")
    {
      thinkingDelay = 3000;
    }
	  else thinkingDelay = thinkingDelay_In;
	  //side = side_In;
	}

	public void makeMove(int miniIndex)//MiniBoard mini)
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
//    int playerMove = mainBoard.getMove(); //need this function
    int move = 0; //default
    
    if( (difficulty == "RANDOM") || (difficulty == "EASY") )
    {
      if( !(mainBoard.boards.get( miniIndex ).is_active())  )
      {
        Random randBoard = new Random();
        do
        {
          //if EASY?
          //...
          //...
          
          miniIndex = abs( (randBoard.nextInt() % 9) );
        }
        while( !(mainBoard.boards.get(miniIndex).is_active()) );
      }
      
      Random randMove = new Random();
      do
      {
        if(difficulty == "EASY")
        {
          move = findWinSquare(miniIndex);
          //iterate over miniboard to find if any winners available
          if(move >= 0){}
          else move = abs( (randMove.nextInt() % 9) );
        }
        
        else  move = abs( (randMove.nextInt() % 9) );
      }
      while(mainBoard.boards.get( miniIndex ).buttons.get( move ).get_fill() != 'n');
          
      mainBoard.boards.get( miniIndex ).buttons.get( move ).doClick(); 
    }
    else if(difficulty == "EASY")
	  {
	    /*
	    for(/*iterate over miniboard*)
	    {
	      //find 1st empty box ('n'?)
	      if(/*box == empty*)
	      {
	        move = button.getIndex();
	      }
	    }*/
	  }
	  else if(difficulty == "MEDIUM")
	  {
	    
	  }
	  else if(difficulty == "HARD")
	  {
	    
	  }
	  
	  return;
	}
	
	private int findWinSquare(int miniIndex)
	{
	  int square = -1;
	  //top row
	  if((mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == side)
	      && (mainBoard.boards.get( miniIndex ).buttons.get(1).get_fill() == side)
	      && (mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == 'n'))
	  {
	    square = 2;
	  }
	  else if((mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(1).get_fill() == 'n'))
    {
      square = 1;
    }
	  else if((mainBoard.boards.get( miniIndex ).buttons.get(1).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == 'n'))
    {
      square = 0;
    }
	  
	  //mid row
	  else if((mainBoard.boards.get( miniIndex ).buttons.get(3).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(5).get_fill() == 'n'))
    {
      square = 5;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(3).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(5).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == 'n'))
    {
      square = 4;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(5).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(3).get_fill() == 'n'))
    {
      square = 3;
    }
	  
	  //bot row
    else if((mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(7).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == 'n'))
    {
      square = 8;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(7).get_fill() == 'n'))
    {
      square = 7;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(7).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == 'n'))
    {
      square = 6;
    }
	  
	  //left col
    else if((mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(3).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == 'n'))
    {
      square = 6;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(3).get_fill() == 'n'))
    {
      square = 3;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(3).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == 'n'))
    {
      square = 0;
    }
	  
	  //mid col
    else if((mainBoard.boards.get( miniIndex ).buttons.get(1).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(7).get_fill() == 'n'))
    {
      square = 7;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(1).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(7).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == 'n'))
    {
      square = 4;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(7).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(1).get_fill() == 'n'))
    {
      square = 1;
    }
	  
	  //right col
    else if((mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(5).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == 'n'))
    {
      square = 8;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(5).get_fill() == 'n'))
    {
      square = 5;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(5).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == 'n'))
    {
      square = 2;
    }
	  
	  //0,4,8 diag
    else if((mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == 'n'))
    {
      square = 8;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == 'n'))
    {
      square = 4;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(8).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(0).get_fill() == 'n'))
    {
      square = 0;
    }
	  
	  //2,4,6 diag
    else if((mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == 'n'))
    {
      square = 6;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == 'n'))
    {
      square = 4;
    }
    else if((mainBoard.boards.get( miniIndex ).buttons.get(4).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(6).get_fill() == side)
        && (mainBoard.boards.get( miniIndex ).buttons.get(2).get_fill() == 'n'))
    {
      square = 2;
    }
	  
	  return square;
	}
}
