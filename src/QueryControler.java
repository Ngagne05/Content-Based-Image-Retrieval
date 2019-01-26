import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class QueryControler {
	static String selectedImagePath=new String();
	
	//window w= new window();
	   public static  float euclideanDistance(double[][][] hist1,double[][][] hist2)
	    {
	        	float euclideanDist = 0;
	             int n= (hist1.length-1 )/3; 
	             for (int i=0;i<n;i++)
	             {
	            	 for (int j = 0; j < n; j++) {
						for (int k = 0; k < n; k++) {
							euclideanDist+=Math.pow((hist1[i][j][k]-hist2[i][j][k]),2);
						}	
					}
	            	 
	             }
	             return (float) Math.sqrt(euclideanDist);   
	    }
	   public static  float euclideanDistance(float[] t,float[] t2)
	    {
	        	float euclideanDist = 0;
	             int n= (t.length-1 )/3; 
	             for (int i=0;i<n;i++)
	             {	            	
							euclideanDist+=Math.pow((t[i]-t2[i]),2);					
	            	 
	             }
	             return (float) Math.sqrt(euclideanDist);   
	    }
	   static String[] query(ImgDescVectors[] imgVect) throws IOException, ClassNotFoundException{
		   
		   int n=imgVect.length;		   
		   float[] euclideanDistances= new float[n];
		   String[] resultPaths= new String[n];
		   for (int i = 0; i < n; i++) {
			  
			   
			   double[][][]imageQueryHist= descriptors.histogram(ImageIO.read(new File(selectedImagePath)));
			//	double[][][]imageCurrentHist= descriptors.histogram(ImageIO.read(new File("dataset/"+pathArray[i]))); 
			   
			   euclideanDistances[i] = euclideanDistance(imgVect[i].hist, imageQueryHist);
			//   resultPaths[i]=imgVect[i].path;
			}
			   
			   for (int i = 0; i < resultPaths.length; i++) {
				resultPaths[i]=imgVect[minimum(euclideanDistances)].path;
				//System.out.println(euclideanDistances[minimum(euclideanDistances)]+","+resultPaths[i]);
				euclideanDistances[minimum(euclideanDistances)]=Integer.MAX_VALUE; 
			}
		   
		  return resultPaths;
	  }
	
	static int minimum(float[] a){
		 int index = 0;
	       
	       for (int i=1; i<a.length; i++){

	           if (a[i] < a[index] ){	               
	               index = i;
	           }
	       }
	       return index ;

	}

}
