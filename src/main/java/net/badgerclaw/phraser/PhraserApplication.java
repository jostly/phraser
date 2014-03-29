package net.badgerclaw.phraser;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.badgerclaw.phraser.domain.WordType;
import net.badgerclaw.phraser.health.WordsHealthCheck;
import net.badgerclaw.phraser.resources.PhraserResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class PhraserApplication extends Application<PhraserConfiguration> {

    public static void main(String[] args) throws Exception {
        new PhraserApplication().run(args);
    }

    @Override
    public String getName() {
        return "Phraser";
    }

    @Override
    public void initialize(Bootstrap<PhraserConfiguration> phraserConfigurationBootstrap) {

    }

    @Override
    public void run(PhraserConfiguration configuration, Environment environment) throws Exception {

        List<String> adjectives = readWords(configuration.getAdjectivesFile());
        List<String> animals = readWords(configuration.getAnimalsFile());
        final PhraserResource resource = new PhraserResource(
                adjectives,
                animals
        );
        environment.healthChecks().register("adjectives", new WordsHealthCheck(WordType.ADJECTIVE, adjectives));
        environment.healthChecks().register("animals", new WordsHealthCheck(WordType.ANIMAL, animals));
        environment.jersey().register(resource);
    }

    private List<String> readWords(String resource) {
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resource)));
        return br.lines()
                .map(String::trim)
                .filter(line -> line.length() > 1)
                .distinct()
                .collect(Collectors.toList());
    }
}
