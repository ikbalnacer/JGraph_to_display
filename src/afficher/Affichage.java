package afficher;

import java.awt.Color;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * A demo applet that shows how to use JGraph to visualize JGraphT graphs.
 *
 * @author Barak Naveh
 *
 * @since Aug 3, 2003
 */
public class Affichage extends JApplet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
    private static final Dimension DEFAULT_SIZE = new Dimension( 530, 320 );

    // 
    private JGraphModelAdapter m_jgAdapter;

    
    public static void main(String [] args)
    {
        UndirectedGraph<String, DefaultEdge> stringGraph = createStringGraph();

        // note undirected edges are printed as: {<v1>,<v2>}
        System.out.println(stringGraph.toString());

        // create a graph based on URL objects
        DirectedGraph<URL, DefaultEdge> hrefGraph = createHrefGraph();

        // note directed edges are printed as: (<v1>,<v2>)
        System.out.println(hrefGraph.toString());
    }

    /**
     * Creates a toy directed graph based on URL objects that represents link
     * structure.
     *
     * @return a graph based on URL objects.
     */
    private static DirectedGraph<URL, DefaultEdge> createHrefGraph()
    {
        DirectedGraph<URL, DefaultEdge> g =
                new DefaultDirectedGraph<>(DefaultEdge.class);

        try {
            URL amazon = new URL("http://www.amazon.com");
            URL yahoo = new URL("http://www.yahoo.com");
            URL ebay = new URL("http://www.ebay.com");

            // add the vertices
            g.addVertex(amazon);
            g.addVertex(yahoo);
            g.addVertex(ebay);

            // add edges to create linking structure
            g.addEdge(yahoo, amazon);
            g.addEdge(yahoo, ebay);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return g;
    }

    /**
     * Create a toy graph based on String objects.
     *
     * @return a graph based on String objects.
     */
    private static UndirectedGraph<String, DefaultEdge> createStringGraph()
    {
        UndirectedGraph<String, DefaultEdge> g =
                new SimpleGraph<>(DefaultEdge.class);

        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add the vertices
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        // add edges to create a circuit
        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }
}