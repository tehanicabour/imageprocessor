package view;

import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.Image;
import model.ImageModel;
import model.Pixel;

/**
 * The interactive implementation of a GUI view for an Image Manipulation and Enhancement
 * application. Provides user-friendly display.
 */
public class GUIViewImpl extends JFrame implements GUIView, ActionListener {

  private ImageModel imageModel;
  private final ArrayList<Listener> listener;
  private final JTextArea displayMessages;
  private final JLabel imageHolder;
  private String currentID;
  private DefaultListModel<String> listModel;
  private JPanel histogramPanel;
  private DrawHistogram histogramGraph;

  /**
   * Constructor for the interactive GUI implementation of the view.
   *
   * @param imageModel to be visualized
   */
  public GUIViewImpl(ImageModel imageModel) {
    super();
    this.imageModel = imageModel;
    this.listener = new ArrayList<>();
    listModel = new DefaultListModel<>();

    JFrame imageFrame = new JFrame("Image Editor");
    imageFrame.setLayout(new FlowLayout());
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    // creating the message pane which displays textview messages
    this.displayMessages = new JTextArea("Messages will be displayed \nhere.");
    this.displayMessages.setAlignmentX(Component.LEFT_ALIGNMENT);
    this.displayMessages.setLineWrap(true);
    this.displayMessages.setPreferredSize(new Dimension(200, 150));
    this.displayMessages.setBackground(new Color(240, 236, 236));
    this.displayMessages.setBorder(BorderFactory.createTitledBorder("Messages"));
    this.displayMessages.setEditable(false);
    imageFrame.add(displayMessages);


    //creating the image map part of ImageGUI
    JList<String> navigator = new JList<>(listModel);
    navigator.setBackground(new Color(240, 236, 236));
    navigator.setAlignmentX(Component.LEFT_ALIGNMENT);
    navigator.setPreferredSize(new Dimension(200, 150));
    navigator.setBorder(BorderFactory.createTitledBorder("Image Navigator"));
    navigator.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    navigator.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent evt) {
        JList list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {

          // Double-click detected
          int index = list.locationToIndex(evt.getPoint());
          setCurrentImageFrame(listModel.get(index));
          visualizeHistogram("red", Color.red);
        }
      }
    });
    imageFrame.add(navigator);

    //visualization of the histogram
    histogramPanel = new JPanel();
    histogramPanel.setPreferredSize(new Dimension(400, 300));
    histogramPanel.setBorder(BorderFactory
            .createTitledBorder("Visualization of Color Distributions"));
    histogramPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    histogramPanel.setBackground(new Color(240, 236, 236));
    this.histogramGraph = new DrawHistogram(new int[0], Color.red);

    JButton histogramShowRedButton = new JButton("red");
    histogramShowRedButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeHistogram("red", Color.red);
      }
    });
    JButton histogramShowGreenButton = new JButton("green");
    histogramShowGreenButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeHistogram("green", Color.green);
      }
    });
    JButton histogramShowBlueButton = new JButton("blue");
    histogramShowBlueButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeHistogram("blue", Color.blue);
      }
    });

    JButton histogramShowIntensityButton = new JButton("intensity");
    histogramShowBlueButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeHistogram("intensity", Color.black);
      }
    });

    histogramPanel.add(histogramShowRedButton);
    histogramPanel.add(histogramShowGreenButton);
    histogramPanel.add(histogramShowBlueButton);
    histogramPanel.add(histogramShowIntensityButton);
    histogramPanel.add(histogramGraph);
    imageFrame.add(histogramPanel);


    // creates the scrolling image panel and displays the image
    JPanel image = new JPanel();
    image.setAlignmentX(Component.RIGHT_ALIGNMENT);
    image.setBackground(new Color(240, 236, 236));
    image.setPreferredSize(new Dimension(400, 300));
    image.setBorder(BorderFactory.createTitledBorder("Current Image"));
    image.setLayout(new BoxLayout(image, BoxLayout.PAGE_AXIS));
    ImageIcon i = new ImageIcon(new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB));

    this.imageHolder = new JLabel();

    i.getImage().flush();
    this.imageHolder.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.imageHolder.setAlignmentY(Component.CENTER_ALIGNMENT);
    this.imageHolder.setBackground(new Color(240, 236, 236));
    image.add(this.imageHolder);
    JScrollPane imageScroll = new JScrollPane(image);
    imageFrame.add(imageScroll);


    // creates the menus
    JMenuBar topMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu transformMenu = new JMenu("Transformations");

    //preview menu; handles preview mode function
    //previewImageFrame
    JMenuItem previewTransformation = new JMenuItem("Preview");
    previewTransformation.addActionListener(this);
    previewTransformation.setActionCommand("preview");
    topMenuBar.add(previewTransformation);

    // file menu; handles image commands and import/export
    JMenuItem loadImage = new JMenuItem("Load Image");
    loadImage.addActionListener(this);
    loadImage.setActionCommand("loadImage");

    JMenu saveImage = new JMenu("Save Image");
    JMenuItem saveJPG = new JMenuItem(".jpeg");
    saveJPG.addActionListener(this);
    saveJPG.setActionCommand("saveJpeg");
    JMenuItem savePNG = new JMenuItem(".png");
    savePNG.addActionListener(this);
    savePNG.setActionCommand("savePng");
    JMenuItem savePPM = new JMenuItem(".ppm");
    savePPM.addActionListener(this);
    savePPM.setActionCommand("savePpm");
    JMenuItem saveBMP = new JMenuItem(".bmp");
    savePPM.addActionListener(this);
    savePPM.setActionCommand("saveBmp");

    saveImage.add(saveJPG);
    saveImage.add(savePNG);
    saveImage.add(savePPM);
    saveImage.add(saveBMP);


    fileMenu.add(loadImage);
    fileMenu.add(saveImage);


    //transformation menu: transforms images
    //GreyScales
    JMenu greyscale = new JMenu("GreyScale");
    JMenuItem luma = new JMenuItem("Luma GreyScale");
    luma.addActionListener(this);
    luma.setActionCommand("luma");
    JMenuItem intensity = new JMenuItem("Intensity GreyScale");
    intensity.addActionListener(this);
    intensity.setActionCommand("intensity");
    JMenuItem maxValueComponent = new JMenuItem("Max Component Grayscale");
    maxValueComponent.addActionListener(this);
    maxValueComponent.setActionCommand("value-component");
    //Flips
    JMenu flip = new JMenu("Flip");
    JMenuItem horizontalFlip = new JMenuItem("Horizontal-Flip");
    horizontalFlip.addActionListener(this);
    horizontalFlip.setActionCommand("horizontal-flip");
    JMenuItem verticalFlip = new JMenuItem("Vertical-Flip");
    verticalFlip.addActionListener(this);
    verticalFlip.setActionCommand("vertical-flip");
    //set so can control brightness
    JMenuItem brighten = new JMenuItem("Brighten");
    brighten.addActionListener(this);
    brighten.setActionCommand("brighten");
    JMenuItem redComponent = new JMenuItem("Red Component Grayscale");
    redComponent.addActionListener(this);
    redComponent.setActionCommand("red-component");
    JMenuItem greenComponent = new JMenuItem("Green Component Grayscale");
    greenComponent.addActionListener(this);
    greenComponent.setActionCommand("green-component");
    JMenuItem blueComponent = new JMenuItem("Blue Component Grayscale");
    blueComponent.addActionListener(this);
    blueComponent.setActionCommand("blue-component");
    JMenuItem sepia = new JMenuItem("Sepia");
    sepia.addActionListener(this);
    sepia.setActionCommand("sepia");
    JMenuItem blur = new JMenuItem("Blur");
    blur.addActionListener(this);
    blur.setActionCommand("blur");
    JMenuItem sharpen = new JMenuItem("Sharpen");
    sharpen.addActionListener(this);
    sharpen.setActionCommand("sharpen");
    JMenuItem downscale = new JMenuItem("Downscale");
    downscale.addActionListener(this);
    downscale.setActionCommand("downscale");

    transformMenu.add(greyscale);
    greyscale.add(luma);
    greyscale.add(intensity);
    greyscale.add(maxValueComponent);
    greyscale.add(redComponent);
    greyscale.add(greenComponent);
    greyscale.add(blueComponent);
    transformMenu.add(flip);
    flip.add(horizontalFlip);
    flip.add(verticalFlip);
    transformMenu.add(sepia);
    transformMenu.add(sharpen);
    transformMenu.add(blur);
    transformMenu.add(downscale);
    transformMenu.add(brighten);

    topMenuBar.add(fileMenu);
    topMenuBar.add(transformMenu);
    imageFrame.setJMenuBar(topMenuBar);


    imageFrame.pack();
    imageFrame.setLocationByPlatform(true);
    imageFrame.setVisible(true);
  }

  @Override
  public void makeVisible(boolean b) {
    if (b) {
      this.setVisible(true);
      pack();
    }
  }

  /**
   * Invoked when an action occurs.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "loadImage":
        this.loadImage();
        this.renderImageRep(currentID);
        this.renderMessage("Image " + currentID + " has been loaded.");
        visualizeHistogram("red", Color.red);
        addToImageMap(currentID);
        break;
      case "selectCurrent":
        this.setCurrentImageFrame(currentID);
        this.renderMessage("Image " + currentID + " set as current.");
        visualizeHistogram("red", Color.red);
        break;
      case "saveJpeg":
        this.saveImageFrame("jpg");
        this.renderMessage("Image " + currentID + " saved as a JPEG file.");
        break;
      case "savePng":
        this.saveImageFrame("png");
        this.renderMessage("Image " + currentID + " saved as a PNG file.");
        break;
      case "savePpm":
        this.saveImageFrame("ppm");
        this.renderMessage("Image " + currentID + " saved as a PPM file.");
        break;
      case "saveBmp":
        this.saveImageFrame("bmp");
        this.renderMessage("Image " + currentID + " saved as a BMP file.");
        break;
      case "preview":
        this.previewImageFrame();
        this.renderMessage("Image " + currentID + " shown as preview.");
        break;
      case "luma":
      case "intensity":
      case "value-component":
      case "horizontal-flip":
      case "vertical-flip":
      case "brighten":
      case "red-component":
      case "green-component":
      case "blue-component":
      case "sepia":
      case "sharpen":
      case "downscale":
      case "blur":
        String oldID = currentID;
        try {
          this.transform(e.getActionCommand());
          this.renderMessage("Transformation " + e.getActionCommand());
          this.renderImageRep(currentID);
          addToImageMap(currentID);
          visualizeHistogram("red", Color.red);
        } catch (NullPointerException ne) {
          renderMessage("Invalid value entered.");
        }
        break;
      default:
        return;
    }
  }

  /**
   * Emits the event to apply a transformation on an image.
   *
   * @param transformation given transformation on image
   */
  protected void emitTransformationEvent(String transformation, String id,
                                         String newID, int field1, int field2) {
    for (Listener l : this.listener) {
      l.applyTransformationListen(transformation, id, newID, field1, field2);
    }
  }


  /**
   * Emits the event to load an image.
   *
   * @param path path of the loaded image
   * @param id   id loaded image is saved as
   */
  protected void emitLoadImageEvent(String path, String id) {
    for (Listener l : this.listener) {
      l.loadImageGUI(path, id);
    }
  }

  /**
   * Emits the event to save an image.
   *
   * @param path filepath where image is saved
   * @param id   id of saved image
   */
  protected void emitSaveImageEvent(String path, String id) {
    for (Listener l : this.listener) {
      l.saveImageGUI(path, id);
    }
  }


  /**
   * Adds a view listener to this GUIView.
   *
   * @param actionListener the action listener to be added to the GUI
   */
  @Override
  public void addListener(Listener actionListener) {
    this.listener.add(actionListener);
  }

  /**
   * Render a specific message to given destination.
   *
   * @param message the message given
   */
  @Override
  public void renderMessage(String message) {
    this.displayMessages.setText(message);
  }

  /**
   * Render image given the image's key.
   *
   * @param id key of rendered image
   */
  @Override
  public void renderImageRep(String id) {
    BufferedImage bufferedImage = getBufferedImage(id);
    ImageIcon icon = new ImageIcon(bufferedImage);
    icon.getImage().flush();
    this.imageHolder.setIcon(icon);
    this.revalidate();
    this.repaint();
    this.pack();
    return;

  }


  /**
   * Returns a BufferedImage using an image's key.
   *
   * @param id of image to be transformed into a buffered image
   * @return BufferedImage version of given image
   */
  private BufferedImage getBufferedImage(String id) {
    Image img = this.imageModel.getImage(id);
    BufferedImage result = new BufferedImage(img.getPixels().get(0).size(),
            img.getPixels().size(), BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < img.getPixels().size(); i++) {
      for (int j = 0; j < img.getPixels().get(i).size(); j++) {
        Pixel pixel = img.getPixels().get(i).get(j);
        Color toAdd = new Color(pixel.getRed(), pixel.getGreen(), pixel.getBlue());
        result.setRGB(j, i, toAdd.getRGB());
      }
    }
    return result;
  }

  //Extra Windows

  /**
   * Creates a pop-up which lets user choose image to load.
   */
  private void loadImage() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setDialogTitle("Choose an image file to import");
    int retvalue = fchooser.showOpenDialog(GUIViewImpl.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();

      JOptionPane op = new JOptionPane();
      String id = op.showInputDialog("Enter id for the new image");
      currentID = id;

      this.emitLoadImageEvent(f.getAbsolutePath(), id);
    }

  }

  /**
   * Creates a pop-up which lets user choose which image to transform, its new key, and
   * increments of transformation value for brightness.
   *
   * @param actionCommand of transformation to perform on image
   */
  private void transform(String actionCommand) {
    int field1 = 0;
    int field2 = 0;
    JOptionPane opCurrent = new JOptionPane();
    String idCurrent = opCurrent.showInputDialog("Enter id for the current image");
    JOptionPane opNew = new JOptionPane();
    String idNew = opNew.showInputDialog("Enter id for the new image");
    currentID = idNew;
    boolean exception = false;

    if (actionCommand.equals("brighten")) {
      JOptionPane opBrighten = new JOptionPane();
      String incrementEntered = opBrighten.showInputDialog("Enter brightness value.");
      field1 = Integer.valueOf(incrementEntered);
    }
    if (actionCommand.equals("downscale")) {
      JOptionPane opDownscale = new JOptionPane();
      String widthEntered = opDownscale.showInputDialog("Enter new width.");
      field1 = Integer.valueOf(widthEntered);
      String heightEntered = opDownscale.showInputDialog("Enter new height.");
      field2 = Integer.valueOf(heightEntered);
    }
    this.emitTransformationEvent(actionCommand, idCurrent, idNew, field1, field2);
  }

  /**
   * Creates a pop-up which lets user choose a current image.
   *
   * @param id of desired current image
   */
  private void setCurrentImageFrame(String id) {
    this.renderImageRep(id);
    currentID = id;
  }

  /**
   * Creates a pop-up which lets user choose which image to save and where.
   *
   * @param fileType the type of the file to be stored
   */
  private void saveImageFrame(String fileType) {
    JOptionPane op = new JOptionPane();
    String id = op.showInputDialog("Enter id for the image to save");
    currentID = id;

    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setDialogTitle("Save Current Image");
    fchooser.setSelectedFile(new File(currentID + "." + fileType));
    int retvalue = fchooser.showSaveDialog(GUIViewImpl.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      this.emitSaveImageEvent(f.getAbsolutePath(), id);
    }

  }

  private void previewImageFrame() {
    BufferedImage bufferedImage = getBufferedImage(currentID);
    ImageIcon img = new ImageIcon(bufferedImage);
    JScrollPane imageScroll = new JScrollPane(new JLabel(img));
    JFrame previewFrame = new JFrame("Preview Frame");
    previewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    previewFrame.getContentPane().add(imageScroll, BorderLayout.CENTER);
    //accurate??
    previewFrame.setSize(200, 200);
    previewFrame.setResizable(false);
    // previewFrame.pack();
    previewFrame.setVisible(true);


  }

  //Misc.

  /**
   * Adds a loaded image to the image map.
   *
   * @param key of image to be added
   */
  private void addToImageMap(String key) {
    if (!listModel.contains(key) && key != null) {
      listModel.addElement(key);
    }
  }

  /**
   * Visualizes the histogram according to the given type.
   *
   * @param type  of histogram to visualize (red, green, blue, or intensity)
   * @param color of line in graph
   */
  private void visualizeHistogram(String type, Color color) {
    histogramPanel.remove(histogramGraph);
    this.histogramGraph = new DrawHistogram(imageModel.getImage(currentID)
            .getHistogram(type), color);
    histogramPanel.add(histogramGraph);
    histogramPanel.revalidate();
    histogramPanel.repaint();
  }

}



