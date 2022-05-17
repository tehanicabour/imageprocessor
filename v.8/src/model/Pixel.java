package model;

/**
 * This interface represents the operations offered by the Pixel class.
 */
public interface Pixel {

  /**
   * Gets a pixel with with all three RGB values set to the previous maximum RGB value.
   *
   * @return a Pixel with its three components maximized
   */
  Pixel getValue();

  /**
   * Gets a pixel brightened with the intensity formula.
   *
   * @return a Pixel with its RGB components intensified
   */
  Pixel getIntensity();

  /**
   * Gets a pixel brightened with the luma formula.
   *
   * @return a Pixel with luma formula being applied
   */
  Pixel getLuma();

  /**
   * Returns the int value for the red color component of a Pixel.
   *
   * @return the int value for the red color component of a Pixel
   */
  int getRed();

  /**
   * Returns the int value for the green color component of a Pixel.
   *
   * @return the int value for the green color component of a Pixel
   */
  int getGreen();

  /**
   * Returns the int value for the blue color component of a Pixel.
   *
   * @return the int value for the blue color component of a Pixel
   */
  int getBlue();

  /**
   * Returns a Pixel with all RGB components replaced with value of the red component.
   *
   * @return a Pixel whose RGB values have been replaced with all Red
   */
  Pixel getRedComponent();

  /**
   * Returns a Pixel with all RGB components replaced with value of the green component.
   *
   * @return a Pixel whose RGB values have been replaced with all green
   */
  Pixel getGreenComponent();

  /**
   * Returns a Pixel with all RGB components replaced with value of the blue component.
   *
   * @return a Pixel whose RGB values have been replaced with all blue
   */
  Pixel getBlueComponent();

  /**
   * Returns a Pixel that is brightened by a given increment.
   *
   * @param change the desired increment to brighten the pixels
   * @return a Pixel whose RGB values have been brightened by the given increment
   */
  Pixel brighten(int change);


  Pixel sepia();

  Position getPosition();

}
