class TreeSet<E>
    boolean add(E e)
    // O(logn) Adds the specified element to this set if it is not already present.
    remove(Object o)
    // O(logn) Removes the specified element from this set if it is present.

    E pollFirst()
    // O(logn) Retrieves and removes the first (lowest) element, or returns null if this set is empty.
    E pollLast()
    // O(logn) Retrieves and removes the last (highest) element, or returns null if this set is empty.

    boolean contains(E e)
    // O(logn) Returns true if this set contains the specified element.

    E lower(E e)
    // O(logn) Returns the greatest element in this set strictly less than the given element, or null if there is no such element.
    E higher(E e)
    // O(logn) Returns the least element in this set strictly greater than the given element, or null if there is no such element.

    E floor(E e)
    // O(logn) Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
    E ceiling(E e)
    // O(logn) Returns the least element in this set greater than or equal to the given element, or null if there is no such element.

    E first()
    // O(1) Returns the first (lowest) element currently in this set.
    E last()
    // O(1) Returns the last (highest) element currently in this set.

