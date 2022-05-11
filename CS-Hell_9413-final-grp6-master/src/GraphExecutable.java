import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Program for creating a Graph through a text file, traversing a graph, and
 * finding the shortest path from a vertex to other vertices.
 */
public class GraphExecutable {

    /**
     * Data attribute for GraphExecutable
     */
    private static HashMap<Vertex,DataList<Character, Double>> Graph = new HashMap<>();

    /**
     * Main method of the program.
     */
    public static void main(String[] args){

        try{
            GraphExecutable myProgram = new GraphExecutable();
            myProgram.run();
        }catch (Exception e){
            e.printStackTrace();
        }

    }//end of main

    /**
     * Method that runs the program.
     */
    public void run(){
        Scanner scan = new Scanner(System.in);
        do {
            showMenu();
            System.out.print("Choice : ");
            int choice = scan.next().charAt(0);
            switch (choice) {
                case '1' -> {
                    //Load Data File here
                    try{
                        if(!getVertices().isEmpty()) getVertices().clear();
                        if(!makeList().isEmpty()) makeList().clear();

                        List<Vertex> vertices = getVertices();
                        List<DataList<Character, Double>> allList = makeList();
                        makeHashMap(vertices, allList);

                        System.out.println("Format for file reading: ");
                        System.out.println("Vertex:AdjacentVertex-Weight,AdjacentVertex-Weight....");
                        System.out.println(" ");
                        System.out.println("Adjacency List of the Graph");


                        for(Map.Entry<Vertex,DataList<Character, Double>> entry : Graph.entrySet()){
                            Vertex key = entry.getKey();
                            System.out.print(key + " => ");
                            entry.getValue().show();
                        }

                    } catch (Exception x){
                        System.out.println("There is an error!");
                    }
                }
                case '2' -> {
                    initiateDepthFirstTraversal();
                }
                case '3' -> {
                    initiateBreadthFirstTraversal();
                }
                case '4'-> {
                    try {
                        initiateShortestPath();
                    }catch(Exception ex){
                        System.out.println("File Not yet Loaded!");
                    }
                }
                case '5' -> {
                    System.out.println();
                    System.out.println("Thank you for using the program!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        } while(true);
    }//end of run

    /**
     * Method used for getting the vertices of the Graph from a text file.
     * Returns a list of the vertices.
     */
    public static List<Vertex> getVertices(){
        try{
            List<Vertex> vertices = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("Files\\TRIAL.txt"));
            while(true){
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                String[] temp = line.split(":");
                Vertex vertex = new Vertex(temp[0].charAt(0));
                vertices.add(vertex);
            }
            reader.close();
            return vertices;
        }catch (Exception e){
            throw new RuntimeException("File Cannot Loaded");
        }
    }//end of getVertices

    /**
     * Method used for getting the adjacent vertices and weight of a vertex from a text file.
     * Returns a list of the data in String form.
     */
    public static List<String> getData(){
        try{
            List<String> data = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader("Files\\TRIAL.txt"));
            while(true){
                String line = reader.readLine();
                if (line == null){
                    break;
                }
                String[] temp = line.split(":");
                String datum = temp[1];
                data.add(datum);
            }
            reader.close();
            return data;
        }catch (Exception e){
            throw new RuntimeException("File Cannot be Loaded");
        }
    }//end of getData

    /**
     * Method used for creating a list of lists of the data from the textfile.
     * Returns a list of lists.
     */
    public static List<DataList<Character, Double>> makeList(){
        List<DataList<Character, Double>> allList = new ArrayList<>();

        for(int i = 0; i<getData().size(); i++){
            DataList<Character, Double> list = new DataList<>();
            String line = getData().get(i);
            String[] temp = line.split(",");

            for(String s : temp) {
                String[] temp2 = s.split("-");
                Character number = temp2[0].charAt(0);
                String weight = temp2[1];
                list.insert(number, Double.parseDouble(weight));
            }

            allList.add(list);
        }
        return allList;
    }//end of makeList

    /**
     * Method used for creating a HashMap of the vertices of the graph and its adjacent
     * vertices and weights.
     */
    public static void makeHashMap(List<Vertex> vertices, List<DataList<Character, Double>> data){
        for(int i = 0; i < vertices.size() && i<data.size() ; i++){
            Graph.put(vertices.get(i),data.get(i));
        }
    }//end of makeHashMap

    /**
     * Simple Show Menu.
     */
    public void showMenu(){
        System.out.println("====================================================");
        System.out.println("1) Load Data File");
        System.out.println("2) Perform Depth First Traversal");
        System.out.println("3) Perform Breadth First Traversal");
        System.out.println("4) Show shortest path of one vertex to another");
        System.out.println("5) Exit");
        System.out.println("====================================================");
    }//end of showMenu

    /**
     * Method in initiating (4) Shortest Path choice.
     */
    public static void initiateShortestPath(){
        if(!getVertices().isEmpty()) getVertices().clear();
        if(!makeList().isEmpty()) makeList().clear();

        List<Vertex> vertices = getVertices();
        List<DataList<Character, Double>> allList = makeList();
        makeHashMap(vertices, allList);

        Scanner scan = new Scanner(System.in);
        DijkstraShortestPath shortestPath = new DijkstraShortestPath();

        //List<Vertex> vertices = new ArrayList<>(Graph.keySet());//contains the list of vertices
        List <Character> vNames = new ArrayList<>();//contains the "names" of the vertices

        //for loop responsible for storing the names of the vertices in a List (vNames)
        for(Vertex v: vertices){
            vNames.add(v.getData());
        }

        //for loop responsible for assigning the corresponding EDGES & ITS WEIGHT to the VERTICES INCIDENT TO IT
        DataList<Character, Double> dataList;
        Edge[] edges;
        for(Vertex key: vertices){
            dataList = Graph.get(key);
            edges = new Edge[dataList.getSize()];
            //for loop responsible for getting the edges and its weight from the hashmap, "Graph", constructor
            for(int i = 0; i <= dataList.getSize(); i++){
                edges [i] = new Edge(vertices.get(vNames.indexOf(dataList.getHead().getData())), dataList.getHead().getWeight());
                dataList.setHead(dataList.getHead().getNext());
            }
            key.adjVertices = edges;
        }

        List<Character> availableChars = new LinkedList<>();
        for (Vertex vertex: vertices) {
            System.out.println(vertex.getData());
            availableChars.add(vertex.getData());
        }

        char root;

        if(availableChars.isEmpty()){
            System.out.println("File has not loaded properly / File Error.");
            System.exit(0);
        }

        do {
            System.out.println("====================================================");
            System.out.println("Note: Input '0' to quit the program");
            System.out.print("Starting vertex: ");
            String choice = scan.next();
            choice = choice.toUpperCase();

            if(availableChars.contains(choice.charAt(0))){
                root = choice.charAt(0);
                break;
            }
            else if(choice.equalsIgnoreCase("0")){
                System.out.println("Thank you for using the program.");
                System.exit(0);
            }

            System.out.println("Invalid Choice!");
            System.out.println();
        } while(true);

        System.out.println("====================================================");
        Character root2 = Character.toUpperCase(root);
        shortestPath.computePaths(vertices.get(vNames.indexOf(root2)));
        List<Vertex> path = null;
        for (Vertex v : vertices) {
            System.out.println("Distance to " + v + ": " + v.minDistance);
            path = shortestPath.getShortestPathToVertex(v);

            for (Vertex paths : path) {
                System.out.printf("->%s", paths.getData());
            }
            System.out.println("");
        }

    }//end of initiateShortestPath

    /**
     * Method in initiating (2) Depth First Traversal
     */
    public void initiateDepthFirstTraversal() {
        Scanner scan = new Scanner(System.in);
        List<Vertex> vertices = getVertices();
        List<DataList<Character, Double>> allList = makeList();
        makeHashMap(vertices, allList);

        Graph graph = new Graph();
        int i = 0;
        for (DataList d: allList) {
            List<Vertex> neighbors = new ArrayList<>();
            String w = d.getHead().getWeight().toString();
            w = w.substring(0, w.length()-2);
            Vertex current = new Vertex(d.getHead().getData().toString().charAt(0), Integer.parseInt(w));
            neighbors.add(current);
            while(true) {
                String wyt = d.getHead().getWeight().toString();
                wyt = wyt.substring(0, wyt.length()-2);
                char character;
                int weight;
                try {
                    character = d.getHead().getNext().getData().toString().charAt(0);
                    weight = Integer.parseInt(wyt);
                    d.setHead(d.getHead().getNext());
                } catch (Exception x){
                    break;
                }
                current = new Vertex(character,weight);
                neighbors.add(current);
            }
            graph.addVertex(vertices.get(i).getData(), neighbors);
            i++;
        }


        System.out.println("Available Vertices: ");
        List<Character> availableChars = new LinkedList<>();
        for (Vertex vertex: vertices) {
            System.out.println(vertex.getData());
            availableChars.add(vertex.getData());
        }
        String root;
        do {
            System.out.print("Starting vertex: ");
            String choice = scan.next().toUpperCase();
            if(availableChars.contains(choice.charAt(0))){
                root = choice;
                break;
            }
            System.out.println("Invalid Choice!");
            System.out.println();
        } while(true);

        Set<String> visited = new LinkedHashSet<>();
        visited.add(root);
        int o = 0;
        while(visited.size() != vertices.size()){
            while(visited.contains(root) && visited.size() != vertices.size()) {
                try {
                    int y = 0;
                    List<Vertex> currentAdj = graph.getAdjVertices(root);
                    root = currentAdj.get(y).getData().toString();
                    if (visited.contains(root)) {
                        y++;
                        root = currentAdj.get(y).getData().toString();
                    }
                    visited.add(root);
                } catch (Exception ex){
                    break;
                }
            }
            root = vertices.get(o).getData().toString();
            o++;
        }

        System.out.print("Depth First Traversal: ");
        Iterator<String> it = visited.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
            if(it.hasNext()){
                System.out.print(", ");
            }
        }
        System.out.println();
    }//end of initiateDepthTraversal


    /**
     * Method in initiating (3) Breath First Traversal
     */
    public void initiateBreadthFirstTraversal() {
        Scanner scan = new Scanner(System.in);
        List<Vertex> vertices = getVertices();
        List<DataList<Character, Double>> allList = makeList();
        makeHashMap(vertices, allList);

        Graph graph = new Graph();
        int i = 0;
        for (DataList d: allList) {
            List<Vertex> neighbors = new ArrayList<>();
            String w = d.getHead().getWeight().toString();
            w = w.substring(0, w.length()-2);
            Vertex current = new Vertex(d.getHead().getData().toString().charAt(0), Integer.parseInt(w));
            neighbors.add(current);
            while(true) {
                String wyt = d.getHead().getWeight().toString();
                wyt = wyt.substring(0, wyt.length()-2);
                char character;
                int weight;
                try {
                    character = d.getHead().getNext().getData().toString().charAt(0);
                    weight = Integer.parseInt(wyt);
                    d.setHead(d.getHead().getNext());
                } catch (Exception x){
                    break;
                }
                current = new Vertex(character,weight);
                neighbors.add(current);
            }
            graph.addVertex(vertices.get(i).getData(), neighbors);
            i++;
        }


        System.out.println("Available Vertices: ");
        List<Character> availableChars = new LinkedList<>();
        for (Vertex vertex: vertices) {
            System.out.println(vertex.getData());
            availableChars.add(vertex.getData());
        }
        String root;
        do {
            System.out.print("Starting vertex: ");
            String choice = scan.next().toUpperCase();
            if(availableChars.contains(choice.charAt(0))){
                root = choice;
                break;
            }
            System.out.println("Invalid Choice!");
            System.out.println();
        } while(true);

        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            for (Vertex v : graph.getAdjVertices(vertex)) {
                if (!visited.contains(v.getData().toString())) {
                    visited.add(v.getData().toString());
                    queue.add(v.getData().toString());
                }
            }
        }

        System.out.print("Breadth First Traversal: ");
        Iterator<String> it = visited.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
            if(it.hasNext()){
                System.out.print(", ");
            }
        }
        System.out.println();
    }//end of initiateBreadthTraversal
    
}//end of GraphExecutable class

