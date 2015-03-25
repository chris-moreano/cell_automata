import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class SimpleImageWriter
{
	public static void saveToImageFile(String filename, int[][] pixels)
	{
		BufferedImage outputImage = new BufferedImage(pixels.length, pixels[0].length, BufferedImage.TYPE_INT_RGB);
	
		for(int x = 0; x < pixels.length; x++ )
		{
			for( int y = 0; y < pixels[0].length; y++ )
			{
				outputImage.setRGB(x, y, pixels[x][y]);
			}
		}
		
		try
		{
			ImageIO.write(outputImage, "png", new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
