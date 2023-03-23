public class Edge extends GraphObject {
    boolean used=false;
    public Edge(String name) {
        super(name);
    }

    public Edge(String name, int weight) {
        super(name, weight);
    }

    public boolean equals(Edge other){
        return this.getName().equals(other.getName());
    }
    public String toString() {
        return    " Edge{"+ this.getName() + '}';
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}