package at.ac.fhcampuswien.fhmdb.factory_pattern;

import javafx.util.Callback;

public class BaseFactory implements Callback<Class<?>, Object> {
    @Override
    public Object call(Class<?> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
