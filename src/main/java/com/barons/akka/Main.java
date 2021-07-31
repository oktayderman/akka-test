package com.barons.akka;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.*;
import akka.stream.javadsl.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Main {



    public static void main(String[] args) throws InterruptedException {

        ActorSystem system = ActorSystem.create();


        int bufferSize = 10;
        int elementsToProcess = 5;

        BoundedSourceQueue<Integer> sourceQueue =
                Source.<Integer>queue(1000000)
                        .map(x -> x * x).groupedWithin(3,Duration.ofMillis(100))
                        .to(Sink.foreach(x -> System.out.println("got: " + x)))
                        .run(system);

        List<Integer> fastElements = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11,12,13,14,15,16,17);

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
