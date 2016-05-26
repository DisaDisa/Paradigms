/**
 * Created by Disa on 13.03.2016.
 */
public abstract class AbstractQueue implements Queue {
    protected int size = 0;


    public void enqueue(Object q) {
        assert (q != null);
        size++;
        doEnqueue(q);
    }

    public Object element() {
        assert (size != 0);
        return doElement();
    }


    public Object dequeue() {
        assert (size != 0);
        size--;
        return doDequeue();
    }


    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        while (size > 0)
            dequeue();
    }


    public Object[] toArray() {
        Object ans[] = new Object[size];
        for (int i = 0; i < size; i++) {
            Object now = dequeue();
            ans[i] = now;
            enqueue(now);
        }
        return ans;
    }

    protected abstract void doEnqueue(Object q);

    protected abstract Object doDequeue();

    protected abstract Object doElement();
}
