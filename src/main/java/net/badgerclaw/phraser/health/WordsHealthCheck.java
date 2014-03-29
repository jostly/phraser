package net.badgerclaw.phraser.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.collect.Sets;
import net.badgerclaw.phraser.domain.WordType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordsHealthCheck extends HealthCheck {

    private final static Set<String> alphabet = Sets.newHashSet("abcdefghijklmnopqrstuvwxyz".split(""));

    private final WordType wordType;
    private final List<String> words;

    public WordsHealthCheck(WordType wordType, List<String> words) {
        this.wordType = wordType;
        this.words = words;
    }

    @Override
    protected Result check() throws Exception {
        if (words.stream()
                .map(word -> word.substring(0, 1))
                .collect(Collectors.toSet()).containsAll(alphabet)) {
            return Result.healthy();
        } else {
            return Result.unhealthy("The " + wordType + " word list must cover the entire alphabet");
        }
    }
}
