package at.ac.fhcampuswien.fhmdb.factory_pattern;

import at.ac.fhcampuswien.fhmdb.controllers.WatchlistController;

public class WatchlistControllerFactory extends BaseFactory {
    private static WatchlistController controllerInstance;

    @Override
    public Object call(Class<?> type) {
        if(controllerInstance == null) {
            try {
                controllerInstance = (WatchlistController) type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return controllerInstance;
    }

}
