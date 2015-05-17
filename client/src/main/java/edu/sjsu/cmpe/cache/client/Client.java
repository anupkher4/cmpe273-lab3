package edu.sjsu.cmpe.cache.client;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
public class Client {

    public static void main(String[] args) throws Exception {
    	final Funnel<CharSequence> strFunnel = Funnels.stringFunnel();
        HashFunction hf = Hashing.md5();
        System.out.println("Starting Cache Client...");
        String node1 = "http://localhost:3000";
        String node2 = "http://localhost:3001";
        String node3 = "http://localhost:3002";
        CacheServiceInterface cache1 = new DistributedCacheService(node1);
        CacheServiceInterface cache2 = new DistributedCacheService(node2);
        CacheServiceInterface cache3 = new DistributedCacheService(node3);
        Map<Integer, String> objects = new HashMap<Integer, String>();
        objects.put(1, "a");
        objects.put(2, "b");
        objects.put(3, "c");
        objects.put(4, "d");
        objects.put(5, "e");
        objects.put(6, "f");
        objects.put(7, "g");
        objects.put(8, "h");
        objects.put(9, "i");
        objects.put(10, "j");
        List<String> al = new ArrayList<String>(); 
        al.add(node1);
        al.add(node2);
        al.add(node3);
        
 //       ConsistentHash hashAlgo = new ConsistentHash(hf, 1, al);
        RendezvousHash hashAlgo = new RendezvousHash(hf, strFunnel, strFunnel, al);
        
        String objNode1 =(String)hashAlgo.get("1");
        String objNode2 =(String)hashAlgo.get("2");
        String objNode3 =(String)hashAlgo.get("3");
        String objNode4 =(String)hashAlgo.get("4");
        String objNode5 =(String)hashAlgo.get("5");
        String objNode6 =(String)hashAlgo.get("6");
        String objNode7 =(String)hashAlgo.get("7");
        String objNode8 =(String)hashAlgo.get("8");
        String objNode9 =(String)hashAlgo.get("9");
        String objNode10 =(String)hashAlgo.get("10");


        ArrayList<String> objNodes = new ArrayList<String>();
        objNodes.add(0, "");
        objNodes.add(1, objNode1);
        objNodes.add(2, objNode2);
        objNodes.add(3, objNode3);
        objNodes.add(4, objNode4);
        objNodes.add(5, objNode5);
        objNodes.add(6, objNode6);
        objNodes.add(7, objNode7);
        objNodes.add(8, objNode8);
        objNodes.add(9, objNode9);
        objNodes.add(10, objNode10);

        for(int i=1;i<=10;i++) {
        	System.out.println(objNodes.get(i)+" : "+i+" -> "+objects.get(i));
        }
        String node="";
        for(int i = 1;i<objNodes.size();i++) {

            node = objNodes.get(i);
            if(node.equals(node1)) cache1.put(i, objects.get(i));
            else if(node.equals(node2)) cache2.put(i, objects.get(i));
            else if(node.equals(node3)) cache3.put(i, objects.get(i));
        }
        
//        System.out.println("get(2) => " + cache1.get(2));
//        System.out.println("get(3) => " + cache1.get(3));
//        System.out.println("get(4) => " + cache1.get(4));
//        System.out.println("get(7) => " + cache1.get(7));
//        System.out.println("get(8) => " + cache1.get(8));
//        System.out.println("get(6) => " + cache2.get(6));
//        System.out.println("get(1) => " + cache3.get(1));
//        System.out.println("get(5) => " + cache3.get(5));
//        System.out.println("get(9) => " + cache3.get(9));
//        System.out.println("get(10) => " + cache3.get(10));
        
        System.out.println("Existing Cache Client...");
    }

}
