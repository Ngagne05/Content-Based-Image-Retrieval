import java.awt.Color;
import java.awt.image.BufferedImage;


public class HuInvariantMoment {
	private BufferedImage image;
	private float xg; //gravity center abscisse
	private float yg; // gravity center ordonne
	private float[] moments;
	public HuInvariantMoment(BufferedImage image)  {
		this.image=image;
		
		moments= new float[7];
		xg= geometricMoment(1,0)/geometricMoment(0,0);;
		yg= geometricMoment(0,1)/geometricMoment(0,0);
		
		//moments = getHUsInvariantMoments();
		
	}
	public float[] getHUsInvariantMoments() {
		moments[0] = normalizedCentredMoments(2, 0) + normalizedCentredMoments(0, 2);
        moments[1] = (float) Math.pow((normalizedCentredMoments(2, 0) - normalizedCentredMoments(0, 2)), 2) 
        		+ (float) Math.pow((2 * normalizedCentredMoments(1, 1)), 2);
        moments[2] = (float) Math.pow((normalizedCentredMoments(3, 0) - 3 * normalizedCentredMoments(1, 2)), 2)
        		+ (float) Math.pow((3 * normalizedCentredMoments(2, 1) - normalizedCentredMoments(0, 3)), 2);
        moments[3] = (float) Math.pow((normalizedCentredMoments(3, 0) + normalizedCentredMoments(1, 2)), 2)
        		+ (float) Math.pow((normalizedCentredMoments(2, 1)+ normalizedCentredMoments(0, 3)), 2);
        moments[4] = (normalizedCentredMoments(3, 0) - 3 * normalizedCentredMoments(1, 2)) * (normalizedCentredMoments(3, 0) 
        		+ normalizedCentredMoments(1, 2)) * ( (float) Math.pow((normalizedCentredMoments(3, 0) 
        				+ normalizedCentredMoments(1, 2)), 2) - 3 * (float) Math.pow((normalizedCentredMoments(2, 1) 
        						+ normalizedCentredMoments(0, 3)), 2)) 
        						+ (3 * normalizedCentredMoments(2, 1) - normalizedCentredMoments(0, 3)) * (normalizedCentredMoments(2, 1) 
        								+ normalizedCentredMoments(0, 3)) * (3 * (float) Math.pow((normalizedCentredMoments(0, 3) 
        										+ normalizedCentredMoments(1, 2)), 2) - (float) Math.pow((normalizedCentredMoments(2, 1) 
        												+ normalizedCentredMoments(0, 3)), 2));
        moments[5] = (normalizedCentredMoments(2, 0) - normalizedCentredMoments(0, 2)) * ((float) Math.pow((normalizedCentredMoments(3, 0) 
        		+ normalizedCentredMoments(1, 2)), 2)) - ((float) Math.pow((normalizedCentredMoments(2, 1) 
        				+ normalizedCentredMoments(0, 3)), 2)) + 4 * normalizedCentredMoments(1, 1) * (normalizedCentredMoments(3, 0) 
        						+ normalizedCentredMoments(1, 2)) * (normalizedCentredMoments(2, 1) + normalizedCentredMoments(0, 3));
        moments[6] = (3 * normalizedCentredMoments(2, 1) - normalizedCentredMoments(0, 3)) * (normalizedCentredMoments(3, 0) 
        		+ normalizedCentredMoments(1, 2)) * (float) Math.pow(((float) Math.pow((normalizedCentredMoments(3, 0) + normalizedCentredMoments(1, 2)), 2) - 3 * (normalizedCentredMoments(2, 1) 
        				+ normalizedCentredMoments(0, 3))), 2) + (normalizedCentredMoments(3, 0) - 3 * normalizedCentredMoments(1, 2)) * (normalizedCentredMoments(2, 1) 
        						+ normalizedCentredMoments(0, 3)) * (3 * ((float) Math.pow((normalizedCentredMoments(3, 0) 
        								+ normalizedCentredMoments(1, 2)), 2)) - ((float) Math.pow((normalizedCentredMoments(2, 1) 
        										+ normalizedCentredMoments(0, 3)), 2)));
     //   System.out.println(moments[0]+ " "+moments[1]+ " "+moments[2]+ " "+moments[3]+ " "+moments[4]+ " "+moments[5]+ " "+moments[6]);//+ " "+
		
		return moments;
		
	}
	private float normalizedCentredMoments(int p, int q) {
		float quotient = ( p + q )/2 + 1;
		return (centredMoments(p,q)/((float) Math.pow(centredMoments(0,0),quotient)));
	}
	private float centredMoments(int p, int q) {
		float n = 0;
		float value;
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				//value = (float) image.getData().getSample(i,j, 0);
				Color rgb = new Color(image.getRGB(0, 0));
				float newR = rgb.getRed();
				float newG = rgb.getGreen();
				float newB = rgb.getBlue();				
				
				value = (newR + newG + newB) / 3;
				if(value != 0){
					value = 1;
				}
				else{
					value = 0;
				}
				n += (float) Math.pow(( i - xg ), p) * (float) Math.pow(( j - yg ), q) * value;

			}
		}
		return n;
	}
	private float geometricMoment(int p, int q) {
		double intensityValue;
		float m = 0;
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
			//	intensityValue = (float) image.getData().getSample(i, j, 0);
				Color rgb = new Color(image.getRGB(0, 0));
				float newR = rgb.getRed();
				float newG = rgb.getGreen();
				float newB = rgb.getBlue();				
				
				intensityValue = (newR + newG + newB) / 3;
				if(intensityValue != 0){
					intensityValue = 1;
				}
				else{
					intensityValue = 0;
				}
				m += (float) Math.pow(i,p) * (float) Math.pow(j,q) * intensityValue;
			}

		}
		return m;		
	}
/*	public float[] getMoments() {
		return moments;
	}*/
}
