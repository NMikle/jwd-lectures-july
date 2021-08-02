package com.epam.jwd.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Application {

    private static final Logger LOG = LogManager.getLogger(Application.class);
    private static final String PATH_TO_AWESOME_FILE = "/resources/awesome.file";

    public static void main(String[] args) {
//        char a = '\u0041';
//        char a = 65;
//        char a = 'A';
//        System.out.println(a);

//        byte tinyValue = 3;
//        short biggerValue = tinyValue;
//        int integerValue = biggerValue;
//        long biggestValue = integerValue;
//        integerValue = (int) biggestValue;
//        biggerValue = (short) integerValue;
//        tinyValue = (byte) biggerValue;

//        System.out.println(Integer.MAX_VALUE + 1 == Integer.MIN_VALUE);

//        int a = 0xff; // 255
//        System.out.println(a);

//        boolean a = false;
//        System.out.println(Boolean.valueOf("hello world"));
//        System.out.println(Boolean.parseBoolean("hey"));

//        final Integer x = Integer.valueOf("-129");
//        final Integer y = Integer.valueOf("-129");
//        System.out.println(x == y); //todo: Byte, Short, Integer, Long have [-128, 127] cache
//        final int y = Integer.parseInt("123");
//        System.out.println(x);

//        String a = "orange fox";//literal pool
//        String b = "Hello " + a;//concatenated -- no pool
//        System.out.println(b);
//        String c = "Hello orange fox"; //literal pool
//        String d = "orange fox"; //literal pool same as a
//        System.out.println(a == d); //true: both from literal pool
//        System.out.println(c == b); //false: b is not from literal pool
//        System.out.println(c.equals(b)); //true: equals checks inner state
//        b = b.intern();
//        System.out.println(c == b); //true: b.intern() returns reference from literal pool
//        final String str = "";
//        if (!str.isEmpty()) {
//            ///
//        }

//        final String inputStr = "Hello world. Some string here";
//        final String[] tokens = inputStr.split(" ");
//        for (int i = 0; i < tokens.length; i++) {
//            System.out.println(tokens[i]);
//        }

//        final StringBuilder builder = new StringBuilder("Hello");
//        builder.append(" ").append("world").append("!");
//        final String result = builder.toString();
//        System.out.println(result);

//        if (someTestMethod()) {
//            System.out.println("true");
//        } else if (anotherTestMethod()) {
//            System.out.println("middle");
//        } else {
//            System.out.println("false");
//        }
//
//        if (someTestMethod()) {
//            System.out.println("should not be printed");
//        } else if (someTestMethod()) {
//            System.out.println("should not be printed");
//        }

        System.out.println(didICatchAFish("sharks s"));


    }

    private static String didICatchAFish(String catchResult) {
        String result;
        switch (catchResult) {
            case "fish":
                result = "hooray, got a fish!";
                break;
            case "sharks":
            case "shark":
                result = "wow, it's a shark";
                break;
            default:
                result = "so bad, no fish here";
                break;
        }
        return result;
    }

//    private static String didICatchAFish(String param) {
//        return "fish".equals(param)
//                ? "hooray, got a fish!"
//                : "shark".equals(param) ? "wow, it's a shark" : "so bad, no fish here";
//    }
//
//    private static boolean someTestMethod() {
//        return false;
//    }
//
//    private static boolean anotherTestMethod() {
//        return true;
//    }

}
