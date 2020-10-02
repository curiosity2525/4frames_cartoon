import java.io.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.SwingUtilities;

//画像の合成
public class Stack extends JFrame implements ActionListener{

	JButton choiceButton1;
	JButton choiceButton2;
	JButton stackButton;

	ImageIcon icon1;
	ImageIcon icon2;
	ImageIcon icon3;

	ImageIcon resized1;
	ImageIcon resized2;
	ImageIcon resized3;

	String filename1 = "img1.jpg";
	String filename2 = "img1.jpg";
	
	boolean image1 = false;
	boolean image2 = false;

	//コンストラクタ
	Stack(String title){
		JPanel stackPanel = new JPanel();
		setTitle(title);
		setBounds(100, 100, 600, 400);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		icon1 = new ImageIcon2("noimage.png", this);
		resized1 = PictureBuilder.resizeIcon(icon1, 100, 100);
		
		icon2 = new ImageIcon2("noimage.png", this);
		resized2 = PictureBuilder.resizeIcon(icon2, 100, 100);
		
		icon3 = new ImageIcon2("noimage.png", this);
		resized3 = PictureBuilder.resizeIcon(icon3, 200, 200);

		choiceButton1 = new JButton("choice1", resized1);
		choiceButton2 = new JButton("choice2", resized2);
		stackButton = new JButton("stack", resized3);
		stackButton.setEnabled(false);

		choiceButton1.addActionListener(this);
		choiceButton2.addActionListener(this);
		stackButton.addActionListener(this);

		stackPanel.add(choiceButton1);
		stackPanel.add(choiceButton2);
		stackPanel.add(stackButton);

		Container contentPane = getContentPane();
		contentPane.add(stackPanel);
	}

	public void actionPerformed(ActionEvent event){
		//PictureOpenクラスのインスタンス化
		PictureOpen pictureOpen = new PictureOpen();
		

		//選択ボタン1
		if(event.getSource().equals(choiceButton1)){
			System.out.println("choice1");
			filename1 = pictureOpen.open();
			System.out.print(filename1);
			ImageIcon icon1_change = new ImageIcon(filename1);
			ImageIcon resized1_change = PictureBuilder.resizeIcon(icon1_change, 100, 100);
			choiceButton1.setIcon(resized1_change);
			image1 = true;
			if(image1 == true && image2 == true){
				stackButton.setEnabled(true);
			}else{
				stackButton.setEnabled(false);
			}
		}

		//選択ボタン2
		else if(event.getSource().equals(choiceButton2)){
			System.out.println("choice2");
			filename2 = pictureOpen.open();
			System.out.print(filename2);
			ImageIcon icon2_change = new ImageIcon(filename2);
			ImageIcon resized2_change = PictureBuilder.resizeIcon(icon2_change, 100, 100);
			choiceButton2.setIcon(resized2_change);
			image2 = true;
			if(image1 == true && image2 == true){
				stackButton.setEnabled(true);
			}else{
				stackButton.setEnabled(false);
			}
		}

		//合成ボタン
		else if(event.getSource().equals(stackButton)){
			System.out.println("stack");
			//画像の重ね合わせ
			BufferedImage img = null;

			try{
				img = ImageIO.read(new File(filename1));
			}
			catch(Exception e){
				e.printStackTrace();
				return;
			}

			BufferedImage img2 =  null;

			try{
				img2 = ImageIO.read(new File(filename2));
			}
			catch(Exception e){
				e.printStackTrace();
				return;
			}
			
			Graphics2D gr = img.createGraphics();
			gr.drawImage(img2, 0, 0, 100, 100, null);
			gr.dispose();

			String filename3 = filename1 + "_new.jpg";

			try{
				ImageIO.write(img, "jpg", new File(filename3));
			}
			catch(Exception e){
				e.printStackTrace();
				return;
			}

			ImageIcon icon3_change = new ImageIcon(filename3);
			ImageIcon resized3_change = PictureBuilder.resizeIcon(icon3_change, 200, 200);
			stackButton.setIcon(resized3_change);
						
			image1 = false;
			image2 = false;
			
			ImageIcon icon1_before = new ImageIcon("noimage2.png");
			ImageIcon resized1_before = PictureBuilder.resizeIcon(icon1_before, 100, 100);
			choiceButton1.setIcon(resized1_before);
			
			ImageIcon icon2_before = new ImageIcon("noimage2.png");
			ImageIcon resized2_before = PictureBuilder.resizeIcon(icon2_before, 100, 100);
			choiceButton2.setIcon(resized2_before);
		}
	}
	
}