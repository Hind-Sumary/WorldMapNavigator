package application;

public class edges {
    vertex desination;
    vertex source;
    double weight;

    public edges(vertex source, vertex desination, double weight) {
        super();
        this.desination = desination;
        this.weight = weight;
    }

    public vertex getDestination() {
        return desination;
    }

    public void setDestination(vertex desination) {
        this.desination = desination;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public vertex getSource() {
        return source;
    }

    public void setSource(vertex source) {
        this.source = source;
    }
}
