

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public  class window extends JFrame {
	JButton uploadButton;
	JTextField pathTextField;
	JButton queryButton;
	JLabel imageIconeLabel;
	JLabel[] resultImgLabel;
	JRadioButton histogramRB;
	final int N=15;
	ImgDescVectors[] imgVect ;
	
	  public window(){
		this.setSize(800,600); 
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Content based image retrieval");
		uploadButton=new JButton("Select an image");
		pathTextField=new JTextField(1);
		pathTextField.setMaximumSize(new Dimension(Integer.MAX_VALUE,pathTextField.getMinimumSize().height));
		queryButton=new JButton("search");
		imageIconeLabel=new JLabel();
		histogramRB= new JRadioButton("histogram");

		resultImgLabel = new JLabel[N];
		
		for (int i = 0; i < resultImgLabel.length; i++) {
			resultImgLabel[i]=new JLabel();
			
		}
		File imgDirectory=new File("dataset");			
		if(imgDirectory.isDirectory())
			try {
				imgVect =ImgDescVectors.getAllVectors(imgDirectory);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			 JOptionPane.showMessageDialog(null, "can't found the images folder", "message", JOptionPane.INFORMATION_MESSAGE);
		
		framesPosition();
		listeners();
	}
	  
	private void listeners() {
		
		uploadButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
												
						JFileChooser file=new JFileChooser();
						file.setCurrentDirectory(new File("dataset"));
						String[] imagesExtension={"png", "jpeg","gif","bmp"};
						FileNameExtensionFilter filter= new FileNameExtensionFilter("*.Images",imagesExtension);
						file.addChoosableFileFilter(filter);
						int result=file.showSaveDialog(null);
						if (result==JFileChooser.APPROVE_OPTION) {
							
							QueryControler.selectedImagePath=file.getSelectedFile().getAbsolutePath();
							
							imageIconeLabel.setIcon(resize(QueryControler.selectedImagePath));
							pathTextField.setText(QueryControler.selectedImagePath);
						} else {
							
						}
						
					}
				});
		queryButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {					
					
					displayResults(QueryControler.query(imgVect ));
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} 	;
			}
		});
		
	}
	private void framesPosition() {
		
		Box b1= Box.createHorizontalBox();
		b1.add(uploadButton);
		b1.add(pathTextField);
		Box b2= Box.createVerticalBox();
		b2.add(imageIconeLabel);
		b2.add(queryButton);
		Box b3 = Box.createHorizontalBox();
		//b3.add(histogramRB);
		Box bViceGeneral= Box.createVerticalBox();
		bViceGeneral.add(b1); bViceGeneral.add(b2); bViceGeneral.add(b3);
		Box bGeneral= Box.createHorizontalBox(); 
		bGeneral.add(bViceGeneral);

		JPanel jp=new JPanel();
		GridLayout resultlayout= new GridLayout(3, 3 );
		jp.setLayout(resultlayout);
		for (int i = 0; i < resultImgLabel.length; i++) {
		
			jp.add(resultImgLabel[i]);
		
		}
		
		bGeneral.add(jp);
	
		//this.getContentPane().add(bGeneral);
		this.getContentPane().add(bGeneral);
		
	}
	ImageIcon resize(String path){
		Image i=new ImageIcon(path).getImage();
		ImageIcon ii=new ImageIcon(i.getScaledInstance(200, 200, Image.SCALE_SMOOTH)); 
		return ii;
	}
	void displayResults(String[] resultsPath){
		for (int i = 0; i < resultImgLabel.length; i++) {
			//System.out.println(i+" "+resultsPath[i]);
			resultImgLabel[i].setIcon(resize("dataset/"+resultsPath[i])); //resultsPath.length is always gratter than resultImgLabel.length   
		}
	}
}

