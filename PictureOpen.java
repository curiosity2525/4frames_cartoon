import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PictureOpen extends JFrame{

	//ファイル選択ダイアログを表示し、選択
	public String open(){
		JFileChooser fc = new JFileChooser();

		//画像ファイルの拡張子を設定
		fc.setFileFilter(new FileNameExtensionFilter("画像ファイル", "png", "jpg", "Jpeg", "GIF", "bmp"));
		
		//ファイル選択ダイアログを表示、戻り値がAPPROVE_OPTIONの場合、画像ファイルを開く
		String result = "no";

		if(fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			File f = fc.getSelectedFile();
			System.out.println(f.getPath());
			result = f.getPath();
		}
		return result;
	}
}

























