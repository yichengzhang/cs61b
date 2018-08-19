public class ArrayDeque<Item> implements Deque<Item> {
    private Item[] items;
    private int nextFirst, nextLast, size;
    private static final int INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        items = (Item[])new Object[INITIAL_CAPACITY];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    public boolean isFull() {
        return size() == items.length;
    }

    public boolean isSparse() {
        return size() < items.length/4 && items.length >= 16;
    }


    @Override
    public void addFirst(Item item) {
        if (isFull()) extend();

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    @Override
    public void addLast(Item item) {
        if (isFull()) extend();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public void extend() {
        resize(2 * size());
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return items.length;
    }

    @Override
    public Item removeFirst() {
        if (isSparse()) shrink();
        nextFirst = plusOne(nextFirst);
        Item toRemove = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return toRemove;
    }

    @Override
    public Item removeLast() {
        if (isSparse()) shrink();
        nextLast = minusOne(nextLast);
        Item toRemove = items[nextLast];
        items[nextLast] = null;
        size--;
        return toRemove;
    }

    public void shrink() {
        resize(items.length/2);
    }



    private void resize(int target) {
        System.out.printf("Resizing from %5d to %5d\n", items.length, target);
        Item[] oldItems = items;
        int oldFirst = plusOne(nextFirst);
        int oldLast = minusOne(nextLast);

        items = (Item[]) new Object[target];
        nextLast = 0;
        nextFirst = 1;
        for (int i = oldFirst; i != oldLast; i = plusOne(i, oldItems.length)) {
            items[nextLast] = oldItems[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = oldItems[oldLast];
        nextLast = plusOne(nextLast);
    }

    @Override
    public Item get(int index) {
        return items[plusIndex(nextFirst + 1, index)];
    }

    @Override
    public boolean isEmpty() {
        return minusOne(nextLast) == nextFirst;
    }

    public int minusOne(int index) {
        return Math.floorMod(index-1, items.length);
    }

    public int plusOne(int index) {
        return Math.floorMod(index+1, items.length);
    }

    public int plusOne(int index, int size) {
        return Math.floorMod(index + 1, size);
    }

    public int plusIndex (int index, int offset) {
        return Math.floorMod(index+offset, items.length);
    }

    @Override
    public void printDeque() {
        for (int i = plusOne(nextFirst); i != nextLast; i = plusOne(i)) {
            System.out.print(items[i] + " ");
        }
    }
}
