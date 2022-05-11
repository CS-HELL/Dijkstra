import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for the DataList object
 * The DataList object is a list that will contain the adjacent vertices and weights of a vertex
 * in the program.
 */
public class DataList<T,J> {

    /**
     * Data attribute of the DataList object.
     */
    private Node<T,J> head;

    /**
     * Method used for getting the size of a DataList.
     */
    public int getSize() {
        int count;
        if (head == null){
            return 0;
        } else {
            count = 1;
            Node<T,J> current = head;
            while(current.getNext() != null){
                current = current.getNext();
                count += 1;
            }
        }
        return count;
    }// end of getSize

    /**
     * Method used for inserting an object to the DataList.
     */
    public void insert(T data, J weight) {
        Node<T,J> node = new Node<T,J>(data,weight);

        if (head == null) {
            head = node;
        } else {
            Node<T,J> current = head;
            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(node);
        }
    }//end of insert

    /**
     * Method used for assigning the head to a node.
     */
    public void setHead(Node<T,J> newHead){
        head = newHead;
    }//end of setHead

    /**
     * Method used for returning the current head.
     */
    public Node<T,J> getHead() {
        return head;
    }//end of getHead

    /**
     * Method used to check if the DataList is empty.
     * Returns true if empty, false if otherwise.
     */
    public boolean isEmpty(){
        return head == null;
    }//end of isEmpty

    /**
     * Method used for checking if the current node has a node after it.
     * Returns true if there is none, false if otherwise.
     */
    public boolean hasNext(){
        return head.getNext() == null;
    }//end of hasNext

    /**
     * Method used to search an element inside the DataList.
     */
    public boolean search(T data) {
        boolean searched = false;
        Node<T,J> node = head;

        if(node.getData().equals(data)){
            searched = true;
        }
        while(node != null) {
            if(node.getData().equals(data)){
                searched = true;
                break;
            } else {
                node = node.getNext();
            }
        }
        return searched;
    }//end of search

    /**
     * Method used for deleting an element inside the DataList.
     */
    public void delete(T data){
        Node<T,J> node = head;
        if(search(data)){
            System.out.println("Data already deleted");
        }

        while(node != null) {
            if(node.getNext().getData().equals(data) && node.getNext().getNext() != null){
                node.setNext(node.getNext().getNext());
                node.getNext().setNext(null);
                break;
            }if(node.getNext().getData().equals(data) && node.getNext().getNext() == null){
                node.setNext(null);
            }
            else {
                node = node.getNext();
            }
        }
        System.out.println("Data deleted");
    }//end of delete

    /**
     * Method used for showing the elements inside a DataList.
     */
    public void show(){
        if (!isEmpty()) {
            Node<T,J> node = head;
            while (node.getNext() != null) {
                System.out.printf("%-10s","Vertex: " + node.getData() + " " + "Weight: " + node.getWeight()+"\n");
                node = node.getNext();
            }
            System.out.println("     Vertex: " + node.getData() + " " + "Weight: " + node.getWeight());
            System.out.println(" ");

        } else {
            System.out.println("List is empty");
        }
    }//end of show

    public static List<Vertex> getShortestPathTo(Vertex target){

        List<Vertex> path = new ArrayList<Vertex>();

        for (Vertex vertex = target; vertex != null; vertex = vertex.antecedent)
            path.add(vertex);

        Collections.reverse(path);

        return path;
    }

}
