package org.terrier.remote.impl;

import org.terrier.querying.Manager;
import org.terrier.querying.RemoteClientManager;
import org.terrier.structures.Index;
import java.util.HashMap;

public class ImportedIndexes {

    private static HashMap<String, Index> indexes;
    private static HashMap<String, Manager> managers;

    private ImportedIndexes(){}

    public static HashMap<String, Index> getIndexes() {
        if(indexes == null){
            indexes = new HashMap<String,Index>();
        }

        return indexes;
    }

    public static HashMap<String, Manager> getManagers(){
        if(managers == null){
            managers = new HashMap<String, Manager>();
        }
        return managers;
    }

    public static void addIndex(String indexName, Index index){
        indexes.put(indexName, index);
    }
    public static void addManager(String indexName, Manager manager) { managers.put(indexName, manager); }
}
