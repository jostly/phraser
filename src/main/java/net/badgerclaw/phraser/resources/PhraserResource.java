package net.badgerclaw.phraser.resources;

import com.codahale.metrics.annotation.Timed;
import net.badgerclaw.phraser.domain.Phrase;
import net.badgerclaw.phraser.domain.PhraseBuilder;
import net.badgerclaw.phraser.views.PhraseView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class PhraserResource implements PhraseBuilder {
    private final List<String> adjectives;
    private final List<String> animals;

    public PhraserResource(List<String> adjectives, List<String> animals) {
        this.adjectives = adjectives;
        this.animals = animals;
    }

    @GET
    @Path("/random")
    @Timed
    public String randomPhraseAsText() {
        return randomPhrase(adjectives, animals).toString();
    }

    @GET
    @Path("/{letter: [a-wy-z]}")
    @Timed
    public String constrainedPhraseAsText(@PathParam("letter") String firstLetter) {
        return constrainedPhrase(adjectives, animals, firstLetter).toString();
    }

    @GET
    @Timed
    public String alliterativePhraseAsText() {
        return alliterativePhrase(adjectives, animals).toString();
    }

    @GET
    @Path("/random")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Phrase randomPhraseAsJson() {
        return randomPhrase(adjectives, animals);
    }

    @GET
    @Path("/{letter: [a-wy-z]}")
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Phrase constrainedPhraseAsJson(@PathParam("letter") String firstLetter) {
        return constrainedPhrase(adjectives, animals, firstLetter);
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Phrase alliterativePhraseAsJson() {
        return alliterativePhrase(adjectives, animals);
    }

    @GET
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public PhraseView alliterativePhraseAsHtml() {
        return new PhraseView(alliterativePhrase(adjectives, animals));
    }

    @GET
    @Path("/random")
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public PhraseView randomPhraseAsHtml() {
        return new PhraseView(randomPhrase(adjectives, animals));
    }

    @GET
    @Path("/{letter: [a-wy-z]}")
    @Timed
    @Produces(MediaType.TEXT_HTML)
    public PhraseView constrainedPhraseAsHtml(@PathParam("letter") String firstLetter) {
        return new PhraseView(constrainedPhrase(adjectives, animals, firstLetter));
    }
}
