package net.mirror.service;

import net.mirror.dao.RandomDataMap;
import net.mirror.model.DataModel;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private RandomDataMap dataMap = new RandomDataMap();

    public void put(String key, String value) {
        dataMap.put(key, value);
    }

    public DataModel get(String key) {
        return dataMap.get(key);
    }

    public DataModel getRandom() {
        return dataMap.getRandom();
    }
}
