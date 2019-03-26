# MicroBenchmark (JMH)
- Find prime numbers in the range of 4 to 10000 via loop
- Find prime numbers in the range of 4 to 10000 via stream
- Find prime numbers in the range of 4 to 10000 via  parrallel stream

Benchmark                                                   Mode  Cnt    Score     Error   Units
MyBenchmark.testFindingThePrimeNumbersViaLoop              thrpt    5    2.292 ±   0.339  ops/ms
MyBenchmark.testFindingThePrimeNumbersViaParralelStream    thrpt    5    0.121 ±   0.022  ops/ms
MyBenchmark.testFindingThePrimeNumbersViaStream            thrpt    5    0.882 ±   0.045  ops/ms
MyBenchmark.testThrowsTheCustomExceptionWithOutStackTrace  thrpt    5  739.181 ± 109.759  ops/ms
MyBenchmark.testThrowsTheCustomExceptionWithStackTrace     thrpt    5   35.842 ±   3.453  ops/ms


# Dynamic proxy
The implementation on Dynamy proxy object which substitute the method parameter (${variable}) if the system variable "port" is set up

# Dymamical reload the class
Upload updated class each time in a loop. 


