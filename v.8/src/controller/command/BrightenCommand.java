package controller.command;

import model.ImageModel;


/**
 * Represents the command to find the brighten operation on an image
 * to the model.
 */
public class BrightenCommand implements ImageCommand {
  private final int brighten;

  /**
   * Constructs the brighten operation given an increment to brighten by.
   */
  public BrightenCommand(int brighten) {
    this.brighten = brighten;
  }

  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.brighten(id, dest, brighten);
  }
}
