package at.ac.fhcampuswien.fhmdb.api;

import at.ac.fhcampuswien.fhmdb.models.Genre;

public class MovieAPIRequestBuilder {
    public static final String DELIMITER = "&";
    private final StringBuilder url;

    public MovieAPIRequestBuilder(String endpoint) {
        this.url = new StringBuilder(endpoint);
    }

    public MovieAPIRequestBuilder query(String query) {
        if (query != null && !query.isEmpty()) {
            url.append(getDelimiter()).append("query=").append(query);
        }
        return this;
    }

    public MovieAPIRequestBuilder genre(Genre genre) {
        if (genre != null) {
            url.append(getDelimiter()).append("genre=").append(genre);
        }
        return this;
    }

    public MovieAPIRequestBuilder releaseYear(String releaseYear) {
        if (releaseYear != null) {
            url.append(getDelimiter()).append("releaseYear=").append(releaseYear);
        }
        return this;
    }

    public MovieAPIRequestBuilder ratingFrom(String ratingFrom) {
        if (ratingFrom != null) {
            url.append(getDelimiter()).append("ratingFrom=").append(ratingFrom);
        }
        return this;
    }

    private String getDelimiter() {
        if(url.indexOf("?") == -1) {
            return "?";
        } else {
            return DELIMITER;
        }
    }

    public String build() {
        return url.toString();
    }
}
