package com.barons.akka;
import akka.*;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.event.LoggingAdapter;
import akka.japi.function.Procedure;
import akka.stream.*;
import akka.stream.javadsl.*;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.FiniteDuration;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Source<Integer,NotUsed> source = Source.range(1, 100);
        source.runForeach(new Procedure<Integer>() {
            @Override
            public void apply(Integer param) throws Exception, Exception {
                System.out.println(param);
            }
        }, new Materializer() {
            @Override
            public Cancellable scheduleAtFixedRate(FiniteDuration initialDelay, FiniteDuration interval, Runnable task) {
                return null;
            }

            @Override
            public ActorRef actorOf(MaterializationContext context, Props props) {
                return null;
            }

            @Override
            public void shutdown() {

            }

            @Override
            public Cancellable scheduleOnce(FiniteDuration delay, Runnable task) {
                return null;
            }

            @Override
            public LoggingAdapter logger() {
                return null;
            }

            @Override
            public Cancellable schedulePeriodically(FiniteDuration initialDelay, FiniteDuration interval, Runnable task) {
                return null;
            }

            @Override
            public Materializer withNamePrefix(String name) {
                return null;
            }

            @Override
            public <Mat> Mat materialize(Graph<ClosedShape, Mat> runnable, Attributes defaultAttributes) {
                return null;
            }

            @Override
            public <Mat> Mat materialize(Graph<ClosedShape, Mat> runnable) {
                return null;
            }

            @Override
            public ActorMaterializerSettings settings() {
                return null;
            }

            @Override
            public ExecutionContextExecutor executionContext() {
                return null;
            }

            @Override
            public Cancellable scheduleWithFixedDelay(FiniteDuration initialDelay, FiniteDuration delay, Runnable task) {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public ActorRef supervisor() {
                return null;
            }

            @Override
            public ActorSystem system() {
                return null;
            }
        });

        Thread.sleep(3000);
    }
}
