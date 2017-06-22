import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Date now = new Date();

        int pixelSize = 10;

        for (int row = 0; row < img.getWidth() - pixelSize; row += pixelSize) {
            for (int col = 0; col < img.getHeight() - pixelSize; col += pixelSize) {

                int red = 0;
                int green = 0;
                int blue = 0;

                for (int pRow = row; pRow < row + pixelSize; pRow++) {
                    for (int pCol = col; pCol < col + pixelSize; pCol++) {
                        int clr = img.getRGB(pRow, pCol);
                        red += (clr & 0x00ff0000) >> 16;
                        green += (clr & 0x0000ff00) >> 8;
                        blue += clr & 0x000000ff;
                    }
                }

                Color res = new Color(red / pixelSize / pixelSize, green / pixelSize / pixelSize, blue / pixelSize / pixelSize);

                for (int pRow = row; pRow < row + pixelSize; pRow++) {
                    for (int pCol = col; pCol < col + pixelSize; pCol++) {
                        img.setRGB(pRow, pCol, res.getRGB());
                    }
                }
            }
        }

        System.out.println("\nPerformance check: " + (new Date().getTime() - now.getTime()) + " ms;\n");

        try {
            ImageIO.write(img, "jpg", new File("test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
