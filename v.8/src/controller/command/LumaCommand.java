package controller.command;

import model.ImageModel;


/**
 * Represents the operation to create a luma
 * version of an image to the model.
 */
public class LumaCommand implements ImageCommand {

  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.luma(id, dest);
  }
}
