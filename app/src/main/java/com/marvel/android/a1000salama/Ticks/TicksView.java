package com.marvel.android.a1000salama.Ticks;

import java.util.ArrayList;

import Model.Tick;

/**
 * Created by ahmedpc on 25/5/2018.
 */

public interface TicksView {
    void showLoader();
    void hideLoader();
    void setTicksList(ArrayList<Tick> ticksList);
    void noOldTicks();
    void showError();
}
