/**
 *Class for the Node object.
 * Nodes are used for containing the data for the DataList.
 */
public class Node<T,J>{

    /**
     * Data attributes for the Node object.
     */
    private T data;
    private J weight;
    private Node<T,J> next;

    /**
     * Default constructor of the Node object.
     */
    public Node(){
        data = null;
        weight = null;
        next = null;
    }//end of Node

    /**
     * Constructor for the Node object when data and weight are inputted.
     */
    public Node(T data, J weight){
        this.data = data;
        this.weight = weight;
        next = null;
    }//end of Node

    /**
     * Method used for setting the data to a Node.
     */
    public void setData(T data){
        this.data = data;
    }//end of setData

    /**
     * Method used for assigning the weight to a Node.
     */
    public void setWeight(J weight){
        this.weight = weight;
    }//end of setWeight

    /**
     * Method used for assigning the next node to a node.
     */
    public void setNext(Node<T,J> nextNode){
        next = nextNode;
    }//end of setNext

    /**
     * Method used for returning the data of a node.
     */
    public T getData(){
        return data;
    }//end of getData

    /**
     * Method used for returning the weight of a node.
     */
    public J getWeight(){
        return weight;
    }//end of getWeight

    /**
     * Method used for getting the node after the current node.
     */
    public Node<T,J> getNext(){
        return next;
    }//end of getNext

    /**
     * Method used for the String form of a Node.
     */
    public String toString(){
        return "Vertex: " +data.toString() + " " + "Weight: " + weight.toString() + " ";
    }//end of toString
}

/**
 * Class for the Vertex Node.
 */
class Vertex implements Comparable<Vertex>{ //Duplicate of Node.java since I cant seem to use operators with Generics

    /**
     * Data attributes of a Vertex object.
     */
    private Character data;
    private Integer weight;
    public Edge[] adjVertices;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex antecedent;

    /**
     * Constructor of a Vertex object without a weight.
     */
    public Vertex(Character data){
        this.data = data;
    }//end of Vertex

    /**
     * Constructor of a Vertex object with data and weight.
     */
    public Vertex(Character data, Integer weight){
        super();
        this.data = data;
        this.weight = weight;
    }//end of Vertex

    /**
     * Method used for returning the data of a vertex.
     */
    public Character getData(){
        return data;
    }//end of getData

    /**
     * Method used for assigning the data to a vertex.
     */
    public void setData(Character data){
        this.data = data;
    }//end of setData

    /**
     * Method used for the String form of a vertex.
     */
    public String toString(){
        return Character.toString(this.data);
    }//end of toString

    /**
     * Method used for returning the hashCode of a vertex.
     */
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }//end of hashCode

    /**
     * Method used for comparing two vertex objects.
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex other = (Vertex) obj;
        if (weight == null) {
            if (other.weight != null)
                return false;
        } else if (!weight.equals(other.weight))
            return false;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }//end of equals

    /**
     * Method used for comparing the minimum distances of to vertices.
     */
    @Override
    public int compareTo(Vertex o) {
       return Double.compare(minDistance, o.minDistance);
    }//end of compareTo

} //END

