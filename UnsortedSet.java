import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently. 
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

	private ArrayList<E> container;

	public UnsortedSet() {
		container = new ArrayList<E>();
	}

	@Override
	// O(N)
	public boolean add(E item) {
		if (item == null)
			throw new IllegalArgumentException("The given item is null.");
		if (this.contains(item))
			return false;
		container.add(item);
		return true;
	}

	@Override
	// O(N^2)
	public ISet<E> difference(ISet<E> otherSet) {
		ISet<E> result = new UnsortedSet<E>();
		for (E obj: this) {
			if (!otherSet.contains(obj))
				result.add(obj);
		}
		return result;
	}

	@Override
	// O(N^2)
	public ISet<E> intersection(ISet<E> otherSet) {
		ISet<E> result = new UnsortedSet<E>();
		for (E obj: this) {
			if (otherSet.contains(obj))
				result.add(obj);
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
	// O(N^2)
	public ISet<E> union(ISet<E> otherSet) {
		ISet<E> result = new UnsortedSet<E>();
		result.addAll(this);
		result.addAll(otherSet);
		return result;
	}
}
