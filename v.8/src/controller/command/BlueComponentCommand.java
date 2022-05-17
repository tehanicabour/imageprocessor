package controller.command;

import model.ImageModel;

/**
 * Represents the command to find the blue-component of an image to the model.
 */
public class BlueComponentCommand implements ImageCommand {


  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.blueComponent(id, dest);
  }
}
