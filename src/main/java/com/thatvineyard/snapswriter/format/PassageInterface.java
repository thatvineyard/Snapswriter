package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public interface PassageInterface<T> {

    public void add(T phrase);
    public void append(PassageInterface<T> other);
    public Iterator<T> getPhrasesIterator();
    public int getNumberOfPhrases();
    public boolean containsSamePhraseAs(PassageInterface<T> other);
    public Collection<T> getPhrasesWhere(Predicate<T> predicate);
    public Collection<T> getPhrases();
}
