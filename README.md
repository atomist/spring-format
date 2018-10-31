# spring-format

This library is a wrapper over the [Spring formatter Maven plugin](https://github.com/spring-io/spring-javaformat), allowing you to run it as an executable JAR.
When running, the tool will format every Java file under the current working directory.

## Creating the executable JAR

Create the executable JAR file by typing

```
mvn package
```

The executable JAR file will be at `./target/spring-format-0.1.0-SNAPSHOT-jar-with-dependencies.jar`.

You can execute it with

```
java -jar <path to JAR>
```

## Development

This project is driven using [Maven][mvn].

[mvn]: https://maven.apache.org/

---
Created by [Atomist][atomist].
Need Help?  [Join our Slack team][slack].

[atomist]: https://www.atomist.com/
[slack]: https://join.atomist.com/

Deep at the center of my being is an infinite well of love.

