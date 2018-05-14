package final_project;

import net.datastructures.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.ArrayList;


public class Project {

    public HashMap<String, Integer> ddlist = new HashMap<>();//the directed distance info
    public AdjacencyMapGraph<String, Integer> g = new AdjacencyMapGraph<>(false);// the graph info
    public HashMap<String, Vertex<String>> verts = new HashMap<>();


    public Project() {
    }

    public HashMap<String, Integer> getDdlist() {
        return ddlist;
    }

    public AdjacencyMapGraph<String, Integer> getG() {
        return g;
    }

    /**
     * read the vertex and edge matrix from a graph file
     *
     * @param graph_file
     * @return edge list array
     * @throws FileNotFoundException
     */
    public static String[][] readGraphMap(File graph_file) throws FileNotFoundException {
        Scanner graph_input = new Scanner(graph_file);
        ArrayList<String[]> edges = new ArrayList<>();
        String[] vertss = graph_input.nextLine().trim().split(" +");
        System.out.println("The number of nodes in this graph is : " + vertss.length + "\n");
        String[][] graphmap = new String[vertss.length][vertss.length];
        int j = 0;
        while (graph_input.hasNextLine()) {
            String[] temp = graph_input.nextLine().split(" +");
            for (int i = 0; i < temp.length - 1; i++) {
                graphmap[j][i] = temp[i + 1];
            }
            j++;
        }
        for (int i = 0; i < vertss.length; i++) {
            for (j = i + 1; j < vertss.length; j++) {
                if (!graphmap[i][j].equals("0")) {
                    String[] edge = new String[3];
                    edge[0] = vertss[i];
                    edge[1] = vertss[j];
                    edge[2] = graphmap[i][j];
                    edges.add(edge);
                }
            }
        }
        int size = edges.size();
        String[][] array = edges.toArray(new String[size][3]);
        return array;
//        System.out.println(graphFromEdgelist(array, false));
    }


    /**
     * Constructs a graph from an array of array strings.
     * <p>
     * An edge can be specified as { "SFO", "LAX" }, in which case edge is created
     * with default edge value of 1, or as { "SFO", "LAX", "337" }, in which case
     * the third entry should be a string that will be converted to an integral value.
     */
    public void setGraphFromEdgelist(String[][] edges, boolean directed) {
        this.g = new AdjacencyMapGraph<>(directed);

        // first pass to get sorted set of vertex labels
        TreeSet<String> labels = new TreeSet<>();
        for (String[] edge : edges) {
            labels.add(edge[0]);
            labels.add(edge[1]);
        }
        // now create vertices (in alphabetical order)
        for (String label : labels)
            verts.put(label, g.insertVertex(label));

        // now add edges to the graph
        for (String[] edge : edges) {
            Integer cost = (edge.length == 2 ? 1 : Integer.parseInt(edge[2]));
            g.insertEdge(verts.get(edge[0]), verts.get(edge[1]), cost);
        }
    }

    /*
    read the directed_distance.txt
    return Map<Vertice, distance>
     */
    public void set_ddList(File directed_file) throws FileNotFoundException {
        Scanner file_input = new Scanner(directed_file);
        while (file_input.hasNextLine()) {
            String[] ss = file_input.nextLine().trim().split(" +");
            this.ddlist.put(ss[0], Integer.valueOf(ss[1]));
        }
    }


    /*
    input vertice
    get distance of this point to Z
     */
    public Integer getdDistance(String vertice_lable) {
        Integer dd = ddlist.get(vertice_lable);
        return dd;
    }

    /**
     * @param v    the current vertex
     * @param path the path
     * @return
     */
    public Edge<Integer> findShortestEdge1(Vertex<String> v, Path path) {
        Integer temp = null;
        Edge<Integer> cloestEdge = null;
        if (!path.getCurrentAdV(v).isEmpty()) {
            Iterator<Vertex<String>> it = path.getCurrentAdV(v).iterator();//get each available adjacent vertex of current one
            while (it.hasNext()) {
                Vertex<String> tempv = it.next();
                if (tempv.getElement().equals("Z"))// if find Z, return directly
                    return g.getEdge(v, tempv);
                Edge<Integer> tempe = g.getEdge(v, tempv);
                if (!path.getPath_edge().isEmpty() && path.getPath_edge().top().equals(tempe) && !path.getDeadPath().contains(tempe))
                    continue;
                if (temp == null || temp > getdDistance(tempv.getElement())) {
                    temp = getdDistance(tempv.getElement());
                    cloestEdge = tempe;
                }
            }
        }
        return cloestEdge;
    }


    /**
     * @param v    the current vertex
     * @param path
     * @return
     */
    public Edge<Integer> findShortestEdge2(Vertex<String> v, Path path) {

        Integer temp = null;
        Edge<Integer> cloestEdge = null;
//        Path path = new Path(v,g);
        if (!path.getCurrentAdV(v).isEmpty()) {
            Iterator<Vertex<String>> it = path.getCurrentAdV(v).iterator();//get each adjacent vertex of current one
            while (it.hasNext()) {
                Vertex<String> tempv = it.next();
                if (tempv.getElement().equals("Z"))// if find Z, return directly
                    return g.getEdge(v, tempv);
                Edge<Integer> tempe = g.getEdge(v, tempv);
                if (!path.getPath_edge().isEmpty() && path.getPath_edge().top().equals(tempe) && !path.getDeadPath().contains(tempe))
                    continue;
                if (temp == null || temp > tempe.getElement() + getdDistance(tempv.getElement())) {
                    temp = tempe.getElement() + getdDistance(tempv.getElement());
                    cloestEdge = tempe;
                }
            }
        }
        return cloestEdge;
    }


    /**
     * find opposite vertex
     *
     * @param e the shortest edge
     * @param v the orginal vertex
     * @return
     */
    public Vertex<String> findShortestVert(Vertex<String> v, Edge<Integer> e) {
        return g.opposite(v, e);
    }


    /**
     * the Algorithm 1 for finding shortest Path
     *
     * @param startp
     * @return
     */
    public Path findShortPath1(Vertex<String> startp) {
        Vertex<String> walk = startp;
        Path path = new Path(walk, g);
        if (walk.getElement().equals("Z")) {
            System.out.println("You can not set Z as the start point");
            return path;
        }
        while (!walk.getElement().equals("Z")) {
            Edge<Integer> shortedge = findShortestEdge1(walk, path);
            //if dead end occur
            if (shortedge == null) {
                walk = path.backaStep(walk);
                continue;
            }
            walk = findShortestVert(walk, shortedge);
            if (path.contains(walk)) {
                walk = path.backTrack(walk);
                continue;
            } else {

                path.addVertex(walk);
            }

        }
        return path;
    }

    /**
     * the algorithm2 for finding a shortest path
     *
     * @param startp
     * @return
     */
    public Path findShortPath2(Vertex<String> startp) {
        Vertex<String> walk = startp;
        Path path2 = new Path(startp, g);
        if (walk.getElement().equals("Z")) {
            System.out.println("You can not set Z as the start point");
            return path2;
        }
        while (!walk.getElement().equals("Z")) {
            Edge<Integer> shortedge = findShortestEdge2(walk, path2);
            //if dead end occur
            if (shortedge == null) {
                walk = path2.backaStep(walk);
                continue;
            }
            walk = findShortestVert(walk, shortedge);
            if (path2.contains(walk)) {
                walk = path2.backTrack(walk);
                continue;
            }
//            System.out.print("->"+walk.getElement());
            path2.addVertex(walk);
        }
        return path2;
    }

    /**
     * print path info
     *
     * @param path
     */
    public static void printPath(Path path) {
        StringBuilder sb = new StringBuilder();
        LinkedStack<Vertex<String>> passNode = path.getPath_vertex();
        LinkedStack<Vertex<String>> printNode = new LinkedStack<>();

        LinkedStack<Edge<Integer>> passEdge = path.getPath_edge();
        LinkedStack<Edge<Integer>> printEdge = new LinkedStack<>();


        while (!passNode.isEmpty()) {
            printNode.push(passNode.pop());
        }
        sb.append("Shortest path:" + printNode.pop().getElement());
        while (!printNode.isEmpty()) {
            sb.append(" -> " + printNode.pop().getElement());
        }

        sb.append("\n");


            while (!passEdge.isEmpty()) {
                printEdge.push(passEdge.pop());
            }
        if(printEdge.top()!=null){
            int sum =0;
            sum = printEdge.pop().getElement();
            sb.append("Shortest length" + sum);
            while (!printEdge.isEmpty()) {
                int i = printEdge.pop().getElement();
                sb.append(" + " + i);
                sum += i;
            }
            sb.append(" = " + sum);
        }


        System.out.println(sb.toString());

    }

    /**
     * @param
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        File graph_file = new File("src/final_project/graph_input.txt");//read the graph file
        String[][] edges = Project.readGraphMap(graph_file);
        File directed_file = new File("src/final_project/direct_distance.txt");//read directed distance file
        Project p = new Project();
        p.setGraphFromEdgelist(edges, false);
        p.set_ddList(directed_file);


        //print menu
//        int flag = 0;
//        while (flag == 0) {
//            System.out.print("Please enter a vertice name(except Z, enter \"quit\" to quit): ");
//            Scanner input = new Scanner(System.in);
//            String s = input.next();
//            if (s.length() == 1 && Character.isUpperCase(s.charAt(0))) {
//                System.out.println("The start point is :" + s);
//                Vertex<String> v = p.verts.get(s);
//                System.out.println("The Algorithm1 :");
//                printPath(p.findShortPath1(v));
//                System.out.println("The Algorithm2 :");
//                printPath(p.findShortPath2(v));
//                System.out.println("\n");
//            } else if (s.equals("quit")) {
//                flag = 1;
//            } else {
//                System.out.println("Please enter a upper class letter!");
//            }
//
//        }
//

        //test code
//
        Iterator<String> it = p.verts.keySet().iterator();
        while(it.hasNext()){
            String s = it.next();
            Vertex<String> v = p.verts.get(s);
            System.out.println("Start point is :"+ s);
            System.out.println("The Algorithm1 :");
            printPath(p.findShortPath1(v));
            System.out.println("The Algorithm2 :");
            printPath(p.findShortPath2(v));
            System.out.println("\n");
        }
    }
}
