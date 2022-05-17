package controller.command;


import model.ImageModel;

/**
 * Represents the operation to save an image in the model.
 */
public class SaveCommand implements ImageCommand {

  @Override
  public void execute(ImageModel im, String id, String dest) {
    //change im.savePPM(id, dest);
    im.savePPM(dest, id);
  }
}
