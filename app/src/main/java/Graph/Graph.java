package Graph;

import java.util.Vector;

/**
 * Created by Julio on 22/08/2015.
 */
public class Graph <T , K> {
    public Vector<Edge <K> > edges;
    public Vector<Vertex <T> > vertexes;


    public Graph(){

        edges = new Vector< Edge<K> >();
        vertexes = new Vector< Vertex<T> >();
    }

    public void addVertex(T vertexValue){
        Vertex<T> v = new Vertex<T> (vertexValue);
        if(!vertexes.contains(v))
            vertexes.add(v);
    }

    public void addVertex(Vertex <T> vertex){
        if(!vertexes.contains(vertex))
            vertexes.add(vertex);
    }
    public void addEdge(Edge <K> edge){
        if(!edges.contains(edge))
            edges.add(edge);
    }

    public void addEdge(T vertexValue1, T vertexValue2){
        Vertex<T> v1 = new Vertex<T> (vertexValue1);
        Vertex<T> v2 = new Vertex<T> (vertexValue2);

        int vertexIndex = vertexes.indexOf(v1);
        if(vertexIndex != -1 )
            v1 = vertexes.get(vertexIndex);

        vertexIndex = vertexes.indexOf(v2);
        if(vertexIndex != -1 )
            v2 = vertexes.get(vertexIndex);

        connect(v1, v2);
    }


    public void connect(Vertex <T> v1, Vertex <T> v2){
        Edge<K> e = new Edge<K>(v1,v2);
        if(!edges.contains(e))
            edges.add(e);
    }
}
