package controller.command;

import model.ImageModel;


/**
 * Represents the operation to apply the blur operation
 * on an Image.
 */
public class BlurCommand implements ImageCommand {


  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.blur(id, dest);
  }
}
