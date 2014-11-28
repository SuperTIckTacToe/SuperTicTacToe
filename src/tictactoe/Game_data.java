package tictactoe;

import java.util.ArrayDeque;


public class Game_data 
{
	private ArrayDeque< String > moves;
	private ArrayDeque< String > undo_moves; 
	
	public Game_data()
	{
		moves = new ArrayDeque< String >();
		undo_moves = new ArrayDeque< String >();	
	}
	
	public void add( String move ) throws InvalidInputException
	{
		if( move.length() != 3 ||( move.charAt( 0 ) != 'x' && move.charAt( 0 ) != 'o' ) 
				|| !Character.isDigit(move.charAt(1)) || !Character.isDigit(move.charAt(2))  )
		{
			throw new InvalidInputException( "Invalid move given " + move );
		}
		undo_moves = new ArrayDeque< String >();	
		moves.addLast( move );
	}
	
	public String undo_move()
	{
		String last_move = moves.pollLast();
		if( last_move == null )
			return last_move; 
		undo_moves.addFirst( last_move );
		return last_move;
	}
	
	public ArrayDeque<String> get_moves()
	{
		return moves;
	}
	
	public ArrayDeque<String> get_undo_moves()
	{
		return undo_moves;
	}
	
	public String redo()
	{
		String move = undo_moves.removeFirst();
		if( move == null )
			return null; 
		moves.addLast( move );
		return move; 
	}
	
}
