package model.transformation;

import model.Pixel;
import model.PixelImpl;


/**
 * Represents the concrete implementing class for a Transformation object
 * that can transforms a Pixel.
 */
public class TransformationImpl implements Transformation {

  @Override
  public Pixel applyTransform(Pixel pixel, double[][] transform) throws
      IllegalArgumentException {

    if (transform.length != 3 || transform[0].length != 3
        || transform[1].length != 3 || transform[2].length != 3) {
      throw new IllegalArgumentException("Transformation arrays must be 3 x 3");
    }

    int[] tempPixel = new int[3];

    for (int i = 0; i < 3; i++) {
      tempPixel[i] = doubleToInt(transform[i][0] * pixel.getRed()
                                 + transform[i][1] * pixel.getGreen()
                                 + transform[i][2] * pixel.getBlue());
    }

    return new PixelImpl(clamp(tempPixel[0]),
        clamp(tempPixel[1]),
        clamp(tempPixel[2]),
        pixel.getPosition());
  }


  private int doubleToInt(double val) {
    return Math.round(Math.round(val));
  }

  private int clamp(int component) {
    if (component < 0) {
      component = 0;
    } else if (component > 255) {
      component = 255;
    }
    return component;
  }


}
