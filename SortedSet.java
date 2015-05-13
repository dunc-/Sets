import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	private ArrayList<E> container;

	/**
	 * create an empty SortedSet
	 */
	public SortedSet() {
		container = new ArrayList<E>();
	}

	/**
	 * create a SortedSet out of an unsorted set. <br>
	 * @param other != null
	 */
	// O(Nlog(N))
	public SortedSet(ISet<E> other) {
		if (other == null)
			throw new IllegalArgumentException("The given set is null.");
		container = new ArrayList<E>();
		for (E obj: other)
			container.add(obj);
		container = mergeSort(container);
	}

	/**
	 * Return the smallest element in this SortedSet.
	 * <br> pre: size() != 0
	 * @return the smallest element in this SortedSet.
	 */
	// O(1)
	public E min() {
		if (container.size() == 0)
			throw new IllegalStateException("The set has no elements.");
		return container.get(0);
	}

	/**
	 * Return the largest element in this SortedSet.
	 * <br> pre: size() != 0
	 * @return the largest element in this SortedSet.
	 */
	// O(1)
	public E max() {
		if (container.size() == 0)
			throw new IllegalStateException("The set has no elements.");
		return container.get(this.size()-1);
	}

	@Override
	// O(N)
	public boolean add(E item) {
		if (item == null)
			throw new IllegalArgumentException("The given item is null.");
		if (this.contains(item))
			return false;
		if (container.size() == 0) {
			container.add(item);
			return true;
		}
		for (int i=0; i<container.size(); i++) {
			E temp = container.get(i);
			if (item.compareTo(temp) < 0) {
				container.add(i, item);
				return true;
			}
		}
		container.add(item);
		return true;
	}

	@Override
	// O(N)
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null.");
		int check = this.size();
		SortedSet<E> newSortedSet = getNewSet(otherSet);
		int pos;
		for (E obj: newSortedSet) {
			pos = 0;
			boolean checked = false;
			while (pos < this.size()) {
				if (container.get(pos).compareTo(obj) == 0) {
					checked = true;
					pos++;
				}
				else if (container.get(pos).compareTo(obj) > 0 && !checked) {
					container.set(pos, obj);
					pos++;
				}
				else
					pos++;
			}
			while (pos < newSortedSet.size() && !checked) {
				checked = true;
				container.add(obj);
				pos++;
			}
		}
		return container.size() > check;
	}

	@Override
	// Implements binary search like that on the class slides
	// O(log(N))
	public boolean contains(E item) {
		if (item == null)
			throw new IllegalArgumentException("The given item is null.");
		int low = 0;
		int high = container.size()-1;
		int middle;
		while (low <= high) {
			middle = low + ((high-low)/2);
			if (container.get(middle).compareTo(item) == 0)
				return true;
			if (container.get(middle).compareTo(item) < 0)
				low = middle + 1;
			else
				high = middle - 1;
		}
		return false;
	}

	@Override
	// O(N)
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null.");
		if (this.size() == 0 && otherSet.size() != 0)
			return false;
		if (otherSet.size() > this.size())
			return false;
		SortedSet<E> newSortedSet = getNewSet(otherSet);
		int pos = 0;
		for (E obj: newSortedSet) {
			boolean check = false;
			while (!check) {
				if (container.get(pos).compareTo(obj) == 0)
					check = true;
				else if (container.get(pos).compareTo(obj) < 0)
					pos++;
				else
					return false;
			}
		}
		return true;
	}

	@Override
	// O(N)
	public ISet<E> difference(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null.");
		SortedSet<E> newSortedSet = getNewSet(otherSet);
		int pos = 0;
		SortedSet<E> result = new SortedSet<E>();
		for (E obj: newSortedSet) {
			boolean contains = false;
			while (pos < this.size()) {
				if (container.get(pos).compareTo(obj) == 0) {
					contains = true;
					pos++;
				}
				else if (container.get(pos).compareTo(obj) < 0 && !contains) {
					result.add(container.get(pos));
					pos++;
				}
				else
					pos++;
			}
			if (pos < container.size()) {
				result.add(container.get(pos));
				pos++;
			}
		}
		return result;
	}

	@Override
	// O(N)
	public boolean equals(Object other) {
		if (!(other instanceof ISet<?>))
			return false;
		ISet<?> otherSet = (ISet<?>) other;
		if (this.size() != otherSet.size())
			return false;
		if (otherSet instanceof UnsortedSet)
			return super.equals(otherSet);
		Iterator<E> iterator1 = this.iterator();
		Iterator<?> iterator2 = otherSet.iterator();
		while (iterator1.hasNext()) {
			if (!iterator1.next().equals(iterator2.next()))
				return false;
		}
		return true;
	}

	@Override
	// O(N)
	public ISet<E> intersection(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null.");
		SortedSet<E> newSortedSet = getNewSet(otherSet);
		SortedSet <E> result = new SortedSet<E>();
		int pos = 0;
		for (E obj: newSortedSet) {
			boolean check = false;
			while (pos < container.size() && !check) {
				if (container.get(pos).compareTo(obj) == 0) {
					check = true;
					result.add(container.get(pos));
				}
				else
					pos++;
			}
		}
		return result;
	}

	@Override
	// O(1)
	public Iterator<E> iterator() {
		return container.iterator();
	}

	@Override
	// O(N)
	public boolean remove(E item) {
		if (item == null)
			throw new IllegalArgumentException("The given item is null.");
		if (!this.contains(item))
			return false;
		container.remove(item);
		return true;
	}

	@Override
	// O(1)
	public int size() {
		return container.size();
	}

	@Override
	// O(N)
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null");
		SortedSet<E> newSortedSet = getNewSet(otherSet);
		SortedSet <E> result = new SortedSet<E>();
		int pos = 0;
		for (E obj: newSortedSet) {
			boolean check1 = false;
			while (pos < container.size()) {
				if (container.get(pos).compareTo(obj) > 0 && !check1) {
					check1 = true;
					result.add(obj);
				} else {
					result.add(container.get(pos));
					pos++;
				}
			}
			if (pos == container.size())
				result.add(obj);
			while (pos < container.size()) {
				boolean check2 = false;
				if (container.get(pos).compareTo(obj) < 0 || check2) {
					result.add(container.get(pos));
					pos++;
				} else if (container.get(pos).compareTo(obj) > 0) {
					check2 = true;
					result.add(obj);
				} else {
					check2 = true;
					result.add(container.get(pos));
					pos++;
				}
			}
		}
		return result;
	}

	// Code for merge sort, which is on average O(nlog(n)).
	// Slightly modified code from the class slides.
	public ArrayList<E> mergeSort(ArrayList<E> a)
	{
		ArrayList<E> left = new ArrayList<E>();
		ArrayList<E> right = new ArrayList<E>();
		int center;
		if (a.size()==1)    
			return a;
		else {
			center = a.size()/2;
			for (int i=0; i<center; i++) {
				left.add(a.get(i));
			}
			for (int i=center; i<a.size(); i++) {
				right.add(a.get(i));
			}
			left  = mergeSort(left);
			right = mergeSort(right);
			merge(left,right,a);
		}
		return a;
	}

	// Helper method for merge sort
	private void merge(ArrayList<E> left, ArrayList<E> right, ArrayList<E> a) {
		int leftIndex = 0;
		int rightIndex = 0;
		int aIndex = 0;
		while (leftIndex < left.size() && rightIndex < right.size()) {
			if ((left.get(leftIndex).compareTo(right.get(rightIndex))) < 0) {
				a.set(aIndex, left.get(leftIndex));
				leftIndex++;
			}
			else {
				a.set(aIndex, right.get(rightIndex));
				rightIndex++;
			}
			aIndex++;
		}
		ArrayList<E> rest;
		int restIndex;
		if (leftIndex >= left.size()) {
			rest = right;
			restIndex = rightIndex;
		}
		else {
			rest = left;
			restIndex = leftIndex;
		}
		for (int i=restIndex; i<rest.size(); i++) {
			a.set(aIndex, rest.get(i));
			aIndex++;
		}
	}

	// Helper method to get a new sorted set out of the given set
	private SortedSet<E> getNewSet(ISet<E> set) {
		SortedSet<E> result = new SortedSet<E>();
		if (!(set instanceof SortedSet<?>))
			result = new SortedSet<E>(set);
		else
			result = (SortedSet<E>) set;
		return result;
	}
}
