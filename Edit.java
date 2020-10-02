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
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;

public class Edit extends JFrame{
	//画像選択ボタン
	JButton choiceButton;
	//グレースケール変換ボタン
	JButton grayButton;
	//赤色変換ボタン
	JButton detectRedButton;
	//ネガポジ変換ボタン
	JButton negaPosiButton;
	//落書きボタン
	JButton rakugakiButton;
	//合成ボタン
	JButton stackButton;
	
	JLabel editLabel;

	//編集する画像のファイル名
	String filename = "noimage.png";
	
	//画像選択したかどうか
	boolean imgChoice = false;

	//コンストラクタ
	Edit(){
		JPanel editPanel = new JPanel();
		setTitle("Edit");
		setBounds(100, 100, 300, 320);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setVisible(true);
		
		//最初の画像
		ImageIcon editIcon = new ImageIcon2("noimage.png", this);
		ImageIcon editResize  = PictureBuilder.resizeIcon(editIcon, 200, 200);
		editLabel = new JLabel(editResize);
		
		//選択ボタン
		choiceButton = new JButton("choice");
		choiceButton.addActionListener(new myListener1());
		
		//グレースケール変換ボタン
		grayButton = new JButton("gray");
		grayButton.addActionListener(new myListener2());
		grayButton.setEnabled(false);
		
		//赤色変換ボタン
		detectRedButton = new JButton("detectRed");
		detectRedButton.addActionListener(new myListener3());
		detectRedButton.setEnabled(false);
		
		//ネガポジ変換ボタン
		negaPosiButton = new JButton("negaPosi");
		negaPosiButton.addActionListener(new myListener4());
		negaPosiButton.setEnabled(false);
		
		//落書きボタン
		rakugakiButton = new JButton("rakugaki");
		rakugakiButton.addActionListener(new myListener5());
		rakugakiButton.setEnabled(false);
		
		//合成ボタン
		stackButton = new JButton("Mix2image");
		stackButton.addActionListener(new myListener6());
		
		editPanel.add(editLabel);
		editPanel.add(choiceButton);
		editPanel.add(grayButton);
		editPanel.add(detectRedButton);
		editPanel.add(negaPosiButton);
		editPanel.add(rakugakiButton);
		editPanel.add(stackButton);
		
		Container contentPane = getContentPane();
		contentPane.add(editPanel);
	}
	
	//ファイルから画像選択
	public class myListener1 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("choiceButton");
			//PictureOpenクラスのインスタンス化
			PictureOpen pic = new PictureOpen();
			//open methodの呼び出し
			String choice_name = pic.open();
			filename = choice_name;
			System.out.println(choice_name);
			ImageIcon icon = new ImageIcon(choice_name);
			ImageIcon resize = PictureBuilder.resizeIcon(icon, 200, 200);
			editLabel.setIcon(resize);
			
			grayButton.setEnabled(true);
			detectRedButton.setEnabled(true);
			negaPosiButton.setEnabled(true);
			rakugakiButton.setEnabled(true);
		}
	}

	//グレースケール
	public class myListener2 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("grayButton");
			//GrayScaleクラスのインスタンス化
			GrayScale gray = new GrayScale();
			//GraScaleクラスのmethodの呼び出し
			String gray_img = gray.changeGray(filename); 
			System.out.println("grayed");
			ImageIcon icon2 = new ImageIcon(gray_img);
			ImageIcon resize2 = PictureBuilder.resizeIcon(icon2, 200, 200);
			editLabel.setIcon(resize2);
			
		}
	}

	//赤色変換
	public class myListener3 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("detectRedButton");
			//DetectRedクラスのインスタンス化
			DetectRed detectRed = new DetectRed();
			//DetectRedクラスのmethodの呼び出し
			String detectRed_img = detectRed.detectRed(filename); 
			System.out.println("detected Red");
			ImageIcon icon3 = new ImageIcon(detectRed_img);
			ImageIcon resize3 = PictureBuilder.resizeIcon(icon3, 200, 200);
			editLabel.setIcon(resize3);
			
		}
	}

	//ネガポジ変換
	public class myListener4 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("negaPosiButton");
			//negaPosiクラスのインスタンス化
			NegaPosi negaPosi = new NegaPosi();
			//negaposiクラスのmethodの呼び出し
			String negaPosi_img = negaPosi.negaPosi(filename); 
			System.out.println("negaposi");
			ImageIcon icon4 = new ImageIcon(negaPosi_img);
			ImageIcon resize4 = PictureBuilder.resizeIcon(icon4, 200, 200);
			editLabel.setIcon(resize4);
			
		}
	}
	
	//落書き
	public class myListener5 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("落書きボタンn");
			//Rakugakiクラスのインスタンス化
			//rakugakiButton.setEnabled(false);
			Rakugaki raku = new Rakugaki("Rakugaki", filename);
			raku.setVisible(true);
			
		}
	}
	
	//合成
	public class myListener6 implements ActionListener{
		public void actionPerformed(ActionEvent event){
			System.out.println("合成ボタン");
			//negaPosiクラスのインスタンス化
			stackButton.setEnabled(false);
			Stack stackImage = new Stack("Stack");
			stackImage.setVisible(true);
			
		}
	}
}