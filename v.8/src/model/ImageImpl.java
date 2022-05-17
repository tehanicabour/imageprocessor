package model;

import java.util.ArrayList;

/**
 * Represents an Image as a 2D Arraylist of its pixels.
 */
public class ImageImpl extends AbstractImage {

  /**
   * Constructs an image given a 2D Arraylist of pixels.
   *
   * @param pixels 2D Arraylist of pixels
   */
  public ImageImpl(ArrayList<ArrayList<Pixel>> pixels) throws IllegalArgumentException {
    super(pixels);
  }

  //CHANGE -->
  @Override
  public int[] getHistogram(String type) {
    //action being either red, green, or blue component
    int[] histogram = new int[256];
    for (int i = 0; i < 256; i++) {
      histogram[i] = 0;
    }

    for (ArrayList<Pixel> pixel : pixels) {
      for (Pixel p : pixel) {
        for (int i = 0; i < 256; i++) {
          switch (type) {
            case "red":
              if (p.getRed() == i) {
                histogram[i]++;
              }
              break;
            case "blue":
              if (p.getBlue() == i) {
                histogram[i]++;
              }
              break;
            case "green":
              if (p.getGreen() == i) {
                histogram[i]++;
              }
              break;
            case "intensity":
              if (((p.getBlue() + p.getGreen() + p.getRed()) / 3) == i) {
                histogram[i]++;
                System.out.println("intensity");
              }
              break;
            default:
              throw new IllegalArgumentException("Invalid color chosen.");
          }

        }
      }
    }
    return histogram;
  }

  @Override
  protected Image createImage(ArrayList<ArrayList<Pixel>> pixels) {
    return new ImageImpl(pixels);
  }

  @Override
  public int getHeight() {
    return pixels.size();
  }

  @Override
  public int getWidth() {
    return pixels.get(0).size();
  }


}
