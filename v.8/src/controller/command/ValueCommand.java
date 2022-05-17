package controller.command;

import model.ImageModel;


/**
 * Represents the operation to create a value-component
 * version of an image in the model.
 */
public class ValueCommand implements ImageCommand {


  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.maxValue(id, dest);
  }
}
