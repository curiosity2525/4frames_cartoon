import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.net.URL;

//スタート画面
public class Start extends JFrame{
    ImageIcon tital;
    ImageIcon resize_tital;
    JButton startButton;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    CardLayout layout;
    JPanel panel1;
    JPanel panel2;

    public void init(){
    	//スタート画面の表示
        System.out.println("Start_init");
        setTitle("Start");
        setBounds(500, 500, 340, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//ラベル
        label1 = new JLabel("クリック");
    	startButton = new JButton(new ImageIcon2("tital.png", this));
		//パネル
        panel1 = new JPanel();
        panel1.add(startButton);
        panel2 = new JPanel();
    	
		//マウスクリック後に実行
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setTitle("Play");
                setBounds(500, 500, 600, 500);
                System.out.println("panel2");

                //次の画面を表示
                layout.show(getContentPane(), "panel2");
                //Playクラスのインスタンス化
                Play play = new Play();
                //Playクラスのinitを呼び出す
                play.init(panel2);
                play.setVisible(true);
            }
        });
       
        layout = new CardLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);
        contentPane.add(panel1, "panel1");
        contentPane.add(panel2, "panel2");
    }
}
