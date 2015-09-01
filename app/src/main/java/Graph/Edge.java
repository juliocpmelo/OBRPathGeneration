package Graph;

/**
 * Created by Julio on 22/08/2015.
 */
public class Edge <T> {
    public T value; //each edge might contain a specific value associated
    public Vertex v1, v2; //each edge connects to two nodes
    public boolean directed;

    public Edge(Vertex v1, Vertex v2, boolean directed){
        this.v1 = v1;
        this.v2 = v2;
        this.directed = directed;
    }

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
        this.directed = false;
    }

    public boolean equals(Object o){
        if(o instanceof Edge){
            Edge<T> e = (Edge<T>)o;
            if(e.v1 == this.v1 && e.v2 == this.v2)
                return true;
            else if(!directed && e.v2 == this.v1 && e.v1 == this.v2)
                return true;
        }
        return false;
    }
}
