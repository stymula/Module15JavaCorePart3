package org.example;

import javax.lang.model.type.ArrayType;
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

        Stream.generate(Math::random)
                .limit(20)
                .forEach(System.out::println);

        // 3) Collect Stream elements to a List:  Convert list elements to stream,
        // select only even ones, use the collect method to collect them into a List:
        System.out.println("3)");

        Stream<Integer> stream2 = Stream.of(1,2,3,4,5,6,7,8,9);
        List<Integer> EvenList = stream2
                .filter(x -> x % 2 == 0)
                .toList();

        EvenList.forEach(System.out::println);

        // Task 2
        System.out.println("Task 2");

        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            list.add(i);
        }

        // 4) Repeat the same as in task 3) but collect all the elements in Array [];
        System.out.println("4)");

        Integer[] array = list.stream().filter(x -> x % 2 == 0).toArray(Integer[]::new);
        for (int x : array) {
            System.out.println(x);
        }

        // 5) filter values, keep those that start with "c", convert all values,
        // convert to uppercase, sort in Descending order
        System.out.println("5)");

        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

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

        memberNames
                .stream()
                .filter(s -> s.startsWith("A") && s.length() > 5)
                .forEach(System.out::println);

        // b) Sort all names and display them in lower case on the screen
        System.out.println("b)");

        memberNames
                .stream()
                .sorted(Comparator.naturalOrder())
                .map(String::toLowerCase)
                .forEach(System.out::println);

        // 7) Stream.match() - Various matching operations can be used to check whether a given predicate matches the stream elements.
        // Match returns a boolean result.
        //From task 6, take an array and check with allMatch or anyMatch whether the list contains names with the letter ‘S’
        // (allMatch should display false, anyMatch should display true)
        //From task 6, take an array and check with noneMatch whether the list contains names with the letter ‘H’ (should display true)

        System.out.println("7)");

        boolean containsS = memberNames
                .stream()
                .anyMatch(s -> s.contains("S"));

        System.out.println("anyMatch should display true - " + containsS);

        boolean containsH = memberNames
                .stream()
                .noneMatch(s -> s.contains("H"));

        System.out.println("noneMatch should display true - " + containsH);

        // 8) Stream.count() - The count() is a terminal operation returning the number of elements in the stream as a long value.
        System.out.println("8)");

        long count = memberNames
                .stream()
                .count();

        System.out.println("Number of elements in memberNames array: " + count);

        // 9) From task 6 count the number of names starting with “A” - display their number.
        System.out.println("9)");

        long countOfNamesStartingWithA = memberNames
                .stream()
                .filter(s -> s.startsWith("A"))
                .count();

        System.out.println("Number of names starting with A: " + countOfNamesStartingWithA);

        // 10) Stream.findFirst() - take the array from task 6 and print the first name starting with ‘L’
        System.out.println("10)");

        Optional<String> nameStartingWithL = memberNames.stream().filter(s -> s.startsWith("L")).findFirst();

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

        List<Integer> intsFlatStream = intsNested
                .stream()
                .flatMap(Collection::stream)
                .toList();

        System.out.println(intsFlatStream);

        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};

        String[] dataArrayFlat = Arrays
                .stream(dataArray)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);

        System.out.println(Arrays.toString(dataArrayFlat));

        // 12) Stream.distinct() to remove duplicates - remove duplicates from this array and display.
        System.out.println("12)");

        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7));
        List<Integer> numbersListDistinct = numbersList
                .stream()
                .distinct()
                .toList();

        numbersListDistinct.forEach(System.out::println);

        // 13) (OPTIONAL, NOT MANDATORY) Collectors.toMap()
        // - move the list into Map where the key is an element of the array
        // and the value is the number of times the element occurs in the array
        System.out.println("13)");

        // Collectors.toMap(keyMapper, valueMapper, mergeFunction - used to resolve collisions between values associated with the same key)
        Map<Integer, Integer> map = numbersList.stream().collect(Collectors.toMap(x -> x, x -> 1, Integer::sum));
        System.out.println(map);

        // Task 5
        System.out.println("Task 5");
        // 14) Write to a new list only letters that occur in the values of this map
        System.out.println("14)");

        Map<String, List<String>> people = new HashMap<>();
        people.put("John", Arrays.asList("555-1123","s", "555-3389", "a"));
        people.put("Mary", Arrays.asList("555-2243","z", "555-5264"));
        people.put("Steve", Arrays.asList("555-6654", "555-3242", "d"));

        List<String> onlyLetters = people
                .values()
                .stream()
                .flatMap(List::stream)
                .filter(str -> str.matches("[a-zA-Z]"))
                .toList();

        System.out.println(onlyLetters);
    }
}