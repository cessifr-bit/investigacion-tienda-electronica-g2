package benchmark;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class EjecutarBenchmark {

    public static void main(String[] args) throws Exception {

        Options opciones = new OptionsBuilder()
                .include("benchmark.ConsultaBenchmark.*")
                .addProfiler("gc")
                .result("resultados-jmh.json")
                .resultFormat(ResultFormatType.JSON)
                .shouldFailOnError(true)
                .build();

        new Runner(opciones).run();
    }
}