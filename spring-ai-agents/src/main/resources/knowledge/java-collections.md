# Java Collections

## List

List represents an ordered collection that can contain duplicate elements.

ArrayList is backed by a dynamically resizable array. It provides fast random access by index and is commonly used when reads are frequent.

LinkedList is implemented as a doubly linked list. It can be useful for certain insertion and removal patterns, but ArrayList is usually preferred for general-purpose list usage.

## Set

Set represents a collection that does not allow duplicate elements.

HashSet provides efficient average-case lookup and insertion using hashing.

LinkedHashSet maintains insertion order.

TreeSet stores elements in sorted order and is generally based on a balanced tree structure.

## Map

Map stores key-value pairs.

HashMap provides average constant-time lookup for many common operations.

LinkedHashMap maintains insertion order.

TreeMap stores keys in sorted order.

ConcurrentHashMap is designed for concurrent access from multiple threads.

## Queue

Queue represents elements waiting to be processed.

PriorityQueue orders elements according to their priority rather than insertion order.

## Choosing a Collection

Use ArrayList when you mainly need an ordered collection and frequent index-based access.

Use HashSet when uniqueness is important and ordering is not required.

Use LinkedHashSet when uniqueness and insertion order are both important.

Use TreeSet when sorted order is required.

Use HashMap for general key-value storage.

Use LinkedHashMap when predictable insertion order is useful.

Use TreeMap when sorted keys are required.

Use ConcurrentHashMap when a map is accessed concurrently by multiple threads.