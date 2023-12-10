[![Test](https://github.com/gildastema/object-merge/actions/workflows/maven.yml/badge.svg)](https://github.com/gildastema/object-merge/actions/workflows/maven.yml)
# Object Merge 
The goal of the package is to merge 2 java objects

## Installation
[Current Version](https://github.com/gildastema/object-merge/packages/2011716) 
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

### 1- First Method
This one take just 2 parameters **destination** and **source**
```java
  Student  merge(Student destination, Student source);
```
### 2- Method with validation

```java
import io.github.gildastema.objectmerge.type.ObjectMergeExclude;

Student merge(Student destination, Student source, ObjectMergeExclude.NULL);
```
When NULL exclude is call, all null field on source object will avoid.

