package net.badgerclaw.phraser;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class PhraserConfiguration extends Configuration {
    @NotEmpty
    private String adjectivesFile;

    @NotEmpty
    private String animalsFile;

    @JsonProperty
    public String getAdjectivesFile() {
        return adjectivesFile;
    }

    @JsonProperty
    public void setAdjectivesFile(String adjectivesFile) {
        this.adjectivesFile = adjectivesFile;
    }

    @JsonProperty
    public String getAnimalsFile() {
        return animalsFile;
    }

    @JsonProperty
    public void setAnimalsFile(String animalsFile) {
        this.animalsFile = animalsFile;
    }
}
