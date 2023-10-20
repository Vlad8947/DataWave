package net.mirror.dao;

import net.mirror.model.DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomDataMap {

    // ConcurrentHashMap so slowly in 'put' method.
    // Need more speed, then use sync key.
    private HashMap<String, DataModel> dataMap;
    private ArrayList<DataModel> dataList = new ArrayList<>();

    private final String syncKey = "Sync Key";

    private Random random = new Random();

    public RandomDataMap() {
        dataMap = new HashMap<>();
    }

    public RandomDataMap(int initialCapacity) {
        dataMap = new HashMap<>(initialCapacity);
    }

    public void put(String key, String value) {
        if (dataMap.containsKey(key)) {
            var data = dataMap.get(key);
            data.setValue(value);
            return;
        }
        var data = new DataModel(key, value);
        synchronized (syncKey) {
            dataMap.put(key, data);
            dataList.add(data);
        }
    }

    public DataModel get(String key) {
        return dataMap.get(key);
    }

    public DataModel getRandom() {
        return dataList.get(random.nextInt(dataList.size()));
    }

}
