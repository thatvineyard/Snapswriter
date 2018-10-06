package com.thatvineyard.snapswriter.metre;

import java.util.Collection;

public interface PassageMetreInterface extends MetreInterface {
    Collection<? extends LineMetreInterface> getMetreList();

}
