# Java Basics

## Object-Oriented Programming

Java is an object-oriented programming language. The major object-oriented concepts are encapsulation, inheritance, polymorphism, and abstraction.

Encapsulation means keeping an object's data and behavior together and controlling access to its internal state. In Java, encapsulation is commonly implemented using private fields and public methods.

Inheritance allows one class to inherit behavior and properties from another class. Java supports class inheritance using the extends keyword.

Polymorphism means that the same interface or parent type can represent different implementations. Runtime polymorphism is commonly achieved through method overriding.

Abstraction means exposing the important behavior of an object while hiding unnecessary implementation details. Java supports abstraction through interfaces and abstract classes.

## Interface

An interface defines a contract that implementing classes must follow. A Java class can implement multiple interfaces.

Interfaces are useful when the application needs to depend on behavior rather than a concrete implementation.

## Abstract Class

An abstract class can contain both abstract methods and concrete methods. A class can extend only one class in Java.

## Exception Handling

Java provides try, catch, finally, throw, and throws for exception handling.

Checked exceptions must normally be handled or declared. Unchecked exceptions generally extend RuntimeException.

## Optional

java.util.Optional represents a value that may or may not exist.

Optional can make APIs clearer when a missing value is a valid result. It should not normally be used as a replacement for every nullable field or method parameter.

## Streams

The Java Stream API provides a declarative way to process collections of data.

Common stream operations include filter, map, flatMap, sorted, distinct, collect, reduce, and forEach.

Intermediate operations such as filter and map are lazy. Terminal operations such as collect and forEach trigger stream processing.