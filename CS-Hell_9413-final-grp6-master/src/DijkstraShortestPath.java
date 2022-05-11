import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraShortestPath{
    /**
     * Method in computing the paths/distance required to the end vertex
     * @param first will be the initial Vertex
     */
    public void computePaths(Vertex first){
        first.minDistance = 0.;
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(first);

        while (!vertexQueue.isEmpty()) {
            Vertex returnRemove = vertexQueue.poll();

            for (Edge edge : returnRemove.adjVertices){
                Vertex v = edge.target;
                double distance = returnRemove.minDistance + edge.weight;
                if (distance < v.minDistance) {
                    vertexQueue.remove(v);
                    v.minDistance = distance ;
                    v.antecedent = returnRemove;
                    vertexQueue.add(v);
                }
            }
        }
    }//end of computePaths

    /**
     * Method in getting and returning the shortest path from the initial Vertex to the End Vertex
     * @param end will be the final Vertex
     * @return the shortest path
     */
    public List<Vertex> getShortestPathToVertex(Vertex end){

        List<Vertex> path = new ArrayList<Vertex>();

        for (Vertex vertex = end; vertex != null; vertex = vertex.antecedent) path.add(vertex);

        Collections.reverse(path);

        return path;
    }//end of getShortestPathToVertex
}//end of class