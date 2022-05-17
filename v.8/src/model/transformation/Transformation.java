package model.transformation;

import model.Pixel;

/**
 * Represents all the operations that a transformation can perform.
 */
public interface Transformation {

  /**
   * Applies a color transformation onto a pixel.
   *
   * @param pixel the given pixel to be transformed
   * @param transform the 3 by 3 2-d color transformation array
   * @return the transformed Pixel
   */

  Pixel applyTransform(Pixel pixel, double[][] transform) throws
      IllegalArgumentException;
}
