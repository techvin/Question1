package question1;

import java.util.ArrayList;
import java.util.List;

public interface GNode {

	public String getName();
	public GNode[] getChildren();
	public void setName(String name);
	public void setChildren(GNode[] childrenArray);
	public List<List<Integer>> getAdjacencyList();
	public void printAdjacencyList();
	@SuppressWarnings("rawtypes")
	public ArrayList walkGraph(GNode node);
}
