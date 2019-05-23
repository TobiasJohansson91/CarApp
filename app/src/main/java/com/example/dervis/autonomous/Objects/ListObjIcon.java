package com.example.dervis.autonomous.Objects;

import android.graphics.drawable.Drawable;

public class ListObjIcon extends ListObj {
    public transient final Drawable icon;
    public final int iconId;

    public ListObjIcon(int id, String title, String description, Drawable icon, int iconId) {
        super(id, title, description);
        this.icon = icon;
        this.iconId = iconId;
    }
}
