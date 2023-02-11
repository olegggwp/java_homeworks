import java.util.Arrays;

public class IntList {
    private int[] arr;
    private int length;

    public IntList() {
        arr = new int[1];
        length = 0;
    }

    public int get(int pos) {
        if (pos >= length) {
            return 0;
        }
        return arr[pos];
    }

    public boolean set(int pos, int x) {
        if (pos >= length) {
            return false;
        }
        arr[pos] = x;
        return true;
    }

    public boolean set(int x) {
        if (length < 1) {
            return false;
        }
        arr[length - 1] = x;
        return true;
    }

    public void add(int x) {
        length++;
        if (arr.length < length) {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
        arr[length - 1] = x;
    }

    public int length() {
        return length;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(arr, length));
    }
}
