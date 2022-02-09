package org.iesfm.airline;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class PassengerId implements Serializable {

    private String nif;
    private int id;

    public PassengerId(String nif, int id) {
        this.nif = nif;
        this.id = id;
    }

//    @JsonCreator
//    public PassengerId(
//            @JsonProperty("nif") String nif,
//            @JsonProperty("id") int id){
//        this.nif=nif;
//        this.id=id;
//    }


}
