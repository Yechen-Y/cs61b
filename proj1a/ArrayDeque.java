public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }


    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    //正确处理nextFirst的变换
    public void addFirst(T item) {
        if (isFull()) {
            grow();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (isFull()) {
            grow();
        }
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    private boolean isFull() {
        return size == items.length;
    }
    // resizing 后应该调整nextFirst和nextLast
    private void grow() {
        T[] newArray = (T[]) new Object[items.length * 2];
        resize(newArray);
    }

    private void shrink() {
        T[] newArray = (T[]) new Object[items.length / 2];
        resize(newArray);
    }

    private void resize(T[] newArray) {
        int index = 0;
        int ptrFirst = plusOne(nextFirst);
        int ptrLast = minusOne(nextLast);

        while (index != size) {
            newArray[index] = items[ptrFirst];
            ptrFirst = plusOne(ptrFirst);
            index++;
        }
        nextLast = index;
        nextFirst = newArray.length - 1;
        items = newArray;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int startIndex;
        for (int i = 0; i < size; i++) {
            startIndex = plusOne(nextFirst);
            System.out.print(items[startIndex] + " ");
        }

    }

    public T removeFirst() {
        T temp;
        if (size == 0) {
            return null;
        }
        if (items.length > 16 && items.length / size > 4) {
            shrink();
        }
        nextFirst = plusOne(nextFirst);
        temp = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return temp;
    }

    public T removeLast() {
        T temp;
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && items.length / size > 4) {
            shrink();
        }
        nextLast = minusOne(nextLast);
        temp = items[nextLast];
        items[nextLast] = null;
        size--;
        return temp;
    }


    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            int startIndex = plusOne(nextFirst);
            for (int i = 0; i < index; i++) {
                startIndex = plusOne(startIndex);
            }
            return items[startIndex];
        }
    }
}
