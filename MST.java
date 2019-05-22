package apps;

import structures.*;
import java.util.ArrayList;

public class MST {

    /**
     * Initializes the algorithm by building single-vertex partial trees
     *
     * @param graph Graph for which the MST is to be found
     * @return The initial partial tree list
     */
    public static PartialTreeList initialize(Graph graph) {

    	/* COMPLETE THIS METHOD */
		//System.out.println(graph.vertices.length); WHY THE FUCK IS ThIS NOT WORKING 
		// AHAHAHAHAAHAHAHHAHAHAHAHAHAHHAHAh
		// NEED TO FIX THE FUCKING NULL POINTER EXCEPTION
		PartialTreeList starter = new PartialTreeList();
		int lang = graph.vertices.length;
		for(int x = 0;x<lang;x++){
			//System.out.println("sauce");
			Vertex vert = graph.vertices[x];  // lil uzi reference 
			// i have a vertex for ever iteration
			Vertex.Neighbor supreme = vert.neighbors;
			PartialTree temptree = new PartialTree(vert);
			// starts trees for the array
		
			// used to get the next value of vert
			
			while(supreme!=null){
				/*
				if(supreme!=null){
					break;
					// break case
					// bc 
				}
				THIS STUPID GODDAMN CASE BROKE EVERTYING FOR LIKE 6 hrs GAHHHHHHHh
				ight ima rant for a bit i am a little dumb but whateveer that should have worked 
				*/
				PartialTree.Arc edge = new PartialTree.Arc(vert, supreme.vertex, supreme.weight);
					// edges need to be regulated
				temptree.getArcs().insert(edge);
					// not sure why this doesnt owrk i think the .getarcs is getting the error 
				supreme = supreme.next;
					// i really got to come up with better variable names
			}
		starter.append(temptree);
			
			
		}
		//String fub = starter.toString();
		//System.out.println();
		return starter;
    }

    /**
     * Executes the algorithm on a graph, starting with the initial partial tree list
     *
     * @param ptlist Initial partial tree list
     * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
     */
    public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
    	// never rely on an ifstatement to break a while loop again
 ArrayList<PartialTree.Arc> edgeBuckets = new ArrayList<>();
 int count = 0;
 while (ptlist.size() > 1) {
// count++
	 PartialTree treeloop = ptlist.remove();
	 	MinHeap<PartialTree.Arc> edgeheap = treeloop.getArcs();
	 		PartialTree.Arc arc = edgeheap.deleteMin(); // gets the arc from the edgehap
            while (arc != null) {                                       
                // 

                Vertex two = arc.v2; // xo vert two 
                Vertex one = arc.v1;  // xo vert one
               //Vertex zero = two;
                

                // C
                PartialTree debbie;
                
                debbie = ptlist.removeTreeContaining(one);      
                //Thows(nullpointerException e)
                if (debbie == null) {
                    
                   debbie = ptlist.removeTreeContaining(two);     
                    //
                    //}
                }
                PartialTree hold = debbie;
                //incase debbie gets screwed up 
                //System.out.println(hold);
                
// System.out.println (debbie.size);
                //
                if (hold == null) {                    
                	
                }
                else {
                	treeloop.merge(hold);
                    edgeBuckets .add(arc);                             
                     //
                     ptlist.append(treeloop);                             
                    
                     break;    
                }

              
                arc = edgeheap.deleteMin();
            }
        }
        // System.out.println(edgeBuckets);
        
 //System.out.println("we out mother truckers");
  //rock();
  
 return edgeBuckets;
        
     
    }
    private boolean comp(Vertex vert,PartialTree Shenanigans){
    	return true;
    	// not needed any more that was a stupid attempt at a solution to a dumb problem
    	
    }
    private static void rock(){
    	System.out.println("dunzo");
    }
}
// FINAL BURRITOOOOOOOOOO 