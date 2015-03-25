
public class Game_CA_01
{
	private final static int SIZE = 50;
	private final static int REPEATS = 4;
	
	public static void main(String[] args)
	{
		int[][] ca = new int[SIZE][SIZE];
		
		// initial conditions = random
		for( int i = 0; i < ca.length; i++ )
			for( int j = 0; j < ca[i].length; j++ )
				ca[i][j] = (int)Math.floor(Math.random() * 2.0);
		
		// run the ca a number of times
		int counter = 0;
		while( counter < REPEATS )
		{
			int[][] newValues = new int[SIZE][SIZE]; // temporary storage
			
			// recalculate each cell
			for( int i = 0; i < ca.length; i++ )
			{
				for( int j = 0; j < ca[i].length; j++ )
				{
					int newValue = 0;
					
					// get the sum of the neighborhood
					int sum = getNeighborhoodSum(i, j, ca);
					
					// if the surrounding neighborhood is mostly walls
					// then make this a wall
					if( sum > 4 ) newValue = 1;

					// store the new value
					newValues[i][j] = newValue; /// this is now a wall if above
					
					// print for debugging only
					if( newValue == 1 ) 
						System.out.print(" "); // this is a wall
					
					else System.out.print("#");
				}
				System.out.println("");
			}
			System.out.println("");

			// copy over the array
			ca = newValues;
			
			counter++;
		}
		
		//SimpleImageWriter.saveToImageFile("Game_CA_01_Output.png", ca);
		SimpleTmxWriter.saveToTmxFile("CA", ca);
		
		System.out.println("Done.");
	}
	
	private static int getNeighborhoodSum(int x, int y, int[][] ca)
	{
		int sum = 0;
		
		sum += (x+1 < ca.length && y + 1 < ca[x+1].length) ? ca[x+1][y+1] : 0;
		sum += (x-1 > 0 && y + 1 < ca[x-1].length) ? ca[x-1][y+1] : 0;
		sum += (x+1 < ca.length && y-1 > 0) ? ca[x+1][y-1] : 0;
		sum += (y + 1 < ca[x].length) ? ca[x][y+1] : 0;	
		sum += (x-1 > 0 && y-1 > 0) ? ca[x-1][y-1] : 0;
		sum += (y+1) > ca.length ? ca[x+1][y+1] : 0;
		sum += (x-1) > ca.length ? ca[x+1][y-1] : 0;
		sum += (x+1 > ca.length)  ? ca[x][y-1] : 0;
		sum += (x+1 < ca.length) ? ca[x+1][y] : 0;	
		sum += (y-1 > 0) ? ca[x][y-1] : 0;
		sum += ((y-1) < 0) ? ca[x][y+1] : 0;
		sum += (x-1 > 0) ? ca[x-1][y] : 0;
		sum += (x-1 < 0) ? ca[x][y] : 0;
		sum += ca[x][y];




	
		return sum;
	}

}
