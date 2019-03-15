package Demo.ChapterEleven;

import java.util.*;

/**
 * Created by asus on 2017/12/24.
 */
public class PrintingContainers {
    static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("bird");
        collection.add("fish");
        collection.add("dog");
        return collection;
    }

    static Map fill(Map<String , String> map){
        map.put("rat" , "Fuzzy");
        map.put("cat" , "Rags");
        map.put("dog" , "Bosco");
        map.put("bird" , "test");
        map.put("fish" , "hhh");
        map.put("dog" , "Spot");
        return map;
    }

    public static void main(String[] args){
        System.out.println("ArrayList\t" + fill(new ArrayList<String>()));
        System.out.println("LinkedList\t" + fill(new LinkedList<String>()));
        System.out.println("HashSet\t" + fill(new HashSet<String>()));  // 无序，不重复
        System.out.println("TreeSet\t" + fill(new TreeSet<String>())); // 有序 , 升序
        System.out.println("LinkedHashSet\t" +fill(new LinkedHashSet<String>())); // 可以按照插入的顺序遍历
        System.out.println("HashMap\t" + fill(new HashMap<String, String>()));
        System.out.println("TreeMap\t" + fill(new TreeMap<String, String>())); // 红黑树 有序， 按键值升序
        System.out.println("LinkedHashMap\t" + fill(new LinkedHashMap<String, String>())); // 可以按照插入的顺序遍历

//        [rat, cat, dog, dog]
//        [rat, cat, dog, dog]
//        [cat, dog, rat]
//        [cat, dog, rat]
//        [rat, cat, dog]
//        {cat=Rags, dog=Spot, rat=Fuzzy}
//        {cat=Rags, dog=Spot, rat=Fuzzy}
//        {rat=Fuzzy, cat=Rags, dog=Spot}
    }
}
