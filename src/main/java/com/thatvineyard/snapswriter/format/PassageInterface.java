package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public interface PassageInterface<T> {

    void add(T phrase);
    void append(PassageInterface<T> other);
    Iterator<T> getPhrasesIterator();
    int getNumberOfPhrases();
    boolean containsSamePhraseAs(PassageInterface<T> other);
    Collection<T> getPhrasesWhere(Predicate<T> predicate);
    Collection<T> getPhrases();
}
