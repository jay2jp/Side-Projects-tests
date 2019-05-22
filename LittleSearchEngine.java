package search;

import java.io.*;
import java.util.*;

/**
 * This class encapsulates an occurrence of a keyword in a document. It stores the
 * document name, and the frequency of occurrence in that document. Occurrences are
 * associated with keywords in an index hash table.
 * 
 * @author Sesh Venugopal
 * 
 */
class Occurrence {
	/**
	 * Document in which a keyword occurs.
	 */
	String document;
	
	/**
	 * The frequency (number of times) the keyword occurs in the above document.
	 */
	int frequency;
	
	/**
	 * Initializes this occurrence with the given document,frequency pair.
	 * 
	 * @param doc Document name
	 * @param freq Frequency
	 */
	public Occurrence(String doc, int freq) {
		document = doc;
		frequency = freq;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(" + document + "," + frequency + ")";
	}
}

/**
 * This class builds an index of keywords. Each keyword maps to a set of documents in
 * which it occurs, with frequency of occurrence in each document. Once the index is built,
 * the documents can searched on for keywords.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in descending
	 * order of occurrence frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash table of all noise words - mapping is from word to itself.
	 */
	HashMap<String,String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashMap<String,String>(100,2.0f);
	}
	
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.put(word,word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeyWords(docFile);
			mergeKeyWords(kws);
		}
		
		
	}

	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	

	
	//first method
	
	
	public HashMap<String,Occurrence> loadKeyWords(String docFile)    
	throws FileNotFoundException {
		
		StringTokenizer lamp = new StringTokenizer(docFile," ");
		/*
		StringTokenizer tet = lamp;
		while(tet.hasMoreTokens()){
			System.out.println(getKeyWord(tet.nextToken()));
		}	*/
		ArrayList<String> xed = new ArrayList<String>(); // gonna fill with one with the words gone through and the next one with the ammount of occurances
		ArrayList<Integer> zed  = new ArrayList<Integer>();
		int greatest=0;
		for(int z = 0; z<docFile.length(); z++){
			if(lamp.hasMoreTokens()==false){
				break;
			}
			//System.out.println(lamp.nextToken());
			String deck = lamp.nextToken()+" ";
			String charlie = getKeyWord(deck);
			
			for(int y=0;y<xed.size();y++){
				if(charlie  == xed.get(y)){
					charlie = getKeyWord(lamp.nextToken());
					break;
					// breaks out of this loop 
				}
			}
			
			if (charlie != null){
			xed.add(charlie);
			}
		}
		// deletions loop 
		ArrayList<String> cred = xed;
		System.out.println(cred);
		System.out.println(cred);
		for(int s = 0;s<xed.size();s++){
			String check = xed.get(s);
			//System.out.println(xed);
			int count = 1;
			for(int u = 1;u<xed.size();u++){
				String barly = xed.get(u);
				if(check.equals(barly)){   //  fix this delete duplicates and fix occurrence coutner 
					//System.out.println(check + " and "+ barly);
					if(s==u){
						
					}else{
						count++;
						
						System.out.println(count);
						
					xed.remove(u);
					}
				}
				//zed.add(s))
			//	zed.add(s,count);
				//System.out.println(zed);
			}
			zed.add(s,count);
		}
		System.out.println(xed);
		// fixed deletion loop
		// now fix the frequencny loop 
		System.out.println(zed);
		
		/*
		for(int taco = 0; taco<xed.size(); taco ++){
			StringTokenizer camp = new StringTokenizer(docFile," ");
			String countfile = xed.get(taco);
			int count = 1;
			for(int yaco =taco+1;yaco<docFile.length();yaco++){
				
				if(camp.hasMoreTokens()== false){
					break;
				}
				String test = getKeyWord(camp.nextToken());
				if (test == countfile){
					count++;
				}
				
			}
			zed.add(count);
			if(count>greatest){
				greatest=count;
			}
			
		}
		
		*/
		
		//System.out.println(xed);
		// now all key words and numbers are alligned with the arrays
		HashMap<String,Occurrence> snart = new HashMap<String,Occurrence>(greatest);
		for(int u = 0;u<xed.size();u++){
			int ked= zed.get(u);
			Occurrence seck = new Occurrence(docFile,ked);
					String tart  = xed.get(u);
					//System.out.println(tart);
					snart.put(tart,seck);
			
		}
		return snart;
		// incorrect because the string is the key not the number 
		// i think this is ciorrect 
		// i think i finished htis one 
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE'
		
		//return null;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeyWords(HashMap<String,Occurrence> kws) {
		// COMPLETE THIS METHOD
		// idk 
		// sequentially search throught the hash and then order the current one to greatest to smallest
		// and then for loop it into global index
		// learn how to use enhanced loops better its kind of confusing
		// leanred how to use enhanced loooooooppppssss booooooooosttiiiooooo
		// this loop goes through kws and makes current it because we cant get the keys because they are fucking strings why they gotta do this cs112
		
		   for (Map.Entry<String, Occurrence> current : kws.entrySet()) {
			 String key = current.getKey();
			 Occurrence currently =  current.getValue();
			 ArrayList<Occurrence> entry = keywordsIndex.get(key);
			 if(entry == null){
				entry = new ArrayList<Occurrence>();
			
				 entry.add(currently);
				 keywordsIndex.put(key,entry);
			 }else if( entry != null){    // pretty sure i can do this with an else but need some assurance encaase
				 Occurrence sauce = currently;
				 entry.add(sauce);
				 insertLastOccurrence(entry); // not entirely sure if this works but it shoudl do the sorting for me
				 
			 }
		   }
		System.out.println(keywordsIndex);
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * TRAILING punctuation, consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyWord(String word) {
		
		boolean trails = false;
		word=word.toLowerCase();
		word=word+" ";
		for(int x=0 ;x<word.length();x++){
		
			if (Character.isAlphabetic(word.charAt(x))==false){
			
				if(Character.isAlphabetic(word.charAt(x+1))==false||word.length()-1==x ){
				word = word.substring(0, x);
			//	System.out.println(word);
				if( noiseWords.containsKey(word)){
					//boolean sauce = noiseWords.containsKey(word);
				//	System.out.println("lel");
					
					word = null;
					break;
				}
				//return word;
				
				break;
				
			}else{
				return null;
			}
			
		}
				
				
		}
		return word;
		//end of loop
		
			
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		//return null;
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * same list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion of the last element
	 * (the one at index n-1) is done by first finding the correct spot using binary search, 
	 * then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	ArrayList<Integer> midpoints = new ArrayList<Integer>();
		int end = occs.size()-1;
		int iterater = occs.get(end).frequency;
		
		int beg = 0;
	int integrand = occs.size()-2;
	int midone = occs.size()/2-1;
		// OH OK SO THE LAST ELEMEMENT IS THE ONLY ONE NOT IN ORDER
	ArrayList<Occurrence> wasabi = occs;
	Occurrence unorder = occs.get(end);
	wasabi.remove(end);// ok so this clone does not have the last one int he array list 
	
		int search = unorder.frequency;
		// i donw what this acutally does 0
		int midmath = wasabi.size()/2 - 1;
		ArrayList<Integer> ans = new ArrayList<Integer>();
		int count = 0;
		int mids=0;
		Occurrence curr=wasabi.get(0);
		while(wasabi.size()==count){
			// infinite looop \
			// will run an extra bunch of times but it will still get done but we have to get rid of the duplciates in the array list;
			count++;
			  curr = wasabi.get(midmath);
			ans.add(midmath);
		 mids = curr.frequency;
			if (search>mids){
				int addative= wasabi.size()-midmath;
				int saddative=addative/2;
				int fin = midmath+saddative;
				midmath=fin;
				// goes up if the frequency is bigge
				// gotta figure out how to break out
				
				
			}else if (search<mids){
				int negater = midmath/2;
				midmath = midmath-negater;
				
			}
			
		// will repeat until it finds what its greater than and less than i have to figure out how to break out of this 	
		
		}
		ArrayList<Occurrence> inserted = wasabi;
		
		if(search>mids){
			int jar = inserted.indexOf(curr);
			int gar = jar;
			ArrayList<Occurrence> pie = (ArrayList<Occurrence>) inserted.subList(0, gar);
			pie.add(gar+1,unorder);
			ArrayList<Occurrence> edder = (ArrayList<Occurrence>) inserted.subList(gar+1, inserted.size());
			for(int ges = 0;ges<edder.size();ges++){
				pie.add(edder.get(ges));
			}
			occs = pie;
			
		}else if(mids>search){
			int jar = inserted.indexOf(curr);
			int gar = jar-1;
			ArrayList<Occurrence> pie = (ArrayList<Occurrence>) inserted.subList(0, gar);
			pie.add(gar+1, unorder);
			ArrayList<Occurrence> edder = (ArrayList<Occurrence>) inserted.subList(gar+1,inserted.size());
			for(int ges = 0;ges<edder.size();ges++){
				pie.add(edder.get(ges));
			}
			occs = pie;
			// this works a lot better than the other one if it works
		}
		for(int y = 0; y<ans.size();y++){
			int  tec = ans.get(y);
			for(int sec = 1; sec<ans.size();sec++){
				int charles = ans.get(sec);
				if (tec == charles){
					ans.remove(sec);
					// removes duplicates 
					// there are gonna be a decent amount because of how i wrote this so
				}
				
			}
			
		}
		if(ans.size()<1){
			return null;
		}else{
			return ans;
		}
		//return null;
		// i think this is done
		
		// nvm i havent insterted yet
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of occurrence frequencies. (Note that a
	 * matching document will only appear once in the result.) Ties in frequency values are broken
	 * in favor of the first keyword. (That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2
	 * also with the same frequency f1, then doc1 will appear before doc2 in the result. 
	 * The result set is limited to 5 entries. If there are no matching documents, the result is null.
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of NAMES of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matching documents,
	 *         the result is null.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		// not sure why this gives me 2 strings what m i supposed to do 
		// gonna make it lower case
		String one = kw1.toLowerCase();
		String two = kw2.toLowerCase();
		// arraylist to ansert
		ArrayList<String> ans = new ArrayList<String>();
		// lists for the individual words 
		ArrayList<Occurrence> staco = keywordsIndex.get(one);
		ArrayList<Occurrence> taco = keywordsIndex.get(two);
		int startone;
		int starttwo;
		if(staco == null){
			startone  = -1;
			
		}else{
			startone = 0;
		}
		if(taco == null){
			starttwo = -1;
		}else{
			starttwo = 0;
		}
		// just set up starting points incase the strings they gave me dont exist in the global index
		// gotta figure out how to stop this loop once i have five results to return
		// just realized i need a hash to store these documetns gonna use a hashmap bc i dont know if storing in indexes is ok
		HashMap<String,String> hashans=new HashMap<String,String>();
		while(true){
			if((ans.size()<5) && (startone>=0 || starttwo>=0)){
				// break case
				break;
			}
			Occurrence keywordone;
				if(startone>=0){
				keywordone = 	staco.get(startone);
					
				}else{
					keywordone =null;
				}
			Occurrence secondkeyword ;
			// WHY DO THEY SPELL OCURRENCE WRONG GOD
				if(starttwo>=0){
					if(taco!=null){
						secondkeyword = taco.get(starttwo);
					}else{
						secondkeyword = null;
					}
				}else{
					secondkeyword = null;
				}
				// set insertion nodes this method is tough
			Occurrence top; //extra / i think i need a nother one to top these lists
			if(keywordone == null){
				top = secondkeyword; // when keyword one isnt appicable 
				starttwo ++ ;
			}else if ( secondkeyword == null){
				top = keywordone; // really starting to wish i named keywordone firstkeyword
				startone++;
			}
			else
			{
				if(keywordone.frequency>= secondkeyword.frequency){
					// if the kw1 bigger than kw2
					top = keywordone;
					startone++;
				}
				else 
				{
					top = secondkeyword;
					starttwo ++;
				}
			}
			// hopefully the documets can be returned after one loop or this shit isgonna be slow 
			if (top != null){
				if(hashans.containsKey(top.document)== false){
					// just gonna return the document because julian is only returning docmunt
					ans.add(top.document);
					hashans.put(top.document,top.document);
					// lol im storing it by asking the user to remeber the sentace this is so dumb but i got no other solutions.
				}else{
					// void space because if the hashans has the document then there is no point in putting pack in right
					
				}
				
			}
			
			if((startone>=0)){
				if(startone==staco.size()){
					startone = -1; // ends loopin bc its less than zero
				}
			}
			if(taco !=null){
				if(starttwo >=0){
					if(starttwo == taco.size()){
						starttwo = -1; // also ends the loop
					}
				}
			}
		}
		if(ans.size() == 0){
			return null;
		}else{
			return ans;
		}
				// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		//return null;
	}
	
}
