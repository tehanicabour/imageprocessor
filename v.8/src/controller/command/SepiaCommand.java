package controller.command;

import model.ImageModel;


/**
 * Represents the operation to apply the sepia operation
 * on an Image.
 */
public class SepiaCommand implements ImageCommand {
  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.sepia(id, dest);
  }
}
