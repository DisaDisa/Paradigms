/**
 * Created by Disa on 28.02.2016.
 */
public class ArrayQueue extends AbstractQueue implements Queue {

    private int sizeOfArray = 16, head = 0, tail = 0;
    private Object[] elements = new Object[sizeOfArray];


    private void ensureCapacity() {
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


    public void doEnqueue(Object element) {
        ensureCapacity();
        elements[tail] = element;
        tail = (tail + 1) % sizeOfArray;
    }



    public Object doElement() {
        return elements[head];
    }



    public Object doDequeue() {
        Object res = elements[head];
        head = (head + 1) % sizeOfArray;
        return res;
    }


}
