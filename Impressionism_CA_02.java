
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Impressionism_CA_02
{
	//private final static int SIZE = 320;
	private final static int REPEATS = 50;
	
	public static void main(String[] args)
	{
		BufferedImage sourceImage= null;
		try
		{
			sourceImage = ImageIO.read(new File("barn.jpg"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		int width = sourceImage.getWidth();
		int height = sourceImage.getHeight();
		
		int[][] ca = new int[width][height];
		
		// initial conditions = random
		for( int i = 0; i < ca.length; i++ )
			for( int j = 0; j < ca[i].length; j++ )
				ca[i][j] = sourceImage.getRGB(i, j);
		
		// run the ca a number of times
		int counter = 0;
		while( counter < REPEATS )
		{
			System.out.println(counter);
			
			int[][] newValues = new int[width][height]; // temporary storage
			
			// recalculate each cell
			for( int i = 0; i < ca.length; i++ )
			{
				for( int j = 0; j < ca[i].length; j++ )
				{
					// get the values in the neighborhood
					int xOffset = 0;
					int yOffset = 0;
					
					if( Math.random() < 0.5 )
						xOffset = (int)Math.floor(Math.random() * 3.0) - 1;
					else
						yOffset = (int)Math.floor(Math.random() * 3.0) - 1;

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
		
		SimpleImageWriter.saveToImageFile("Impressionism_02_Output.png", ca);
		
		System.out.println("Done.");
	}

}
