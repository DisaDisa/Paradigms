/**
 * Created by Disa on 28.02.2016.
 */
public class ArrayQueueADT {
    // inv: size >= 0, for i = 1 ... size: elements[i] != null

    private int sizeOfArray = 16, head = 0, tail = 0;
    private Object[] elements = new Object[sizeOfArray];

    // pre: queue != null
    // post: res = array of queue
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static Object[] toArray(ArrayQueueADT queue) {
        assert (queue != null);
        Object[] newElements = new Object[size(queue)];
        if (queue.head <= queue.tail) {
            System.arraycopy(queue.elements, queue.head, newElements, 0, size(queue));
        } else {
            System.arraycopy(queue.elements, queue.head, newElements, 0, queue.sizeOfArray - queue.head);
            System.arraycopy(queue.elements, 0, newElements, queue.sizeOfArray - queue.head, queue.tail);
        }
        return newElements;
    }

    // pre: queue != null
    // post: if elements number == sizeOfArray -> head = 0, tail = size - 1, sizeOfArray = sizeOfArray' * 2
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    private static void ensureCapacity(ArrayQueueADT queue) {
        assert (queue != null);
        if ((queue.tail + 1) % queue.sizeOfArray == queue.head) {
            Object[] newElements = new Object[queue.sizeOfArray * 2];
            for (int tail = 0; tail < queue.sizeOfArray; tail++) {
                newElements[tail] = queue.elements[(queue.head + tail) % queue.sizeOfArray];
            }
            queue.tail = queue.sizeOfArray - 1;
            queue.head = 0;
            queue.sizeOfArray *= 2;
            queue.elements = newElements;
        }
    }


    // pre:  element != null, queue != null
    // post: size = size' + 1
    // for i = 1 ... size': elements[i] = elements[i]'
    // elements[size] = element
    public static void enqueue(ArrayQueueADT queue, Object element) {
        assert queue != null;
        assert element != null;
        ensureCapacity(queue);
        queue.elements[queue.tail] = element;
        queue.tail = (queue.tail + 1) % queue.sizeOfArray;
    }


    // pre:  size > 0, queue != null
    // post: res = elements[1]
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static Object element(ArrayQueueADT queue) {
        assert (queue != null);
        assert !isEmpty(queue);
        return queue.elements[queue.head];
    }


    // pre:  size > 0, queue != null
    // post: res = elements[1]
    // size = size' - 1
    // for i = 1 ... size: elements[i] = elements[i + 1]'
    public static Object dequeue(ArrayQueueADT queue) {
        assert queue != null;
        assert !isEmpty(queue);
        Object res = queue.elements[queue.head];
        queue.head = (queue.head + 1) % queue.sizeOfArray;
        return res;
    }


    // pre: queue != null
    // post: res = size
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static int size(ArrayQueueADT queue) {
        return (queue.head <= queue.tail) ? queue.tail - queue.head : queue.sizeOfArray - queue.head + queue.tail;
    }


    // pre: queue != null
    // post: res = (size == 0)
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    public static boolean isEmpty(ArrayQueueADT queue) {
        assert (queue != null);
        return queue.head == queue.tail;
    }


    // pre: queue != null
    // post: size = 0;
    // empty queue
    public static void clear(ArrayQueueADT queue) {
        assert (queue != null);
        queue.head = 0;
        queue.tail = 0;
    }

}
