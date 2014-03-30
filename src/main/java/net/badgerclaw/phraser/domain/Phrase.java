package net.badgerclaw.phraser.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Phrase {
    final String adjective;
    final String animal;

    @JsonCreator
    public Phrase(@JsonProperty("adjective") String adjective, @JsonProperty("animal") String animal) {
        this.adjective = adjective;
        this.animal = animal;
    }

    @JsonProperty("adjective")
    public String getAdjective() {
        return adjective;
    }

    @JsonProperty("animal")
    public String getAnimal() {
        return animal;
    }

    @Override
    public String toString() {
        return adjective + " " + animal;
    }
}
