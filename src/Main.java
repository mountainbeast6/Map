public class Main {
    public static void main(String[] args) {
        MyGraph g = new MyGraph();
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        g.insertVertex("E");
        g.insertVertex("F");
        g.insertVertex("G");
        g.insertVertex("H");
        g.insertVertex("I");
        g.insertVertex("J");
        g.insertVertex("K");
        g.insertVertex("L");
        g.insertVertex("M");
        g.insertVertex("N");
        g.insertVertex("O");
        g.insertVertex("P");

        g.insertEdge("A","B","1");
        g.insertEdge("C","D","2");
        g.insertEdge("A","F","3");
        g.insertEdge("B","G","4");
        g.insertEdge("G","D","5");
        g.insertEdge("D","H","6");
        g.insertEdge("E","F","7");
        g.insertEdge("G","H","8");
        g.insertEdge("E","I","9");
        g.insertEdge("E","J","10");
        g.insertEdge("J","G","11");
        g.insertEdge("G","K","12");
        g.insertEdge("H","L","13");
        g.insertEdge("I","J","14");
        g.insertEdge("K","L","15");
        g.insertEdge("I","M","16");
        g.insertEdge("J","N","18");
        g.insertEdge("K","P","19");
        g.insertEdge("M","N","20");
        g.insertEdge("O","P","21");
        g.print();
        System.out.println(g.pathFromVertexToVertex("K", "N"));

    }

}