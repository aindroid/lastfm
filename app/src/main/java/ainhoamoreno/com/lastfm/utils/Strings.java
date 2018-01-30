package ainhoamoreno.com.lastfm.utils;

public interface Strings {

    static boolean isEmpty(String text) {
        return text == null || "".equals(text.trim());
    }
}
