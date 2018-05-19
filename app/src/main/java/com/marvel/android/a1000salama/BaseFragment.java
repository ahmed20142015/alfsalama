package com.marvel.android.a1000salama;

import android.support.v4.app.Fragment;

/**
 * Created by ahmed on 19/01/18.
 */

public class BaseFragment extends Fragment {
    /**
     * Could handle back press.
     * @return true if back press was handled
     */
    public boolean onBackPressed() {
        return true;
    }
}
