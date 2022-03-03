package comp261.tut1;

import java.io.File;
import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodeList;
    private ArrayList<Edge> edgeList;

    private double[][] adjacencyMatrix;

    public Graph(ArrayList<Node> nodeList, ArrayList<Edge> edgeList) {
        this.nodeList = nodeList;
        this.edgeList = edgeList;
        buildAdjacencyMatrix();
    }

    

    //constructor for the graph
    public Graph(File nodeFile, File edgeFile) {
        this.nodeList = Parser.parseNodes(nodeFile);
        this.edgeList = Parser.parseEdges(edgeFile);
        buildAdjacencyMatrix();
    }

    public ArrayList<Node> getNodeList() {
        return nodeList;
    }   

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    //build the adjacency matrix
    public void buildAdjacencyMatrix() {
        adjacencyMatrix = new double[nodeList.size()][nodeList.size()];
        for (Edge e : edgeList) {
            adjacencyMatrix[e.getFrom()][e.getTo()] = e.getWeight();
            adjacencyMatrix[e.getTo()][e.getFrom()] = e.getWeight();
        }
    }

    //getter for the adjacency matrix
    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
}
