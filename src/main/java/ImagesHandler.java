import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ImagesHandler {
    private  List<BufferedImage> images = new ArrayList<BufferedImage>();
    public static  Map<Integer,BufferedImage> selectedImages = new HashMap<Integer, BufferedImage>();
    private List<Map.Entry<BufferedImage,BufferedImage>> pictures = new ArrayList<Map.Entry<BufferedImage, BufferedImage>>();
    private JFrame frame;
    private MouseListener mouseListener;

    public ImagesHandler() {
        initFrame();
    }

//    public ImagesHandler(List<Map.Entry<BufferedImage, BufferedImage>> pictures) {
//        JFrame frame = new JFrame();
//        frame.getContentPane().setLayout(new FlowLayout());
//        frame.setBounds(0,0,1000,1000);
//        this.pictures = pictures;
//    }

    public void displayImages(List<BufferedImage> images){
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setBounds(0,0,1000,1000);
        for (BufferedImage image : images) {
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label);
        }
        frame.pack();
        frame.setVisible(true);
}
    public void displayThumbnails(){
        frame.getContentPane().setLayout(new FlowLayout());
        for (Map.Entry<BufferedImage,BufferedImage> entry : pictures) {
            JLabel label = new JLabel(new ImageIcon(entry.getValue()));
            frame.getContentPane().add(label);
        }
        frame.pack();
        frame.setVisible(true);
    }
    public void initFrame() {
        this.frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.setBounds(0, 0, 1000, 1000);
    }

    public void showPicture(int jsonPictureIndex, String originalImageLink, BufferedImage image) {
        ImageShowingLabel label = new ImageShowingLabel(originalImageLink,image,jsonPictureIndex);
        frame.getContentPane().add(label);
        if(!frame.isVisible())
            frame.setVisible(true);
    }
}