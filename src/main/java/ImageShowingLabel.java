import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.sun.nio.sctp.MessageInfo;

/**
 * Created by bagrram on 22/05/2018.
 */
public class ImageShowingLabel extends JLabel {
    private String originalImage;
    private int index;
    private MouseListener listener = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            try {
                ImagesHandler.selectedImages.put(index,ImageIO.read(new URL(originalImage)));
            } catch (IOException e1) {


                JOptionPane.showMessageDialog(new JFrame(), "Eggs are not supposed to be green.","n", JOptionPane.ERROR_MESSAGE);
            }
        }
    };

    public ImageShowingLabel(String originalImage, BufferedImage thumbnailImage, int index) {
        super(new ImageIcon(thumbnailImage));
        this.originalImage = originalImage;
        this.index = index;
        this.addMouseListener(listener);

    }


}
