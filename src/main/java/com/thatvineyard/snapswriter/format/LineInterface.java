package com.thatvineyard.snapswriter.format;

import java.util.Collection;
import java.util.Iterator;

public interface LineInterface<T extends WordInterface> {

    Collection<T> getWords();
    void add(T word);
    void append(LineInterface<T> other);
    Iterator<T> getWordIterator();
}
