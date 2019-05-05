package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

class CollapsingToolbarLayout extends View {
    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
