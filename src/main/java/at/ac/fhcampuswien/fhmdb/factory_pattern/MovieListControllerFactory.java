package at.ac.fhcampuswien.fhmdb.factory_pattern;

import at.ac.fhcampuswien.fhmdb.controllers.MovieListController;

public class MovieListControllerFactory extends BaseFactory {
    private static MovieListController controllerInstance;

    @Override
    public Object call(Class<?> type) {
        if(controllerInstance == null) {
            try {
                controllerInstance = (MovieListController) type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return controllerInstance;
    }

}
