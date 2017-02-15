package com.cache.application.lruCache;

import com.cache.application.ICacheComponent;
import com.cache.application.pojos.SingleCacheEntry;

import java.util.*;

/**
 * Created by michal.zfania on 13-Feb-17.
 */
public class LRUCacheStorageComponent implements ICacheComponent {
    private static Map<String, Node> cacheStorage = new HashMap();
    private static Node leastRecentlyUsed;
    private static Node mostRecentlyUsed;
    private static int MAX_SIZE = 3;
    private static int currentSize = 0;
    private static LRUCacheStorageComponent instance = new LRUCacheStorageComponent();

    class Node{
        Node previous;
        Node next;
        private String key;
        private String value;

        public Node(Node previous, Node next, String key, String value){
            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static LRUCacheStorageComponent getInstance(){
        return instance;
    }

    private LRUCacheStorageComponent(){
        if (leastRecentlyUsed == null) {
            leastRecentlyUsed = new Node(null, null, null, null);
            mostRecentlyUsed = leastRecentlyUsed;
        }
    }

    public String getValue(String key){
        Node searchedNode = (Node) cacheStorage.get(key);
        if (searchedNode == null){
            return null;
        }

        //If the value we need is the most recently used. there is no action needed
        if (searchedNode.key.equals(mostRecentlyUsed.key)){
            return mostRecentlyUsed.value;
        }

        //save the previous and next node of our search node
        Node nextNode = searchedNode.next;
        Node previousNode = searchedNode.previous;

        //if our node is the least recently used, we should change the LRU pointer to point to the one after the LRU
        if (searchedNode.key.equals(leastRecentlyUsed.key)){
            nextNode.previous = null;
            leastRecentlyUsed = searchedNode.next;
        }
        //normal case - search node is somewhere in the middle. we should take out the node from the list. changing the neighboring nodes to point to each other
        else{
            previousNode.next = nextNode;
            nextNode.previous = previousNode;
        }

        //make our node MRU and fix the pointer of the last MRU.
        searchedNode.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = searchedNode;
        mostRecentlyUsed = searchedNode;
        mostRecentlyUsed.next = null;

        return searchedNode.value;
    }

    public void setEntry(String key, String value){
        //if the key exists. we don't need to insert again. (we might change this behavior to replace the value, but its to our choice here)
        if (cacheStorage.containsKey(key)){
            return;
        }

        //if we reached out limit, we should have to take out the LRU before inserting another node
        Node myNode = new Node(mostRecentlyUsed, null, key, value);
        if (currentSize == MAX_SIZE){
            cacheStorage.remove(leastRecentlyUsed.key);
            leastRecentlyUsed = leastRecentlyUsed.next;
            leastRecentlyUsed.previous = null;
        }else{
            if (currentSize == 0){
                leastRecentlyUsed = myNode;
            }
            currentSize++;
        }

        mostRecentlyUsed.next = myNode;
        cacheStorage.put(key, myNode);
        mostRecentlyUsed = myNode;

    }

    public List<SingleCacheEntry> getAllEntries(){
        List<SingleCacheEntry> cacheList =  new ArrayList<>();

        Iterator it = cacheStorage.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            cacheList.add(new SingleCacheEntry((String)entry.getKey(), ((Node)entry.getValue()).getValue()));
        }

        return cacheList;
    }

}
