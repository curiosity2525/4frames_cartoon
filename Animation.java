import java.awt.*;
import java.awt.Image;

public class Animation extends Canvas implements Runnable {

	private final int MAX_IMAGE = 4;
    private final int WIDTH = 300;
    private final int HIGHT = 400;
    private Image[] image = new Image[MAX_IMAGE];
    private MediaTracker tracker;

	//ダブルバッファリング（オフスクリーン・バッファ用）変数
	private Graphics offg;
	private Image offImage;
	//画像の座標
	private int x = 0;			
	private int y = 0;  
	//画像の枚数カウント
	private int imageNum = 0;
	 //画像の枚数カウント制御
	private int imageCount = 1;        
	//スレッドの監視
	private boolean running = true; 

	public Animation(String file1, String file2, String file3, String file4) {
		setSize(WIDTH, HIGHT);
		setBackground(Color.black);

        Toolkit tk = Toolkit.getDefaultToolkit();
        tracker = new MediaTracker(this);

        image[0] = tk.getImage(file1);
		image[1] = tk.getImage(file2);
		image[2] = tk.getImage(file3);
		image[3] = tk.getImage(file4);

		//全イメージのダウンロードが終わるまで待つ
        try {
            tracker.waitForAll();
        }catch (InterruptedException e) {
            System.err.println("tracker error");
        }
    }

	//スレッド処理
	int num = 0;
	public void run() {
		while (running) {	
			if(num > 12){
				stopRunning();
			}
			try {
				//0.5秒スリープ
				Thread.sleep(500);		
			}
			catch (InterruptedException ex) {
				System.err.println(ex);
			}
			repaint();
			num += 1;
		}
		System.out.println("スレッド終了");
		
	}

	//スレッドの実行停止
	public void stopRunning(){
		running = false;
	}

	//画像描写が必要な場合の処理
	public void update (Graphics g) {
		//オフスクリーン・バッファへの描き込み。実際にはまだ見えない
		offg.setColor(Color.black);
		offg.fillRect(0, 0, WIDTH, HIGHT);

		//オフスクリーン・バッファへの描き込み。実際にはまだ見えない。
		offg.drawImage(image[imageNum], x, y, 300, 300, this);

		//フォントの設定
		 Font font = new Font("Serif", Font.ITALIC, 30);
                offg.setFont(font);
		offg.setColor(Color.yellow);
                offg.drawString("アニメーション実行", 10, 350);

		//オフスクリーン・バッファに描かれたものを画面に表示
		g.drawImage(offImage, 0, 0, this);
		imageNum+=imageCount;
		if(imageNum > 3){
			imageNum = 0;
		}
	}

	//キャンバスで描写の必要のあるときに呼ばれるメソッド
	public void paint(Graphics g) {

		//オフスクリーン・バッファの領域がない場合は作成
		if (offg == null) {

			//キャンバスと同じ大きさの仮想画面を生成
			offImage = createImage(getSize().width, getSize().height);

			//offimageから仮想画面描写用のグラフィックスコンテキストを取得
			offg = offImage.getGraphics();
		}

		g.drawImage(image[imageNum], x, y, 300, 300, this);

		//フォントの設定
		Font font = new Font("Serif", Font.ITALIC, 30);
		g.setFont(font);
		g.setColor(Color.yellow);
		g.drawString("アニメーション実行", 10, 350);
	}
}