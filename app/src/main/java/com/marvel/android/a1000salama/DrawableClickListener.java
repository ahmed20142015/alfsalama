package com.marvel.android.a1000salama;

/**
 * Created by ahmed on 10/02/18.
 */

public interface DrawableClickListener {

    public static enum DrawablePosition { TOP, BOTTOM, LEFT, RIGHT };
    public void onClick(DrawablePosition target);
}