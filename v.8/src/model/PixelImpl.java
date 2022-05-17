package model;

import model.transformation.Transformation;
import model.transformation.TransformationImpl;

/**
 * Represents a simple implementing class of a Pixel.
 */
public class PixelImpl implements Pixel {

  private final int red;
  private final int green;
  private final int blue;
  private final Position pos;

  /**
   * Constructs a Pixel with 3 RGB components and a position.
   *
   * @param red   the red component of a Pixel
   * @param green the green component of a Pixel
   * @param blue  the blue component of a Pixel
   * @param pos   the position of a pixel
   */
  public PixelImpl(int red, int green, int blue, Position pos) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.pos = pos;
  }

  /**
   * Constructs a Pixel given another Pixel.
   *
   * @param other the given Pixel whose components are replicated
   */
  public PixelImpl(PixelImpl other) {
    this.red = other.red;
    this.green = other.green;
    this.blue = other.blue;
    this.pos = other.pos;
  }


  @Override
  public Pixel getValue() {

    if (this.red == Math.max(this.red, Math.max(this.green, this.blue))) {
      return this.getRedComponent();
    } else if (this.green == Math.max(this.red, Math.max(this.green, this.blue))) {
      return this.getGreenComponent();
    } else {
      return this.getBlueComponent();
    }


  }

  /*
  @Override
  public Pixel getValue() {
    return pixelHelper(Math.max(this.red, Math.max(this.green, this.blue)));
  }
   */

  @Override
  public Pixel getIntensity() {

    double[][] intensity = new double[][]
        {{1.0 / 3, 1.0 / 3, 1.0 / 3},
            {1.0 / 3, 1.0 / 3, 1.0 / 3},
            {1.0 / 3, 1.0 / 3, 1.0 / 3}};

    return transformHelper(intensity);
  }

  /*
  @Override
  public Pixel getIntensity() {
    return pixelHelper(roundDoubleToInt((1.0 * this.red + this.green + this.blue) / 3));
  }
   */

  @Override
  public Pixel getLuma() {
    double[][] luma = new double[][]
        {{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};

    return transformHelper(luma);
  }

  /*
  @Override
  public Pixel getLuma() {
    return pixelHelper(roundDoubleToInt(0.2126 * this.red + 0.7152 * this.green
            + 0.0722 * this.blue));
  }
   */

  @Override
  public int getRed() {

    return this.red;
  }

  /*
  @Override
  public int getRed() {
    return this.red;
  }
   */

  @Override
  public int getGreen() {
    return this.green;
  }

  /*
  @Override
  public int getGreen() {
    return this.green;
  }
   */

  @Override
  public int getBlue() {
    return this.blue;
  }

  /*
  @Override
  public int getBlue() {
    return this.blue;
  }
   */

  @Override
  public Pixel getRedComponent() {

    double[][] red = new double[][]
        {{1.0, 0, 0},
            {1.0, 0, 0},
            {1.0, 0, 0}};


    return transformHelper(red);

  }

  /*
  @Override
  public Pixel getRedComponent() {
    return pixelHelper(this.red);
  }
   */

  @Override
  public Pixel getGreenComponent() {


    double[][] green = new double[][]
        {{0, 1.0, 0},
            {0, 1.0, 0},
            {0, 1.0, 0}};

    return transformHelper(green);
  }

  /*
  @Override
  public Pixel getGreenComponent() {
    return pixelHelper(this.green);
  }
   */

  @Override
  public Pixel getBlueComponent() {

    double[][] blue = new double[][]
        {{0, 0, 1.0},
            {0, 0, 1.0},
            {0, 0, 1.0}};


    return transformHelper(blue);

  }

  /*
   @Override
  public Pixel getBlueComponent() {
    return pixelHelper(this.blue);
  }
   */


  @Override
  public Pixel brighten(int change) {
    return createPixel(clamp(this.red + change),
            clamp(this.green + change), clamp(this.blue + change), this.pos);
  }

  @Override
  public Pixel sepia() {
    double[][] sepia = new double[][]
        {{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};


    return transformHelper(sepia);
  }

  @Override
  public Position getPosition() {
    return new Position(pos.getX(), pos.getY());
  }


  private Pixel transformHelper(double[][] transform) {

    Transformation t = new TransformationImpl();

    return t.applyTransform(this, transform);
  }


  protected int clamp(int component) {
    if (component < 0) {
      component = 0;
    } else if (component > 255) {
      component = 255;
    }
    return component;
  }


  protected int roundDoubleToInt(double val) {
    return Math.round(Math.round(val));
  }

  protected Pixel createPixel(int r, int g, int b, Position p) {
    return new PixelImpl(r, g, b, p);
  }

  protected Pixel pixelHelper(int val) {
    return createPixel(val, val, val, this.pos);
  }
}



