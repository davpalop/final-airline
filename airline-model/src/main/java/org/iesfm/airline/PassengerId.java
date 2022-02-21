package org.iesfm.airline;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class PassengerId implements Serializable {

    @Id
    private String nif;
    private Integer flightId;

    @JsonCreator
    public PassengerId(
            @JsonProperty("nif") String nif,
            @JsonProperty("flightId") Integer flightId){
        this.nif=nif;
        this.flightId =flightId;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }
}
