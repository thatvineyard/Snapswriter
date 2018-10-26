package com.thatvineyard.snapswriter.model.metre;

import java.util.Collection;

public interface PassageMetreInterface extends MetreInterface {
    Collection<? extends LineMetreInterface> getMetreList();

}
