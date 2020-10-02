import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Rakugaki extends JFrame implements ActionListener{
	//ラベル
	JLabel label_x;
	JLabel label_y;
	JLabel label_word;
	JLabel labelX;
	JLabel labelY;
	JLabel labelWord;

	//テキストフィールド
	JTextField text_x;
	JTextField text_y;
	JTextField text_word;

	//ボタン
	JButton button_word;

	//落書き用
	ImageIcon rakugaki_img;
	ImageIcon rakugaki_resize;
	JLabel rakugaki_label;
	
	//ファイル名
	String inname;
	//ファイルの名前が被らないようにするための番号
	int file_num = 0;
	//何も書いていないときのファイル名
	String initFile;

	//コンストラクタ
	Rakugaki(String title, String filename){
		
		//何も書いていないときのファイル名を代入
		initFile = filename;
		
		setTitle(title);
		setBounds(100, 100, 300, 300);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		labelX = new JLabel("x");
		text_x = new JTextField(3);
		
		labelY = new JLabel("y");
		text_y = new JTextField(3);
		
		labelWord = new JLabel("word");

		text_word = new JTextField(10);
		button_word = new JButton("Paint");
		button_word.addActionListener(this);
		
		//何も書いていないときの画像を表示
		rakugaki_img = new ImageIcon(initFile);
		rakugaki_resize = PictureBuilder.resizeIcon(rakugaki_img, 200, 200);
		rakugaki_label = new JLabel(rakugaki_resize);

		JPanel p = new JPanel();

		p.add(labelX);
		p.add(text_x);
		p.add(labelY);
		p.add(text_y);
		p.add(labelWord);
		p.add(text_word);
		p.add(button_word);
		p.add(rakugaki_label);

		label_x = new JLabel();
		label_y = new JLabel();
		label_word = new JLabel();

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.CENTER);
		contentPane.add(label_x, BorderLayout.WEST);
		contentPane.add(label_y, BorderLayout.EAST);
		contentPane.add(label_word, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent event){
		
		if(event.getSource().equals(button_word)){
			System.out.println("button_word");
			label_word.setText(text_word.getText());

			BufferedImage image = null;

			//ファイル読み込み
			try{
				image = ImageIO.read(new File(initFile));
			}catch(Exception e){
				e.printStackTrace();
			}

			Graphics graphics = image.createGraphics();

			//フォント
			Font font = new Font("HPG行書体", Font.BOLD, 50);
			graphics.setFont(font);

			//文字の描画
			graphics.setColor(Color.RED);
			if(text_x.getText() != null || text_y.getText() != null){
				System.out.println(text_x.getText());
				System.out.println(text_y.getText());
				graphics.drawString(text_word.getText(), Integer.parseInt(text_x.getText()), Integer.parseInt(text_y.getText()));
			}else{
				graphics.drawString("hello!", 50, 50);
			}

			//出力ファイル名
			String outname = initFile.substring(0, initFile.lastIndexOf('.')) +  "_rakugaki" + String.valueOf(file_num) + ".jpg";
			file_num += 1;
			
			
			//ファイル保存
			try{
				ImageIO.write(image, "jpg", new File(outname));
			}catch(Exception e){
				e.printStackTrace();
			}

			System.out.println("終了");

			//画像表示
			ImageIcon rakugaki_img2 = new ImageIcon(outname);
			ImageIcon rakugaki_resize2 = PictureBuilder.resizeIcon(rakugaki_img2, 200, 200);
			rakugaki_label.setIcon(rakugaki_resize2);
		}
	}
}