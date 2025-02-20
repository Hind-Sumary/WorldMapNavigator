package application;

import java.util.LinkedList;

public class vertex implements Comparable<vertex> {
    Capital capital;
    vertex previous;
    int num;
    double distance = Double.MAX_VALUE;
    boolean visited;
    IyadLinkedList<edges> e = new IyadLinkedList<>();

    public vertex(Capital capital, int number) {
        super();
        this.capital = capital;
        this.num = number;
    }

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public IyadLinkedList<edges> getEdge() {
        return e;
    }

    public void setEdge(IyadLinkedList<edges> e) {
        this.e = e;
    }

    public boolean FindEdge(String ss) {
        for (edges edge : e) {
            if (edge.getDestination().getCapital().getName().equalsIgnoreCase(ss)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder r = new StringBuilder(capital.getName() + ":");
        for (edges edge : e) {
            r.append(edge.desination.capital.getName()).append(",");
        }
        return r.toString();
    }

    @Override
    public int compareTo(vertex other) {
        return Double.compare(this.distance, other.distance);
    }
}
