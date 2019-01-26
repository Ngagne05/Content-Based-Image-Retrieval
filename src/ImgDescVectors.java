import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;


public class ImgDescVectors implements Serializable {

	double[][][] hist;
	float[] HuMoments;
	float[] har4feature; 
	String path;
	 public ImgDescVectors() {
	
		
	}
		 
	 static void  store(){
		
	}
	static void storeAllVectors(String[] pathArray) throws IOException{
		
			int n=pathArray.length;	   
			ImgDescVectors[] img=new ImgDescVectors[n];
		   for (int i = 0; i < n; i++) {
			  
			  	//double[][][]imageCurrentHist= descriptors.histogram(ImageIO.read(new File("dataset/"+pathArray[i]))); 
			  	img[i]=new ImgDescVectors();
			  	img[i].path=pathArray[i];			  	
			   img[i].hist=descriptors.histogram(ImageIO.read(new File("dataset/"+img[i].path)));
			//   img[i].HuMoments=descriptors.huInvariantMoments(ImageIO.read(new File("dataset/"+img[i].path)));
			//   img[i].har4feature =descriptors.Haralick4feature(ImageIO.read(new File("dataset/"+img[i].path)));
				
			}
		   
		   FileOutputStream f = new FileOutputStream("file.txt");
		   ObjectOutput s = new ObjectOutputStream(f);
		   s.writeObject(img);	
		   s.close();
	}
	ImgDescVectors[] retrieveAllVectors() throws IOException, ClassNotFoundException{
		FileInputStream in = new FileInputStream("file.txt");
	    ObjectInputStream s = new ObjectInputStream(in);
	    
	    return (ImgDescVectors[]) s.readObject();
	}
	static	ImgDescVectors[] getAllVectors(File imgDirectory) throws IOException, ClassNotFoundException{
		String[] pathArray=imgDirectory.list();
		int n=pathArray.length;	   
		ImgDescVectors[]  img=new ImgDescVectors[n];
		   for (int i = 0; i < n; i++) {
			  
			  	//double[][][]imageCurrentHist= descriptors.histogram(ImageIO.read(new File("dataset/"+pathArray[i]))); 
			  	img[i]=new ImgDescVectors();
			  	img[i].path=pathArray[i];			  	
			   img[i].hist=descriptors.histogram(ImageIO.read(new File("dataset/"+img[i].path)));
			  /* img[i].HuMoments=descriptors.huInvariantMoments(ImageIO.read(new File("dataset/"+img[i].path)));
			   img[i].har4feature =descriptors.Haralick4feature(ImageIO.read(new File("dataset/"+img[i].path)));*/
			 //  System.out.println(i);
			}		   		   
	    
	    return img;
	}
}
