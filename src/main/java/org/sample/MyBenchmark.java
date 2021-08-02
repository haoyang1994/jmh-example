package org.sample;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Summary;
import org.openjdk.jmh.annotations.*;

import java.util.Random;

@State(Scope.Benchmark)
public class MyBenchmark {

    private MeterRegistry meterRegistry;

    private String[] tagKVs = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u"
        ,"v","w","x","y","z"};

    private Summary summary = Summary.build("meter_id2","bench mark test for prometheus").labelNames("tagKey").register(CollectorRegistry.defaultRegistry);

    private Random random;

    private static final String METER_ID = "meter.id";

    @Setup(Level.Trial)
    public void setUp(){
        meterRegistry = SampleRegistries.prometheus();
        random = new Random(System.currentTimeMillis());
    }

    @Benchmark
    public void micrometer() {
        meterRegistry.summary(METER_ID,"tagKey",tagKVs[random()]).record(random.nextDouble());
    }

    @Benchmark
    public void prometheus() {
        summary.labels(tagKVs[random()]).observe(random.nextDouble());
    }

    @TearDown
    public void tearDown(){
        System.out.println("final meters size : " + meterRegistry.getMeters().size());
    }

    private int random(){
        return random.nextInt(tagKVs.length);
    }


}
