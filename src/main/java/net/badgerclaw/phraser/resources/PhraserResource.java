package net.badgerclaw.phraser.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PhraserResource {

    private final String[] adjectives;
    private final String[] animals;

    public PhraserResource(String[] adjectives, String[] animals) {
        this.adjectives = adjectives;
        this.animals = animals;
    }

    @GET
    @Timed
    public String randomPhrase() {
        Random random = new Random();
        return oneOf(adjectives, random) + " " + oneOf(animals, random);
    }

    private String oneOf(String[] words, Random random) {
        return words[random.nextInt(words.length)];
    }
}
