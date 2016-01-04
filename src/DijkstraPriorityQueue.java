import java.util.ArrayList;
import java.util.Comparator;

public class DijkstraPriorityQueue<P> {

    private int size;
    private ArrayList<Entry> array;
    private Comparator<P> comparePriority;
    
    private class Entry {
        private int key;
        private P priority;
        public Entry(int key, P priority){
            this.key = key;
            this.priority = priority;
        }
        public int getKey() {
            return key;
        }
        public P getPriority() {
            return priority;
        }
    }

    public DijkstraPriorityQueue(Comparator<P> priorityComparator,
           int numVerts) {
		array = new ArrayList<Entry>(size + 1);
		comparePriority = priorityComparator;
    }

    public boolean isEmpty() {
    	return size == 0;
    }

    public boolean contains(int v) {
    	for (Entry e: array) {
    		if (v == e.getKey()) return true;
    	}
    	return false;
    }

    public void insert(int v, P priority) {
    	array.add(new Entry(v, priority)); 
    	size++;
    }

    public int deleteMin() {
    	Entry lowest = array.get(0);
    	for (int i = 0; i < array.size(); i++) {
    		Entry temp = array.get(i);
    		if (comparePriority.compare(temp.getPriority(), lowest.getPriority()) < 0) {
    			lowest = temp;
    		}
    	}
    	int v = lowest.getKey();
    	size--;
    	array.remove(lowest);
    	return v;
    }

    public void decreaseKey(int v, P priority) {
    	Entry e = new Entry(v, priority);
    	for (int i = 0; i < array.size(); i++) {
    		Entry temp = array.get(i);
    		if (temp.getKey() == v) {
    			array.remove(temp);
    			break;
    		}
    	}
    	array.add(e);
    }
    
    public String toString() {
    	String string = "{";
    	for (Entry e: array) {
    		string = string.concat("[" + e.getPriority() + ", " + e.getKey() + "], ");
    	}
    	string = string.concat("}");
    	return string;
    }

    /*
     * Unit test.
     */
    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    public static void main(String[] args) {
        final int n = 20;
        DijkstraPriorityQueue<Integer> Q = 
            new DijkstraPriorityQueue<Integer>(new IntComparator(), n);
        for (int i = 0; i < 20; i++) {
            Q.insert(i, (i-10)*(i-10) + i);
        }
        System.out.println(Q.toString());
        System.out.println();
        for (int i = 10; i < 20; i++) {
            Q.decreaseKey(i,(i-5)*(i-5));
            System.out.println("i: " + i + " to: " + (i-5)*(i-5));
            System.out.println(Q.toString());
        }
        //System.out.println(Q.toString());
        System.out.println();
        while (!Q.isEmpty()) {
            int v = Q.deleteMin();
        }
        System.out.println(Q.toString());
    }
}