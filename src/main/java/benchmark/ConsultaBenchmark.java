package benchmark;

import consulta.ConsultaJPA;
import consulta.ConsultaNativa;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 30)
@Fork(1)
@State(Scope.Benchmark)
public class ConsultaBenchmark {

    @Param({"100", "500", "1000"})
    public int volumen;

    @Setup(Level.Trial)
    public void preparar() {
        ConsultaJPA.iniciarBenchmark();
    }

    @Benchmark
    public int sqlNativo() throws Exception {
        return ConsultaNativa.consultarProductos(volumen);
    }

    @Benchmark
    public int jpaHibernate() {
        return ConsultaJPA.consultarProductos(volumen);
    }

    @TearDown(Level.Trial)
    public void finalizar() {
        ConsultaJPA.cerrarBenchmark();
    }
}