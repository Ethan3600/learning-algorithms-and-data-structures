package Chutes_and_Ladders;

public class Board
{
	final public static int WINNING_POSITION = 999; 
	
	private static Board boardSingleton;
	
	final private int[] board = new int[101]; // 0 is off the board
	
	/**
	 * private constructor so user can't call it
	 */
	private Board(){}
	
	public static Board createNewBoard()
	{
		
		if(Board.boardSingleton != null)
		{
			return Board.boardSingleton; 
		}
		else
		{
			Board.boardSingleton = new Board();
			Board.boardSingleton.initializeBoard();
			return Board.boardSingleton;
		}
		
	}

	/**
	 * returns position to go to if player
	 * landed on a chute or ladder
	 * 
	 * @param position
	 * @return
	 */
	public int checkForChutesOrLadders(int position)
	{
		// protects from out of bounds exception
		if(position > 100) return position;
		
		if(board[position] != 0)
		{
			position = board[position]; 
		}
		return position;
	}
	
	public int getPositionValue(int position)
	{
		// protects from out of bounds exception
		if(position > 100) return position;
		return this.board[position];
	}
	
	private void initializeBoard()
	{
		this.board[1] 		= 38;
		this.board[4] 		= 14;
		this.board[9] 		= 31;
		this.board[16] 		= 6;
		this.board[21] 		= 42;
		this.board[28] 		= 84;
		this.board[36] 		= 44;
		this.board[47] 		= 26;
		this.board[49] 		= 11;
		this.board[51] 		= 67;
		this.board[56] 		= 53;
		this.board[62] 		= 19;
		this.board[64] 		= 60;
		this.board[71] 		= 91;
		this.board[80] 		= 100;
		this.board[87] 		= 24;
		this.board[93] 		= 73;
		this.board[95] 		= 75;
		this.board[98] 		= 78;
		this.board[100] 	= WINNING_POSITION;
	
	}
	
}
