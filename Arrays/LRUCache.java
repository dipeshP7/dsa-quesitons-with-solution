package com.example.demo.problems.LRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

  private final int capcity;
  private static Map<Integer, Node> lruCache = new HashMap<>();
  private static Node head = new Node(0, 0);
  private static Node tail = new Node(0,0);

  public static class Node{
     int k ,v;
     Node next,prev;
     Node(int key, int values){
       this.k=key;
       this.v = values;
     }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
      return sb.append("key="+ this.k + "|Value="+this.v).toString();
    }
  }

  LRUCache(int capacity) throws Exception {
    if(capacity <= 0){
      throw new Exception("Capacity can not be zero or less than zero");
    }
    this.capcity = capacity;
    head.next=tail;
    tail.prev=head;

  }

  public Node getKeyFromLruCache(int key) throws Exception{
   Node node = lruCache.get(key);
   if(node == null){
     throw new Exception("Key not found from lru cache");
   }
    moveToFront(node);
   return node;
  }

  public void moveToFront(Node node){
    remove(node);
    addFirst(node);
  }

  public void addFirst(Node node){
    node.next=head.next;
    node.prev=head;
    head.next.prev=node;
    head.next=node;
  }
  public void remove(Node node){
    node.prev.next=node.next;
    node.next.prev=node.prev;
  }

  public Node removeLast(){
    Node lru = tail.prev;
    remove(lru);
    return lru;
  }

  public void setKeyToLruCache(int key,int value) throws Exception{
    //check key exist already in lrucachce
    Node node = lruCache.get(key);
    System.out.println("node  "+node);
    if(node != null){
      node.v= value;
      moveToFront(node);
      return;
    }
    System.out.println("Size "+lruCache.size());

      //add new one
      Node freshKey = new Node(key, value);
      //move to front
      lruCache.put(key, freshKey);
      addFirst(freshKey);

    if(lruCache.size() > capcity){
      Node lru = removeLast();
      lruCache.remove(lru.k);
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("LRU Cache Program");
    LRUCache cache = new LRUCache(2);
    cache.setKeyToLruCache(1, 10);
    System.out.println(lruCache.entrySet());
    cache.setKeyToLruCache(2, 20);
    System.out.println(lruCache.entrySet());
    cache.setKeyToLruCache(3, 30);
    System.out.println(lruCache.entrySet());
    cache.setKeyToLruCache(4, 40);
    System.out.println(lruCache.entrySet());
    cache.getKeyFromLruCache(3);
    System.out.println(lruCache.entrySet());
    cache.setKeyToLruCache(5, 50);
    System.out.println(lruCache.entrySet());


  }
}
