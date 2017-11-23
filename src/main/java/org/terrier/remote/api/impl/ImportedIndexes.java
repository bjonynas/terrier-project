package org.terrier.remote.api.impl;

import org.terrier.structures.Index;
import java.util.HashMap;

public class ImportedIndexes {

    private static HashMap<String, Index> indexes;

    private ImportedIndexes(){}

    public static HashMap<String, Index> getIndexes() {
        if(indexes == null){
            indexes = new HashMap<String,Index>();
        }

        return indexes;
    }

    public static void addIndex(String indexId, Index index){
        indexes.put(indexId, index);
    }
}
