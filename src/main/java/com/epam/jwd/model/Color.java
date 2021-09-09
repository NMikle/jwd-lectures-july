package com.epam.jwd.model;

public enum Color {
    RED("FF0000"),
    GREEN("00FF00"),
    BLUE("0000FF");

    private static final String COLOR_NOT_FOUND_MSG = "%s does not have representation in %s";

//    private static final Map<String, Color> cache = new HashMap<>();
//
//    static {
//        System.out.println("static start");
//        for (Color color : values()) {
//            cache.put(color.getRgbValue(), color);
//        }
//        System.out.println("static end");
//    }

    private final String rgbValue;

    Color(String rgbValue) {
        System.out.printf("Initializing %s Color constant with ordinal %s\n", name(), ordinal());
        this.rgbValue = rgbValue;
    }

    public String getRgbValue() {
        return rgbValue;
    }

    public static Color of(String rgbValue) {
//        final Color result = cache.get(rgbValue.toUpperCase(Locale.ROOT));
//        if (result == null) {
//            throw new IllegalArgumentException(String.format(COLOR_NOT_FOUND_MSG, rgbValue, Color.class.getSimpleName()));
//        }
//        return result;
        for (Color color : Color.values()) {
            if (color.rgbValue.equalsIgnoreCase(rgbValue)) {
                return color;
            }
        }
        throw new IllegalArgumentException(String.format(COLOR_NOT_FOUND_MSG, rgbValue, Color.class.getSimpleName()));
    }

    class Inner {}
    static class Nested {}
}
