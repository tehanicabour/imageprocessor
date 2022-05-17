package controller.command;

import model.ImageModel;


/**
 * Represents the operation to apply the sharpen operation
 * on an Image.
 */
public class SharpenCommand implements ImageCommand {


  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.sharpen(id, dest);
  }
}
