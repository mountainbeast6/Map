public class Vertex extends GraphObject {
    boolean used=false;
    public Vertex(String name) {
        super(name);
    }

    public Vertex(String name, int weight) {
        super(name, weight);
    }

    public boolean equals(Vertex other){
        return this.getName().equals(other.getName());
    }
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    public String toString() {
        return    "Vertex{" +
                "name='" + this.getName() + '\'' +
                ", weight=" + this.getWeight() +
                '}';
    }
}
