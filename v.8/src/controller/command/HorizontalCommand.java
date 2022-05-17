package controller.command;

import model.ImageModel;

/**
 * Represents the operation to create a horizontally-flipped
 * version of an image to the model.
 */
public class HorizontalCommand implements ImageCommand {
  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.horizontallyFlip(id, dest);
  }
}
