package controller;

import java.util.Objects;

import controller.command.BlueComponentCommand;
import controller.command.BlurCommand;
import controller.command.BrightenCommand;
import controller.command.DownscaleCommand;
import controller.command.GreenComponentCommand;
import controller.command.HorizontalCommand;
import controller.command.IntensityCommand;
import controller.command.LoadCommand;
import controller.command.LumaCommand;
import controller.command.RedComponentCommand;
import controller.command.SaveCommand;
import controller.command.SepiaCommand;
import controller.command.SharpenCommand;
import controller.command.ValueCommand;
import controller.command.VerticalCommand;
import model.ImageModel;
import view.GUIView;
import view.Listener;

/**
 * Represents the controller which communicates with the GUIView class. Uses the listener class's
 * methods to listen and act to events within GUI via commands.
 */
public class GUIControllerImpl implements Listener, ImageController {

  private final ImageModel model;
  private final GUIView guiView;

  /**
   * Constructs instance of GUIControllerImpl. Sets itself as a listener for the inputted GUI.
   * Listens to GUI view and acts on model accordingly.
   *
   * @param model the model to control
   * @param view  the GUI to listen to
   */
  public GUIControllerImpl(ImageModel model, GUIView view) {
    this.model = Objects.requireNonNull(model);
    this.guiView = Objects.requireNonNull(view);
    this.guiView.addListener(this);
  }

  @Override
  public String beginRead() {

    this.guiView.makeVisible(true);
    return "GUIView start";
  }

  /**
   * Applies the given transformation.
   *
   * @param transformation transformation user wants on image
   */
  @Override
  public void applyTransformationListen(String transformation,
                                        String id, String newID, int field1, int field2) {
    switch (transformation) {
      case "luma":
        new LumaCommand().execute(model, id, newID);
        return;
      case "intensity":
        new IntensityCommand().execute(model, id, newID);
        return;
      case "value-component":
        new ValueCommand().execute(model, id, newID);
        return;
      case "horizontal-flip":
        new HorizontalCommand().execute(model, id, newID);
        return;
      case "vertical-flip":
        new VerticalCommand().execute(model, id, newID);
        return;
      case "red-component":
        new RedComponentCommand().execute(model, id, newID);
        return;
      case "green-component":
        new GreenComponentCommand().execute(model, id, newID);
        return;
      case "blue-component":
        new BlueComponentCommand().execute(model, id, newID);
        return;
      case "sepia":
        new SepiaCommand().execute(model, id, newID);
        return;
      case "sharpen":
        new SharpenCommand().execute(model, id, newID);
        return;
      case "blur":
        new BlurCommand().execute(model, id, newID);
        return;
      case "brighten":
        new BrightenCommand(field1).execute(model, id, newID);
        return;
      case "downscale":
        try {
          new DownscaleCommand(field1, field2).execute(model, id, newID);
        } catch (IllegalArgumentException | NullPointerException e) {
          return;
        }
        return;
      default:
        this.guiView.renderMessage("Invalid transformation.");
    }
  }

  /**
   * Loads image with the given file name and allocates it the given id.
   *
   * @param filename name of the image file
   * @param id       id of loaded image
   */
  @Override
  public void loadImageGUI(String filename, String id) {
    if (this.checkString(filename) || this.checkString(id)) {
      if (filename.endsWith("jpg")) {
        new LoadCommand()
                .execute(this.model, id, filename);
      } else if (filename.endsWith("png")) {
        new LoadCommand()
                .execute(this.model, id, filename);
      } else if (filename.endsWith("ppm")) {
        new LoadCommand()
                .execute(this.model, id, filename);
      } else {
        this.guiView.renderMessage("This file type is not supported!");
      }
    }
  }

  /**
   * Saves the entire image in a file name.
   *
   * @param filename the name of the saved image file
   * @param id       the id of the image to save
   */
  @Override
  public void saveImageGUI(String filename, String id) {
    if (this.checkString(filename) || this.checkString(id)) {
      new SaveCommand().execute(this.model, id, filename);
    }
  }

  /**
   * Verifies that given String is valid.
   *
   * @param toCheck String verified
   * @return true if String valid
   */
  private boolean checkString(String toCheck) {
    if (toCheck == null) {
      this.guiView.renderMessage("Please enter something!");
      return false;
    } else {
      return true;
    }
  }

}
