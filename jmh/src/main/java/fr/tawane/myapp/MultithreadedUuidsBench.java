package fr.tawane.myapp;

import fr.tawane.myapp.app.MultihtreadedUuids;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
public class MultithreadedUuidsBench {

	MultihtreadedUuids sut = new MultihtreadedUuids();

	@Fork(1)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 1)
	@Benchmark
	public List<UUID> withRandom() {
		return sut.fromRandom();
	}

	@Fork(1)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@BenchmarkMode(Mode.AverageTime)
	@Warmup(iterations = 1)
	@Benchmark
	public List<UUID> withUrandom() {
		return sut.fromUrandom();
	}
}