import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DetectRed{
	public String detectRed(String inname){

		boolean result; //結果格納フラグ
		String outname;
		String error = "error";
		BufferedImage img = null; //画像格納フラグ

		//出力ファイルの名前
		outname = inname + "_red.jpg";

		try{
			img = ImageIO.read(new File(inname));
		}
		catch(Exception e){
			e.printStackTrace();
			return error;
		}

		//画像の色の持ち方をチェック
		if(BufferedImage.TYPE_3BYTE_BGR != img.getType()){
			System.out.println("対応していないカラーモデルです!("+ inname + ")");
			return error;
		}

		//赤色変換
		int x, y;
		int width, height;
		int color, r, g, b;
		int p;
		int newcolor;

		//画像サイズの取得
		width = img.getWidth();
		height = img.getHeight();

		for(y=0; y<height; ++y){
			for(x=0; x<width; ++x){
				//(x, y)の色を取得
				color = img.getRGB(x, y);

				//色をr,g,bに分解
				r = (color >> 16) & 0xff;
				g = (color >> 8) & 0xff;
				b = color & 0xff;

				if(r > 128 && g < 100 && b < 100){
					r = 255;
					g = 255;
					b = 255;
				}

				//r,g,bの色を合成
				newcolor = (r << 16) + (g << 8) + b;

				//合成した色を(x,y)に設定
				img.setRGB(x, y, newcolor);
			}
		}

		try{
			//imgをoutnameに保存
			result = ImageIO.write(img, "jpg", new File(outname));
		}
		catch(Exception e){
			//outname(出力JPEGの)保存に失敗した時の処理
			e.printStackTrace();
			return error;
		}
		//正常に終了
		System.out.println("正常に終了しました");
		return outname;
	}
}