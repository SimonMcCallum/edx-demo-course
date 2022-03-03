package comp261.tut1;

public class Edge {
    private int from;
    private int to;
    private double weight;

    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
        this.weight = 1.0;
    }

    public int getFrom() {
        return from;
    }

    public int getTo(){
        return to;
    }

    public double getWeight() {
        return weight;
    }

}
