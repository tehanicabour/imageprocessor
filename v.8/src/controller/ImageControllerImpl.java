package controller;

import controller.command.BlueComponentCommand;
import controller.command.BlurCommand;
import controller.command.BrightenCommand;
import controller.command.DownscaleCommand;
import controller.command.GreenComponentCommand;
import controller.command.HorizontalCommand;
import controller.command.ImageCommand;
import controller.command.IntensityCommand;
import controller.command.LoadCommand;
import controller.command.LumaCommand;
import controller.command.PartialImageCommand;
import controller.command.RedComponentCommand;
import controller.command.SaveCommand;
import controller.command.SepiaCommand;
import controller.command.SharpenCommand;
import controller.command.VerticalCommand;
import controller.command.ValueCommand;
import model.ImageModel;
import view.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;

/**
 * Represents a controller object that enables user to interact with the model.
 */
public class ImageControllerImpl implements ImageController {

  ImageModel model;
  ImageView view;
  Readable readable;

  /**
   * Constructs an instance of the Controller for an Image Processor given
   * a model, view, and something to read to.
   *
   * @param model    the model of an Image Processor
   * @param view     the view of an ImageProcessor
   * @param readable what is being read into the controller to enact commands
   */
  public ImageControllerImpl(ImageModel model, ImageView view, Readable readable)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Cannot have null model or view.");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;

  }

  @Override
  public String beginRead() {
    Scanner scan = new Scanner(this.readable);

    Map<String, Function<Scanner, ImageCommand>> possibleCommands =
            new HashMap<String, Function<Scanner, ImageCommand>>();

    Stack<ImageCommand> commandStack = new Stack<ImageCommand>();

    possibleCommands.put("luma", sc -> new LumaCommand());
    possibleCommands.put("intensity", sc -> new IntensityCommand());
    possibleCommands.put("value-component", sc -> new ValueCommand());
    possibleCommands.put("horizontal-flip", sc -> new HorizontalCommand());
    possibleCommands.put("vertical-flip", sc -> new VerticalCommand());
    possibleCommands.put("brighten", sc -> new BrightenCommand(sc.nextInt()));
    possibleCommands.put("red-component", sc -> new RedComponentCommand());
    possibleCommands.put("green-component", sc -> new GreenComponentCommand());
    possibleCommands.put("blue-component", sc -> new BlueComponentCommand());
    possibleCommands.put("save", sc -> new SaveCommand());
    possibleCommands.put("load", sc -> new LoadCommand());
    possibleCommands.put("sepia", sc -> new SepiaCommand());
    possibleCommands.put("sharpen", sc -> new SharpenCommand());
    possibleCommands.put("blur", sc -> new BlurCommand());
    possibleCommands.put("downscale", sc -> new DownscaleCommand(sc.nextInt(), sc.nextInt()));
    possibleCommands.put("partial-image", sc -> new PartialImageCommand(sc.next(), sc.next()));


    while (scan.hasNext()) {
      ImageCommand command;

      String in = scan.next();

      if (in.contains("-file")) {
        try {
          System.out.println("Looking for file...");
          scan = new Scanner(new FileInputStream(scan.next()));
          in = scan.next();
        } catch (IOException io) {
          return "File not found.";
        }
      }

      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        return "program quit";
      }
      Function<Scanner, ImageCommand> cmd = possibleCommands.getOrDefault(in, null);
      if (cmd == null) {
        this.view.renderMessage("Invalid command");
      } else {
        try {
          command = cmd.apply(scan);
          commandStack.add(command);
          String id = scan.next();
          String dest = scan.next();
          command.execute(model, id, dest);
        } catch (IllegalArgumentException ae) {
          this.view.renderMessage("Invalid values");
        }
      }
    }
    return "program start";
  }
}

