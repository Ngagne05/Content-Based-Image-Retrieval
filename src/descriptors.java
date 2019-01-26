import java.awt.Color;
import java.awt.image.BufferedImage;


public class descriptors {
	public static double[][][] histogram(BufferedImage image){
			final int numberOfBeans=32, nipb=256/numberOfBeans ;
			double[][][] ch = new double[numberOfBeans][numberOfBeans][numberOfBeans];
	         
	        for(int x = 0; x < image.getWidth(); x++)
	            for(int y = 0; y < image.getHeight(); y++) {
	                Color color = new Color( image.getRGB(x, y));
	             
	                int red = color.getRed();
	                int green = color.getGreen();
	                int blue = color.getBlue();
	                ch[red/nipb][green/nipb][blue/nipb]++;
	            }
	       return ch;

	}
	public static float[] Haralick4feature(BufferedImage image) {
		return null;
	}
	public static float[] huInvariantMoments(BufferedImage image){
		return new HuInvariantMoment(image).getHUsInvariantMoments();
	}
	
}

