import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageRes implements Runnable {
    private File file;

    private int newWidth;
    private String dstFolder;
    private long start;

    public ImageRes(File file, int newWidth, String dstFolder, long start) {
        this.file = file;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    System.out.println("image == null");
                }
                else {
                    int newHeight = (int) Math.round(
                            image.getHeight() / (image.getWidth() / (double) newWidth)
                    );
                    BufferedImage newImage = Scalr.resize(image, newWidth, newHeight);
                    File newFile = new File(dstFolder + "/" + file.getName());
                    ImageIO.write(newImage, "jpg", newFile);
                }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
