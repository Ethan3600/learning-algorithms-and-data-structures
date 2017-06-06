package Chutes_and_Ladders;

public class Player
{
	/**
	 * flag to check if player won
	 */
	private boolean isWinner = false;
	
	/**
	 * contains board singleton
	 */
	private Board board;
	
	/**
	 * indicates position on the board
	 */
	private int position = 0;

	private String name;
	
	Player(Board board, String name)
	{
		this.name = name;
		this.board = board;
	}
	
	public void playTurn()
	{
		int originalPosition = this.position;
		int roll = Die.rollDie();
		System.out.println(this.name + " rolled a " + roll);
		this.position = (roll + this.position);
		
		// used to determine if player hit a chute or a ladder or won
		int goTo = this.board.checkForChutesOrLadders(this.position);
		
		if(this.board.getPositionValue(this.position) == 0) 
			System.out.println(this.name + " is moving to position " + this.position);
		
		else if(this.board.getPositionValue(goTo) == Board.WINNING_POSITION)
			this.isWinner = true;
		
		else if (this.position > 100)
		{
			System.out.println(this.name + " overrolled :( staying in position " + originalPosition);
			goTo = originalPosition;
		}
		
		else if(goTo < this.position) 
			System.out.println("Oh No! " + this.name + " hit a chute! Moving to position: " + goTo);
		
		else if(goTo > this.position) 
			System.out.println(this.name + " hit a ladder! Moving to: " + goTo);
		
		
		this.position = goTo;
	}
	
	public int getPosition()
	{
		return this.position;
	}
	
	public boolean isWinner()
	{
		return this.isWinner;
	}
	
}
