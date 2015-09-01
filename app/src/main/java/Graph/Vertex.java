package Graph;

import java.util.Vector;

/**
 * Created by Julio on 22/08/2015.
 */
public class Vertex <T> {
    public T value; //each node could have an associated value
    public Vector<Edge> edges; //nodes have edges

    Vertex(T value){
        this.value = value;
    }

    public boolean equals(Object o){

        if (o instanceof Vertex){
            Vertex<T> v = (Vertex<T>) o;
            return v.value == this.value;
        }
        return false;
    }
}
