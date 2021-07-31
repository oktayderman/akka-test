package com.barons.akka;



import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class MainDcevm {


    public static void main(String args[]) throws InterruptedException {

        System.out.println(Arrays.asList("test","test").stream().collect(toMap(String::length,a->a,BinaryOperator.maxBy(comparing(String::hashCode)))));

        Stream.of(args).flatMap(s -> Stream.of(s.length()));
        Stream.of(args).map(s -> Stream.of(s.length()));
        while(true) {
            mainLoop();
            Thread.sleep(1000);
        }





    }


    public static <E> Stream<List<E>> of(List<E> list) {
        return IntStream.range(0, list.size())
                .mapToObj(start ->
                        IntStream.rangeClosed(start + 1, list.size())
                                .mapToObj(end -> list.subList(start, end)))
                .flatMap(x -> x);
    }


    private static void mainLoop() {
        System.out.println("Hello World3");
    }

   // HOTSWAP AGENT: 00:07:23.084 INFO (org.hotswap.agent.HotswapAgent) - Loading Hotswap agent {1.4.0} - unlimited runtime class redefinition.
   // HOTSWAP AGENT: 00:07:24.126 INFO (org.hotswap.agent.config.PluginRegistry) - Discovered plugins: [JdkPlugin, Hotswapper, WatchResources, ClassInitPlugin, AnonymousClassPatch, Hibernate, Hibernate3JPA, Hibernate3, Spring, Jersey1, Jersey2, Jetty, Tomcat, ZK, Logback, Log4j2, MyFaces, Mojarra, Omnifaces, ELResolver, WildFlyELResolver, OsgiEquinox, Owb, Proxy, WebObjects, Weld, JBossModules, ResteasyRegistry, Deltaspike, GlassFish, Vaadin, Wicket, CxfJAXRS, FreeMarker, Undertow, MyBatis]
   // Starting HotswapAgent '/myData/dev/jdks/dcevm-11.0.6+1/lib/hotswap/hotswap-agent.jar'


    public static class Data {
        private String name;
        private int value;

        private Data(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }
}


