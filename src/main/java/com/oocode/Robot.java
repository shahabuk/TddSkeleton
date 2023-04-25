package com.oocode;

public interface Robot {
    void moveOnto(Integer from, Integer to);

    void moveOver(Integer from, Integer to);

    void pileOnto(Integer from, Integer to);

    void pileOver(Integer from, Integer to);
}
