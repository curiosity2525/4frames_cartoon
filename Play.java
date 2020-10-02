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
import java.awt.*;
import javax.swing.JFrame;


public class Play extends JFrame implements ActionListener{
	//ボタン
	JButton editButton;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton animeButton;

	//画像
	ImageIcon icon1;
	ImageIcon icon2;
	ImageIcon icon3;
	ImageIcon icon4;

	//リサイズ後の画像
	ImageIcon resized1;
	ImageIcon resized2;
	ImageIcon resized3;
	ImageIcon resized4;
	
	//画像を選択したかどうか
	boolean image1 = false;
	boolean image2 = false;
	boolean image3 = false;
	boolean image4 = false;
	
	//選択した画像の名前
	String filename1 = "noimage.png";
	String filename2 = "noimage.png";
	String filename3 = "noimage.png";
	String filename4 = "noimage.png";

	//初期化
	public void init(JPanel panel2){
		repaint();
		System.out.println("Play_init");

		//画像
		icon1 = new ImageIcon2("noimage.png", this);
		icon2 = new ImageIcon2("noimage.png", this);
		icon3 = new ImageIcon2("noimage.png", this);
		icon4 = new ImageIcon2("noimage.png", this);

		//大きさを変更した後の画像
		resized1 = PictureBuilder.resizeIcon(icon1, 200, 200);
		resized2 = PictureBuilder.resizeIcon(icon2, 200, 200);
		resized3 = PictureBuilder.resizeIcon(icon3, 200, 200);
		resized4 = PictureBuilder.resizeIcon(icon4, 200, 200);

		//ボタン1
    	button1 = new JButton("image1", resized1);
    	panel2.add(button1);
    	button1.addActionListener(this);

    	//ボタン2
    	button2 = new JButton("image2", resized2);
    	button2.setHorizontalAlignment(JButton.CENTER);
    	panel2.add(button2);
    	button2.addActionListener(this);

    	//ボタン3
    	button3 = new JButton("image3", resized3);
    	button3.setHorizontalAlignment(JButton.CENTER);
    	panel2.add(button3);
    	button3.addActionListener(this);

    	//ボタン4
    	button4 = new JButton("image4", resized4);
    	button4.setHorizontalAlignment(JButton.CENTER);
    	panel2.add(button4);
    	button4.addActionListener(this);

    	//編集ボタン
    	editButton = new JButton("edit");
    	panel2.add(editButton, BorderLayout.WEST);
    	editButton.addActionListener(this);

		
		//アニメーション用ボタン
    	animeButton = new JButton("animation");
    	panel2.add(animeButton, BorderLayout.WEST);
    	animeButton.addActionListener(this);
		
		//アニメーションボタンの有効・無効を判断
		if(image1 == true && image2 == true && image3 == true && image4 == true){
			animeButton.setEnabled(true);
		}
		else{
			animeButton.setEnabled(false);
		}
	}

	//ボタンが押された時
	public void actionPerformed(ActionEvent event){

		//PictureOpenクラスのインスタンス化
		PictureOpen pictureOpen = new PictureOpen();

		//編集ボタン
		if(event.getSource().equals(editButton)){
			System.out.println("edit");
			
			editButton.setEnabled(false);
        	Edit edit = new Edit();
		}

		//ボタン1
		else if(event.getSource().equals(button1)){
			System.out.println("image1");
			
			filename1 = pictureOpen.open();
			System.out.print(filename1);
			ImageIcon icon1_change = new ImageIcon(filename1);
			ImageIcon resized1_change = PictureBuilder.resizeIcon(icon1_change, 200, 200);
			button1.setIcon(resized1_change);
			image1 = true;
			if(image1 == true && image2 == true && image3 == true && image4 == true){
			animeButton.setEnabled(true);
			}
		}

		//ボタン2
		else if(event.getSource().equals(button2)){
			System.out.println("image2");
			
			filename2 = pictureOpen.open();
			ImageIcon icon2_change = new ImageIcon(filename2);
			ImageIcon resized2_change = PictureBuilder.resizeIcon(icon2_change, 200, 200);
			button2.setIcon(resized2_change);
			image2 = true;
			if(image1 == true && image2 == true && image3 == true && image4 == true){
			animeButton.setEnabled(true);
			}
		}

		//ボタン3
		else if(event.getSource().equals(button3)){
			System.out.println("image3");
			
			filename3 = pictureOpen.open();
			ImageIcon icon3_change = new ImageIcon(filename3);
			ImageIcon resized3_change = PictureBuilder.resizeIcon(icon3_change, 200, 200);
			button3.setIcon(resized3_change);
			image3 = true;
			if(image1 == true && image2 == true && image3 == true && image4 == true){
			animeButton.setEnabled(true);
			}
		}

		//ボタン4
		else if(event.getSource().equals(button4)){
			System.out.println("image4");
			
			filename4 = pictureOpen.open();
			ImageIcon icon4_change = new ImageIcon(filename4);
			ImageIcon resized4_change = PictureBuilder.resizeIcon(icon4_change, 200, 200);
			button4.setIcon(resized4_change);
			image4 = true;
			if(image1 == true && image2 == true && image3 == true && image4 == true){
			animeButton.setEnabled(true);
			}
		}

		//アニメーション実行
		else if(event.getSource().equals(animeButton)){
			System.out.println("animation");
			
			Animation anime = new Animation(filename1, filename2, filename3, filename4);
			JFrame frame = new JFrame("Animation1");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.add(anime);
			frame.pack();
			frame.setVisible(true);
			
			new Thread(anime).start();
		}
	}
}