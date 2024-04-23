package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamUtils {

    private StreamUtils() {
        throw new IllegalStateException("Can not instantiate utility class.");
    }

    public static Stream<Double> generateRandomNumbersStream(int limit) {
        return Stream
                .generate(Math::random)
                .limit(limit);
    }

    public static List<Integer> filterEvenNumbersToList(Stream<Integer> stream) {
        return stream
                .filter(x -> x % 2 == 0)
                .toList();
    }

    public static Integer[] filterEvenNumbersToArray(List<Integer> list) {
        return list.stream()
                .filter(x -> x % 2 == 0)
                .toArray(Integer[]::new);
    }

    public static List<String> filterAndSortStringList(List<String> list) {
        return list.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public static List<String> filterNamesStartingWithA(List<String> memberNames) {
        return memberNames.stream()
                .filter(s -> s.startsWith("A") && s.length() > 5)
                .toList();
    }

    public static List<String> convertToLowerCaseAndSort(List<String> memberNames) {
        return memberNames.stream()
                .sorted(Comparator.naturalOrder())
                .map(String::toLowerCase)
                .toList();
    }

    public static boolean containsNameStartingWithS(List<String> memberNames) {
        return memberNames.stream()
                .anyMatch(s -> s.contains("S"));
    }

    public static boolean containsNoNameStartingWithH(List<String> memberNames) {
        return memberNames.stream()
                .noneMatch(s -> s.contains("H"));
    }

    public static long countNames(List<String> memberNames) {
        return memberNames.stream()
                .count();
    }

    public static long countNamesStartingWithA(List<String> memberNames) {
        return memberNames.stream()
                .filter(s -> s.startsWith("A"))
                .count();
    }

    public static Optional<String> findFirstNameStartingWithL(List<String> memberNames) {
        return memberNames.stream()
                .filter(s -> s.startsWith("L"))
                .findFirst();
    }

    public static List<Integer> flattenNestedList(List<List<Integer>> nestedList) {
        return nestedList.stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public static String[] flattenNestedArray(String[][] array) {
        return Arrays
                .stream(array)
                .flatMap(Arrays::stream)
                .toArray(String[]::new);
    }

    public static List<Integer> removeDuplicates(List<Integer> numbersList) {
        return numbersList.stream()
                .distinct()
                .toList();
    }

    public static Map<Integer, Integer> countOccurrences(List<Integer> numbersList) {
        return numbersList.stream()
                .collect(Collectors.toMap(x -> x, x -> 1, Integer::sum));
    }

    public static List<String> extractLetters(Map<String, List<String>> people) {
        return people.values().stream()
                .flatMap(List::stream)
                .filter(str -> str.matches("[a-zA-Z]"))
                .toList();
    }
}
