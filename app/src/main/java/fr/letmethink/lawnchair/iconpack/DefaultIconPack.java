package fr.letmethink.lawnchair.iconpack;

import android.graphics.drawable.Drawable;

import fr.letmethink.lawnchair.compat.LauncherActivityInfoCompat;

public class DefaultIconPack extends IconPack {

    public DefaultIconPack() {
        super(null, null, null, null, null, null, 1f, null);
    }

    @Override
    public Drawable getIcon(LauncherActivityInfoCompat info) {
        return info.getIcon(0);
    }

    @Override
    public String getPackageName() {
        return "";
    }
}
