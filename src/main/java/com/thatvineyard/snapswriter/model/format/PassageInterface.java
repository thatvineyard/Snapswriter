package com.thatvineyard.snapswriter.model.format;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;

public interface PassageInterface<T extends  LineInterface> {

    void add(T phrase);
    void append(PassageInterface<T> other);
    Collection<T> getLines();
    Iterator<T> getLineIterator();
    int getNumberOfLines();
    boolean containsSamePhraseAs(PassageInterface<T> other);
    Collection<T> getPhrasesWhere(Predicate<T> predicate);
}
