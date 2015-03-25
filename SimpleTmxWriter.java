

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

/**
 * SimpleTmx. A class for writing a simplified,
 * single layer tmx file.
 * 
 * Created for use in programming classes so that
 * we wouldn't have to use the Tiled library classes
 * for relatively simple examples.
 * 
 * You will need two things to view these maps
 * 1. Tiled Map Editor (http://www.mapeditor.org/)
 * 2. SimpleTmx_Tileset_01.png (on Canvas - just two 16x16 tiles)
 * 
 * @author Evan X. Merz
 *
 */
public class SimpleTmxWriter
{
    private static final int LAST_BYTE = 0x000000FF;
	
    /**
     * saveToTmxFile. Write a tmx for a map with two tiles. 0 = wall. 1 = floor.
     * 
     * @param outputFilename
     * @param map
     * @throws IOException
     */
	public static void saveToTmxFile(String outputFilename, int[][] map)
	{
		try
		{
			PrintWriter out = new PrintWriter(outputFilename);

			String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
			header += "<map version=\"1.0\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\""	+ map.length + "\" height=\"" + map[0].length + "\" tilewidth=\"32\" tileheight=\"32\">";
			header += "<tileset firstgid=\"1\" name=\"Tiles\" tilewidth=\"32\" tileheight=\"32\">";
			header += "<image source=\"SimpleTmx_Tileset_01.png\" width=\"64\" height=\"32\"/>";
			header += "<tile id=\"0\"><properties><property name=\"blocked\" value=\"1\"/></properties></tile>";
			header += "<tile id=\"1\"><properties><property name=\"blocked\" value=\"0\"/></properties></tile>";
			header += "</tileset>";
			header += "<layer name=\"Layer1\" width=\"" + map.length + "\" height=\"" + map[0].length + "\">";
			header += "<data encoding=\"base64\" compression=\"gzip\">";

			out.write(header);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream mapOut = new GZIPOutputStream(baos);
			for (int x = 0; x < map.length; x++)
			{
				for (int y = 0; y < map[0].length; y++)
				{
					int gid = map[x][y];

					mapOut.write(gid & LAST_BYTE);
					mapOut.write(gid >> 8 & LAST_BYTE);
					mapOut.write(gid >> 16 & LAST_BYTE);
					mapOut.write(gid >> 24 & LAST_BYTE);
				}
			}
			mapOut.finish();

			String data = Base64.getEncoder().encodeToString(baos.toByteArray());
			out.print(data);

			String footer = "</data></layer></map>";
			out.print(footer);

			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
	}
}
