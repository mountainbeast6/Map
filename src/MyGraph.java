import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class MyGraph {
    private int numvert;
    private int numEdges;
    private HashMap<Vertex, ArrayList<GraphPairing>> graph;
    private ArrayList<Edge> Edges = new ArrayList<>();
    private ArrayList<Vertex> Verts = new ArrayList<>();

    public MyGraph() {
        numEdges = 0;
        numvert = 0;
        graph = new HashMap<>();
    }

    public void print() {
        Set<Vertex> vertexList = graph.keySet();
        for (Vertex v : vertexList) {
            System.out.print(v + ": ");
            System.out.println(graph.get(v));
        }
    }

    public int numEdges() {
        return Edges.size() + 1;
    }

    public void insertVertex(String name) {
        Vertex temp = new Vertex(name);
        //assume for now that the given name DNE in the map
        graph.put(temp, null);
        numvert++;
    }

    public ArrayList<Vertex> vertic() {
        return Verts;
    }

    public ArrayList<Edge> edges() {
        return Edges;
    }

    private Set<Vertex> vertices() {
        return graph.keySet();
    }

    public String getEdge(String V1, String V2) {
        Vertex v1 = getVertexFromStr(V1);
        Vertex v2 = getVertexFromStr(V2);

        if (v1 != null && v2 != null) {
            for (Vertex v : vertices()) {
                if (v.compareTo(v1) == 0) {
                    for (GraphPairing g : graph.get(v)) {
                        if (g.getV().compareTo(v2) == 0) {
                            return g.getE().getName();
                        }
                    }
                }
            }
        }
        return null;
    }

    public String opposite(String V, String E) {
        Vertex v1 = getVertexFromStr(V);
        Edge e = getEdgeFromStr(E);

        if (v1 != null && e != null) {
            for (Vertex v : vertices()) {
                if (v.compareTo(v1) == 0) {
                    for (GraphPairing g : graph.get(v)) {
                        if (g.getE().compareTo(e) == 0) {
                            return g.getV().getName();
                        }
                    }
                }
            }
        }
        return null;
    }

    public void insertEdge(String v1, String v2, String edgeName) {
        Vertex vertex1 = null;
        Vertex vertex2 = null;
        for (Vertex v : vertices()) {
            if (v.compareTo(new Vertex(v1)) == 0) {
                vertex1 = v;
            }
            if (v.compareTo(new Vertex(v2)) == 0) {
                vertex2 = v;
            }
        }

        Edge edge = new Edge(edgeName);

        ArrayList<GraphPairing> v1EdgeList = graph.get(vertex1);
        if (v1EdgeList == null)
            graph.put(vertex1, new ArrayList<>());
        graph.get(vertex1).add(new GraphPairing(vertex2, edge));

        ArrayList<GraphPairing> v2EdgeList = graph.get(vertex2);
        if (v2EdgeList == null)
            graph.put(vertex2, new ArrayList<>());
        graph.get(vertex2).add(new GraphPairing(vertex1, edge));

        Edges.add(edge);
    }

    public ArrayList<String> endVerticies(String E) {
        Edge e = getEdgeFromStr(E);
        if (e != null) {
            for (Vertex v : vertices()) {
                for (GraphPairing g : graph.get(v)) {
                    if (g.getE().compareTo(e) == 0) {
                        ArrayList<String> ret = new ArrayList<>();
                        ret.add(v.getName());
                        ret.add(opposite(v.getName(), E));
                        return ret;
                    }
                }
            }
        }
        return null;
    }

    public int numVerticies() {
        return numvert;
    }

    public String removeEdge(String E) {
        Edge e = getEdgeFromStr(E);
        if (e != null) {
            Set<Vertex> vertices = graph.keySet();
            for (Vertex v : vertices) {
                ArrayList<GraphPairing> graphPairingArrayList = graph.get(v);
                if (graphPairingArrayList != null) {
                    for (int i = 0; i < graphPairingArrayList.size(); i++) {
                        GraphPairing g = graphPairingArrayList.get(i);
                        if (g.getE().compareTo(e) == 0) {
                            ArrayList<GraphPairing> GraphPairingArrayList = graph.get(g.getV());
                            for (int k = 0; k < graphPairingArrayList.size(); k++) {
                                GraphPairing G = graphPairingArrayList.get(k);
                                if (G.getE().compareTo(e) == 0) {
                                    graph.get(g.getV()).remove(G);
                                    break;
                                }
                            }

                            graph.get(v).remove(g);
                            break;
                        }

                    }
                }
            }
        }
        if (e == (null))
            return null;
        edges().remove(e);
        return e.getName();
    }

    public String removeVertex(String V) {
        Vertex v = getVertexFromStr(V);

        for (int i = graph.get(v).size() - 1; i >= 0; i--) {
            removeEdge(graph.get(v).get(i).getE().getName());
        }
        graph.remove(v);
        vertic().remove(v);
        return null;
    }

    private Vertex getVertexFromStr(String name) {
        Set<Vertex> vertices = graph.keySet();
        for (Vertex v : vertices) {
            if (v.getName().equals(name)) {
                return v;
            }
        }
        return null;
    }

    private Edge getEdgeFromStr(String name) {
        Set<Vertex> vertices = graph.keySet();
        for (Vertex v : vertices) {
            ArrayList<GraphPairing> graphPairingArrayList = graph.get(v);
            if (graphPairingArrayList != null) {
                for (GraphPairing g : graphPairingArrayList) {
                    if (g.getE().getName().equals(name)) {
                        return g.getE();
                    }
                }
            }
        }
        return null;
    }

    public String pathFromVertexToVertex(String V1, String V2) {
        Vertex v1 = getVertexFromStr(V1);
        Vertex v2 = getVertexFromStr(V2);
        ArrayList<ArrayList<Vertex>> used = new ArrayList<>();
        Stack<Vertex> moves = new Stack<>();
        for (Vertex v : graph.keySet())
            used.add(new ArrayList<Vertex>());
        moves.push(v1);
        while (moves.peek().compareTo(v2) != 0) {
            Vertex top = moves.peek();
            Vertex temp = getNextMove(pathOptions(top), v2, used);
            if (temp != null) {
                used.get(getIndexFromKey(top)).add(temp);
                moves.push(temp);
            } else {
                int v = getIndexFromKey(moves.pop());
                for (int i = 0; i < used.get(v).size(); i++) {
                    used.get(v).remove(0);
                }
            }

        }
        if (moves.size() == 0) {
            return "??????????????????????????????????????????????????????";
        } else {
            return "solution";
        }
    }
    private Stack<Vertex> pathOptions(Vertex V1) {
        Stack<Vertex> ret = new Stack<>();
        for (GraphPairing g : graph.get(V1)) {
            ret.add(g.getV());
        }
        return ret;
    }

    private Vertex getNextMove(Stack<Vertex> rec, Vertex goal, ArrayList<ArrayList<Vertex>> used) {
        Vertex sending = null;
        for (int i = 0; i<rec.size();i++){
            if(!inExhausted(goal, rec.get(i), used)){
                return rec.get(i);
            }
        }
        return sending;
    }
    private boolean inExhausted(Vertex source, Vertex dest, ArrayList<ArrayList<Vertex>> used){
        for(Vertex v: used.get(getIndexFromKey(source))){
            if(v.compareTo(source)==0)
                return true;
        }
        return false;
    }

    private Integer getIndexFromKey(Vertex v) {
        int i = 0;

        for (Vertex V : graph.keySet()) {
            if (V.compareTo(v) == 0) {
                return i;
            }
            i++;
        }
        return null;
    }
}