/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rybalchenko;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@BenchmarkMode(Mode.All)
@Fork(value = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MyBenchmark {


    @State(Scope.Thread)
    public static class MyState {
        public TextService textService = new TextService();
    }

    @Benchmark
    public void testFindingThePrimeNumbersViaLoop() {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 4; i < 10000; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeNumbers.add(i);
            }
        }
    }

    @Benchmark
    public void testFindingThePrimeNumbersViaStream() {
        List<Integer> collect = IntStream.rangeClosed(4, 10000)
                .filter(el -> IntStream.rangeClosed(2, (int) Math.sqrt(el))
                        .noneMatch(sqrt -> el % sqrt == 0)).boxed().collect(Collectors.toList());
    }

    @Benchmark
    public void testFindingThePrimeNumbersViaParralelStream() {
        List<Integer> collect = IntStream.rangeClosed(4, 10000).parallel()
                .filter(el -> IntStream.rangeClosed(2, (int) Math.sqrt(el)).parallel()
                        .noneMatch(sqrt -> el % sqrt == 0)).boxed().collect(Collectors.toList());
    }


    @Benchmark
    public void testThrowsTheCustomExceptionWithOutStackTrace(MyState myState) {
        try {
            myState.textService.exception(null);
        }catch (CustomException ex){
            ex.printStackTrace();
        }
    }

    @Benchmark
    public void testThrowsTheCustomExceptionWithStackTrace(MyState myState) {
        try {
            myState.textService.exception(null);
        }catch (CustomException ex){
            System.out.println("nothing");
        }
    }

}
