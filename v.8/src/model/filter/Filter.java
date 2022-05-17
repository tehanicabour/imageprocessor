package model.filter;

import model.Image;
import model.Pixel;

import java.util.ArrayList;

/**
 * This interface represents all the operations that a Filter can perform.
 */
public interface Filter {

  /**
   * Applies a kernel onto an image and gets the filtered image.
   *
   * @param image  the input image
   * @param kernel the odd by odd filter that is applied on the Image
   * @return a 2-d ArrayList of Pixels component of an Image
   * @throws IllegalArgumentException if any of the kernel dimensions are even
   */

  ArrayList<ArrayList<Pixel>> applyFilter(Image image, double[][] kernel)
      throws IllegalArgumentException;
}
