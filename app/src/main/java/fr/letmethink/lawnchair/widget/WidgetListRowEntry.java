package fr.letmethink.lawnchair.widget;

import java.util.ArrayList;

import fr.letmethink.lawnchair.model.PackageItemInfo;

public class WidgetListRowEntry {
    public final PackageItemInfo pkgItem;
    public String titleSectionName;
    public final ArrayList widgets;

    public WidgetListRowEntry(PackageItemInfo packageItemInfo, ArrayList arrayList) {
        this.pkgItem = packageItemInfo;
        this.widgets = arrayList;
    }
}