# jmh-example
A jmh example - comparing micrometer and native prometheus performance

# Run

1. ```mvn clean verify```
2. ```java -jar target/benchmarks.jar```


# My Local BenchMark Result

```
Benchmark                 Mode  Cnt         Score        Error  Units
MyBenchmark.micrometer   thrpt   25   8356178.553 ±  68904.889  ops/s
MyBenchmark.prometheus   thrpt   25  19118595.932 ± 150137.336  ops/s
```