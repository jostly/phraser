package net.badgerclaw.phraser.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PhraserResource {

    private final List<String> adjectives;
    private final List<String> animals;

    public PhraserResource(List<String> adjectives, List<String> animals) {
        this.adjectives = adjectives;
        this.animals = animals;
    }

    @GET
    @Path("/random")
    @Timed
    public String randomPhrase() {
        Random random = new Random();
        return oneOf(adjectives, random) + " " + oneOf(animals, random);
    }

    @GET
    @Path("/{letter: [a-z]}")
    @Timed
    public String constrainedPhrase(@PathParam("letter") String firstLetter) {
        Random random = new Random();
        return oneOfStartingWith(adjectives, firstLetter, random) + " " +
                oneOfStartingWith(animals, firstLetter, random);
    }

    @GET
    @Timed
    public String alliterativePhrase() {
        Random random = new Random();
        String adjective = oneOf(adjectives, random);
        String firstLetter = adjective.substring(0, 1);
        return adjective + " " + oneOfStartingWith(animals, firstLetter, random);
    }

    private String oneOf(List<String> words, Random random) {
        return words.get(random.nextInt(words.size()));
    }

    private String oneOfStartingWith(List<String> words, String firstLetter, Random random) {
        return oneOf(words.stream()
                .filter(word -> word.startsWith(firstLetter))
                .collect(Collectors.toList()),
                random);
    }
}
