package Chutes_and_Ladders;

public class GameSimulation
{

	public static void main(String[] args)
	{
		// =================== Initialize Board ================== //
		
		Board board = Board.createNewBoard();
		
		// =================== Initialize Players ================== //
		
		Player p1 = new Player(board, "Ethan");
		Player p2 = new Player(board, "Michael");
		
		// =================== Initialize Game Loop ================== //
		
		while(!p1.isWinner() && !p2.isWinner())
		{
			p1.playTurn();
			p2.playTurn();
		}
		
		if(p1.isWinner())
			System.out.println("PLAYER 1 WINS!");
		else if(p2.isWinner())
			System.out.println("PLAYER 2 WINS!");
			
	}

}
