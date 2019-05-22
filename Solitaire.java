package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		// COMPLETE THIS 
		CardNode prev = deckRear;
		CardNode jokerA = deckRear;
		while(jokerA != null){
			if (jokerA.cardValue == 27){
				break;
				}
					jokerA=jokerA.next;
				
			
		}
		
		/*while(jokerA.next.next != jokerA){
			if(jokerA.next.next == jokerA){
				break;
			}else{
				jokerA.next = jokerA.next.next; 
			}
		}
		*/
while(prev != null){
	if(prev.next.cardValue == 27){
		break;
	}
		prev=prev.next;	
		
	
}
//printList(deckRear);

		 CardNode hold;
		 CardNode holdtwo;
		 hold = jokerA.next;
		 holdtwo=hold.next;
		 hold.next=jokerA;
		 jokerA.next=holdtwo;
		 prev.next=hold;
		 
		
	//	System.out.println("heya");
//	printList(deckRear);
		// jokerA.next.next=jokerA;
		//prev.next=jokerA.next;
		// jokerA.next=jokerA.next.next.next;
		
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    // COMPLETE THIS METHOD
		CardNode jokerB = deckRear;
		CardNode prev = deckRear;
		while(jokerB != null){
			if (jokerB.cardValue == 28){
				break;
				}else{
					jokerB=jokerB.next;
				}
			
			// if that doesnt work u can use the circle traversal u wrote earlier for joker A
		}
		while(prev != null){
			if(prev.next.cardValue == 28){
				break;
			}else{
				prev=prev.next;	
				}
			
		}
		//printList(deckRear);
		CardNode hold, holdtwo,holdthree;
		hold = jokerB.next.next;
		holdtwo=jokerB.next;
		holdthree=hold.next;
		hold.next=jokerB;
		prev.next=holdtwo;
		jokerB.next=holdthree;
		//jokerB.next=jokerB.next.next.next;
		//jokerB.next.next.next= jokerB;
		//prev=jokerB.next;
		//jokerB.next=jokerB.next.next.next.next;
		
		//System.out.println("heyb");
		//printList(deckRear);
	}
		
	
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
// broken af
	void tripleCut() {
		//System.out.println("Help Me Please This Is So Annoying");
		
		CardNode jokerF=deckRear;
		CardNode jokerS=deckRear;
		CardNode head = deckRear.next;
		CardNode prt=deckRear;
		CardNode ztr;

		while(jokerF!= null){
			if(jokerF.cardValue==27||jokerF.cardValue==28){
				break;
			}
			else{
				jokerF=jokerF.next;
			}
		}
		jokerS=jokerF.next;
		
		while(jokerS!=null){
			if(jokerS.cardValue==27||jokerS.cardValue==28){
				break;
			}
			else{
				jokerS=jokerS.next;
			}
		}
		
		while(prt!=null){
			if(prt.next.cardValue==27||prt.next.cardValue==28){
				break;
			}
			else{
				prt=prt.next;
			}
		}
		
		
		ztr= jokerS.next;
		CardNode rear = deckRear;
		//printList(deckRear);
		//System.out.println("test1");
		jokerS.next=head;
		CardNode hold = jokerF;
		CardNode hold2 = jokerS;
		prt.next=ztr;
		rear.next=hold;
		//printList(deckRear);
		//System.out.println("test2");
}
		// COMPLETE THIS METHOD
	/*	CardNode a = null;
		CardNode b=null;
		CardNode Temp = null;
		CardNode beg1;
		CardNode beg2;
		CardNode rear;
		CardNode ptr=deckRear;
		while(ptr != null){
			
			if(ptr.cardValue==27 || ptr.cardValue==28){
				a = ptr;
				break;
				
			}else{
				ptr=ptr.next;
			}
		}
		ptr=a;
	
		while(ptr != null){
			if(ptr.cardValue==28 || ptr.cardValue ==27){
				b=ptr;
				break;
			}else{
				ptr=ptr.next;
			}
		}
		beg1=deckRear.next;
		ptr=beg1;
		while(ptr!=null){
			if(ptr.next==a){
				Temp=ptr;
				break;
			}
			ptr=ptr.next;
		}
		// what is in between beg 1 and Temp has all the shit before the first joker
		printList(deckRear);
		beg2=b.next;
		rear=deckRear;
		if(deckRear.next.cardValue == a.cardValue){
			deckRear.next=beg2;
			rear.next=a;
			deckRear=b;
		}else if(b==deckRear){
			b.next=beg1;
			Temp.next=beg2;
			deckRear=rear;
			
		}else{
			b.next=beg1;
			
			deckRear=Temp;
			deckRear.next=beg2;
			rear.next=a;
			
		}
		
	
		printList(deckRear);
		//System.out.println("hey3 cut");
	}
	*/
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	// SOMEHTING IS FUCKING UP WITH THIS AND TRIPLE CUT FIX IT
	
	void countCut() {		
		int count=0;
		int x = deckRear.cardValue; 
		if(deckRear.cardValue == 28){
			x=27;
		}
		CardNode gb;
		int count2 = 0;
		CardNode first= deckRear.next;
		CardNode last;
		
	CardNode ptr=deckRear;
	CardNode ztr = deckRear;
	while(ztr != null){
		if(ztr.next==deckRear){
			break;
			
		}else{
			ztr=ztr.next;
		}
	}
	for( last = deckRear;count<x;last=last.next){
			count++;
		}
		while(ptr != null){
			if(ptr.next==deckRear){
				break;
			}else{
				ptr=ptr.next;
			}
		}
		//System.out.println(ztr.cardValue +"and"+  last.cardValue + "and"+ ptr.cardValue);
		//printList(deckRear);
		CardNode hold = deckRear;
		CardNode hold2=last.next;
		ztr.next=first;
		hold.next=hold2;
		last.next=hold;
		
		//ptr.next=first;
		//ztr.next=first;
		//System.out.println("heya count");
		//printList(deckRear);
			
		/*for(CardNode ptr = deckRear.next;count<x;ptr=ptr){
			count++;
			while(count2<=count){
			count2 ++;
			ptr=ptr.next;
			}
			
		} */ 
	}
	// when ran you have O(n^2) which is shitty so try to get it better.
	// also not entirely sure if it will work what it does is it finds deckrear and takes the value and goes taht many down and starts making its .next into deckrear and then after what deck rears ".prev" is into the next ones .next 
	/**
	 * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
	 * counts down based on the value of the first card and extracts the next card value 
	 * as key. But if that value is 27 or 28, repeats the whole process (Joker A through Count Cut)
	 * on the latest (current) deck, until a value less than or equal to 26 is found, which is then returned.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
	jokerA();
	jokerB();
	tripleCut();
	countCut();
	if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
		jokerA();
		jokerB();
		tripleCut();
		countCut();
		if(deckRear.next.cardValue == 27 || deckRear.next.cardValue == 28){
			getKey();
		}
		return deckRear.next.cardValue;
		
	}else{
	
	return deckRear.next.cardValue;
	}
		
		// COMPLETE THIS METHOD
		
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
  //  return -1;
	}
	
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		//jokerA();
		//jokerB();
		//tripleCut();
		//countCut();
		int length = message.length();
		int[] KeyStream = new int [length];
		for(int x = 0; x<length;x++){
			KeyStream[x]=getKey();
		}
		String upper= "";
		for(int k = 0; k <length;k++){
			char p = Character.toUpperCase(message.charAt(k));
			upper = upper + p;
		}
		int[] numbers = new int[length];
		for(int x = 0;x<length;x++){
			numbers[x]=upper.charAt(x)-'A'+1;
		}
		int [] mixed = new int[length];
		for(int x = 0;x<length;x++){
			if(KeyStream[x]+numbers[x]> 26){
				int big = KeyStream[x]+numbers[x];
				mixed[x]= big-26;
				//WPBI
				continue;
			}
			mixed[x]=KeyStream[x]+numbers[x];
			}
		String finall = "";
		for(int x=0;x<length;x++){
			finall= finall + (char)(mixed[x]-1+'A');
		}
		return finall;
			
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	 //   return null; 
	}
	
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
	int length = message.length();
	int [] KeyStream = new int[length];
	for(int x = 0;x<length;x++){
		KeyStream[x]=getKey();
	}
	String dycrypted = "";
	int[] numbers = new int[length];
	for(int x = 0;x<length;x++){
		numbers[x] = message.charAt(x)-'A'+1;
	}
	int[]mixed=new int[length];
	for(int x = 0;x<length;x++){
		if(numbers[x]-KeyStream[x]<0){
			int few = numbers[x]+26;
			int right = few-KeyStream[x];
			mixed[x]=right;
			continue;
		}else{
			mixed[x]=numbers[x]-KeyStream[x];
		}
		
		
	}
	String finall = "";
	for(int x = 0;x<length;x++){
		finall = finall + (char)(mixed[x]-1+'A');
	}
	return finall;
			
		/*for(int z=0;z<spoon.length; z++){
			int now = spoon[z];
			
			g = taco.next.cardValue;
			taco= deckRear.next;
			int moomoo = g+now;
			if(moomoo > 26){
			int snu = 	moomoo - 26;
			fork[z]=snu;
			}else{
			fork[z]= moomoo;
		}
		} */
		// COMPLETE THIS METHOD
		
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	 //    return null;
	}

		
	}


