package com.thatvineyard.snapswriter.metre;

import java.util.Collection;
import java.util.Iterator;

public interface LineMetreInterface extends MetreInterface {
    Collection<? extends WordMetreInterface> getMetreList();
}
