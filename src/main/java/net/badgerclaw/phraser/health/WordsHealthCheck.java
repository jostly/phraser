package net.badgerclaw.phraser.health;

import com.codahale.metrics.health.HealthCheck;
import com.google.common.collect.Sets;
import net.badgerclaw.phraser.domain.WordType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WordsHealthCheck extends HealthCheck {

    private final static Set<String> alphabet = Sets.newHashSet("abcdefghijklmnopqrstuvwyz".split("")); // x intentionally left out

    private final WordType wordType;
    private final List<String> words;

    public WordsHealthCheck(WordType wordType, List<String> words) {
        this.wordType = wordType;
        this.words = words;
    }

    @Override
    protected Result check() throws Exception {
        Set<String> letters = words.stream()
                .map(word -> word.substring(0, 1))
                .collect(Collectors.toSet());

        boolean entireAlphabetInLetters = letters.containsAll(alphabet);
        boolean entireLettersInAlphabet = alphabet.containsAll(letters);
        if (entireAlphabetInLetters && entireLettersInAlphabet) {
            return Result.healthy();
        } else if (!entireAlphabetInLetters) {
            return Result.unhealthy("The " + wordType + " word list must cover the entire alphabet." +
                    "Missing: " + alphabet.stream().filter(a -> !letters.contains(a))
                    .sorted()
                    .collect(Collectors.toList()).toString());
        } else {
            return Result.unhealthy("The " + wordType + " word list contains words starting with an illegal letter: " +
                    letters.stream().filter(a -> !alphabet.contains(a))
                            .sorted()
                            .collect(Collectors.toList()).toString());
        }
    }
}
