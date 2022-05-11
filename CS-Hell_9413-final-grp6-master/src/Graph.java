import java.util.*;

/**
 * Class for the Graph object.
 * Uses a HashMap to create a adjacency list of the graph.
 */
class Graph{

    /**
     * Data attribute for the Graph object.
     */
    private final Map<Character, List<Vertex>> vertices;

    /**
     * Constructor for the Graph object.
     */
    public Graph() {
        this.vertices = new HashMap<Character, List<Vertex>>();
    }//end of Graph

    /**
     * Method used for adding a vertex to the Graph.
     */
    public void addVertex(Character character, List<Vertex> vertex){
        this.vertices.put(character, vertex);
    }//end of addVertex

    /**
     * Method used for getting the adjacent vertices of a vertex.
     */
    public List<Vertex> getAdjVertices(String vertex) {
        List<Vertex> vertices = new LinkedList<>();
        for (Map.Entry<Character, List<Vertex>> map: this.vertices.entrySet()) {
            if(map.getKey().equals(vertex.charAt(0))){
                vertices.addAll(map.getValue());
            }
        }
        return vertices;
    }//end of getAdjVertices
}//end of Graph class

/**
 * Class for the Edge object.
 */
class Edge{

    /**
     * Data attributes of the Edge object.
     */
    public final Vertex target;
    public final double weight;

    /**
     * Constructor of the Edge object.
     */
    public Edge(Vertex argTarget, double argWeight){
        target = argTarget; weight = argWeight;
    }//end of Edge
}//end of Edge class

