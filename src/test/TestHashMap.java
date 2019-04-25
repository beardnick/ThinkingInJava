package test;

import java.util.HashMap;

public class TestHashMap {

    public static void main(String[] args) {
        HashMap<String, Object> testCapcity = new HashMap<>(4);
        testCapcity = new HashMap<>(8);
        testCapcity = new HashMap<>(16);
        testCapcity = new HashMap<>(32);
        HashMap<String, Object> testMethod = new HashMap<>(2);
        testMethod.put("1", 1);
        testMethod.put("2", 2);
        testMethod.put("3", 3);
        testMethod.put("4", 4);
    }
}
