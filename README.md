# COMP261 Tutorial 1

This code is for the discussion of data structures and starting to play with moving things around.

## Running in the lab
For those of you who are in the lab you should be able to get this code to compile and run by:
1. Go to or create a folder you want to work in perhaps something like `comp261/tutorials/`
1. type `git clone https://gitlab.ecs.vuw.ac.nz/lms/comp261/2022/tutorial-1.git`  This will copy the repository into a folder tutorial-1
1. type `cd tutorial-1`
1. type `code .` This will open visual studio code in the tutorial 1 directory.  VSCode needs to open the folder at the level where you can see the .vscode folder
1. In VSCode either press `F5` or click on the play button with the bug on the left menu, and then the green play button above the inspection view that opens up.

## Outside the lab

1. [Install Java 17.02](https://jdk.java.net/17/)  
1. [Install JavaFX](https://openjfx.io/) this is built into Intellij, [Bluej](https://www.bluej.org/faq.html#faq_How_do_I_use_JavaFX_in_BlueJ_), and Eclipse via plugin e(fx)clipse. 
1. In VSCode you need to install the extension for JavaFX Support via the box icon on the left bar with the small box coming out.
1. In the tutorial code edit the [JavaFX settings in .vscode/launch.json L16](/.vscode/launch.json#L16) This needs to change to your own path to JavaFx. 
    * On Windows Something like: `"vmArgs": "--module-path \"c:/Program Files/Java/javafx-sdk-17.0.1/lib\" --add-modules javafx.controls,javafx.fxml",` remember the \" is to include " in the actual string so it does not read the " " space as an end to the command line argument.
    * On ECS Linux: `"vmArgs": "--module-path /usr/pkg/java/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml",`
1. In the tutorial code edit the [JavaFX settings in .vscode/settings.json L16](/.vscode/launch.json#L16) This needs to change to your own path to JavaFx. 
    * On Windows Something like: `"c:/Program Files/Java/javafx-sdk-17.0.1/lib\",` 
    * On ECS Linux: `"/usr/pkg/java/javafx-sdk-11.0.2/lib",`
1. Run the code, from the Run menu.  The button in the top left with the arrow will sometime try and run the **file** you are in rather than the project.  So run from the Run menu, Debug menu, or using `F5`

## Tutorial activities

As part of the tutorial we would like you to
1. Start editing the code to change the colours of the nodes and the edges.
1. Use the weight of the edges in the file to change the thickness of the edges.
1. Add some text on the graph using gc.strokeText() for example `gc.strokeText("Origin", model2ScreenX(0), model2ScreenY(0));`
1. Change the highlighting so that when a node is clicked it changes the colour of the edges going OUT from that node.
1. Extention: Design and implement a way to show the direction of the edges. ( you could add a circle or text to the end of the edge )
1. Extention: Edit the data structures and play with the code to try something interesting.


This code has both an Edge Adjacency List and an [Adjacency Matrix line 11 Graph.java](/src/comp261/tut1/Graph.java#L11) `[Adjacency Matrix line 11 Graph.java](/src/comp261/tut1/Graph.java#L11)`


## Nodes on updates
* version 0.2.1  
    * Changed all file paths to relative and added null checks on intial load
    * Edited the default language to English
    * Changed to linux paths for main and create a windows branch for outside the lab
