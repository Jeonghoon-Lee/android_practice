package ca.jeonghoon.day5passingindent.model;

import java.io.Serializable;

public class Client implements Serializable {
    private int id;
    private String email;
    private String movieType;

    public Client() { }

    public Client(int id, String email, String movieType) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", movieType='" + movieType + '\'' +
                '}';
    }
}
