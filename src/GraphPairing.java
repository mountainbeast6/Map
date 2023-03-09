public class GraphPairing {
    private Vertex v;
    private Edge e;

    public GraphPairing(Vertex v, Edge e) {
        this.v = v;
        this.e = e;
    }

    public Vertex getV() {
        return v;
    }

    public void setV(Vertex v) {
        this.v = v;
    }

    public Edge getE() {
        return e;
    }

    public void setE(Edge e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "GraphPairing{" +
                "v=" + v +
                ", e=" + e +
                '}';
    }
}
