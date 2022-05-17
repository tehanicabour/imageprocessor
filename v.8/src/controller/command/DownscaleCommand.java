package controller.command;

import model.ImageModel;

/**
 * Represents the operation to create a downscaled version of the image given a specified new
 * height and width.
 */
public class DownscaleCommand implements ImageCommand {
  private final int width;
  private final int height;

  /**
   * Constructs the downscale operation given a width and height to adjust image by.
   */
  public DownscaleCommand(int width, int height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public void execute(ImageModel im, String id, String dest) {
    im.downscale(id, dest, this.width, this.height);
  }
}
