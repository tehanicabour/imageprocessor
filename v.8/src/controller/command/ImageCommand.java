package controller.command;

import model.ImageModel;


/**
 * Represents the interface for operations on an Image
 * that can be accessed via the model through the
 * controller.
 */
public interface ImageCommand {

  /**
   * Enacts the given command as an operation in the model
   * given an Image and the name of the final edited image.
   *
   * @param im   the model of an Image Processor
   * @param id   the name of the image you want processed
   * @param dest the desired name of the processed image
   */
  void execute(ImageModel im, String id, String dest);
}
