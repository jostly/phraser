package net.badgerclaw.phraser.domain;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public interface PhraseBuilder {

    default Phrase alliterativePhrase(List<String> adjectives, List<String> animals) {
        Random random = new Random();
        String adjective = oneOf(adjectives, random);
        String firstLetter = adjective.substring(0, 1);
        return new Phrase(adjective, oneOfStartingWith(animals, firstLetter, random));
    }

    default Phrase constrainedPhrase(List<String> adjectives, List<String> animals, String firstLetter) {
        Random random = new Random();
        return new Phrase(oneOfStartingWith(adjectives, firstLetter, random),
                oneOfStartingWith(animals, firstLetter, random));
    }

    default Phrase randomPhrase(List<String> adjectives, List<String> animals) {
        Random random = new Random();
        return new Phrase(oneOf(adjectives, random), oneOf(animals, random));
    }

    default String oneOf(List<String> words, Random random) {
        return words.get(random.nextInt(words.size()));
    }

    default String oneOfStartingWith(List<String> words, String firstLetter, Random random) {
        return oneOf(words.stream()
                .filter(word -> word.startsWith(firstLetter))
                .collect(Collectors.toList()),
                random);
    }

}
