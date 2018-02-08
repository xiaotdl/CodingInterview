1. Classic Comparator example.

Comparator<Developer> byName = new Comparator<Developer>() {
	@Override
	public int compare(Developer o1, Developer o2) {
		return o1.getName().compareTo(o2.getName());
	}
};

2. Lambda expression equivalent.

Comparator<Developer> byName =
	(Developer o1, Developer o2)->o1.getName().compareTo(o2.getName());


== e.g. ==
        PriorityQueue<Element> minHeap = new PriorityQueue<>(k,
			new Comparator<Element>(){
				@Override
				public int compare(Element e1, Element e2) {
					return e1.val - e2.val;
				}
			}
        );
