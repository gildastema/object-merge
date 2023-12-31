[![Test](https://github.com/gildastema/object-merge/actions/workflows/maven.yml/badge.svg)](https://github.com/gildastema/object-merge/actions/workflows/maven.yml)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.gildastema/object-merge/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.gildastema/object-merge)

# Object Merge 
The goal of the package is to merge 2 java objects

## Installation
### Maven Installation
```xml
<dependency>
  <groupId>io.github.gildastema</groupId>
  <artifactId>object-merge</artifactId>
  <version> current-version </version>
</dependency>
```
### Gradle Installation
```groovy
implementation 'io.github.gildastema:object-merge:current-version'
```

## Simple merge

```java
import io.github.gildastema.objectmerge.ObjectMerge;
import type.com.github.gildastema.objectmerge.ObjectMergeExclude;

var merger = new ObjectMerge<Student>();
var studentResult = merger.merge(new Student(), new Student("Gildas"));
assertThat(studentResult.name).equals("Gildas");

```

### 1- First Method
This one take just 2 parameters **destination** and **source**
```java
  Student  merge(Student destination, Student source);
```
### 2- Method with validation

```java
Student merge(Student destination, Student source, ObjectMergeExclude.NULL);
```
When NULL exclude is call, all null field on source object will avoid.

