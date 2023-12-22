package io.github.gildastema.objectmerge;

import io.github.gildastema.objectmerge.type.MergeType;
import io.github.gildastema.objectmerge.type.ObjectMergeExclude;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectMergeTest {
    ObjectMerge<Person> personObjectMerge;
    @BeforeEach
    void beforeEach(){
         personObjectMerge = new ObjectMerge<>();

    }
    @Test
    void shouldAnswerWithTrue() throws IllegalAccessException, NoSuchFieldException {
        Person personDest = new Person();
        String firstName = "John";
        String lastName = "Doe";
        Person personSource = new Person(firstName, lastName);

        personObjectMerge.merge(personDest, personSource);

        assertEquals(personDest.getFirstName(), firstName);
        assertEquals(personDest.getLastName(), lastName);
    }

    @Test
    void testMergeWithExcludeNULL() throws IllegalAccessException, NoSuchFieldException {
        var personDest = new Person("John", "Doe");
        var source = new Person("Jane", null);
        var result = personObjectMerge.merge(personDest, source, ObjectMergeExclude.NULL);
        assertEquals(result.getFirstName(), "Jane");
        assertEquals(personDest.getLastName(), "Doe");
    }
    @Test
    void testMergeWithExcludeNothing() throws IllegalAccessException, NoSuchFieldException {
        var personDest = new Person("John", "Doe");
        var source = new Person("Jane", null);
        var result = personObjectMerge.merge(personDest, source, ObjectMergeExclude.NOTHING);
        assertEquals(result.getFirstName(), "Jane");
        assertNull(personDest.getLastName());
    }
    @Test
    void testMergeWithFieldMergeInclude() throws IllegalAccessException {
        var dest = new Person("John", "Doe");
        var source = new Person("Jane", "Na");
        var result = personObjectMerge.merge(dest, source, List.of("lastName"), MergeType.INCLUDE, ObjectMergeExclude.NOTHING);
        assertEquals(result.getFirstName(), "John");
        assertEquals(result.getLastName(), "Na");
    }
    @Test
    void testMergeWithFieldMergeIncludeExcludeNULL() throws IllegalAccessException {
        var dest = new Person("John", "Doe");
        var source = new Person("Jane", null);
        var result = personObjectMerge.merge(dest, source, List.of("lastName"), MergeType.INCLUDE, ObjectMergeExclude.NULL);
        assertEquals(result.getFirstName(), "John");
        assertEquals(result.getLastName(), "Doe");
    }
    @Test
    void testMergeWithFieldMergeExclude() throws IllegalAccessException {
        var dest = new Person("John", "Doe");
        var source = new Person("Jane", "Na");
        var result = personObjectMerge.merge(dest, source, List.of("lastName"), MergeType.EXCLUDE, ObjectMergeExclude.NOTHING);
        assertEquals(result.getFirstName(), "Jane");
        assertEquals(result.getLastName(), "Doe");
    }
    @Test
    void testMergeWithFieldMergeExcludeNULL() throws IllegalAccessException {
        var dest = new Person("John", "Doe");
        var source = new Person(null, "Na");
        var result = personObjectMerge.merge(dest, source, List.of("lastName"), MergeType.EXCLUDE, ObjectMergeExclude.NULL);
        assertEquals(result.getFirstName(), "John");
        assertEquals(result.getLastName(), "Doe");
    }
}
