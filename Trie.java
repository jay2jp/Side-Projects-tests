package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie.
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	static String globalguide= "";
	
	// prevent instantiation
	static int downthepole = 0;
	private Trie() {
	}

	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!) The words in
	 * the input array are all lower case.
	 * 
	 * @param allWords
	 *            Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	//pr

	private static TrieNode babymama(TrieNode base, int entry, String word, String prefix, String prevprefix) {
		
		int index = base.substr.wordIndex;
		short start = base.substr.startIndex;
		short end = base.substr.endIndex;

		Indexes triplet = new Indexes(index, start, end);
		Indexes based = new Indexes(index, start, end);
		
		Indexes newwordindex = new Indexes(entry, (short) (prefix.length()), (short) (word.length() - 1));
		if (prefix != prevprefix) {
			triplet.startIndex = (short) (start + (short) (prefix.length()) - prevprefix.length());
		} else {
			triplet.startIndex = (short) (start + (short) (prefix.length()));
		}
		
		if (prefix == "") {
			
		}else{
			based.endIndex = (short) (prefix.length() - 1);
		}
		TrieNode newword = new TrieNode(newwordindex, null, null);
		TrieNode child = new TrieNode(triplet, base.firstChild, newword);

		base = new TrieNode(based, child, base.sibling);
		

		return base;
	}

	private static TrieNode engine(String entry, int entrynum, TrieNode first, String[] allWords, String prevprefix,
			TrieNode upperlevel) {
		TrieNode ptr = first;
		String target = allWords[first.substr.wordIndex].substring(0, first.substr.endIndex + 1);
		String prefix = target.substring(0, prefixcompare(entry, target));
		
		if ((prefixcompare(entry, target) > 0) && (prefixcompare(entry, target) > (prevprefix.length()))) {
			if (ptr.firstChild == null) {
	
				String upperprefix = "";
				if (upperlevel != null) {
					upperprefix = allWords[upperlevel.substr.wordIndex].substring(0, upperlevel.substr.endIndex + 1);

				}
				if (prefix.length() < upperprefix.length()) {
				
					for (int x = globalguide.length() - 1; x >= 0; x--) {
						if (globalguide.charAt(x) != 'd') {
							globalguide = globalguide.substring(0, globalguide.length() - 1);
						} else {
							break;
						}
					}
					globalguide = globalguide.substring(0, globalguide.length() - 1);

					
					upperlevel = babymama(upperlevel, entrynum, entry, prefix, prevprefix);
					return upperlevel;
				} else {
					ptr = babymama(ptr, entrynum, entry, prefix, prevprefix);
					return ptr;
				}

			} else {
				
				globalguide = globalguide + "d";
				ptr = engine(entry, entrynum, ptr.firstChild, allWords, target, ptr);
			}
		} else {
			if ((ptr.sibling != null)) {
				globalguide = globalguide + "s";
				
				if ((prefixcompare(entry, target) == (prevprefix.length()))) {
					
					ptr = engine(entry, entrynum, ptr.sibling, allWords, prevprefix, upperlevel);
				} else {
					
					ptr = engine(entry, entrynum, ptr.sibling, allWords, "", upperlevel);
				}
			} else {				
				ptr.sibling = addnode(entry, entrynum, prefix);
				return ptr;
			}
		}
		return ptr;
	}
/*
 * U WANNA KNOW HOW MUCH I HATE RUCURRIOSN 
 * THIS FUCKING Mucb
 * BITCHHCHHCH
 * 
 */
	 //print(root,allWords);
		// should work not entirely sure why its not
		// comparison runs infinite 
	
	/*
	
	int count  = 0;
	// this below is for the first node
	count =1;
	String dunkey = allWords[0];
	int gs =  dunkey.length()-1;
	short gtr = (short)gs;
	short bet = (short)0;
	Indexes alpha  = new Indexes(0,bet,gtr);
	// alpha is the first one to go in 
	Indexes perma = alpha; //  a perminant version of alp
	
	TrieNode parent = new	TrieNode(alpha, null, null);
	TrieNode solid = parent;
	
	while (count < allWords.length){
		String current = allWords[count];
		int count2 = 0;
		if(parent.equals(solid)){
			// base case if there is only one thing in the tree which would be alpha 
			while(count2< solid.substr.toString().length()){
				char thru  = current.charAt(count2);
				if (thru == solid.substr.toString().charAt(count2)){
					count2++;
				
				}
				if(count2>2){
					break;
					// clear prefix
				}
			}
			if(count2!=0){
				int sterk = current.length()-1;
				short stk = (short)sterk ;
				Indexes blue = new Indexes(count,bet,stk); // this may be wrong check tm 
				// alright now for a new tree node this is still for the parents
				TrieNode charles = new TrieNode(blue,null,parent);
				solid.firstChild = charles;
				// i think thats how this works
				
				
			}
			
		}
		// vowel check method below 
		/*
		while(vowelcheck(current.charAt(count2)) && !vowelcheck(current.charAt(count2+1)) ){
			// this loop will find the suffix
			count2++;
		}
		String suffix = current.substring(0, count2);
		// now how to do the prefix or the end
		String prefix = current.substring(count2+1, current.length()-1);
		// ^^ maybe this works i have to ask
		
		count ++;
		
		 // so indexes are the final children while the trie node is the inbetween / transport nodes
		// so the first node does have to be a index 
		// nvm they all tree nodes 
		
		
		
	}
	*/
	// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
	// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
	int countdracula = 0;
	
	private static TrieNode addnode(String entry, int entrynum, String prefix) {
		Indexes newIndex = new Indexes(entrynum, (short) prefix.length(), (short) (entry.length() - 1));
		TrieNode newbeam = new TrieNode(newIndex, null, null);
		return newbeam;
		
	}

	private static String pullout(TrieNode z,String[] allWords){
		if(z.substr == null){
			System.out.println("fuck");
		}
		int bravo = z.substr.wordIndex;
		System.out.println(bravo);
		String alpha = allWords[bravo];
		return alpha;
	}
	private static boolean vowelcheck (char c){
		boolean fin = "AEIOUTYaeiouy".indexOf(c) != -1;
		return  fin;
	}
	
	public static TrieNode buildTrie(String[] allWords) {
		
		TrieNode root = new TrieNode(null, null, null);
	
		if (allWords == null) {
			return root;
		}
		short beg = 0;
		short end = (short) (allWords[0].length() - 1);
		int index = 0;
		Indexes firsttriplet = new Indexes(index, beg, end);
		TrieNode first = new TrieNode(firsttriplet, null, null);
		
		root = new TrieNode(null, first, null);
		
		String entry = "";
		Trie.print(root, allWords);
		for (int x = 1; x < allWords.length; x++) {
			entry = allWords[x];
			globalguide = "";
			TrieNode temp = engine(entry, x, first, allWords, "", null);
			TrieNode ptr = first;
			TrieNode prev = null;
		
			if (globalguide.length() == 0) {
				first = temp;
			} else {
			

				while (globalguide.length() > 1) {
					if (globalguide.charAt(0) == 's') {
						prev = ptr;
						ptr = ptr.sibling;
					} else {
						prev = ptr;
						ptr = ptr.firstChild;
					}
					globalguide = globalguide.substring(1);
					
				}

				if (globalguide.charAt(0) == 's') {
					if (prev == null) {
						ptr = first;
					}
					
					ptr.sibling = temp;
				} else {
					if (prev == null) {
						ptr = first;
					}
					ptr.firstChild = temp;
				}

			}
			root = new TrieNode(null, first, null);
			Trie.print(root, allWords);
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		return root;
	}

	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the
	 * leaf nodes in the trie whose words start with this prefix. For instance,
	 * if the trie had the words "bear", "bull", "stock", and "bell", the
	 * completion list for prefix "b" would be the leaf nodes that hold "bear",
	 * "bull", and "bell"; for prefix "be", the completion would be the leaf
	 * nodes that hold "bear" and "bell", and for prefix "bell", completion
	 * would be the leaf node that holds "bell". (The last example shows that an
	 * input prefix can be an entire word.) The order of returned leaf nodes
	 * DOES NOT MATTER. So, for prefix "be", the returned list of leaf nodes can
	 * be either hold [bear,bell] or [bell,bear].
	 *
	 * @param rootpr
	 *            Root of Trie that stores all words to search on for completion
	 *            lists
	 * @param allWords
	 *            Array of words that have been inserted into the trie
	 * @param prefix
	 *            Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with
	 *         the prefix, order of leaf nodes does not matter. If there is no
	 *         word in the tree that has this prefix, null is returned.
	 *         
	 *         
	 *         
	 *         
	 *         
	 *         
	 */
	
	// make a method to check if the next layer is empty
	// take a child and just makesure none of them have children.
	private static boolean  hunny(TrieNode gtr ){
		int count = 0;
		while (count != -1){
			if(gtr.firstChild != null){
				if(gtr.sibling !=null){
				gtr=gtr.sibling;
				}
				else
				{
					break;
				}
				
			}else{
				count++;
			}
		}
		if(count != 0){
			return true;
		}
		else
		{
			return false;
		}
		
		
	//	return false ;//  place holder  
		
	}
	
	
	 private static ArrayList<TrieNode> modcomparison(TrieNode firstchild, String[] allWords, String prefix){
		//  what this is gonna do is just return the prefix and shit
		 ArrayList<TrieNode> daddy = new ArrayList<TrieNode>();
		 //available methods 
		 TrieNode ptr = firstchild;
		 boolean juice = false;
		 
		 // kill the loop when the while turns true
		 String check;
		 int prefixend = prefix.length()-1;
		 while(juice != true){
			 if(ptr.substr.endIndex == 0){
				 String abdulla = pullout(ptr,allWords);
				 check = abdulla.substring(0,0);
				 
			 }else if(ptr.substr.endIndex == prefixend){
				 String abdulla = pullout(ptr,allWords);
				 check=abdulla.substring(0,prefixend);
				 
			 }else{
		 check = pullout(ptr,allWords); 
			 }
			 System.out.println(check); 
			  // some reason the 2 things it goes thru are empty strings maybe consider a re write ofthis by the end of the day 
			int count = 0;
			int count2 = 0;
			int i;
			for( i = 0;i<prefix.length();i++){
				if(i>=check.length()){
					break;
				}
				char g = check.charAt(i);
				char y = prefix.charAt(i);
				if(g==y){
					count++;
					count2  = count;
				}else{
					count = 0;
				
			}
				if(count == 0){
					break;
				}
				
		 }
			// making a case for an exact match 
			int matt = prefix.length();
			if (prefix.equals(check)){
				// PERFECT MATCH
			
					String bluh  = pullout(ptr,allWords);
					System.out.print(bluh+"   jay");
					
					daddy.add(ptr);
					return daddy;
				
				//	System.out.println(bluh);
				//System.out.println("I HAVE BEEN REACHED ");
				
				}
			
			
			if(count==0){
				if(ptr.sibling!=null){
					ptr = ptr.sibling;
					modcomparison(ptr,allWords,prefix);
					// checks next node
					
				}else{
					if(daddy.isEmpty()){
					return null;
					}else{
						return daddy;
					}
					// returns null to say there is no matches 
				}
			}
			if(count != 0){
				daddy.add(ptr);
				if(ptr.firstChild!=null){
					
					ptr=ptr.firstChild;
					//System.out.println(ptr.substr);
					boolean rod = hunny(ptr);
					if(rod==true){
						modcomparison(ptr,allWords,prefix);
					}else{
						
						// this makes the last added thing in the list the prefix with the children u have to return
						
						return daddy;
					}
					// before u do this check if the next layer doesnt have children aka method called hunny 
				
					
				}else{
					return daddy;
					
				}
			}
		 
		 
		 }
		 return null;
		 
	}
	 
	 
	 
	 
	
		/** COMPLETE THIS METHOD **/
		// OK JAY EVERTHING IS SET UP TO WRTIE THIS SHIT TMMMMMMM LITTY TITY 
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		//print(root,allWords);
		
		
		public static ArrayList<TrieNode> completionList(TrieNode root, String[] allWords, String prefix) {
			/** COMPLETE THIS METHOD **/

			ArrayList<TrieNode> fins = new ArrayList<TrieNode>();
			downthepole = 0;
			boolean slide = false;
			TrieNode ptr = root.firstChild;
			TrieNode ptrstalk = null;
			String compared = prefix;
			if (prefix.isEmpty() == true) {
				return null;
			}
			// System.out.println("prefix node: " + target);
			while (ptr != null) {
				downthepole = 0;
				String curr = allWords[ptr.substr.wordIndex].substring(0, ptr.substr.endIndex + 1);
				// System.out.println("ptr at: " + ptr);
				if (compared.length() > curr.length()) {
					if (compared.startsWith(curr) == true) {
						TrieNode ptrcopy = ptr;
						while (ptrcopy.firstChild != null) {
							if (compared.startsWith(curr) == true) {
								downthepole++;
								ptrcopy = ptrcopy.firstChild;
								

							} else {
								break;// reached level
							}
						}
						
						fins = listmaker(ptrcopy, allWords, fins, compared);
						if (fins.isEmpty() == false) {
							
							downthepole = 0;
						}
						
					}
				} else {
					if (curr.startsWith(compared) == true) {
						
						ptrstalk = ptr;
						if (ptrstalk.firstChild != null) {
							ptrstalk = firepole(ptrstalk, allWords);
							
							TrieNode brother = ptrstalk;

							fins= listmaker(brother, allWords, fins, compared);
						} else {
							
							if (fins.contains(ptrstalk) != true) {
								

								fins.add(ptrstalk);
							}
						}
						 
					}
				}

				
				if (downthepole != 0) {
					
					
					if (downthepole == 1) {
						
						String allStrings = allWords[ptr.substr.wordIndex].substring(0, ptr.substr.endIndex + 1);
						String blank = "";
						if (ptr.sibling != null) {
							blank = allWords[ptr.sibling.substr.wordIndex].substring(0, ptr.sibling.substr.endIndex + 1);
						}
						
						if (blank.isEmpty() || allStrings.isEmpty()) {
						} else {
							if (blank.charAt(0) == allStrings.charAt(0)) {
								ptr = ptr.sibling;
								
								slide = true;
								
								downthepole++;

							} else {
								downthepole++;
								ptr = ptr.firstChild;
							
								if (ptr.sibling != null && ptr.sibling.firstChild != null) {
									ptr = ptr.sibling;
									
									slide = true;
								} else {
									slide = true;
								}

							}
						}

					}

					for (int x = 0; x < downthepole - 1; x++) {
						
						ptr = ptr.firstChild;
						

					}
					if (downthepole > 0) {
						downthepole--;
					
					}

				}
				
				if (slide == false) {

					ptr = ptr.sibling;
				} else {
					slide = false;
				}
			
			}

			
			if (fins.isEmpty() == true) {
				return null;
			} else {
				return fins ;
			}
		
		
		
		
		
		
		
		/*
		TrieNode mother =root.firstChild;// HOW THE FUCK IS THIS NULL OF COUSE THERE IS A CHILD
		ArrayList<TrieNode> sauce = modcomparison(mother,allWords,prefix);
		if(sauce == null){
			System.out.println("sauce is lie");
			return null;
		}
		if (sauce.isEmpty()== true){
		 System.out.println("empty");
		 return null;
		}
		//String baby = sauce.toString();
		//System.out.println(baby);
		int c = sauce.size();
		int z = 0;
		
		
		
			
			
		int g = sauce.size() - 1;
		TrieNode father = sauce.get(g);
		//print(father, allWords);
		ArrayList<TrieNode> avenger = new ArrayList<TrieNode>();
		TrieNode traverse = father.firstChild;
		int count = 0;
		if(traverse == null){
			System.out.println("there is no father");
			avenger.add(father);
			return avenger;
			// there is an error where u are literally returning the end not the prefix node so it fucks u over
			
		}
		while(traverse.sibling!=null){
			count++;
			//System.out.println(count);
			avenger.add(traverse);
			traverse = traverse.sibling;
		
			if(traverse.sibling == null){
				break;
			}
		}
		System.out.println(avenger);
		return avenger;
		
		
		*/
		
		//return null;
	}

	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}

	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i = 0; i < indent - 1; i++) {
			System.out.print("    ");
		}

		if (root.substr != null) {
			String pre = words[root.substr.wordIndex].substring(0, root.substr.endIndex + 1);
			System.out.println("      " + pre);
		}

		for (int i = 0; i < indent - 1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}

		for (TrieNode ptr = root.firstChild; ptr != null; ptr = ptr.sibling) {
			for (int i = 0; i < indent - 1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent + 1, words);
		}
	}

	// returns what part of the word matches the prefix in the start
	private static int prefixcompare(String word, String prefix) {
		int counter = 0;
		for (int i = 0; i <= prefix.length() - 1; i++) {
			if (prefix.charAt(i) == word.charAt(i)) {
				// System.out.println("comparing " + prefix.charAt(i) + " to " +
				// word.charAt(i));
				counter++;
			} else {
				break;
			}
		}
		return counter;
	}
	private static ArrayList<TrieNode> listmaker(TrieNode temporary, String[] allWords, ArrayList<TrieNode> nodelist,
			String objective) {
	

		while (temporary != null) {

			String childsiblingnode = allWords[temporary.substr.wordIndex].substring(0, temporary.substr.endIndex + 1);


			if (childsiblingnode.startsWith(objective) == true) {
				if ((temporary.firstChild == null && (nodelist.contains(temporary) == false))) {
				

					nodelist.add(temporary);
				}
			}
			temporary = temporary.sibling;
		}
		return nodelist;
	}
	private static TrieNode firepole(TrieNode temporary, String[] allWords) {
		while (temporary.firstChild != null) {
			

			downthepole++;
	
			String childnode = allWords[temporary.substr.wordIndex].substring(0, temporary.substr.endIndex + 1);
			temporary = temporary.firstChild;
		

		}
		return temporary;
	}

}