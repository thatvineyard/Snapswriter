package com.thatvineyard.snapswriter.model.metre;

import java.util.Collection;

public interface LineMetreInterface extends MetreInterface {
    Collection<? extends WordMetreInterface> getMetreList();
}
