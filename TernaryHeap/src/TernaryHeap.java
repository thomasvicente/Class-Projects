class TernaryHeap {
    private int[] storage;
    private int size;

    public TernaryHeap() {
        storage = new int[5];
        size = 0;
    }

    public void output() {
        for (int i = 0; i < size; i++) {
            System.out.print(storage[i] + " ");
        }
        System.out.println();
    }

    /* ------------ Modify/add methods below this line ------------ */
    private boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == storage.length;
    }

    public void add(int k) {
        // reverts value of 5th add to 0 instead of given number
        if (isFull()) {
            makeSpace();
        }
        storage[size++] = k;
        heapifyUp(size - 1);
    }

    private void makeSpace() {
        int[] newStorage = new int[3 * storage.length];
        for (int i = 0; i < storage.length; i++) {
            newStorage[i] = storage[i];
        }
        storage = newStorage;
    }

    private int parent(int i) {
        return (i - 1) / 3;
    }

    private static int child(int i, int k) {
        return 3 * i + k;
    }

    public void promote(int idx) {
        int temp = storage[idx];
        storage[idx] = storage[parent(idx)];
        storage[parent(idx)] = temp;
    }

    private void heapifyUp(int idx) {
        //when adding, the 6th item added has its value turned to 0
        int temp = storage[parent(idx)];
        while (storage[idx] > temp) {
            storage[parent(idx)] = storage[idx];
            storage[idx] = temp;
            idx = parent(idx);
            temp = storage[(parent(idx))];
        }
    }

    public int peekMax() {
        if (!isEmpty()) {
            return storage[0];
        }
        return -1;
    }

    private void heapifyDown(int i) {
        int n = size;
        int swapper = i;
        int l = child(i, 1);
        int mid = child(i, 2);
        int r = child(i, 3);
        if (l < n && storage[l] > storage[swapper]) {
            swapper = l;
        }
        if (mid < n && storage[mid] > storage[swapper]) {
            swapper = mid;
        }
        if (r < n && storage[r] > storage[swapper]) {
            swapper = r;
        }
        if (swapper != i) {
            promote(swapper);
            heapifyDown(swapper);
        }

    }

    public int popMax() {
        int ret = storage[0];
        storage[0] = storage[size - 1];
        size--;
        heapifyDown(0);
        return ret;
    }

    public void buildHeap(int[] Y) {
        //when origionally using startIDX as ((n-1)/3) - 1 it never swapped the final index, what gives professor levear?
        this.storage = Y;
        int n = Y.length;
        this.size = n;

        int startIDX = (n - 1 / 3) - 1;

        for (int i = startIDX; i >= 0; i--) {
            staticDown(storage, n, i);
        }
    }

    public int nodesInLevel(int h) {
        //proud of this one!!! my pride and joy of this assignment
        //used structural induction to find that the size of a complete ternary heap is always sigma(3,height);
        //used this to set the upper and lower bound of each level
        if (h == 0 && !isEmpty()) {
            return 1;
        } else {
            int count = 0;
            for (int i = sigma(3, h - 1) - 1; i < sigma(3, h) - 1 && i < size - 1; i++) {
                count++;
            }
            return count;
        }
    }

    public void staticDown(int[] Y, int n, int i) {
        int swapper = i;
        int l = 3 * i + 1;
        int mid = 3 * i + 2;
        int r = 3 * i + 3;
        if (l < n && Y[l] > Y[swapper]) {
            swapper = l;
        }
        if (mid < n && Y[mid] > Y[swapper]) {
            swapper = mid;
        }
        if (r < n && Y[r] > Y[swapper]) {
            swapper = r;
        }
        if (swapper != i) {
            int temp = Y[swapper];
            Y[swapper] = Y[(swapper - 1) / 3];
            Y[(swapper - 1) / 3] = temp;
            staticDown(Y, n, swapper);
        }
    }

    private static int pow(int x, int y) {
        if (y <= 0) {
            return 1;
        }
        return x * pow(x, y - 1);
    }

    private int sigma(int x, int y) {
        if (y <= 0) {
            return 1;
        }
        return pow(x, y) + sigma(x, y - 1);
    }

    private void printHeap(int[] Y, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(Y[i] + " ");
            System.out.println(" ");
        }
    }
    /* ----------------- No mods below this line ------------------ */

    public static void main(String[] args) {
        java.util.Scanner myScanner = new java.util.Scanner(System.in);
        TernaryHeap myHeap = new TernaryHeap();
        boolean done = false;
        System.out.println("Type heap commands:");
        while (!done) {
            String[] tokens = myScanner.nextLine().split(" ");
            String operation = "";
            int[] operands = null;
            if (tokens.length > 0) {
                operation = tokens[0];
                operands = new int[tokens.length - 1];
                for (int i = 0; i < operands.length; i++) {
                    operands[i] = Integer.parseInt(tokens[1 + i]);
                }
            }

            if (operation.equals("add")) {
                myHeap.add(operands[0]);
            } else if (operation.equals("peekMax")) {
                System.out.println(myHeap.peekMax());
            } else if (operation.equals("popMax")) {
                System.out.println(myHeap.popMax());
            } else if (operation.equals("output")) {
                myHeap.output();
            } else if (operation.equals("buildHeap")) {
                myHeap.buildHeap(operands);
            } else if (operation.equals("nodesInLevel")) {
                System.out.println(myHeap.nodesInLevel(operands[0]));
            } else if (operation.equals("quit")) {
                done = true;
            }
        }
    }
}
