import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.net.*;

//jarファイルで表示できないときのためのImageIcon
public class ImageIcon2 extends ImageIcon{
  public ImageIcon2(String f, Window own){
    super();
    Image image;
    try {
      Toolkit tk = own.getToolkit();
      URL url = own.getClass().getResource(f);
      image = tk.createImage((ImageProducer)url.getContent());
      setImage(image);
    } catch (Exception e) {
      System.out.println("Image not Found!");
    }
  }
}
