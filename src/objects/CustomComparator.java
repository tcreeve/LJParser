package objects;
import java.util.Comparator;

public class CustomComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry e1, Entry e2) {
        return e2.compareTo(e1);
    }
}
