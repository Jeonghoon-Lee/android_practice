package ca.jeonghoon.day5passingindent.model;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private int email;
    private int movieType;

    public Client() { }

    public Client(int id, int email, int movieType) {
        this.id = id;
        this.email = email;
        this.movieType = movieType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmail() {
        return email;
    }

    public void setEmail(int email) {
        this.email = email;
    }

    public int getMovieType() {
        return movieType;
    }

    public void setMovieType(int movieType) {
        this.movieType = movieType;
    }
}
