# COMP261 Tutorial 1

This code is for the discussion of data structures and starting to play with moving things around.

To get this code to work, you will need to make some changes for your system. These include:
1. [JavaFX settings in launch.json L16](/.vscode/launch.json#L16) This needs to change to your own path to JavaFx. 
    * On Windows Something like: `"vmArgs": "--module-path \"c:/Program Files/Java/javafx-sdk-17.0.1/lib\" --add-modules javafx.controls,javafx.fxml",`
    * On ECS Linux: `"vmArgs": "--module-path /usr/pkg/java/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml",`

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
