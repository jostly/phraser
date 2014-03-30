package net.badgerclaw.phraser.views;

import io.dropwizard.views.View;
import net.badgerclaw.phraser.domain.Phrase;

public class PhraseView extends View {
    private final Phrase phrase;

    public PhraseView(Phrase phrase) {
        super("phrase.mustache");
        this.phrase = phrase;
    }

    public Phrase getPhrase() {
        return phrase;
    }
}
