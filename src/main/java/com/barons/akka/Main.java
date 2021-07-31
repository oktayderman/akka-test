package com.barons.akka;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.japi.function.Function;
import akka.stream.*;
import akka.stream.javadsl.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class Main {
    public static CompletionStage<Integer> eventHandler(Integer in) throws InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-Processing event number " + in + "...");
            return in;
        });
    }

    public static void main(String[] args) throws InterruptedException {

        ActorSystem system = ActorSystem.create();


        final Source<Integer, NotUsed> events =
                Source.fromIterator(() -> Stream.iterate(1, i -> i + 1).iterator()).limit(10);


        events
                .mapAsync(3, Main::eventHandler)
                .map(in -> "`mapSync` emitted event number ")
                .runWith(Sink.foreach(System.out::println), system);

        int bufferSize = 10;
        int elementsToProcess = 5;
        CompletionStage stage = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {

            }
        });
        final CompletableFuture<Integer> futureInt = new CompletableFuture<>();

        BoundedSourceQueue<Integer> sourceQueue =
                Source.<Integer>queue(1000000).groupedWithin(6, Duration.ofMillis(100)).flatMapConcat(a -> Source.from(a).grouped(2))
                        .mapAsyncUnordered(3, a -> {
                            Thread.sleep(2000);
                            System.out.println(a);
                            return CompletableFuture.completedFuture(null);
                        }).to(Sink.ignore())
                        .run(system);

        List<Integer> fastElements = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);

        fastElements.stream()
                .forEach(
                        x -> {
                            QueueOfferResult result = sourceQueue.offer(x);
                            if (result == QueueOfferResult.enqueued()) {
                                System.out.println("enqueued " + x);
                            } else if (result == QueueOfferResult.dropped()) {
                                System.out.println("dropped " + x);
                            } else if (result instanceof QueueOfferResult.Failure) {
                                QueueOfferResult.Failure failure = (QueueOfferResult.Failure) result;
                                System.out.println("Offer failed " + failure.cause().getMessage());
                            } else if (result instanceof QueueOfferResult.QueueClosed$) {
                                System.out.println("Bounded Source Queue closed");
                            }
                            try {
                                Thread.sleep(1);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        });


        Thread.sleep(30000);
    }
}
