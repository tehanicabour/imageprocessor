

_USEME_ 
= 

**Script Examples (_Instructions_)**
-


**Starting the Jar File**
- Simply double click on the jar file if within IntelliJ 
or type java-jar ImageProcessing5Updated1.jar within the command prompt within IntelliJ
  or the computer's terminal 

- Can run:
  + GUI View with command: java -jar Program.jar
  + Interactive Text Mode: java -jar Program.jar -text
  + Script File: java -jar Program.jar -file path-of-script-file
  
**GUI:
- File: menu to 
  + load an image (select file and enter name)
  + save an image (choose image type, then location, then enter desired file name)
- Transformation: menu to choose transformation, image to transform, destination key.
- and level for brightness
- Image Navigator: map of images model contains
  + double click to set current image. Will get representation of its histogram
  and image rendering
- Visualization of Color Distributions: click on type of distribution to see
current image's histogram

** Can either run the below interactive scripts or type -file nameoffile to run a script 
as a command line option, for example type -file res/script-commands.txt you will run the script file 
and a list of pre-specified operations will be run on the model. 

** You will be nudged to type in a script file, after typing -file as you will see the screen return "Looking for file".

**Image names are examples, you can apply this to any image in our res folder or
any folder in your directory when you simply run the jar**

**type these scripts when the program runs**

**Don't run with -> comments!**

- **load koala images/koala.ppm** -> loads (overwrites the koala) image with the pineapple ppm image

- **brighten 10 koala koala-brighter**  -> brightens the koala-image by 10 and saves it with the name koala-brighter

- **red-component koala koala-red-channel** -> takes the koala-image and gets red-channel grayscale version

- **green-component koala koala-green-channel** -> takes the koala-image and gets green-channel grayscale version

- **blue-component koala koala-blue-channel** -> takes the koala-image and gets its blue-channel grayscale version

- **intensity koala-brighter koala-intense** -> takes the koala-brighter image and gets intensity-value version

- **value-component koala-intense max-koala** -> takes the koala-intense-image and get value-component(max channel
  color) version

- **luma koala koala-luma** -> takes the koala-image and applies the luma formula onto it

- **vertical-flip koala upsidedown** -> takes the koala-image and flips it vertically

- **horizontal-flip koala right-left** -> takes the koala-image and flips it horizontally 

- **sepia koala sepia-koala** -> takes the koala-image, applies the sepia operation and gets a sepia koala

- **sharpen koala sharpened** -> takes the koala-image, applies the sharpen filter on it and gets a sharpened version of koala

- **blur koala blurred-koala** -> takes the koala image, applies the blur filter and gets a blurred version of the koala-image
- **downscale koala downscale-koala** -> takes the koala image, and gets a downscaled version of the koala-image
- **partial-image mask-image blur koala mask-blur-koala** -> takes the koala image, applies the blur filter to masked part of image

- **save max-koala images/max-koala.jpg -**> saves the max-koala-image to the given file-path (Note: you must save the image with its current name,
  other names will not work) This will work for ppms, jpgs, and pngs

**Citation for our Image(s):**

“An Image of a Snail.” https://People.sc.fsu.edu/~Jburkardt/Data/Ppma/Ppma.html. Accessed 3 Nov. 2021. 
"games-catan2" https://www.aq.org/~js/clipart/userpics/grid.html. Accessed 22 Nov. 2021.
"london" https://www.aq.org/~js/clipart/userpics/grid.html. Accessed 22 Nov. 2021.
"goose" https://www.aq.org/~js/clipart/userpics/grid.html. Accessed 22 Nov. 2021.
"mushroom" https://www.peakpx.com/en/hd-wallpaper-desktop-gfvwp. Accessed 01 Dec. 2021.
Test images were screenshots of colors.