package comp261.tut1;

import javafx.geometry.Point2D;

public class Node {
       private double x;
        private double y;
        private String name;
        private int id;
    
        public Node(double x, double y, String name, int id) {
            this.x = x;
            this.y = y;
            this.name = name;
            this.id = id;
        }
    
        public Node(double x, double y) {
            this.x = x;
            this.y = y;
            this.name = "";
            this.id = -1;
        }
    
        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public Point2D getPoint() {
            return new Point2D(x, y);
        }   

        public double distance(double x, double y) {
            return Math.sqrt(Math.pow(x-this.x,2) + Math.pow(y-this.y,2));
        }   

        public String toString() {
            return "Node " + id + ": " + name + " at (" + x + ", " + y + ")";       
        }

        public boolean equals(Node node) {
            return this.id == node.id;
        }

        public boolean equals(int id) {
            return this.id == id;
        }

        public boolean equals(String name) {
            return this.name.equals(name);
        }

        public boolean equalLocation(double x, double y) {
            return this.x == x && this.y == y;
        }

        public boolean equalLocation(Node node) {
            return this.x == node.x && this.y == node.y;
        } 
        
        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public void setName(String name) {
            this.name = name;
        }   

        public void setId(int id) {
            this.id = id;
        }   

        public void setLocation(double x, double y) {
            this.x = x;
            this.y = y;
        }   

        public void setLocation(Node node) {
            this.x = node.x;
            this.y = node.y;
        }   


    }
