

/**
 * Created by Disa on 28.02.2016.
 */
public class ArrayQueueModule {
    // inv: size >= 0, for i = 1 ... size: elements[i] != null

    private static int sizeOfArray = 16, head = 0, tail = 0;
    private static Object[] elements = new Object[sizeOfArray];



    // post: res = array of queue
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static Object[] toArray() {
        Object[] newElements = new Object[size()];
        if (head <=  tail) {
            System.arraycopy(elements, head, newElements, 0, size());
        } else {
            System.arraycopy(elements, head, newElements, 0, sizeOfArray - head);
            System.arraycopy(elements, 0, newElements, sizeOfArray - head, tail);
        }
        return newElements;
    }


    // post: if elements number == sizeOfArray -> head = 0, tail = size - 1, sizeOfArray = sizeOfArray' * 2
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    private static void ensureCapacity() {
        if ((tail + 1) % sizeOfArray == head) {
            Object[] newElements = new Object[sizeOfArray * 2];
            for (int tail = 0; tail < sizeOfArray; tail++) {
                newElements[tail] = elements[(head + tail) % sizeOfArray];
            }
            tail = sizeOfArray - 1;
            head = 0;
            sizeOfArray *= 2;
            elements = newElements;
        }
    }


    // pre:  element != null
    // post: size = size' + 1
    // for i = 1 ... size': elements[i] = elements[i]'
    // elements[size] = element
    public static void enqueue(Object element) {
        assert (element != null);
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % sizeOfArray;
    }


    // pre:  size > 0
    // post: res = elements[1]
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static Object element() {
        assert (!isEmpty());
        return elements[head];
    }


    // pre:  size > 0
    // post: res = elements[1]
    // size = size' - 1
    // for i = 1 ... size: elements[i] = elements[i + 1]'
    public static Object dequeue() {
        assert (!isEmpty());
        Object res = elements[head];
        head = (head + 1) % sizeOfArray;
        return res;
    }


    // post: res = size
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static int size() {
        return (head <= tail) ? tail - head : sizeOfArray - head + tail;
    }



    // post: res = (size == 0)
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static boolean isEmpty() {
        return head == tail;
    }



    // post: size = 0;
    // empty queue
    public static void clear() {
        head = 0;
        tail = 0;
    }
}
