package io.github.gildastema.objectmerge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMergeRecordTest {

    private ObjectMerge<Student> objectMerge;

    @BeforeEach
    void beforeEach(){
        objectMerge = new ObjectMerge<>();
    }

    @Test
    void mergeRecord() throws IllegalAccessException, NoSuchFieldException {
        var student = new Student(null, null);
        Student merged = objectMerge.merge(student, new Student("Gildas", "M45"));
        assertEquals(merged.name(), "Gildas");
    }

}
