package com.example.demo.problems.MajorityElement;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args){
//        Given an array (or list) of elements, identify the element that occurs more than half of the total size of the array.
//        If the array size is n, then you need to find the element whose count is:
//        count(element)> n/2
//        Total elements = 7
//        n/2 = 3.5
//        Element 2 appears 5 times
//        Since 5 > 3.5, 2 is the majority element.
        int[] arr_elements = {2, 2, 1, 2, 3, 2, 2};
        MajorityElement.findMajorityElement(arr_elements);
    }


    public static int findMajorityElement(int[] arrElements){
        int majorityElement = 0;
        int defauntCount = 1;
        double requiredLenght =  ((double) arrElements.length / 2);
        int maxCount = 0;
        Map<Integer,Integer> elementsWithCount = new HashMap<Integer, Integer>();
        for(int i =0; i < arrElements.length; i++){
            if(elementsWithCount.containsKey(arrElements[i])){
                int existingCountValue =  elementsWithCount.get(arrElements[i]);
                existingCountValue = existingCountValue + 1;
                elementsWithCount.put(arrElements[i], existingCountValue);
                if(maxCount < existingCountValue){
                   maxCount = existingCountValue;
                   majorityElement =  arrElements[i];
                   System.out.println("MajorityElem "+ majorityElement+ " maxCount : "+ maxCount);
                }
            } else {
                elementsWithCount.put(arrElements[i], defauntCount);
            }
        }

        if(maxCount <= 0){
            System.out.println("no majority element found");
        }
        if(maxCount > Math.round(requiredLenght)){
            System.out.println("Majority element : "+ majorityElement);
        }
        System.out.println(elementsWithCount.entrySet());
        return majorityElement;
    }

    public static void boyee_moore_approach(int[] arrElements){

    }


}
