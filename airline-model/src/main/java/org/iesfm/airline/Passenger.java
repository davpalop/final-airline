package org.iesfm.airline;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Passenger {

    private String nif;
    private int id_flight;
    private Email email;
    private String name;
    private String surname;
    private int seat;
    private List<Case> cases;

    @JsonCreator
    public Passenger (
            @JsonProperty("nif") String nif,
            @JsonProperty("id") int id_flight,
            @JsonProperty("email") Email email,
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname,
            @JsonProperty("seat") int seat,
            @JsonProperty("cases") List<Case> cases) {
        this.nif = nif;
        this.id_flight = id_flight;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.seat = seat;
        this.cases = cases;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public int getId_flight() {
        return id_flight;
    }

    public void setId_flight(int id_flight) {
        this.id_flight = id_flight;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id_flight == passenger.id_flight && seat == passenger.seat && Objects.equals(nif, passenger.nif) && Objects.equals(email, passenger.email) && Objects.equals(name, passenger.name) && Objects.equals(surname, passenger.surname) && Objects.equals(cases, passenger.cases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif, id_flight, email, name, surname, seat, cases);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "nif='" + nif + '\'' +
                ", id_flight=" + id_flight +
                ", email=" + email +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", seat=" + seat +
                ", cases=" + cases +
                '}';
    }
}
