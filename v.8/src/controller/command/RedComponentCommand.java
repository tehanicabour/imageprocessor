package controller.command;

import model.ImageModel;


/**
 * Represents the operation to create a red-component
 * version of an image to the model.
 */
public class RedComponentCommand implements ImageCommand {
  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.redComponent(id, dest);
  }
}
