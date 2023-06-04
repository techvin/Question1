package question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//This class is used for setting source and destination vertices for a given edge
class Edge
{
    int source, dest;
 
    public Edge(int source, int dest)
    {
        this.source = source;
        this.dest = dest;
    }
}

//This class is used to create a graph
class Graph implements GNode
{
	String name;
	GNode[] childrenArray;
	
    List<List<Integer>> adjList = null;
    Graph(List<Edge> edges, int n)
    {
        adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        for (Edge edge: edges) {
            adjList.get(edge.source).add(edge.dest);
        }
    }
    Graph() {};
    
    public List<List<Integer>> getAdjacencyList() {
        return adjList;
    }
    
    public void printAdjacencyList() {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("Adjacency list of " + i + " = ");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(adjList.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

	@Override
	public String getName() {
		return name;
	}

	@Override
	public GNode[] getChildren() {
		return childrenArray;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setChildren(GNode[] childrenArray) {
		this.childrenArray = childrenArray;
	}
	
	// this function accepts GNode vertex as a parameter and returns all the vertices of a graph
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList walkGraph(GNode graph) {
		ArrayList<GNode> vertexList = new ArrayList<GNode>();
		for (int i = 0; i < graph.getAdjacencyList().size(); i++) {
			GNode vertex = new Graph();
			vertex.setName(String.valueOf(i));
			ArrayList<GNode> childVertexList = new ArrayList<GNode>();
			for (int j = 0; j < graph.getAdjacencyList().get(i).size(); j++) {
				GNode adjvertex = new Graph();
				adjvertex.setName(String.valueOf(graph.getAdjacencyList().get(i).get(j)));
				childVertexList.add(adjvertex);
			}
			GNode[] childrenArray = new GNode[childVertexList.size()];
			childrenArray = childVertexList.toArray(childrenArray);
			vertex.setChildren(childrenArray);
			vertexList.add(vertex);
		}
		return vertexList;
	}
    
}
 
class GraphQuestion1
{	
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    	List<Edge> edges = Arrays.asList(
                new Edge(0, 1), new Edge(1, 2), new Edge(1, 3),
                new Edge(2, 3), new Edge(3, 4)
        );
    	
        int n = 5;
        GNode graph = new Graph(edges, n);
        graph.printAdjacencyList();
        System.out.println();
        System.out.println("=========");
        System.out.println("\nAll vertices of a graph are = " + graph.walkGraph(graph) + "\n");
        ArrayList<GNode> vertexList = graph.walkGraph(graph);
        System.out.println("=========");
        for (GNode vertex: vertexList) {
        	System.out.println();
        	System.out.print("Vertex name : " + vertex.getName());
        	System.out.print(" | Vertex adjacent vertices array size : " + vertex.getChildren().length);
        	System.out.print("| Vertex adjacent vertices array : " + vertex.getChildren());
        }
    }
}