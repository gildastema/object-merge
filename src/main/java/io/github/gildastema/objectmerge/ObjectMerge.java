package io.github.gildastema.objectmerge;

import io.github.gildastema.objectmerge.type.MergeType;
import io.github.gildastema.objectmerge.type.ObjectMergeExclude;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ObjectMerge<T> {
    public T merge(T destination, T source) throws IllegalAccessException {
        List<Field> fields = Arrays.asList(source.getClass().getDeclaredFields());
        return getDestination(destination, source, fields);
    }
    public T merge(T destination, T source, ObjectMergeExclude exclude) throws IllegalAccessException {
        if (exclude == ObjectMergeExclude.NOTHING){
            return merge(destination, source);
        }
        List<Field> fields = Arrays.asList(source.getClass().getDeclaredFields());
        return getDestinationWithExclude(destination, source, fields, exclude);
    }
    private static <T> T getDestinationWithExclude(T destination, T source, List<Field> fields, ObjectMergeExclude exclude) throws IllegalAccessException {
        for (Field field : fields){
            field.setAccessible(true);
            Object value = field.get(source);
            if (exclude == ObjectMergeExclude.NOTHING){
                field.set(destination, value);
            }else {
                if (value != null){
                    field.set(destination, value);
                }
            }
        }
        return destination;
    }

    public T merge(T destination, T source, List<String> fieldsToMerge, MergeType mergeType, ObjectMergeExclude exclude) throws IllegalAccessException {
        if (mergeType == MergeType.INCLUDE){
            List<Field> fields = getFieldsFromListString(source, fieldsToMerge);
            return getDestinationWithExclude(destination, source, fields, exclude);
        }
        List<String> fieldNames = Arrays.stream(source.getClass().getDeclaredFields()).map(Field::getName).toList();
        List<String> fieldNameToMerge = fieldNames.stream().filter(it -> !fieldsToMerge.contains(it)).toList();
        return getDestinationWithExclude(destination, source, getFieldsFromListString(source, fieldNameToMerge), exclude );

    }

    private static <T> List<Field> getFieldsFromListString(T source, List<String> fieldsToMerge) {
        return fieldsToMerge.stream().map(fieldName -> {
            try {
                return source.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return  null;
            }
        }).filter(Objects::nonNull).toList();
    }

    private static <T> T getDestination(T destination, T source, List<Field> fields) throws IllegalAccessException {
        for (Field field : fields){
            field.setAccessible(true);
            Object value = field.get(source);
            field.set(destination, value);
        }
        return destination;
    }

}
