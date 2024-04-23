package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Task 1
        // 1) Show in console Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
        System.out.println("Task 1");
        System.out.println("1)");

        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9);
        stream.forEach(System.out::println);

        // 2) Create a list of random numbers using Stream.generate() , should have 20 items and print in console
        System.out.println("2)");

        StreamUtils.generateRandomNumbersStream(20).forEach(System.out::println);

        // 3) Collect Stream elements to a List:  Convert list elements to stream,
        // select only even ones, use the collect method to collect them into a List:
        System.out.println("3)");

        Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6,7,8,9);
        List<Integer> EvenList = StreamUtils.filterEvenNumbersToList(stream2);
        EvenList.forEach(System.out::println);

        // Task 2
        System.out.println("Task 2");

        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            list.add(i);
        }

        // 4) Repeat the same as in task 3) but collect all the elements in Array [];
        System.out.println("4)");

        Integer[] array = StreamUtils.filterEvenNumbersToArray(list);
        for (int x : array) {
            System.out.println(x);
        }

        // 5) filter values, keep those that start with "c", convert all values,
        // convert to uppercase, sort in Descending order
        System.out.println("5)");

        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        StreamUtils.filterAndSortStringList(myList).forEach(System.out::println);

        // Task 3
        System.out.println("Task 3");

        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Abibaba");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        // 6)
        System.out.println("6)");
        // a) Display names that start with ‘A’ and have length > 5;
        System.out.println("a)");

        StreamUtils.filterNamesStartingWithA(memberNames).forEach(System.out::println);

        // b) Sort all names and display them in lower case on the screen
        System.out.println("b)");

        StreamUtils.convertToLowerCaseAndSort(memberNames).forEach(System.out::println);

        // 7) Stream.match() - Various matching operations can be used to check whether a given predicate matches the stream elements.
        // Match returns a boolean result.
        //From task 6, take an array and check with allMatch or anyMatch whether the list contains names with the letter ‘S’
        // (allMatch should display false, anyMatch should display true)
        //From task 6, take an array and check with noneMatch whether the list contains names with the letter ‘H’ (should display true)

        System.out.println("7)");

        boolean containsS = StreamUtils.containsNameStartingWithS(memberNames);

        System.out.println("anyMatch should display true - " + containsS);

        boolean containsH = StreamUtils.containsNoNameStartingWithH(memberNames);

        System.out.println("noneMatch should display true - " + containsH);

        // 8) Stream.count() - The count() is a terminal operation returning the number of elements in the stream as a long value.
        System.out.println("8)");

        long count = StreamUtils.countNames(memberNames);

        System.out.println("Number of elements in memberNames array: " + count);

        // 9) From task 6 count the number of names starting with “A” - display their number.
        System.out.println("9)");

        long countOfNamesStartingWithA = StreamUtils.countNamesStartingWithA(memberNames);

        System.out.println("Number of names starting with A: " + countOfNamesStartingWithA);

        // 10) Stream.findFirst() - take the array from task 6 and print the first name starting with ‘L’
        System.out.println("10)");

        Optional<String> nameStartingWithL = StreamUtils.findFirstNameStartingWithL(memberNames);

        if (nameStartingWithL.isPresent()) {
            System.out.println(nameStartingWithL.get());
        } else {
            System.out.println("No name starting with 'L' found.");
        }

        // Task 4
        System.out.println("Task 4");
        // 11) Java Stream flatMap() - concatenate 3 arrays into one using flatMap ()
//        List<Integer> list1 = Arrays.asList(1,2,3);
//        List<Integer> list2 = Arrays.asList(4,5,6);
//        List<Integer> list3 = Arrays.asList(7,8,9);
        System.out.println("11)");

        List<List<Integer>> intsNested = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(4,5,6), Arrays.asList(7,8,9));

        List<Integer> intsFlatStream = StreamUtils.flattenNestedList(intsNested);

        System.out.println(intsFlatStream);

        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};

        String[] dataArrayFlat = StreamUtils.flattenNestedArray(dataArray);

        System.out.println(Arrays.toString(dataArrayFlat));

        // 12) Stream.distinct() to remove duplicates - remove duplicates from this array and display.
        System.out.println("12)");

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7));
        List<Integer> numbersListDistinct = StreamUtils.removeDuplicates(numbersList);

        numbersListDistinct.forEach(System.out::println);

        // 13) (OPTIONAL, NOT MANDATORY) Collectors.toMap()
        // - move the list into Map where the key is an element of the array
        // and the value is the number of times the element occurs in the array
        System.out.println("13)");

        // Collectors.toMap(keyMapper, valueMapper, mergeFunction - used to resolve collisions between values associated with the same key)
        Map<Integer, Integer> map = StreamUtils.countOccurrences(numbersList);
        System.out.println(map);

        // Task 5
        System.out.println("Task 5");
        // 14) Write to a new list only letters that occur in the values of this map
        System.out.println("14)");

        Map<String, List<String>> people = new HashMap<>();
        people.put("John", Arrays.asList("555-1123","s", "555-3389", "a"));
        people.put("Mary", Arrays.asList("555-2243","z", "555-5264"));
        people.put("Steve", Arrays.asList("555-6654", "555-3242", "d"));

        List<String> onlyLetters = StreamUtils.extractLetters(people);

        System.out.println(onlyLetters);
    }
}