import java.util.Iterator;

/*
CS 314 Students, put your results to the experiments and answers to
questions here:

Test #1 - The Adventures of Huckleberry Finn

File Size: 593 KB
Number of words: 114254
Number of distinct words: 14277

Time to add (SortedSet): .637 seconds
Time to add (UnsortedSet): 1.504 seconds (236% compared to SortedSet)
Time to add (HashSet): .090 seconds (5.98% compared to UnsortedSet)
Time to add (TreeSet): .156 seconds (173% compared to HashSet)

Test #2 - The Adventures of Tom Sawyer

File Size: 412 KB
Number of words: 73836
Number of distinct words: 14147

Time to add (SortedSet): .660 seconds
Time to add (UnsortedSet): 1.172 seconds (178% compared to SortedSet)
Time to add (HashSet): .060 seconds (5.11% compared to UnsortedSet)
Time to add (TreeSet): .085 seconds (142% compared to HashSet)

Test #3 - Frankenstein

File Size: 439 KB
Number of words: 77986
Number of distinct words: 12193

Time to add (SortedSet): .498 seconds
Time to add (UnsortedSet): .942 seconds (189% compared to SortedSet)
Time to add (HashSet): .062 seconds (6.58% compared to UnsortedSet)
Time to add (TreeSet): .089 seconds (143% compared to HashSet)

Test #4 - Moby Dick

File Size: 1.22 MB
Number of words: 215135
Number of distinct words: 33779

Time to add (SortedSet): 2.789 seconds
Time to add (UnsortedSet): 6.559 seconds (235% compared to SortedSet)
Time to add (HashSet): .166 seconds (2.53% compared to UnsortedSet)
Time to add (TreeSet): .220 seconds (133% compared to HashSet)

Questions:
	1. SortedSet - O(M), UnsortedSet - O(M^2), HashSet and TreeSet - O(N)
	2. The big O of my add methods are both O(N). I think the big O of HashSet is O(1), and maybe O(log(N)) for TreeSet?
	3. TreeSet is close to being sorted, whereas HashSet is random.


CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:

Implementing all three of intersection, union, and differnce in the AbstractSet class
would have been a poor decision because each method relies on the other two to function
without an internal storage container. If all three methods were implemented, an infinite
loop would occur of each method calling the other.

 */


public class SetTester {

	public static void main(String[] args){

		ISet<Integer> s1 = new UnsortedSet<Integer>();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);
		s1.add(5);
		s1.add(1);

		// Test 1 - UnsortedSet - add and contains
		if (s1.contains(1))
			System.out.println("Passed test 1: add and contains methods UnsortedSet");
		else
			System.out.println("Failed test 1: add and contains methods UnsortedSet");

		// Test 2 - UnsortedSet - remove
		s1.remove(3);
		if (!s1.contains(3))
			System.out.println("Passed test 2: remove method UnsortedSet");
		else
			System.out.println("Failed test 2: remove method UnsortedSet");

		// Test 3 - UnsortedSet - size
		if (s1.size() == 4)
			System.out.println("Passed test 3: size method UnsortedSet");
		else
			System.out.println("Failed test 3: size method UnsortedSet");

		ISet<Integer> s2 = new UnsortedSet<Integer>();
		s2.add(6);
		s2.add(7);
		s2.add(8);

		// Test 4 - UnsortedSet - containsAll
		if (!s2.containsAll(s1))
			System.out.println("Passed test 4: containsAll method UnsortedSet");
		else
			System.out.println("Failed test 4: containsAll method UnsortedSet");

		// Test 5 - UnsortedSet - addAll
		s1.addAll(s2);
		ISet<Integer> expected = new UnsortedSet<Integer>();
		expected.add(1);
		expected.add(2);
		expected.add(4);
		expected.add(5);
		expected.add(6);
		expected.add(7);
		expected.add(8);
		if (s1.equals(expected))
			System.out.println("Passed test 5: addAll method UnsortedSet");
		else
			System.out.println("Failed test 5: addAll method UnsortedSet");

		// Test 6 - UnsortedSet - difference and equals methods
		s1 = new UnsortedSet<Integer>();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s2 = new UnsortedSet<Integer>();
		s2.add(3);
		s2.add(4);
		s2.add(6);
		ISet<Integer> s3 = s2.difference(s1);
		expected = new UnsortedSet<Integer>();
		expected.add(4);
		expected.add(6);
		if (s3.equals(expected))
			System.out.println("Passed test 6: difference and equals methods UnsortedSet");
		else
			System.out.println("Failed test 6: difference and equals methods UnsortedSet");

		// Test 7 - UnsortedSet - union and equals methods
		s3 = s2.union(s1);
		expected.add(1);
		expected.add(2);
		expected.add(3);
		if (s3.equals(expected))
			System.out.println("Passed test 7: union and equals methods UnsortedSet");
		else
			System.out.println("Failed test 7: union and equals methods UnsortedSet");

		// Test 8 - UnsortedSet - intersection, equals, and clear methods
		s3 = s2.intersection(s1);
		expected.clear();
		expected.add(3);
		if (s3.equals(expected))
			System.out.println("Passed test 8: intersection, equals, and clear methods UnsortedSet");
		else
			System.out.println("Failed test 8: intersection, equals, and clear methods UnsortedSet");
		
		// Test 9 - UnsortedSet - iterator method
		Iterator<Integer> iterator = s1.iterator();
		if (iterator.next().equals(1))
			System.out.println("Passed test 9: iterator method UnsortedSet");
		else
			System.out.println("Failed test 9: iterator method UnsortedSet");

		//sorted sets
		
		s1 = new SortedSet<Integer>();
		s1.add(1);
		s1.add(2);
		s1.add(3);
		s1.add(4);

		// Test 10 - SortedSet - add and contains methods
		if (s1.contains(4))
			System.out.println("Passed test 10: add and contains methods SortedSet");
		else
			System.out.println("Failed test 10: add and contains methods SortedSet");

		// Test 11 - SortedSet - remove and contains methods
		s1.remove(4);
		if (!s1.contains(4))
			System.out.println("Passed test 11: remove and contains methods SortedSet");
		else
			System.out.println("Failed test 11: remove and contains methods SortedSet");

		// Test 12 - SortedSet - size method
		if (s1.size() == 3)
			System.out.println("Passed test 12: size method SortedSet");
		else
			System.out.println("Failed test 12: size method SortedSet");

		s2 = new SortedSet<Integer>();
		s2.add(1);
		s2.add(2);

		// Test 13 - SortedSet - containsAll method
		if(s1.containsAll(s2))
			System.out.println("Passed test 13: containsAll method SortedSet");
		else
			System.out.println("Failed test 13: containsAll method SortedSet");

		// Test 14 - SortedSet - difference, equals, and clear methods
		ISet<String> s4 = new SortedSet<String>();
		s4.add("A");
		s4.add("B");
		ISet<String> s5 = new SortedSet<String>();
		s5.add("B");
		s5.add("C");
		ISet<String> expected2 = new SortedSet<String>();
		expected2.add("A");
		ISet<String> s6 = s4.difference(s5);
		if (s6.equals(expected2))
			System.out.println("Passed test 14: difference, equals, and clear methods SortedSet");
		else
			System.out.println("Failed test 14: difference, equals, and clear methods SortedSet");
		
		// Test 15 - SortedSet - union and equals methods
		s6 = s4.union(s5);
		expected2.add("B");
		expected2.add("C");
		if (s6.equals(expected2))
			System.out.println("Passed test 15: union and equals methods SortedSet");
		else
			System.out.println("Failed test 15: union and equals methods SortedSet");
		
		// Test 16 - SortedSet - intersection and equals methods
		s6 = s4.intersection(s5);
		expected2.clear();
		expected2.add("B");
		if (s6.equals(expected2))
			System.out.println("Passed test 16: intersection and equals methods SortedSet");
		else
			System.out.println("Failed test 16: intersection and equals methods SortedSet");

		// Test 17 - SortedSet - iterator method
		s1.clear();
		s1.add(1);
		s1.add(2);
		iterator = s1.iterator();
		if (iterator.next() == 1)
			System.out.println("Passed test 17: iterator method SortedSet");
		else
			System.out.println("Failed test 17: iterator method SortedSet");
		
		// Test 18 - SortedSet - addAll method
		s2.clear();
		s2.addAll(s1);
		expected.clear();
		expected.add(1);
		expected.add(2);
		if (s1.equals(expected))
			System.out.println("Passed test 18: addAll method SortedSet");
		else
			System.out.println("Failed test 18: addAll method SortedSet");
	}
}
