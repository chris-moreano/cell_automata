
public class Impressionism_CA_01
{
	private final static int SIZE = 320;
	private final static int REPEATS = 150;
	
	public static void main(String[] args)
	{
		int[][] ca = new int[SIZE][SIZE];
		
		// initial conditions = random
		for( int i = 0; i < ca.length; i++ )
			for( int j = 0; j < ca[i].length; j++ )
				ca[i][j] = (int)Math.floor(Math.random() * Integer.MAX_VALUE);
		
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
					// get the values in the neighborhood
					int xOffset = 0;
					int yOffset = 0;
					
					if( Math.random() < 0.5 )
						xOffset = (Math.random() < 0.5) ? -1 : 1;
					else
						yOffset = (Math.random() < 0.5) ? -1 : 1;

					// do some bounds checking
					if( i == 0 && xOffset == -1 ) xOffset = 0;
					else if( i == ca.length - 1 && xOffset == 1 ) xOffset = 0;
					
					if( j == 0 && yOffset == -1 ) yOffset = 0;
					else if( j == ca[i].length - 1 && yOffset == 1 ) yOffset = 0;
					

					// store the new value
					newValues[i][j] = ca[i + xOffset][j + yOffset];
				}
			}

			// copy over the array
			ca = newValues;
			
			counter++;
		}
		
		SimpleImageWriter.saveToImageFile("Impressionism_01_Output.png", ca);
		
		System.out.println("Done.");
	}

}
