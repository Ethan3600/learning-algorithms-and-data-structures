package Chutes_and_Ladders;

import java.util.Random;

public class Die
{
	public static int rollDie()
	{
		Random r = new Random();
		return r.nextInt(6) + 1;
	}
}
