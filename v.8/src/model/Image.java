package model;

import java.util.ArrayList;

/**
 * Represents the interface of an image, with methods to be implemented by image subclasses.
 */
public interface Image {

  /**
   * Returns the height of the given image.
   *
   * @return the height of a given image
   */
  int getHeight();

  /**
   * Returns the width of the given a image.
   *
   * @return the width of the given image
   */
  int getWidth();

  /**
   * Gets an image with all its pixels being set to their largest RGB component.
   *
   * @return an  image with all its pixels being set to their largest RGB component
   */
  Image getValue();

  /**
   * Gets an image with the intensity formula being applied to all its pixels.
   *
   * @return an image with the intensity formula being applied to all its pixels
   */
  Image getIntensity();

  /**
   * Gets an image with the luma formula being applied to all its pixels.
   *
   * @return an image with the luma formula being applied to all its pixels
   */
  Image getLuma();

  /**
   * Gets an image with all its pixels having the red grayscale channel applied.
   *
   * @return an image with all its pixels having the red grayscale channel applied
   */
  Image getRedComponent();

  /**
   * Gets an image with all its pixels having the green grayscale channel applied.
   *
   * @return an image with all its pixels having the green grayscale channel applied
   */
  Image getGreenComponent();

  /**
   * Gets an image with all its pixels having the blue grayscale channel applied.
   *
   * @return an image with all its pixels having the blue grayscale channel applied
   */
  Image getBlueComponent();

  /**
   * Gets an image brightened by a given increment.
   *
   * @param change represents the desired increment to brighten by
   * @return an image brighten by the given increment
   */
  Image brighten(int change);

  /**
   * Gets an image with all its pixels swapped vertically.
   *
   * @return an image with its pixels swapped vertically
   */
  Image flipVertical();

  /**
   * Gets an image with all its pixels swapped horizontally.
   *
   * @return an image with its pixels swapped horizontally
   */
  Image flipHorizontal();

  /**
   * Saves an image with a given filepath name.
   *
   * @param filePath the desired file-path to be saved to
   * @return an image saved to the given filepath name
   * @throws IllegalArgumentException when the file cannot be found,
   *                                  if the file name already exists, or an image cannot be created
   */
  Image savePPM(String filePath) throws IllegalArgumentException;

  /**
   * Duplicates the entire given image.
   *
   * @return image to duplicate
   */
  Image duplicateImage();


  /**
   * Gets an image with all its pixels having the sepia color transform applied.
   *
   * @return an image with all its pixels having the sepia color transform applied
   */

  Image sepia();

  /**
   * Gets an image with that has been blurred.
   *
   * @return an image with that has been blurred
   */


  Image blur();

  /**
   * Gets an image with that has been sharpened.
   *
   * @return an image with that has been sharpened
   */


  Image sharpen();


  /**
   * Gets a copy of the given image's pixels.
   *
   * @return a copy of the image's pixels
   */

  ArrayList<ArrayList<Pixel>> getPixels();


  //CHANGE -->

  /**
   * Returns a histogram of the pixel values given the type.
   *
   * @param type of visualization wanted (distribution of red, green, blue, or intensity)
   * @return array of histogram's values
   */
  int[] getHistogram(String type);

  /**
   * Returns an image that has been downscaled according to the user's inputted dimensions.
   *
   * @param width  desired by user for downscaled image as an int
   * @param height desired by user for downscaled image as an int
   * @return downscaled image
   */
  Image downscale(int width, int height);

  /**
   * Returns an image that has been transformed only in area designated by mask.
   *
   * @param mask             Image used to designate area in original image which is transformed
   * @param transformedImage Image that has user's inputted transformation on the entire original
   * @return Image that has been transformed only in area designated
   */
  Image partialImageManipulation(Image mask, Image transformedImage);
}
