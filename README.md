# uuid-urandom-benchmark

Microbenchmark used to compare /dev/random and /dev/urandom for UUIDs

The benchmark creates 1 million UUIDs in parallel with either /dev/random or /dev/urandom.

If the blocking from /dev/random is really altering performance, we should see a notable difference.

Results on my ryzen 5 3500U 5.16.14-1-MANJARO :

| Benchmark                                       | Mode | Cnt | Score    | Error    | Units |
|-------------------------------------------------|------|-----|----------|----------|-------|
| MultithreadedUuidsBench.withRandom              | avgt | 5   | 4816.977 | ± 64.741 | ms/op |
| MultithreadedUuidsBench.withUrandom (8 threads) | avgt | 5   | 4833.015 | ± 49.046 | ms/op |

My interpretation : There is no difference with few threads.  
Maybe with 64 threads there are, but I doubt.
