
---

# 11. `spring-boot.md`

```markdown
# Spring Boot

## Spring Boot

Spring Boot simplifies the development of Spring applications by providing auto-configuration, starter dependencies, embedded servers, and production-oriented features.

## Auto Configuration

Spring Boot automatically configures parts of an application based on the dependencies present on the classpath and the application's configuration.

For example, adding a database starter and configuring database properties can cause Spring Boot to configure a DataSource automatically.

Auto-configuration can be customized or disabled when necessary.

## Starter Dependencies

Spring Boot starters provide a convenient set of dependencies for common application capabilities.

For example, spring-boot-starter-web provides dependencies needed for building web applications.

## application.yml

application.yml is commonly used to configure Spring Boot applications.

Configuration can include server settings, database settings, logging settings, application-specific properties, and integration settings.

## Profiles

Spring profiles allow different configurations to be activated for different environments.

Common profiles include dev, test, and prod.

A profile-specific configuration can be stored in files such as application-dev.yml.

## Actuator

Spring Boot Actuator provides production-ready monitoring and management capabilities.

Common actuator endpoints include health, metrics, info, and prometheus when the corresponding functionality is enabled.

## REST Controller

@RestController is used to create HTTP endpoints whose return values are typically written directly to the HTTP response body.

Example:

```java
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}
Configuration Properties

@ConfigurationProperties provides a structured way to bind external configuration into Java objects.

It is generally preferable to scattered configuration values when an application has a group of related properties.