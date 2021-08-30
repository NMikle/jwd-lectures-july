package com.epam.jwd.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        final String input = "A stream pipeline, like the \"widgets\" example above, can be viewed as a query on the stream source.";
        final String[] words = input.split(" ");
        final Stream<String> wordsStream = Arrays.stream(words).onClose(() -> System.out.println("stream closed"));
        final List<String> shortWords = wordsStream.filter(str -> str.length() < 5).collect(Collectors.toList());
        wordsStream.close();
        System.out.println("Hello");
        final List<String> longWords = wordsStream.filter(str -> str.length() >= 5).collect(Collectors.toList());
        System.out.println(shortWords);
        System.out.println(longWords);
    }

}
