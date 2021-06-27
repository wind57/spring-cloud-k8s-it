import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class Del {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);

        System.out.println("map: " + map);

        Map<Integer, Integer> fromMap = map.tailMap(2);

        System.out.println("fromMap: " + fromMap);

        Iterator<Map.Entry<Integer, Integer>> iter = fromMap.entrySet().iterator();
        Map.Entry<Integer, Integer> entry = iter.next();
        System.out.println(entry); // line 1
        iter.remove();
        System.out.println(entry); // line 2. Why entry content changes?
    }

}
