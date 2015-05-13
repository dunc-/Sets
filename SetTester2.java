import java.util.Iterator;

public class SetTester2 {

    public static void main(String[] args){
    	
    	//Unsorted tests
    	
    	//test 1
    	UnsortedSet<String> s1 = new UnsortedSet<String>();
    	s1.add("b");
    	s1.add("z");
    	s1.add("p");
    	String expected = "(b, z, p)";
    	if(s1.toString().equals(expected)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 1: Unsorted add.");
    	
    	//test 2
    	UnsortedSet<String> s2 = new UnsortedSet<String>();
    	s2.addAll(s1);
    	if(s2.containsAll(s1)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 2: Unsorted addAll and ContainsAll.");
    	
    	//test 3
    	s2.clear();
    	if(s2.size() == 0){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 3: Unsorted clear and size.");
    	
    	//test 4
    	s1.clear();
    	if(s1.equals(s2)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 4: Unsorted clear and equals.");
    	
    	//test 5
    	s1.add("d");
    	if(s1.contains("d")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 5: Unsorted add and contains");
    	
    	//test 6
    	s2.add("d");
    	s2.add("f");
    	s2.add("g");
    	ISet<String> s3 = s1.difference(s2);
    	if(s3.toString().equals("()")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 6: Unsorted difference");
    	
    	//test 7
    	Iterator<String> s2It = s2.iterator();
    	StringBuilder s2ItString = new StringBuilder();
    	while(s2It.hasNext()){
    		s2ItString.append(s2It.next());
    	}
    	if(s2ItString.toString().equals("dfg")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 7: Unsorted iterator");
    	
    	//test 8
    	s3 = s1.intersection(s2);
    	if(s3.toString().equals("(d)")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 8: Unsorted intersection");
    	
    	//test 9
    	s3 = s1.union(s2);
    	if(s3.toString().equals("(d, f, g)")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 9: Unsorted union");
    	
    	//Sorted Set tests
    	
    	//test 10
    	SortedSet<String> s4 = new SortedSet<String>(s2);
    	if(s4.toString().equals("(d, f, g)")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 10: Sorted constructor with ISet<E> variable");
    	
    	//test 11
    	if(s4.min().equals("d")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 11: Sorted min");
    	
    	//test 12
    	if(s4.max().equals("g")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 12: Sorted max");
    	
    	//test 13
    	Iterator<String> s4It = s4.iterator();
    	StringBuilder s4ItString = new StringBuilder();
    	while(s4It.hasNext()){
    		s4ItString.append(s4It.next());
    	}
    	if(s4ItString.toString().equals("dfg")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 13: Sorted iterator");
    	
    	//test 14
    	s4.add("a");
    	if(s4.contains("a")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 14: Sorted add and contains");
    	
    	//test 15
    	s4.remove("a");
    	if(!s4.contains("a")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 15: Sorted remove and contains");
    	
    	//test 16
    	SortedSet<String> s5 = new SortedSet<String>();
    	s5.add("l");
    	s5.add("v");
    	s5.add("g");
    	s5.add("f");
    	s5.add("d");
    	s4.addAll(s5);
    	if(s4.toString().equals("(d, f, g, l, v)")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 16: Sorted addAll");
    	
    	//test 17
    	if(s4.containsAll(s5)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 17: Sorted containsAll");
	
    	//test 18
    	s4.clear();
    	if(!s4.containsAll(s5)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 18: Sorted clear and containsAll");
    	
    	//test 19
    	ISet<String> s6 = s4.difference(s5);
    	if(s6.size() == 0){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 19: Sorted difference and size");
    	
    	//test 20 
    	s6 = s5.intersection(s4);
    	if(s6.size() == 0){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 20: Sorted intersection and size");
    	
    	//test 21
    	s6 = s4.union(s5);
    	if(s6.containsAll(s5)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 21: Sorted union and containsAll");
    	
    	//test 22
    	if(s6.equals(s5)){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 22: Sorted equals");
    	
    	//test 23
    	s1.clear();
    	s1.add("a");
    	s1.remove("a");
    	if(!s1.contains("a")){
    		System.out.print("Passed ");
    	}else{
    		System.out.print("FAILED ");
    	}
    	System.out.println("Test 23: Unsorted remove");
    }
}
