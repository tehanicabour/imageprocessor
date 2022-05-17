package model;


/**
 * Represents the model for an Image Processing and Enhancement application.
 */
public interface ImageModel extends ImageModelState {

  //CHANGE FOR HW 6: added previously protected getImage method to interface as public
  /**
   * Gets an image based off its key.
   *
   * @param id the desired image's key
   * @return an Image given its id from map of Images
   */
  Image getImage(String id);

  /**
   * Creates an image with value-component formula applied.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void maxValue(String id, String dest);

  /**
   * Creates an image with intensity formula applied.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void intensity(String id, String dest);

  /**
   * Creates an image with luma formula applied.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void luma(String id, String dest);

  /**
   * Creates an image with all its pixels set to their red
   * component.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void redComponent(String id, String dest);

  /**
   * Creates an image with all its pixels set to their green
   * component.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void greenComponent(String id, String dest);

  /**
   * Creates an image with all its pixels set to their blue
   * component.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void blueComponent(String id, String dest);

  /**
   * Creates an image that is brightened by a given increment.
   *
   * @param id     the name of the desired image to be processed
   * @param dest   the name of the newly processed image
   * @param change the desired increment to brighten the image
   */
  void brighten(String id, String dest, int change);

  /**
   * Creates an image that flipped vertically.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void verticallyFlip(String id, String dest);

  /**
   * Creates an image that flipped horizontally.
   *
   * @param id   the name of the desired image to be processed
   * @param dest the name of the newly processed image
   */
  void horizontallyFlip(String id, String dest);

  /**
   * Loads an image onto the model of the Image Processor.
   *
   * @param filePath the file-path of the new Image
   * @param id       the name of the newly loaded image
   */
  void loadPPM(String filePath, String id);

  /**
   * Saves an image from the model of the Image Processor
   * with a file-path.
   *
   * @param filePath the new file-path of the newly saved image
   * @param id       the key of the saved image
   */
  void savePPM(String filePath, String id);


  /**
   * Creates an image with a sepia filter.
   * @param id id of image to be transformed
   * @param dest id of a transformed image
   */
  void sepia(String id, String dest);

  /**
   * Creates an image that is sharpened.
   * @param id of the image to be transformed
   * @param dest id of the transformed (sharpened) image
   */
  void sharpen(String id, String dest);

  /**
   * Creates an image that is blurred.
   * @param id of the image to be transformed.
   * @param dest id of the transformed (blurred) image.
   */
  void blur(String id, String dest);

  /**
   * Creates an image that is downscaled according to given width and height.
   * @param id of image to be transformed.
   * @param dest id of the transformed (downscaled) image.
   * @param width desired width of the downscaled image.
   * @param height desired height of the downscaled image.
   */
  void downscale(String id, String dest, int width, int height);

  /**
   * Creates an image that is partially transformed according to designated mask area and
   * desired transformation.
   * @param id of image to be transformed.
   * @param dest id of the transformed image.
   * @param mask id of the mask image, which designates area to be transformed.
   * @param func desired function to be performed on part of the image.
   */
  void partialImageManipulation(String id, String dest, String mask, String func);
}
