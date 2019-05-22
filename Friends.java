package friends;

import structures.Queue;
import structures.Stack;

import java.util.*;

public class Friends {
	static int time;

	/**
	 * Finds the shortest chain of people from p1 to p2. Chain is returned as a
	 * sequence of names starting with p1, and ending with p2. Each pair (n1,n2)
	 * of consecutive names in the returned chain is an edge in the graph.
	 * 
	 * @param g
	 *            Graph for which shortest chain is to be found.
	 * @param p1
	 *            Person with whom the chain originates
	 * @param p2
	 *            Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null if there is no path from
	 *         p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		if (g == null) {
			// System.out.println("invalid input");
			return null;
		}
		if (p1 == null || p2 == null) {
			return null;
		}
		if (p1 == "" || p2 == "") {
			return null;
		}

		ArrayList<String> end = new ArrayList<String>();
		Person beg = StringToVertex(g, p1);
		Person Dest = StringToVertex(g, p2);
		if (p1.toLowerCase() == p2.toLowerCase()) {// case insensitive
			return null;
		}
		if (beg == null || Dest == null) {
			return null;
		}
		Queue<Person> queue = new Queue<Person>();
		Stack<Person> pathstack = new Stack<Person>();
		Person[] parentindex = new Person[g.map.size()];
		boolean[] visited = new boolean[g.map.size()];
		String[] personindex = new String[g.map.size()];
		for (int x = 0; x < g.map.size(); x++) {
			personindex[x] = g.members[x].name;
			visited[x] = false;// set all visit to false
		}
		queue.enqueue(beg);// add source
		visited[falsify(beg.name, personindex)] = true;
		// ////System.out.println(Source.name + " enqued and set visit to
		// true");
		while (queue.isEmpty()== false ) {
			Person focus = queue.dequeue();// queue poll
			ArrayList<Person> allneighbors = new ArrayList<Person>();
			allneighbors.clear();
			for (Friend v = focus.first; v != null; v = v.next) {
				allneighbors.add(g.members[v.fnum]);
			}
			for (int x = 0; x < allneighbors.size(); x++) {
				if (visited[falsify(allneighbors.get(x).name, personindex)] == false) {
					queue.enqueue(allneighbors.get(x));
					visited[falsify(allneighbors.get(x).name, personindex)] = true;
					parentindex[falsify(allneighbors.get(x).name, personindex)] = focus;

					pathstack.push(allneighbors.get(x));
					if (focus == Dest) {
						break;
					}
				}
			}
		}

		Person node;
		Person current = Dest;
		end.add(Dest.name);
		while (!pathstack.isEmpty()) {
			pathstack.pop();
			int destindex = falsify(current.name, personindex);
			node = parentindex[destindex];
			if (parentindex[destindex] != null) {

				end.add(node.name);
				current = node;
				if (node == beg) {
					break;
				}
			}
		}
		Collections.reverse(end);
		int tart = end.size();
		if (tart == 1) {
			// //System.out.println("invalid path");
			return null;
		}
		// ////System.out.println("path: " + shortestpath.toString());
		return end;
	}

	static private int falsify(String target, String thearray[]) {
		for (int x = 0; x < thearray.length; x++) {
			if (thearray[x] == target) {
				return x;
			}
		}
		return -1;
	}
// usless method 
	static private ArrayList<Person> addneighbors(Graph g, Person Source, ArrayList<Person> neighborarray) {
		if (Source != null) {
			neighborarray.add(g.members[Source.first.fnum]);
			return addneighbors(g, g.members[Source.first.fnum], neighborarray);
		}
		return neighborarray;

	}

	static private Person StringToVertex(Graph temp, String target) {
		target = target.toLowerCase();
		Person result = null;
		for (int x = 0; x < temp.members.length; x++) {
			if (target.equals(temp.members[x].name.toString().toLowerCase())) {
				result = temp.members[x];
				break;
			}
		}
		return result;
	}

	static private String derst(String name, String term){
		String  output =  name + term;
		return output;
	}
	
	public static ArrayList<String> oldshortestChain(Graph g, String p1, String p2) {
		
		/** COMPLETE THIS METHOD **/	
		// ArrayList<String> mems = g.members;
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION''
		
		// find where p1 is on the map and then dfs all the way 2 p2 
		// ok so things are stored as people and then they have a list of friends and so and so 
		// i got this one down i think so u start at the person and then you go through thier friends and recursively dfs thourgh thier friends and use the friend count in order to find the shortest path boom 
		
		int genghis = g.members.length;
		 Person[] mem = g.members;
		 //  this loop is gonna get us p1 as a person form
		 Person start = null;
		 Person end = null;
		 int ipi =  0;
		 for(int i = 0; i< genghis;i++){
			 Person pace = mem[i];
			 String storage = pace.name;
			 if(p1 == storage){
				 start = pace;
				 ipi = i;
				 
				 break;
			 }
		 }
		 int ind  = 0;
		 for(int i = 0; i< genghis; i++){
			 Person pace = mem [i];
			 String store = pace.name;
			 if( p2 == store){
				 end = pace;
				 ind = i;
			 }
		 }
		 
		 // now pace is the start of the graph 
		 Stack<Person> ini =  new Stack<Person>();
		 Stack<Integer> companion = new Stack<Integer>();
		 Person clone = start;
		 Queue<Person> BFS = new Queue<Person>();
		 BFS.enqueue(start);
		 ArrayList <ArrayList<Person>> cello =  BFS (BFS,g,end);
		 int clout = cello.size();
		 ArrayList<ArrayList<Person>> endins = new ArrayList<ArrayList<Person>>();
		 for(int i = 0; i<clout; i++){
			 ArrayList <Person>zed = cello.get(i);
			
			 int ends = zed.size()-1;
			 Person check = zed.get(ends);
			 if(check.name == end.name){
				 endins.add(zed);
			 }
		 } 
		 // ok now we just have a list with just the arrays that end in the target
		 int clap = endins.size()-1;
		 ArrayList<Integer> slap = new ArrayList<Integer>();
		 for(int i = 0; i<clap ; i++){
			int z = endins.get(i).size();
			slap.add(z);
		 }
		 int y = 1000000;
		 int storor = 0;
		 for(int i = 0 ; i<slap.size(); i++){
			int c =  slap.get(i);
			if(y>c){
				y = c;
				storor = i; 
				// will store the smallest node 
			}
		 }
		 ArrayList<Person> nd = endins.get(storor);
		 int fina = nd.size()-1;
		 ArrayList<String> rnd = new ArrayList<String>();
		 
		 for(int i = 0 ; i<fina;i++){
			 Person ched = nd.get(i);
			 String apply = ched.name;
			 rnd.add(apply);
		 }
		 return rnd;
		 /*
		 while(clone.first!=null){
			 Person stacked = new Person();
			 int index = clone.first.fnum;
			 ipi = ipi + index;
			 stacked = mem[ipi];
			 ini.push(stacked);
			 clone.first = clone.first.next;
			 companion.push(ipi);
			 
			 
		 }
		 */
		 // stack is now full of friends with thier values
		 // now we gotta do the recursive part 
		 
		
		
		// idk how to even operate this graph tho kinda confused because its not in nodes u know 
	// 	return null;
	}
	
	 static ArrayList<ArrayList<Person>> jon = new ArrayList<ArrayList<Person>>();
		private static ArrayList<ArrayList<Person>> BFS( Queue<Person> bfs,Graph g, Person end ){
			Person entry = bfs.dequeue();
			
			Person clone = entry;
			int count = 0;
			while(clone.first.next != null){
				count ++;
				
			}
			for(int i = 0; i<count; i++){
			clone.first.fnum = i;
				Person c = g.members[clone.first.fnum];
				bfs.enqueue(c);
				if ( c.equals(end)){
					break; 
					// we found them boys 
					// but how do we out put the path to get here 
				}
			
			}
			int sizer = bfs.size();
			Queue<Person> fool = bfs;
			for(int i = 0; i<sizer ; i++){
				ArrayList<Person> curr = new ArrayList<Person>();
				curr.add(entry);
				Person vtec =  fool.dequeue();
				curr.add(vtec);
				jon.add(curr);
				
			}
			if(bfs.isEmpty()){
				return jon;
			}else{
			BFS(bfs,g,end);
			}
			return jon;
			// ok the queue is filled for the next round 
			
			
				}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list
	 * contains the names of all students in a clique.
	 * 
	 * @param g
	 *            Graph for which cliques are to be found.
	 * @param school
	 *            Name of school
	 * @return Array list of clique array lists. Null if there is no student in
	 *         the given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		if (g == null) {
			// System.out.println("invalid input");
			return null;
		}
		// FIND A WAY TO ACCOUNT FOR EMPTY SCHOOLS
		boolean[] visited = new boolean[g.map.size()];
		String[] personindex2 = new String[g.map.size()];
		Queue<Person> queue = new Queue<Person>();
		ArrayList<ArrayList<String>> finalresult = new ArrayList<ArrayList<String>>();
		for (int x = 0; x < visited.length; x++) {// set all visit to false
			visited[x] = false;
			personindex2[x] = g.members[x].name;
		}
		for (int v = 0; v < visited.length; v++) {
			if (!visited[v]) {
				ArrayList<String> oneresult = new ArrayList<String>();
				oneresult = bfs(g, school, visited, personindex2, queue, g.members[v]);
				if (oneresult.isEmpty() == false) {
					finalresult.add(oneresult);
				}
			}
		}
		if (finalresult.isEmpty()) {
			return null;
		}
		return finalresult;
	}

	private static ArrayList<String> bfs(Graph g, String school, boolean[] visited, String[] personindex2,
			Queue<Person> queue, Person Source) {
		ArrayList<String> templists = new ArrayList<String>();
		queue.enqueue(Source);
		while (!queue.isEmpty()) {
			Person u = queue.dequeue();// queue poll
			ArrayList<Person> allneighbors = new ArrayList<Person>();
			allneighbors.clear();
			for (Friend v = u.first; v != null; v = v.next) {
				allneighbors.add(g.members[v.fnum]);
			}

			allneighbors.add(u);
			// System.out.println(u.name+"[u]");
			for (int z = 0; z < allneighbors.size(); z++) {
				// System.out.println("neiglist: " + allneighbors.get(z).name);
			}
			for (int x = 0; x < allneighbors.size(); x++) {
				if ((visited[falsify(allneighbors.get(x).name, personindex2)] == false)) {
					if ((school == "") || (school == null)) {
						if (allneighbors.get(x).school == null && u.school == null) {
							queue.enqueue(allneighbors.get(x));
							visited[falsify(allneighbors.get(x).name, personindex2)] = true;
							templists.add(allneighbors.get(x).name);
						}
					}
					if (allneighbors.get(x).school != null && u.school != null) {

						if (allneighbors.get(x).school.toLowerCase().equals(school.toLowerCase())
								&& u.school.toLowerCase().equals(school.toLowerCase())) {
							queue.enqueue(allneighbors.get(x));
							visited[falsify(allneighbors.get(x).name, personindex2)] = true;
							// System.out.println(allneighbors.get(x).name + "
							// [added to templist]");

							templists.add(allneighbors.get(x).name);
						} else {
							// System.out.println(u.name);
						}
					}
				}
			}

		}
		return templists;

	}

	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g
	 *            Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		if (g == null) {
			// System.out.println("invalid input");
			return null;
		}
		time = 0;
		boolean[] visited = new boolean[g.map.size()];
		String[] personindex = new String[g.map.size()];
		ArrayList<String> ap = new ArrayList<String>();

		for (int x = 0; x < g.map.size(); x++) {
			personindex[x] = g.members[x].name;
			visited[x] = false;// set all visit to false
		}
		Map<Person, Integer> visitedrank = new HashMap<>();
		Map<Person, Integer> low = new HashMap<>();
		Map<Person, Person> parentlist = new HashMap<>();
		for (int v = 0; v < visited.length; v++) {
			if (!visited[v]) {
				Person Source = g.members[v];
				dfs(g, visited, ap, Source, visitedrank, low, parentlist, personindex);
			}
		}
		if (ap.isEmpty()) {
			return null;
		}
		return ap;
	}

	static private void dfs(Graph g, boolean[] visited, ArrayList<String> ap, Person vertex,
			Map<Person, Integer> visitedrank, Map<Person, Integer> low, Map<Person, Person> parentlist,
			String[] personindex) {
		visited[falsify(vertex.name, personindex)] = true;
		visitedrank.put(vertex, time);
		low.put(vertex, time);
		time++;
		int childnum = 0;
		boolean isap = false;
		ArrayList<Person> allneighbors = new ArrayList<Person>();
		for (Friend v = vertex.first; v != null; v = v.next) {// add all
																// neighbors to
																// list
			allneighbors.add(g.members[v.fnum]);
		}
		for (int x = 0; x < allneighbors.size(); x++) {
			Person adj = allneighbors.get(x);
			if (adj.equals(parentlist.get(vertex))) {// see if parentlist and
														// vertex are
														// same
				continue;
			}
			if (!visited[falsify(adj.name, personindex)]) {
				childnum++;
				parentlist.put(adj, vertex);
				// //System.out.println("recursive");
				dfs(g, visited, ap, adj, visitedrank, low, parentlist, personindex);// recursive
				if (visitedrank.get(vertex) <= low.get(adj)) {
					isap = true;
				} else {
					low.compute(vertex, (currentVertex, time) -> Math.min(time, low.get(adj)));
					// see how this can be replaced
				}
			} else { // see if visited, replace low times
				low.compute(vertex, (currentVertex, time) -> Math.min(time, visitedrank.get(adj)));
			}
		}
		// condition 1 or 2
		if ((parentlist.get(vertex) == null && childnum >= 2) || parentlist.get(vertex) != null && isap) {
			ap.add(vertex.name);
		}

	}

}
