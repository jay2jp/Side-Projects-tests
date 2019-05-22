package structures;

import java.util.ArrayList;

/**
 * Encapsulates an interval tree.
 * 
 * @author runb-cs112
 */
public class IntervalTree {
	
	/**
	 * The root of the interval tree
	 */
	IntervalTreeNode root;
	
	/**
	 * Constructs entire interval tree from set of input intervals. Constructing the tree
	 * means building the interval tree structure and mapping the intervals to the nodes.
	 * 
	 * @param intervals Array list of intervals for which the tree is constructed
	 */
	public IntervalTree(ArrayList<Interval> intervals) {
		
		// make a copy of intervals to use for right sorting
		ArrayList<Interval> intervalsRight = new ArrayList<Interval>(intervals.size());
		for (Interval iv : intervals) {
			intervalsRight.add(iv);
		}
		
		// rename input intervals for left sorting
		ArrayList<Interval> intervalsLeft = intervals;
		
		// sort intervals on left and right end points
		sortIntervals(intervalsLeft, 'l');
		sortIntervals(intervalsRight,'r');
		
		// get sorted list of end points without duplicates
		ArrayList<Integer> sortedEndPoints = 
							getSortedEndPoints(intervalsLeft, intervalsRight);
		
		// build the tree nodes
		root = buildTreeNodes(sortedEndPoints);
		
		// map intervals to the tree nodes
		mapIntervalsToTree(intervalsLeft, intervalsRight);
	}
	
	/**
	 * Returns the root of this interval tree.
	 * 
	 * @return Root of interval tree.
	 */
	public IntervalTreeNode getRoot() {
		return root;
	}
	
	/**
	 * Sorts a set of intervals in place, according to left or right endpoints.  
	 * At the end of the method, the parameter array list is a sorted list. 
	 * 
	 * @param intervals Array list of intervals to be sorted.
	 * @param lr If 'l', then sort is on left endpoints; if 'r', sort is on right endpoints
	 */
	public static void sortIntervals(ArrayList<Interval> intervals, char lr) {
		// COMPLETE THIS METHOD
		if (lr == 'l')
		{
				for (int i = 0; i < intervals.size(); i++) 
				{
					int tiny = intervals.get(i).leftEndPoint;
					int index = i;
					for (int j = i+1; j < intervals.size(); j++)
					{
						if (intervals.get(j).leftEndPoint < tiny)
						{
							tiny = intervals.get(j).leftEndPoint;
							index = j;
						}
					}
					Interval current = intervals.remove(index);
					intervals.add(i, current);
				}
		}
		//i got lost in the sauce 
		if (lr == 'r')
		{
			for (int i = 0; i < intervals.size(); i++) 
			{
				int tiny = intervals.get(i).rightEndPoint;
				int index = i;
				for (int j = i+1; j < intervals.size(); j++)
				{
					if (intervals.get(j).rightEndPoint < tiny)
					{
						tiny = intervals.get(j).rightEndPoint;
						index = j;
					}
				}
				Interval temp = intervals.remove(index);
				intervals.add(i, temp);
			}
		}
	}
	
	/**
	 * Given a set of intervals (left sorted and right sorted), extracts the left and right end points,
	 * and returns a sorted list of the combined end points without duplicates.
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 * @return Sorted array list of all endpoints without duplicates
	 */
	public static ArrayList<Integer> getSortedEndPoints(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// COMPLETE THIS METHOD
		ArrayList<Integer> leftist = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Interval i : leftSortedIntervals)
			leftist.add(i.leftEndPoint);
		int windex = 0;
		while (leftist.size() > 0)
		{
			//System.out.println("bl");
			if (rightSortedIntervals.size() > windex)
			{
				Interval i = rightSortedIntervals.get(windex);
			if (leftist.size() != 0) {
				if (i.rightEndPoint < leftist.get(0))
				{
					if (result.size() == 0)
						result.add(i.rightEndPoint);
					else if(result.get(result.size()-1) != i.rightEndPoint)
					{
						result.add(i.rightEndPoint);
						//System.out.println("bla");
					}
				}
				else if (i.rightEndPoint > leftist.get(0))
				{
					if (result.size() == 0)
					{
						result.add(leftist.get(0));
					}
					else if(result.get(result.size()-1) != leftist.get(0))
					{
						result.add(leftist.get(0));
					}
					leftist.remove(0);
				}
				else 
				{
					if (result.size() == 0)
						result.add(i.rightEndPoint);
					else if(result.get(result.size()-1) != i.rightEndPoint)
					{
						result.add(i.rightEndPoint);
						leftist.remove(0);
					}
				}
			}
			}
			else
			{
				if (result.size() == 0)
					result.add(leftist.get(0));
				else if (result.get(result.size()-1) != leftist.get(0))
				{
					result.add(leftist.get(0));
				}
				leftist.remove(0);
			}
			windex++;
		}
		for (int i = windex; i < rightSortedIntervals.size(); i++)
		{
			if (result.size() == 0)
				result.add(rightSortedIntervals.get(i).rightEndPoint);
			else if (rightSortedIntervals.get(i).rightEndPoint != result.get(result.size()-1))
				result.add(rightSortedIntervals.get(i).rightEndPoint);
		}
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		//System.out.println(leftSortedIntervals +" " + rightSortedIntervals);
		return result;
	}
	
	/**
	 * Builds the interval tree structure given a sorted array list of end points
	 * without duplicates.
	 * 
	 * @param endPoints Sorted array list of end points
	 * @return Root of the tree structure
	 */
	public static IntervalTreeNode buildTreeNodes(ArrayList<Integer> endPoints) {
		// COMPLETE THIS METHOD
		Queue<IntervalTreeNode> q = new Queue<IntervalTreeNode>();
		//super loop it might work it might not
		for (int x : endPoints)
		{
			IntervalTreeNode squats = new IntervalTreeNode(x,x,x);
			squats.leftIntervals = new ArrayList<Interval>();
			squats.rightIntervals = new ArrayList<Interval>();
			q.enqueue(squats);
		}
		while (q.size() > 1)
		{
		
		int temp = q.size();
		
		while (temp > 1)
		{
			IntervalTreeNode t1 = q.dequeue();
			IntervalTreeNode t2 = q.dequeue();
			IntervalTreeNode t3 = new IntervalTreeNode((t1.minSplitValue+t2.maxSplitValue)/2, t1.minSplitValue, t2.maxSplitValue);
			t3.leftIntervals = new ArrayList<Interval>();
			t3.rightIntervals = new ArrayList<Interval>();
			t3.leftChild = t1;
			t3.rightChild = t2;
			q.enqueue(t3);
			temp-=2;
			//System.out.println("b");
		}
		if (temp == 1)
			q.enqueue(q.dequeue());
		}
		
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		return q.dequeue();
	}
	
	/**
	 * Maps a set of intervals to the nodes of this interval tree. 
	 * 
	 * @param leftSortedIntervals Array list of intervals sorted according to left endpoints
	 * @param rightSortedIntervals Array list of intervals sorted according to right endpoints
	 */
	public void mapIntervalsToTree(ArrayList<Interval> leftSortedIntervals, ArrayList<Interval> rightSortedIntervals) {
		// COMPLETE THIS METHOD
		IntervalTreeNode temp = root;
		for (Interval x : leftSortedIntervals)
		{
			while (temp != null)
			{
				if (temp.splitValue >= x.leftEndPoint || temp.splitValue <= x.rightEndPoint)
				{
					temp.leftIntervals.add(x);
					break;
				}
				else if (temp.splitValue > x.rightEndPoint)
					temp = temp.leftChild;
				else
					temp = temp.rightChild;
			}
		}
		for (Interval x : rightSortedIntervals)
		{
			while (temp != null)
			{
				if (temp.splitValue >= x.leftEndPoint || temp.splitValue <= x.rightEndPoint)
				{
					temp.rightIntervals.add(x);
					break;
				}
				else if (temp.splitValue > x.rightEndPoint)
					temp = temp.leftChild;
				else
					temp = temp.rightChild;
			}
		}
	}
	
	/**
	 * Gets all intervals in this interval tree that intersect with a given interval.
	 * 
	 * @param q The query interval for which intersections are to be found
	 * @return Array list of all intersecting intervals; size is 0 if there are no intersections
	 */
	public ArrayList<Interval> findIntersectingIntervals(Interval q) {
		// COMPLETE THIS METHOD
		ArrayList<Interval> resultlist = new ArrayList<Interval>();
		
		if (root == null)
			return resultlist;
		
		
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE PROGRAM COMPILE
		return quarry(q,root,resultlist);
	}
	
	private ArrayList<Interval> quarry(Interval q, IntervalTreeNode t, ArrayList<Interval> resultlist)
	{
		if (t == null){
			return resultlist;
			//base case 
		}
		if (t.splitValue >= q.leftEndPoint && t.splitValue <= q.rightEndPoint)
		{
			//System.out.println(t.leftIntervals);
			ArrayList<Interval>menace= new ArrayList<Interval>();
			menace=t.leftIntervals;
			for(int x = 0;x<menace.size();x++){
				Interval sauce = menace.get(x);
				if(sauce.rightEndPoint < q.leftEndPoint){
					menace.remove(x);
				}
				if(sauce.leftEndPoint > q.rightEndPoint){
					menace.remove(x);
				}
			}
			resultlist.addAll(menace);
			quarry(q,t.leftChild, resultlist);
			quarry(q, t.rightChild, resultlist);
		}
		else if (t.splitValue < q.leftEndPoint)
		{
			for (int i = t.rightIntervals.size()-1; i >= 0; i--)
			{
				if (q.rightEndPoint >= t.rightIntervals.get(i).leftEndPoint)
					
					if(t.rightIntervals.get(i).rightEndPoint< q.leftEndPoint){
						
					}else if (t.rightIntervals.get(i).leftEndPoint > q.rightEndPoint){
						
					}else{
						resultlist.add(t.rightIntervals.get(i));
					}
					
			}
			quarry(q, t.rightChild, resultlist);
		}
		else if (t.splitValue > q.rightEndPoint)
		{
			for (int i = 0; i < t.leftIntervals.size(); i++)
			{
				if (q.leftEndPoint <= t.leftIntervals.get(i).rightEndPoint)
					resultlist.add(t.leftIntervals.get(i));
			}
			quarry(q, t.leftChild, resultlist);
		}
		return resultlist;
		//so the problem is that it currs thoru all instead of just the intersectin
	}
}

