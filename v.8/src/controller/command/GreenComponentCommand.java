package controller.command;

import model.ImageModel;

/**
 * Represents the operation to create a green-component
 * version of an image to the model.
 */
public class GreenComponentCommand implements ImageCommand {

  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.greenComponent(id, dest);
  }
}
