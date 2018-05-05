package final_project;

import net.datastructures.Edge;
import net.datastructures.LinkedStack;
import net.datastructures.Vertex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * this class for saving path info
 * @param <V>
 * @param <E>
 */


public class Path<V, E> {
    public AdjacencyMapGraph g;// the graph info


    private LinkedStack<Vertex<V>> path_vertex = new LinkedStack<>(); //for saving passing vertex in path
    private LinkedStack<Edge<E>> path_edge = new LinkedStack<>(); //for saving passing edge
    private HashSet<Vertex<V>> vertexHashSet = new HashSet<>(); // saving passing vertex in path for check every vertex appears once
    private HashMap<Vertex<V>, HashSet<Vertex<V>>> adjacencyVertex = new HashMap<>(); //saving passing vertex and its adjacent vertex which haven't passed
    private HashSet<Edge<E>> deadPath = new HashSet<>(); //saving dead passing edge

    public LinkedStack<Edge<E>> getPath_edge() {
        return path_edge;
    }

    public AdjacencyMapGraph getG() {
        return g;

    }

    public LinkedStack<Vertex<V>> getPath_vertex() {
        return path_vertex;
    }

    public HashSet<Vertex<V>> getVertexHashSet() {
        return vertexHashSet;
    }

    public HashMap<Vertex<V>, HashSet<Vertex<V>>> getAdjacencyVertex() {
        return adjacencyVertex;
    }


    public Path(Vertex<V> start_vertex, AdjacencyMapGraph g) {
        this.g = g;
        addVertex(start_vertex);
    }



    //check vertex appears only once
    public boolean contains(Vertex<V> v) {
        return vertexHashSet.contains(v);
    }

    /**
     * add new vertex into path
      * @param v
     */
    public void addVertex(Vertex<V> v) {
        adjacencyVertex.put(v, getAllAdjacentV(v));
        if (!path_vertex.isEmpty()) {
            Edge<E> e = g.getEdge(v, path_vertex.top());
            path_edge.push(e);
        }
        path_vertex.push(v);
        vertexHashSet.add(v);
    }

    //get a new vertex's adjacent vertex for init a new vertex in path
    public HashSet<Vertex<V>> getAllAdjacentV(Vertex<V> v) {
        HashSet<Vertex<V>> adjacent_Vset = new HashSet<>();
        Iterator<Edge<E>> it = g.outgoingEdges(v).iterator();
        while (it.hasNext()) {
            Vertex<V> adv = g.opposite(v, it.next());
//            if(!path_vertex.top().equals(adv.getElement()))
            adjacent_Vset.add(adv);
            if (!path_vertex.isEmpty() && path_vertex.top().equals(adv.getElement()))
                adjacent_Vset.remove(adv);
        }
        return adjacent_Vset;
    }

    //the current never been adjacent vertexs of a vertex
    public HashSet<Vertex<V>> getCurrentAdV(Vertex<V> v) {
        return adjacencyVertex.get(v);
    }

    public HashSet<Edge<E>> getDeadPath() {
        return deadPath;
    }

    //if there is a circle deadEnd
    public Vertex<V> backTrack(Vertex<V> v) {
        Vertex<V> pop = null;
        while (!path_vertex.top().getElement().equals(v.getElement())) {
            pop = path_vertex.pop();
            deadPath.add(path_edge.pop());
            adjacencyVertex.remove(pop);//remove last pop element in path;
            vertexHashSet.remove(pop);//remove current wrong vertex in path
        }
//        System.out.println("Dead End!");
        adjacencyVertex.get(path_vertex.top()).remove(pop);
        ;//delete available adjacent vertex;
        return path_vertex.top();
    }




    //if there is a one way dead end
    public Vertex<V> backaStep(Vertex<V> v){
        Vertex<V> pop = path_vertex.pop();
        deadPath.add(path_edge.pop());

        adjacencyVertex.remove(pop);//remove last pop element in path;
        vertexHashSet.remove(pop);//remove current wrong vertex in path
        return path_vertex.top();
    }

}
