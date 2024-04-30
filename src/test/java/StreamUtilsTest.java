import org.example.StreamUtils;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class StreamUtilsTest {
    @Test
    public void testGenerateRandomNumbersStream() {
        int limit = 10;
        Stream<Double> randomStream = StreamUtils.generateRandomNumbersStream(limit);

        assertNotNull(randomStream);

        List<Double> resultList = randomStream.toList();

        assertEquals(limit, resultList.size());

        for (double num : resultList) {
            assertTrue(num >= 0.0 && num < 1.0, "Element " + num + " is not within [0.0, 1.0)");
        }
    }

    @Test
    public void testFilterEvenNumbersToList() {
        // Test case 1: only even numbers
        Stream<Integer> stream1 = Stream.of(2,4,6,8);
        List<Integer> result1 = StreamUtils.filterEvenNumbersToList(stream1);
        assertEquals(List.of(2,4,6,8), result1);

        // Test case 2: only odd numbers
        Stream<Integer> stream2 = Stream.of(1,3,5,7,9);
        List<Integer> result2 = StreamUtils.filterEvenNumbersToList(stream2);
        assertTrue(result2.isEmpty());

        // Test case 3: even and odd numbers
        Stream<Integer> stream3 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> result3 = StreamUtils.filterEvenNumbersToList(stream3);
        assertEquals(List.of(2,4,6,8,10), result3);

        // Tests case 4: empty stream
        Stream<Integer> emptyStream = Stream.empty();
        List<Integer> result4 = StreamUtils.filterEvenNumbersToList(emptyStream);
        assertTrue(result4.isEmpty());
    }

    @Test
    public void testFilterEvenNumbersToArray() {
        // Test case 1:
        List<Integer> list1 = List.of(2,4,6,8);
        Integer[] arrayResult1 = StreamUtils.filterEvenNumbersToArray(list1);
        assertArrayEquals(new Integer[]{2, 4, 6, 8}, arrayResult1);

        // Test case 2: only odd numbers
        List<Integer> list2 = Arrays.asList(1, 3, 5, 7, 9);
        Integer[] result2 = StreamUtils.filterEvenNumbersToArray(list2);
        assertArrayEquals(new Integer[0], result2);

        // Test case 3: even and odd numbers
        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer[] result3 = StreamUtils.filterEvenNumbersToArray(list3);
        assertArrayEquals(new Integer[]{2, 4, 6, 8, 10}, result3);

        // Test case 4: empty list
        List<Integer> emptyList = List.of();
        Integer[] result4 = StreamUtils.filterEvenNumbersToArray(emptyList);
        assertArrayEquals(new Integer[0], result4);
    }

    @Test
    public void testFilterStringsStartingWithCAndSortList() {
        // Test case 1: strings starting with "c"
        List<String> list1 = Arrays.asList("c2", "c1", "b1", "a2", "a1");
        List<String> result1 = StreamUtils.filterStringsStartingWithCAndSortList(list1);
        assertEquals(Arrays.asList("C2", "C1"), result1);

        // Test case 2: no strings starting with "c"
        List<String> list2 = Arrays.asList("a1", "b1", "a2", "b2", "a3");
        List<String> result2 = StreamUtils.filterStringsStartingWithCAndSortList(list2);
        assertTrue(result2.isEmpty());

        // Test case 3: empty list
        List<String> emptyList = List.of();
        List<String> result3 = StreamUtils.filterStringsStartingWithCAndSortList(emptyList);
        assertTrue(result3.isEmpty());

        // Test case 4: list with mixed strings
        List<String> list4 = Arrays.asList("c1", "b2", "a3", "c4", "a5");
        List<String> result4 = StreamUtils.filterStringsStartingWithCAndSortList(list4);
        assertEquals(Arrays.asList("C4", "C1"), result4);
    }

    @Test
    public void testFilterNamesStartingWithA() {
        // Test case 1: starts with "A" and length > 5
        List<String> memberNames1 = Arrays.asList("Amitabh", "Amanda", "Alexander", "Alfred");
        List<String> result1 = StreamUtils.filterNamesStartingWithA(memberNames1);
        assertEquals(Arrays.asList("Amitabh", "Amanda", "Alexander", "Alfred"), result1);

        // Test case 2: starts with "A" but length <= 5
        List<String> memberNames2 = Arrays.asList("A", "Al", "Amy", "Ava");
        List<String> result2 = StreamUtils.filterNamesStartingWithA(memberNames2);
        assertTrue(result2.isEmpty());

        // Test case 3: NO "A" but length > 5
        List<String> memberNames3 = Arrays.asList("Benjamin", "Charlotte", "David", "Emma");
        List<String> result3 = StreamUtils.filterNamesStartingWithA(memberNames3);
        assertTrue(result3.isEmpty());

        // Test case 4: empty list
        List<String> emptyList = List.of();
        List<String> result4 = StreamUtils.filterNamesStartingWithA(emptyList);
        assertTrue(result4.isEmpty());

        // Test case 5: list with mixed names
        List<String> memberNames5 = Arrays.asList("Amitabh", "Benjamin", "Anna", "Adam", "Alex");
        List<String> result5 = StreamUtils.filterNamesStartingWithA(memberNames5);
        assertEquals(List.of("Amitabh"), result5);
    }

    @Test
    public void testConvertToLowerCaseAndSort() {
        // Test case 1: list with mixed-case names
        List<String> memberNames1 = Arrays.asList("John", "Mary", "Steve", "Alice", "Bob");
        List<String> result1 = StreamUtils.convertToLowerCaseAndSort(memberNames1);
        assertEquals(Arrays.asList("alice", "bob", "john", "mary", "steve"), result1);

        // Test case 2: list with already lower-case names and sorted
        List<String> memberNames2 = Arrays.asList("alice", "bob", "john", "mary", "steve");
        List<String> result2 = StreamUtils.convertToLowerCaseAndSort(memberNames2);
        assertEquals(Arrays.asList("alice", "bob", "john", "mary", "steve"), result2);

        // Test case 3: empty list
        List<String> emptyList = List.of();
        List<String> result4 = StreamUtils.convertToLowerCaseAndSort(emptyList);
        assertTrue(result4.isEmpty());

        // Test case 4: list with duplicate names
        List<String> memberNames5 = Arrays.asList("Alice", "Bob", "Alice", "John", "Bob", "Mary");
        List<String> result5 = StreamUtils.convertToLowerCaseAndSort(memberNames5);
        assertEquals(Arrays.asList("alice", "alice", "bob", "bob", "john", "mary"), result5);
    }

    @Test
    public void testContainsNameStartingWithS() {
        // Test case 1: list with names containing "S"
        List<String> memberNames1 = Arrays.asList("Bob", "Alice", "John", "Stuart", "Mary");
        boolean result1 = StreamUtils.containsNameStartingWithS(memberNames1);
        assertTrue(result1);

        // Test case 2: list with names not containing "S"
        List<String> memberNames2 = Arrays.asList("Alice", "Bob", "John", "Mary");
        boolean result2 = StreamUtils.containsNameStartingWithS(memberNames2);
        assertFalse(result2);

        // Test case 3: empty list
        List<String> emptyList = List.of();
        boolean result3 = StreamUtils.containsNameStartingWithS(emptyList);
        assertFalse(result3);

        // Test case 4: list with a name containing "s"
        List<String> memberNames4 = Arrays.asList("sarah", "Ben", "Lily", "Tom", "Jess");
        boolean result4 = StreamUtils.containsNameStartingWithS(memberNames4);
        assertFalse(result4);
    }

    @Test
    public void testContainsNoNameStartingWithH() {
        // Test case 1: list with no names containing "H"
        List<String> memberNames1 = Arrays.asList("John", "Mary", "Steve", "Alice", "Bob");
        boolean result1 = StreamUtils.containsNoNameStartingWithH(memberNames1);
        assertTrue(result1);

        // Test case 2: list with a name containing "H"
        List<String> memberNames2 = Arrays.asList("Alice", "Bob", "John", "Harry", "Mary");
        boolean result2 = StreamUtils.containsNoNameStartingWithH(memberNames2);
        assertFalse(result2);

        // Test case 3: empty list
        List<String> emptyList = List.of();
        boolean result3 = StreamUtils.containsNoNameStartingWithH(emptyList);
        assertTrue(result3);

        // Test case 4: list with a name containing "h"
        List<String> memberNames4 = Arrays.asList("Sarah", "harry", "Ben", "Lily", "Tom", "Jess");
        boolean result4 = StreamUtils.containsNoNameStartingWithH(memberNames4);
        assertTrue(result4);
    }

    @Test
    public void testCountNames() {
        // Test case 1: list with names
        List<String> memberNames1 = Arrays.asList("Alice", "Bob", "John", "Harry", "Mary");
        long result1 = StreamUtils.countNames(memberNames1);
        assertEquals(5, result1);

        // Test case 2: empty list
        List<String> memberNames2 = List.of();
        long result2 = StreamUtils.countNames(memberNames2);
        assertEquals(0, result2);
    }

    @Test
    public void testCountNamesStartingWithA() {
        // Test case 1: list with names starting with "A"
        List<String> memberNames1 = Arrays.asList("Alice", "Bob", "Amy", "Harry", "Mary");
        long result1 = StreamUtils.countNamesStartingWithA(memberNames1);
        assertEquals(2, result1);

        // Test case 2: list with a name starting with "a"
        List<String> memberNames2 = Arrays.asList("alice", "Bob", "Amy", "Harry", "Mary");
        long result2 = StreamUtils.countNamesStartingWithA(memberNames2);
        assertEquals(1, result2);

        // Test case 3: empty list
        List<String> memberNames3 = List.of();
        long result3 = StreamUtils.countNamesStartingWithA(memberNames3);
        assertEquals(0, result3);

        // Test case 4: list with no names starting with "A"
        List<String> memberNames4 = Arrays.asList("Bob", "Stefan", "Harry", "Mary", "Peter");
        long result4 = StreamUtils.countNamesStartingWithA(memberNames4);
        assertEquals(0, result4);
    }

    @Test
    public void testFindFirstNameStartingWithL() {
        // Test case 1: list with a name starting with "L"
        List<String> memberNames1 = Arrays.asList("John", "Mary", "Luke", "Alice", "Bob");
        Optional<String> result1 = StreamUtils.findFirstNameStartingWithL(memberNames1);
        assertTrue(result1.isPresent());
        assertEquals("Luke", result1.get());

        // Test case 2: list with no name starting with "L"
        List<String> memberNames2 = Arrays.asList("Alice", "Bob", "John", "Mary");
        Optional<String> result2 = StreamUtils.findFirstNameStartingWithL(memberNames2);
        assertTrue(result2.isEmpty());

        // Test case 3: empty list
        List<String> emptyList = List.of();
        Optional<String> result3 = StreamUtils.findFirstNameStartingWithL(emptyList);
        assertTrue(result3.isEmpty());

        // Test case 4: list with a name starting with "l" and name starting with "L"
        List<String> memberNames4 = Arrays.asList("lily", "Ben", "Luke", "Tom", "Jess");
        Optional<String> result4 = StreamUtils.findFirstNameStartingWithL(memberNames4);
        assertTrue(result4.isPresent());
        assertEquals("Luke", result4.get());
    }

    @Test
    public void testFlattenNestedList() {
        // Test case 1: nested list with elements
        List<List<Integer>> nestedList1 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                List.of(6),
                Arrays.asList(7, 8, 9)
        );
        List<Integer> result1 = StreamUtils.flattenNestedList(nestedList1);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), result1);

        // Test case 2: nested list with empty inner lists
        List<List<Integer>> nestedList2 = Arrays.asList(
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );
        List<Integer> result2 = StreamUtils.flattenNestedList(nestedList2);
        assertTrue(result2.isEmpty());

        // Test case 3: nested list with one empty inner list
        List<List<Integer>> nestedList3 = Arrays.asList(
                Arrays.asList(1, 2, 3),
                List.of(),
                Arrays.asList(4, 5),
                List.of(6)
        );
        List<Integer> result3 = StreamUtils.flattenNestedList(nestedList3);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result3);

        // Test case 4: nested list with null elements
        List<List<Integer>> nestedList4 = Arrays.asList(
                Arrays.asList(1, null, 3),
                Arrays.asList(null, 5),
                Arrays.asList(6, null),
                Arrays.asList(null, null, null)
        );
        List<Integer> result4 = StreamUtils.flattenNestedList(nestedList4);
        assertEquals(Arrays.asList(1, null, 3, null, 5, 6, null, null, null, null), result4);
    }

    @Test
    public void testFlattenNestedArray() {
        // Test case 1: nested array with elements
        String[][] array1 = {{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
        String[] result1 = StreamUtils.flattenNestedArray(array1);
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f", "g", "h"}, result1);

        // Test case 2: nested array with empty inner arrays
        String[][] array2 = {{}, {}, {}, {}};
        String[] result2 = StreamUtils.flattenNestedArray(array2);
        assertArrayEquals(new String[]{}, result2);

        // Test case 3: nested array with one empty inner array
        String[][] array3 = {{"a", "b"}, {}, {"c", "d"}, {"e", "f"}};
        String[] result3 = StreamUtils.flattenNestedArray(array3);
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e", "f"}, result3);

        // Test case 4: nested array with null elements
        String[][] array4 = {{"a", "b"}, {"c", null}, {"e", "f"}, {"g", "h"}};
        String[] result4 = StreamUtils.flattenNestedArray(array4);
        assertArrayEquals(new String[]{"a", "b", "c", null, "e", "f", "g", "h"}, result4);
    }

    @Test
    public void testRemoveDuplicates() {
        // Test case 1: list with duplicate elements
        List<Integer> numbersList1 = Arrays.asList(1, 2, 3, 3, 4, 5, 6, 6, 6, 7);
        List<Integer> result1 = StreamUtils.removeDuplicates(numbersList1);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), result1);

        // Test case 2: list with no duplicates
        List<Integer> numbersList2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result2 = StreamUtils.removeDuplicates(numbersList2);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result2);

        // Test case 3: empty list
        List<Integer> emptyList = List.of();
        List<Integer> result3 = StreamUtils.removeDuplicates(emptyList);
        assertTrue(result3.isEmpty());

        // Test case 4: list with all elements as duplicates
        List<Integer> numbersList4 = Arrays.asList(1, 1, 1, 1, 1, 1, 1);
        List<Integer> result4 = StreamUtils.removeDuplicates(numbersList4);
        assertEquals(List.of(1), result4);
    }

    @Test
    public void testCountOccurrences() {
        // Test case 1: list with elements and their occurrences
        List<Integer> numbersList1 = Arrays.asList(1, 2, 3, 3, 4, 5, 6, 6, 6, 7);
        Map<Integer, Integer> result1 = StreamUtils.countOccurrences(numbersList1);
        assertEquals(Map.of(1, 1, 2, 1, 3, 2, 4, 1, 5, 1, 6, 3, 7, 1), result1);

        // Test case 2: list with no elements
        List<Integer> emptyList = List.of();
        Map<Integer, Integer> result2 = StreamUtils.countOccurrences(emptyList);
        assertTrue(result2.isEmpty());

        // Test case 3: list with only one element
        List<Integer> numbersList3 = List.of(5);
        Map<Integer, Integer> result3 = StreamUtils.countOccurrences(numbersList3);
        assertEquals(Map.of(5, 1), result3);

        // Test case 4: list with all elements being the same
        List<Integer> numbersList4 = Arrays.asList(2, 2, 2, 2, 2, 2, 2);
        Map<Integer, Integer> result4 = StreamUtils.countOccurrences(numbersList4);
        assertEquals(Map.of(2, 7), result4);
    }

    @Test
    public void testExtractLetters() {
        // Test case 1: map with multiple entries and values containing letters
        Map<String, List<String>> people1 = new HashMap<>();
        people1.put("John", Arrays.asList("555-1123", "s", "555-3389", "a"));
        people1.put("Mary", Arrays.asList("555-2243", "z", "555-5264"));
        people1.put("Steve", Arrays.asList("555-6654", "555-3242", "d"));
        List<String> result1 = StreamUtils.extractLetters(people1);
        assertEquals(Arrays.asList("d", "s", "a", "z"), result1);

        // Test case 2: map with multiple entries and no values containing letters
        Map<String, List<String>> people2 = new HashMap<>();
        people2.put("John", Arrays.asList("555-1123", "555-3389"));
        people2.put("Mary", Arrays.asList("555-2243", "555-5264"));
        people2.put("Steve", Arrays.asList("555-6654", "555-3242"));
        List<String> result2 = StreamUtils.extractLetters(people2);
        assertTrue(result2.isEmpty());

        // Test case 3: empty map
        Map<String, List<String>> emptyMap = new HashMap<>();
        List<String> result3 = StreamUtils.extractLetters(emptyMap);
        assertTrue(result3.isEmpty());

        // Test case 4: map with values containing only letters
        Map<String, List<String>> people4 = new HashMap<>();
        people4.put("John", Arrays.asList("a", "d"));
        people4.put("Mary", Arrays.asList("g", "x"));
        List<String> result4 = StreamUtils.extractLetters(people4);
        assertEquals(Arrays.asList("a", "d", "g", "x"), result4);
    }
}
