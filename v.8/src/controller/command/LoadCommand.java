package controller.command;

import model.ImageModel;


/**
 * Represents the operation to load an image to the
 * model.
 */
public class LoadCommand implements ImageCommand {


  @Override
  public void execute(ImageModel im, String id, String dest) {
    //change im.loadPPM(id, dest);
    im.loadPPM(dest, id);
  }
}
