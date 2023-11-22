package co.com.user.usecase.utils;

public class ValueUpdaterUtil {

    private ValueUpdaterUtil() {}

    public static <T> T returnValueToUpdate(T valueActual, T valueToUpdate) {
        return valueToUpdate != null ? valueToUpdate : valueActual;
    }
}
