/**
 * Created by Disa on 13.03.2016.
 */
public interface Queue {
    // inv: size >= 0, all elements in queue != null

    // pre: q != null
    // post: size = size' + 1
    // for i = 1 ... size': elements[i] = elements[i]'
    // elements[size] = q
    void enqueue(Object q);

    //pre: size > 0
    //post: res = elements[1]
    //size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    Object element();

    //pre: size > 0
    //post: res = elements[1]
    // size = size' - 1
    // for i = 1 ... size: elements[i] = elements[i + 1]'
    Object dequeue();

    //post: res = size
    // size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    int size();

    //post: res = (size == 0)
    //size = size'
    // for i = 1 ... size: elements[i] = elements[i]'
    boolean isEmpty();

    //post: size = 0;
    // empty queue
    void clear();

    //post: for i = 1 ... size: elements[i] = elements[i]'
    // size = size'
    // res = ans (for i = 1 ... size: ans[i] = elements[i])
    Object[] toArray();
}
