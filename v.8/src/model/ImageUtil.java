package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;


/**
 * This is the util class for an Image. It primarily aids in loading images from
 * a filePath into an Image
 */
public class ImageUtil {

  /**
   * Static method that a loads an Image from a file path.
   *
   * @return an Image that was loaded from a given file path
   */
  public static Image loadPPM(String filePath) {
    BufferedImage img;

    if (filePath.contains("ppm")) {
      img = ppmToBufferedImage(filePath);
    } else {
      try {
        img = ImageIO.read(new FileInputStream(filePath));
      } catch (IOException e) {
        throw new IllegalArgumentException("Invalid file.");
      }
    }
    ArrayList<ArrayList<Pixel>> buildPixels = new ArrayList<ArrayList<Pixel>>();

    int width = img.getWidth();
    System.out.println("Width of image: " + width);
    int height = img.getHeight();
    System.out.println("Height of image: " + height);

    for (int i = 0; i < height; i++) {
      ArrayList<Pixel> addJ = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int color = img.getRGB(j, i);
        Color c = new Color(color);
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        Position p = new Position(i, j);
        Pixel pix = new PixelImpl(r, g, b, p);
        addJ.add(pix);
      }
      buildPixels.add(addJ);
    }
    return new ImageImpl(buildPixels);
  }


  /**
   * Transforms a PPM image into a Buffered Image.
   *
   * @param filePath file path from which ppm file is taken.
   * @return Buffered Image version of the ppm, to be processed in loadPPM
   */
  public static BufferedImage ppmToBufferedImage(String filePath) {
    Scanner sc;
    ArrayList<ArrayList<Pixel>> buildPixels = new ArrayList<ArrayList<Pixel>>();

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filePath + " not found!");
      throw new IllegalArgumentException("Try again.");
    }

    StringBuilder builder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    return output;
  }

}