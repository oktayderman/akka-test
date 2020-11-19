package org.kunicki.akka_streams_java8.importer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.event.LoggingAdapter;
import akka.stream.*;
import akka.stream.javadsl.Source;
import akka.stream.javadsl.StreamConverters;
import akka.util.ByteString;
import scala.concurrent.ExecutionContextExecutor;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.CompletionStage;

public class Main {





   /* private Flow<File, Reading, NotUsed> parseFile() {
        return Flow.of(File.class).flatMapConcat(file -> {
            GZIPInputStream inputStream = new GZIPInputStream(new FileInputStream(file));
            return StreamConverters.fromInputStream(() -> inputStream)
                    .via(lineDelimiter)
                    .drop(linesToSkip)
                    .map(ByteString::utf8String)
                    .mapAsync(nonIOParallelism, this::parseLine);
        });
    }*/
}
