package controller.command;

import model.ImageModel;

/**
 * Represents the operation to create a intensity
 * version of an image to the model.
 */
public class IntensityCommand implements ImageCommand {
  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.intensity(id, dest);
  }
}
