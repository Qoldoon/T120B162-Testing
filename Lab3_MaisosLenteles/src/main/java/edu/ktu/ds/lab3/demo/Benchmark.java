package edu.ktu.ds.lab3.demo;

import edu.ktu.ds.lab3.utils.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(time = 1, timeUnit = TimeUnit.SECONDS)
public class Benchmark {

    @State(Scope.Benchmark)
    public static class FullMap {

        List<String> ids;
        List<Car> cars;
        HashMap<String, Car> carsMap;
        HashMapOa<String, Car> carsMapOa;
        int random;
        @Setup(Level.Iteration)
        public void generateIdsAndCars(BenchmarkParams params) {
            ids = Benchmark.generateIds(Integer.parseInt(params.getParam("elementCount")));
            cars = Benchmark.generateCars(Integer.parseInt(params.getParam("elementCount")));
            random = new Random().nextInt(Integer.parseInt(params.getParam("elementCount")));
        }

        @Setup(Level.Invocation)
        public void fillCarMap(BenchmarkParams params) {
            carsMap = new HashMap<>(HashManager.HashType.DIVISION);
            carsMapOa = new HashMapOa<>(HashManager.HashType.DIVISION);
            putMappings(ids, cars, carsMap);
            putMappings(ids, cars, carsMapOa);
        }
    }

    @Param({"10000", "20000", "40000", "80000"})
    public int elementCount;

    List<String> ids;
    List<Car> cars;

    @Setup(Level.Iteration)
    public void generateIdsAndCars() {
        ids = generateIds(elementCount);
        cars = generateCars(elementCount);
    }

    static List<String> generateIds(int count) {
        return new ArrayList<>(CarsGenerator.generateShuffleIds(count));
    }

    static List<Car> generateCars(int count) {
        return new ArrayList<>(CarsGenerator.generateShuffleCars(count));
    }

    /*@org.openjdk.jmh.annotations.Benchmark
    public Map<String, Car> putMap() {
        Map<String, Car> carsMap = new HashMap<>(HashManager.HashType.DIVISION);
        putMappings(ids, cars, carsMap);
        return carsMap;
    }

    @org.openjdk.jmh.annotations.Benchmark
    public void removeCarMap(FullMap fullMap) {
        fullMap.ids.forEach(id -> fullMap.carsMap.remove(id));
    }

    */
    @org.openjdk.jmh.annotations.Benchmark
    public void getCarMap(FullMap fullMap) {
        fullMap.carsMap.get(fullMap.ids.get(fullMap.random));
    }
    @org.openjdk.jmh.annotations.Benchmark
    public void getCarMapOa(FullMap fullMap) {
        fullMap.carsMapOa.get(fullMap.ids.get(fullMap.random));
    }

    public static void putMappings(List<String> ids, List<Car> cars, Map<String, Car> carsMap) {
        for (int i = 0; i < cars.size(); i++) {
            carsMap.put(ids.get(i), cars.get(i));
        }
    }
    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(Benchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}
