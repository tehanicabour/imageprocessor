package controller.command;

import model.ImageModel;


/**
 * Represents the operation to vertically flip an image in the model.
 */

public class VerticalCommand implements ImageCommand {

  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.verticallyFlip(id, dest);
  }
}
