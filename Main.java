import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//スタート画面
public class Main extends JFrame {

    public static void main(String[] args) {
        System.out.println("スタートのmain");
        Main frame = new Main();
        //Startクラスのインスタンス化
        Start start = new Start();
        //Startクラスのinitを呼び出す
        start.init();
        start.setVisible(true);
    }
}
