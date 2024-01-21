package design;

import java.util.*;

public class MergeArrays {
    public void merge(int[] arr1, int[] arr2) {

        Set<Integer> noDupWithInsertionOrder = new LinkedHashSet<>();
        for (int i : arr1) {
            noDupWithInsertionOrder.add(i);
        }
        for (int i : arr2) {
            noDupWithInsertionOrder.add(i);
        }

        System.out.print("noDupWithInsertionOrder: LinkedHashSet >> ");
        for (Integer num: noDupWithInsertionOrder) {
            System.out.print(num + " ");
        }

        Set<Integer> noDupWithNaturalOrder = new TreeSet<>();
        for (int i : arr1) {
            noDupWithNaturalOrder.add(i);
        }
        for (int i : arr2) {
            noDupWithNaturalOrder.add(i);
        }
        System.out.println("");
        System.out.print("noDupWithNaturalOrder: TreeSet >> ");
        for (Integer num: noDupWithNaturalOrder) {
            System.out.print(num + " ");
        }

        List<Integer> withDupWithInsertionOrder = new ArrayList<>();
        for (int i : arr1) {
            withDupWithInsertionOrder.add(i);
        }
        for (int i : arr2) {
            withDupWithInsertionOrder.add(i);
        }
        System.out.println("");
        System.out.print("withDupWithInsertionOrder: ArrayList >> ");
        for (Integer num: withDupWithInsertionOrder) {
            System.out.print(num + " ");
        }

        Queue<Integer> withDupWithNaturalOrder = new PriorityQueue<>();
        for (int i : arr1) {
            withDupWithNaturalOrder.offer(i);
        }
        for (int i : arr2) {
            withDupWithNaturalOrder.offer(i);
        }
        System.out.println("");
        System.out.print("withDupWithNaturalOrder: PriorityQueue >> ");
        while (!withDupWithNaturalOrder.isEmpty()) {
            System.out.print(withDupWithNaturalOrder.poll() + " ");
        }

    }
}
