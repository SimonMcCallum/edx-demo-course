package comp261.tut1;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.io.File;
import java.util.ArrayList;

import javafx.event.*;

public class GraphController {

    // names from the items defined in the FXML file
    @FXML private TextField searchtext; 
    @FXML private Button load;
    @FXML private Button quit;
    @FXML private Button up;
    @FXML private Button down;
    @FXML private Button left;
    @FXML private Button right;
    @FXML private Canvas mapcanvas;
    @FXML private Label nodeDisplay;

    // These are used to map the nodes to the location on screen
    private Double scale = 2.5;
    private Point2D origin = new Point2D(100, 80);
 
    private static int nodeSize = 10;

    private static int moveDistance = 10;
    private static double zoomFactor = 1.1;

    private ArrayList<Node> highlightNodes = new ArrayList<Node>();

    // map model to screen using scale and origin
    private Point2D model2Screen (Point2D model) {
   return new Point2D(model2ScreenX(model.getX()), model2ScreenY(model.getY()));
    }
    private double model2ScreenX (double modelX) {
      return (modelX - origin.getX()) * scale + mapcanvas.getWidth()/2; 
    }
    private double model2ScreenY (double modelY) {
      return (modelY - origin.getY())* scale + mapcanvas.getHeight()/2;
    }

    // map screen to model using scale and origin
    private double getScreen2ModelY(Point2D screenPoint) {
        return (((screenPoint.getY()-mapcanvas.getHeight()/2)/scale) + origin.getY());
    }
    private double getScreen2ModelX(Point2D screenPoint) {
        return (((screenPoint.getX()-mapcanvas.getWidth()/2)/scale) + origin.getX());
    }
    private Point2D getScreen2Model(Point2D screenPoint) {
        return new Point2D(getScreen2ModelX(screenPoint), getScreen2ModelY(screenPoint));
    }   

    public void initialize() {
       //Could do setup here
    }

    public void handleLoad(ActionEvent event) {
        Stage stage = (Stage) mapcanvas.getScene().getWindow();
        System.out.println("Handling event " + event.getEventType());
        FileChooser fileChooser = new FileChooser();
        //Set to user directory or go to default if cannot access
        File defaultNodePath = new File("data/");
        if(!defaultNodePath.canRead()) {
            defaultNodePath = new File("C:/");
        }
        fileChooser.setInitialDirectory(defaultNodePath);
        FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extentionFilter);

        fileChooser.setTitle("Open Node File");
        File nodeFile = fileChooser.showOpenDialog(stage);

        fileChooser.setTitle("Open Edge File");
        File edgeFile = fileChooser.showOpenDialog(stage);
        Main.graph=new Graph(nodeFile,edgeFile);
        drawGraph();
        event.consume();
    }

    public void handleQuit(ActionEvent event) {
        System.out.println("Quitting with event " + event.getEventType()); 
        event.consume();  
        System.exit(0); 
    }

    public void handleZoomin(ActionEvent event) {
        System.out.println("Zoom in event " + event.getEventType()); 
        scale *= zoomFactor;
        drawGraph();
        event.consume();  
    }

    public void handleZoomout(ActionEvent event) {
        System.out.println("Zoom out event " + event.getEventType()); 
        scale *= 1.0/zoomFactor;
        drawGraph();
        event.consume();  
    }

    public void handleUp(ActionEvent event) {
        System.out.println("Move up event " + event.getEventType()); 
        origin = origin.add(0, moveDistance/scale);
        drawGraph();
        event.consume();  
    }

    public void handleDown(ActionEvent event) {
        System.out.println("Move Down event " + event.getEventType()); 
        origin = origin.subtract(0, moveDistance/scale);
        drawGraph();
        event.consume();  
    }

    public void handleLeft(ActionEvent event) {
        System.out.println("Move Left event " + event.getEventType()); 
        origin = origin.add(moveDistance/scale, 0);
        drawGraph();
        event.consume();  
    }

    public void handleRight(ActionEvent event) {
        System.out.println("Move Right event " + event.getEventType()); 
        origin = origin.subtract(moveDistance/scale, 0);
        drawGraph();
        event.consume();  
    }

    public void handleSearch(KeyEvent event) {
        System.out.println("Look up event " + event.getEventType()+ "  "+searchtext.getText()); 
        String search = searchtext.getText();
        highlightNodes.clear();
        for(Node node: Main.graph.getNodeList()) {
            if(search.length()>0 && node.getName().contains(search)) {
                highlightNodes.add(node);
                nodeDisplay.setText(node.toString());
            }
        }
        drawGraph();
        event.consume();  
    }  

/* handle mouse clicks on the canvas
   select the node closest to the click */
    public void handleMouseClick(MouseEvent event) {
        System.out.println("Mouse click event " + event.getEventType());
        //find node closed to mouse click
        Point2D screenPoint = new Point2D(event.getX(), event.getY());
        double x = getScreen2ModelX(screenPoint);
        double y = getScreen2ModelY(screenPoint);
        highlightClosestNode(x,y);
        event.consume();
    }


    
    public void highlightClosestNode(double x, double y) {
        double minDist = Double.MAX_VALUE;
        Node closestNode = null;
        for(Node node: Main.graph.getNodeList()) {
            double dist = node.distance(x,y);
            if(dist < minDist) {
                minDist = dist;
                closestNode = node;
            };
        }
        if(closestNode != null) {
            highlightNodes.clear();
            highlightNodes.add(closestNode);
            nodeDisplay.setText(closestNode.toString());
            drawGraph();
        }
    }



/*
Drawing the graph on the canvas
*/

    public void drawCircle(double x, double y, int radius) {
        GraphicsContext gc = mapcanvas.getGraphicsContext2D();
        gc.fillOval(x-radius/2, y-radius/2, radius, radius);
    }

    public void drawLine(double x1, double y1, double x2, double y2) {
        mapcanvas.getGraphicsContext2D().strokeLine(x1, y1, x2, y2);
    }
    
    public void drawGraph() {
        Graph graph = Main.graph;
        if(graph == null) {
            return;
        }
        GraphicsContext gc = mapcanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, mapcanvas.getWidth(), mapcanvas.getHeight());

        //store node list so we can use nodes to find edge end points.
        ArrayList<Node> nodeList = graph.getNodeList();

        //draw nodes
        nodeList.forEach(node -> {
            int size = nodeSize;

            if (highlightNodes.contains(node)) {
                gc.setFill(Color.RED);
                size = nodeSize * 2;
            } else {
                gc.setFill(Color.BLUE);
            }
            Point2D screenPoint = model2Screen(node.getPoint());
            drawCircle(screenPoint.getX(), screenPoint.getY(), size);
        });

        //draw edges
        graph.getEdgeList().forEach(edge -> {
            gc.setStroke(Color.BLACK);
            double startX = model2ScreenX(nodeList.get(edge.getFrom()).getX());
            double startY = model2ScreenY(nodeList.get(edge.getFrom()).getY());
            double endX = model2ScreenX(nodeList.get(edge.getTo()).getX());
            double endY = model2ScreenY(nodeList.get(edge.getTo()).getY());
            gc.strokeLine(startX, startY, endX, endY);
        });

        
        //print to standard out adjacency matrix for the node selected
        if(highlightNodes.size() > 0) {
            Node node = highlightNodes.get(0);
            double[][] adjacencyMatrix = graph.getAdjacencyMatrix();
            for(int j=0; j<adjacencyMatrix[node.getId()].length; j++) {
                if(adjacencyMatrix[node.getId()][j] > 0) {
                    System.out.println("Drawing edge from "+node.getName()+" to "+nodeList.get(j).getName());
                }
            }
        }

    }



}
