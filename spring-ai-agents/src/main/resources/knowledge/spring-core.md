# Java Concurrency

## Thread

A thread is an independent path of execution within a process.

Java applications can execute multiple tasks concurrently using multiple threads.

## ExecutorService

ExecutorService provides a higher-level API for managing asynchronous task execution.

Instead of manually creating threads, applications can submit tasks to an executor.

## CompletableFuture

CompletableFuture represents an asynchronous computation.

It can be used to compose multiple asynchronous operations without manually managing thread synchronization.

Common operations include thenApply, thenCompose, thenCombine, exceptionally, handle, and allOf.

## Synchronization

Synchronization is required when multiple threads access shared mutable state and operations must be coordinated.

The synchronized keyword can protect critical sections.

Locks such as ReentrantLock provide more explicit control over locking.

## Atomic Classes

The java.util.concurrent.atomic package provides classes such as AtomicInteger and AtomicLong for atomic operations without traditional synchronized blocks.

## Virtual Threads

Virtual threads are lightweight threads designed to make high-throughput concurrent applications easier to write.

Virtual threads are particularly useful for workloads that spend significant time waiting on blocking operations such as network or database calls.

Virtual threads are not intended to make CPU-intensive work execute faster.

Java applications should still consider resource limits such as database connection pool size when using many virtual threads.

@Bean

The @Bean annotation can be used inside a configuration class to explicitly create a Spring-managed object.

@Configuration

@Configuration identifies a class that contains bean definitions.

@Profile

@Profile allows beans or configuration to be enabled only for specific environments.

For example, an application can have separate configurations for development and production.