**__README**__
-

This README is for the Image Processor set of assignments for CS3500, Object-Oriented Design at Northeastern University.
The Image Processor program follows the Model-View-Controller paradigm. The user/client can use the command line to
interact with the program to modify .ppm files using a variety of operations which include **save, load, brighten,
flip-vertical, flip-horizontal, value-component, red-component, green-component, luma, and intensity.**

**Classes & Interfaces**
- 
Here is an overview of all of the classes and interfaces that build this model

- Model

    - Image: Interface that represents an image
    - AbstractImage: Abstract class that represents any implementation of an Image
    - ImageImpl: A Concrete Implementation of the Image type
    - Pixel: Interface that represents a pixel in an image
    - PixelImpl: A Concrete Implementation of the Pixel type
    - ImageModelState: Interfaces that has all operations for the model that do not change the state of the Model
    - ImageModel: Interface that has all operations that can preformed on the model including the operations that
      process/modify images
    - ImageModelImpl: A Concrete Implementation of the ImageModel type that can perform all necessary operations to
      modify images
    - ImageUtil: A Class that allows Images to be loaded from a filepath
- View

    - ImageView: Interface that represents all operations a view can perform such as display an image or record the
      operation performed
    - ImageViewImpl: A Concrete Implementation of the view of an Image Processor

- Controller

    - ImageController: Interface that represents the controller of an Image Processor
    - ImageControllerImpl: A Concrete Implementation of the ImageController type that allows the Image Processor to work
      and interact with commands
    - **Command: Function Objects that apply operations onto the model**

        - ImageCommand: Interface the represents all operations that can be executed by the model
        - IntensityCommand: Class that applies the intensity operation onto a given image
        - LumaCommand: Class that applies the luma operation onto a given image
        - ValueCommand: Class that applies the value-component operation onto a given image
        - RedComponentCommand: Class that gets the red-channel grayscale version of an image
        - GreenComponentCommand: Class that gets the green-channel grayscale version of an image
        - BlueComponentCommand: Class that gets the blue-channel grayscale version of an image
        - BrightenCommand: Class that brightens an image by certain increment
        - VerticalCommand: Class that flips an image vertically
        - HorizontalCommand: Class the flips an image horizontally
        - SaveCommand: Class that saves an image into a given filepath
        - LoadCommand: Class that loads an image from a file-path onto a model

**Updates in Assignment 5**
=

**Classes & Interfaces**
- 

- Model

    - Transformation: Interface that represents a color
      transformation being applied an on pixel, a color
      transformation is represented by a 3 by 3 matrix
    - TransformationImpl: Concrete implementing class of
      Transformation
    - Filter: Interface that represents an operation like sharpen
      or blur, that takes a kernel and applies to an image
    - FilterImpl: Concrete implementing class of Filter
    - ImageUtil: Added functionality for saving for other file types
    
* Changes in Pixel, PixelImpl, Image, and AbstractImage:
    - Not much was changed other than sepia, sharpen, and blur methods were
    - to in Pixel, PixelImpl, Image, and Abstract Image for sepia and Image, 
      and AbstractImage for sharpen and blur methods specifically
    - A lot of the older operations were refactored using a transformation 
    object in fitting with the principles of abstraction and code re-usability.

**New functionality was easy to implement given the design built
      in Assignment 4.**
    

    
- Controller

    - Command: SepiaCommand, BlurCommand, and Sharpen Command 
        were created in order for the controller to call operations onto the model.
      
- View

    - No change in the view this time.
      

**Updates in Assignment 6**
=

**Classes & Interfaces**
- Controller

    - GUIControllerImpl: Represents the controller which communicates with the GUIView class. 
      - Uses the listener class's methods to listen and act to events within GUI via commands.
- View

    - GUIView:  
      - Representation of a user interactive view of the model, which controller listens to when
        acting on model. 
    - GUIViewImpl: 
      - The interactive implementation of a GUI view for an Image Manipulation and Enhancement 
        application. Provides user-friendly display.
    - Listener:
      - This interface represents the listener of the view and communicates between the view and 
      the controller.

**Extra Changes**
Model
- Image: added a getHistogram method to enable images to return arrays visualizing different
color distributions.
- ImageImpl: implemented aforementioned getHistogram method which considers types "red", 
"blue", "green", and intensity (calculated average).


**Extra Changes**
Model
- Image: added a getHistogram method to enable images to return arrays visualizing different
  color distributions.
- ImageImpl: implemented aforementioned getHistogram method which considers types "red",
  "blue", "green", and intensity (calculated average).


**Updates in Assignment 8: Extra Credit**
=

**Classes & Interfaces**
- Controller
    - DownscaleCommand: added new command class for the controller to call the downscale operation 
    onto the model.
    - PartialImageCommand: added new command class for the controller to call the partial image
      manipulation operation onto the model.
    - ImageControllerImpl:
        1. added downscale and partial image manipulation operations to the possible command stack 
      map.
        2. added try catch to manage invalid value illegal argument exceptions internally.
    - GUIControllerImpl: added downscale and partial image to the applyTransformationListen class. 
      Changed applyTransformationListen method to accept multiple fields.
- View
    - Listener: adjusted applyTransformationListen to accept multiple fields.
    - GUIViewImpl:
        - Added downscale JMenuItem to the transform menu
        - Added downscale to the actionPerformed method
        - Added try catch to manage exceptions internally.
- Model:
    * ImageModel: added downscale() and partialImageManipulation() methods to perform downscale and
    partial image manipulation operations on the model.
    * ImageModelImpl: implemented downscale() and partialImageManipulation() methods from the 
    ImageModel interface. Also added helper method getFunction() to perform the user's string of 
    their desired operation on the original image.
    * Image: added downscale() and partialImageManipulation() methods to perform downscale and 
    partial image manipulation on the image.
    * AbstractImage: fixed blur. Also implemented downscale() and partialImageManipulation()
      methods from the Image interface. Added helper methods downscaleArrayList() and
      getColorComponents() for the downscale operation.
    

**Citation for our Image(s):**

“An Image of a Snail.” https://People.sc.fsu.edu/~Jburkardt/Data/Ppma/Ppma.html. Accessed 3 Nov. 2021.
"games-catan2" https://www.aq.org/~js/clipart/userpics/grid.html. Accessed 22 Nov. 2021.
"london" https://www.aq.org/~js/clipart/userpics/grid.html. Accessed 22 Nov. 2021.
"goose" https://www.aq.org/~js/clipart/userpics/grid.html. Accessed 22 Nov. 2021.
"mushroom" https://www.peakpx.com/en/hd-wallpaper-desktop-gfvwp. Accessed 01 Dec. 2021.
Test images were screenshots of colors.
Mask image made using https://www.pixilart.com/draw.

