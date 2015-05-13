import java.util.Iterator;

/**
 * Implement as many methods
 * as possible using the Iterator from the iterator 
 * method and the other methods. 
 *
 */

public abstract class AbstractSet<E> implements ISet<E> {

	// MUST implement add, difference, intersection, and iterator in both the unsorted
	// and sorted lists. Check the other methods if they can be done faster in either class.

	// Uses the add method to add each element individually.
	// Cannot be implemented faster for the unsorted set.
	// CAN be implemented faster for the sorted set.
	public boolean addAll(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null.");
		boolean result = false;
		for (E obj: otherSet) {
			if (!this.contains(obj)) {
				this.add(obj);
				result = true;
			}
		}
		return result;
	}

	// Uses an iterator to clear out the set
	// Cannot be implemented faster for the unsorted set
	// Cannot be implemented faster for the sorted set
	public void clear() {
		Iterator<E> iterator = this.iterator();
		while (iterator.hasNext()) {
			iterator.next();
			iterator.remove();
		}
	}

	// Uses the built in iterator for the enhanced for loop to check the entire set.
	// Cannot be implemented faster for the unsorted set.
	// CAN be implemented faster for the sorted set.
	public boolean contains(E obj){
		if (obj == null)
			throw new IllegalArgumentException("The given item is null.");
		for(E val : this) {
			if(val.equals(obj))
				return true;
		}
		return false;
	}

	// Uses the built in iterator for the enhanced for loop to check both sets.
	// Cannot be implemented faster for the unsorted set.
	// CAN be implemented faster for the sorted set.
	public boolean containsAll(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set it null.");
		for (E obj: otherSet) {
			if (!this.contains(obj))
				return false;
		}
		return true;
	}

	// Compares each element of the calling set and parameter to see if the two sets are equal.
	// Cannot be implemented faster for the unsorted set.
	// CAN be implemented faster for the sorted set.
	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		if (!(other instanceof ISet<?>))
			return false;
		if (((ISet<?>) other).size() != this.size())
			return false;
		boolean check;
		try {
			check = ((ISet<E>) other).containsAll(this) && this.containsAll((ISet<E>) other);
		}
		catch (Exception e) {
			return false;
		}
		return check;
	}

	// Uses an iterator to go through an remove the given item.
	// Cannot be implemented faster for the unsorted set.
	// Cannot be implemented faster for the sorted set.
	public boolean remove(E item) {
		if (item == null)
			throw new IllegalArgumentException("The given item is null.");
		Iterator<E> iterator = this.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(item)) {
				iterator.remove();
				return true;
			}
			else
				iterator.next();
		}
		return false;
	}

	// Increments a counter as the iterator from the enhanced for loop goes through the
	// entire set.
	// CAN be implemented faster for the unsorted set.
	// CAN be implemented faster for the sorted set.
	public int size() {
		int result = 0;
		for (E val: this)
			result++;
		return result;
	}

	// Uses difference and intersection to create a union of the two sets.
	// CAN be implemented faster in unsorted set.
	// CAN be implemented faster in sorted set.
	public ISet<E> union(ISet<E> otherSet) {
		if (otherSet == null)
			throw new IllegalArgumentException("The given set is null.");
		ISet<E> result = this.difference(otherSet);
		result.addAll(this.intersection(otherSet));
		result.addAll(otherSet.difference(this));
		return result;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		String seperator = ", ";
		result.append("(");
		Iterator<E> it = this.iterator();
		while (it.hasNext()) {
			result.append(it.next());
			result.append(seperator);
		}
		// get rid of extra separator
		if (this.size() > 0)
			result.setLength(result.length() - seperator.length());
		result.append(")");
		return result.toString();
	}
}
