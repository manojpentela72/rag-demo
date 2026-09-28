
---

# 12. `spring-data-jpa.md`

```markdown
# Spring Data JPA

## Spring Data JPA

Spring Data JPA simplifies database access using the Java Persistence API.

It provides repository abstractions that reduce the amount of boilerplate database code.

## Entity

An entity is a Java class mapped to a database table.

The class is commonly annotated with @Entity.

Example:

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}

Repository

Spring Data JPA repositories provide methods for common database operations.

JpaRepository provides CRUD operations and additional JPA-specific functionality.

Example:
public interface UserRepository extends JpaRepository<User, Long> {
}
Query Methods

Spring Data JPA can derive queries from repository method names.

Example:
List<User> findByName(String name);

@Query

The @Query annotation allows developers to define JPQL or native SQL queries explicitly.

Lazy Loading

Lazy loading means related data is loaded when it is accessed rather than immediately.

Lazy loading can improve performance but may cause LazyInitializationException when an entity is accessed outside the persistence context.

N+1 Query Problem

The N+1 query problem occurs when an application executes one query to retrieve a collection of entities and then executes an additional query for each entity to retrieve related data.

This can cause severe performance problems.

Potential solutions include fetch joins, EntityGraph, batch fetching, and carefully designed queries.

Transactions

@Transactional defines a transactional boundary.

A transaction groups database operations so they can be committed or rolled back as a unit.

Transactions should normally be placed around a business operation rather than around arbitrary individual statements.