package controller.command;

import model.ImageModel;

/**
 * Represents the command to find the partial image manipulation operation on an image.
 */
public class PartialImageCommand implements ImageCommand {
  private final String mask;
  private final String func;

  /**
   * Constructs the partial image manipulation operation given a mask image to designate area
   * and the desired transformation.
   */
  public PartialImageCommand(String mask, String func) {
    this.func = func;
    this.mask = mask;
  }

  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.partialImageManipulation(id, dest, mask, func);
  }
}
