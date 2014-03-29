package net.badgerclaw.phraser;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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
        final PhraserResource resource = new PhraserResource(
                readWords(configuration.getAdjectivesFile()),
                readWords(configuration.getAnimalsFile())
        );
        environment.jersey().register(resource);
    }

    private String[] readWords(String resource) {
        BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(resource)));
        return br.lines()
                .map(String::trim)
                .filter(line -> line.length() > 1)
                .collect(Collectors.toSet())
                .toArray(new String[] {});
    }
}
