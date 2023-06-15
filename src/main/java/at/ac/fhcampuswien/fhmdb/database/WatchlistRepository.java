package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.observer_pattern.Observable;
import at.ac.fhcampuswien.fhmdb.observer_pattern.Observer;
import com.j256.ormlite.dao.Dao;
import java.util.ArrayList;
import java.util.List;

public class WatchlistRepository implements Observable {
    private List<Observer> observers = new ArrayList<>();
    Dao<WatchlistMovieEntity, Long> dao;

    private static WatchlistRepository instance;

    private WatchlistRepository() throws DataBaseException {
        try {
            this.dao = DatabaseManager.getInstance().getWatchlistDao();
        } catch (Exception e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    public static WatchlistRepository getInstance() {
        if (instance == null) {
            try {
                instance = new WatchlistRepository();
            } catch (DataBaseException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public List<WatchlistMovieEntity> readWatchlist() throws DataBaseException {
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while reading watchlist");
        }
    }
    public void addToWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            // only add movie if it does not exist yet
            long count = dao.queryBuilder().where().eq("apiId", movie.getApiId()).countOf();
            if (count == 0) {
                dao.create(movie);
                notifyObservers("Movie successfully added to watchlist");
            } else {
                notifyObservers("Movie already on watchlist");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataBaseException("Error while adding to watchlist");
        }
    }

    public void removeFromWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            dao.delete(movie);
            notifyObservers("Movie successfully removed from watchlist");
        } catch (Exception e) {
            throw new DataBaseException("Error while removing from watchlist");
        }
    }

    public boolean isOnWatchlist(WatchlistMovieEntity movie) throws DataBaseException {
        try {
            return dao.queryForMatching(movie).size() > 0;
        } catch (Exception e) {
            throw new DataBaseException("Error while checking if movie is on watchlist");
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        if(!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String msg) {
        for(Observer observer : this.observers){
            observer.update(msg);
        }
    }
}
