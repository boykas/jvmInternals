# ThreadPool
The custom thread pool implementation which executes each submitted task using one of the pooled thread was created initially.

# MicroBenchmark (JMH)
- Find prime numbers in the range of 4 to 10000 via loop
- Find prime numbers in the range of 4 to 10000 via stream
- Find prime numbers in the range of 4 to 10000 via  parrallel stream
- Method throws the exception without getting stack trace
- Method throws the exception with getting stack trace

|Benchmark                                                |   Score    | Error  |   Units |
|---------------------------------------------------------|------------|--------|---------|
|testFindingThePrimeNumbersViaLoop                        |   2.292 ±  | 0.339  |  ops/ms |
|testFindingThePrimeNumbersViaParralelStream              |   0.121 ±  | 0.022  |  ops/ms |
|testFindingThePrimeNumbersViaStream                      |   0.882 ±  | 0.045  |  ops/ms |
|testThrowsTheCustomExceptionWithOutStackTrace            |   739.181 ±| 109.759 |  ops/ms|
|testThrowsTheCustomExceptionWithStackTrace               |   35.842 ± | 3.453  |  ops/ms |


# Dynamic proxy
The implementation on Dynamy proxy object which substitute the method parameter (${variable}) if the system variable "port" is set up

# Dynamic class loading
Upload updated class each time in a loop. 


