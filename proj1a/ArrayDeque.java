public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque(){
        size = 0;
        items = (T[]) new Object[3];
        nextFirst = 0;
        nextLast = 1;
    }

    //正确处理nextFirst的变换
    public void addFirst(T item) {
        if (isFull()) {
            resizing();
            size += 1;
            items[nextFirst] = item;
            nextFirst -= 1;
        } else {
            size += 1;
            items[nextFirst] = item;
            if (nextFirst - 1 < 0) {
                nextFirst = items.length - 1;
            } else {
                nextFirst -= 1;
            }
        }
    }

    public void addLast(T item) {
        if (isFull()){
            resizing();
            size += 1;
            items[nextLast] = item;
            nextLast += 1;
        } else {
            size += 1;
            items[nextLast] = item;
            if (nextLast + 1 > items.length - 1) {
                nextLast = 0;
            } else {
                nextLast += 1;
            }
        }
    }

    private boolean isFull() {
        if (size == items.length) {
            return true;
        } else {
            return false;
        }
    }
    // resizing 后应该调整nextFirst和nextLast
    private void resizing() {
        T[] a = (T[]) new Object[size * 2];
        for (int i = nextLast, j = 0; i < items.length; i++, j++) {
            a[j] = items[i];
        }
        for (int i = 0, j = items.length - nextLast; i < nextLast; i++, j++) {
            a[j] = items[i];
        }
        nextFirst = a.length - 1;
        nextLast = items.length;
        items = a;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (nextFirst == items.length - 1) {
            for (int index = 0; index < nextLast; index++) {
                System.out.print(items[index] + " ");
            }
        } else {
            for (int index = nextFirst + 1; index < items.length; index ++) {
                System.out.print(items[index] + " ");
            }
            for (int index = 0; index < nextLast; index++) {
                System.out.print(items[index] + " ");
            }
        }
    }

    public T removeFirst() {
        T temp;
        size -= 1;
        if (nextFirst + 1 > items.length) {
            nextFirst = 0;
        } else {
            nextFirst += 1;
        }
        temp = items[nextFirst];
        items[nextFirst] = null;
        return temp;
    }

    public T removeLast() {
        T temp;
        size -= 1;
        if (nextLast - 1 > 0){
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        temp = items[nextLast];
        items[nextLast] = null;
        return temp;
    }

    public T get(int index) {
        return items[index];
    }
}
