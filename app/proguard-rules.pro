-keep,allowshrinking,allowoptimization class fr.letmethink.lawnchair.** {
  *;
}

-keep class fr.letmethink.lawnchair.BaseRecyclerViewFastScrollBar {
  public void setThumbWidth(int);
  public int getThumbWidth();
  public void setTrackWidth(int);
  public int getTrackWidth();
}

-keep class fr.letmethink.lawnchair.BaseRecyclerViewFastScrollPopup {
  public void setAlpha(float);
  public float getAlpha();
}

-keep class fr.letmethink.lawnchair.ButtonDropTarget {
  public int getTextColor();
}

-keep class fr.letmethink.lawnchair.CellLayout {
  public float getBackgroundAlpha();
  public void setBackgroundAlpha(float);
}

-keep class fr.letmethink.lawnchair.CellLayout$LayoutParams {
  public void setWidth(int);
  public int getWidth();
  public void setHeight(int);
  public int getHeight();
  public void setX(int);
  public int getX();
  public void setY(int);
  public int getY();
}

-keep class fr.letmethink.lawnchair.dragndrop.DragLayer$LayoutParams {
  public void setWidth(int);
  public int getWidth();
  public void setHeight(int);
  public int getHeight();
  public void setX(int);
  public int getX();
  public void setY(int);
  public int getY();
}

-keep class fr.letmethink.lawnchair.FastBitmapDrawable {
  public void setDesaturation(float);
  public float getDesaturation();
  public void setBrightness(float);
  public float getBrightness();
}

-keep class fr.letmethink.lawnchair.MemoryDumpActivity {
  *;
}

-keep class fr.letmethink.lawnchair.PreloadIconDrawable {
  public float getAnimationProgress();
  public void setAnimationProgress(float);
}

-keep class fr.letmethink.lawnchair.pageindicators.CaretDrawable {
  public float getCaretProgress();
  public void setCaretProgress(float);
}

-keep class fr.letmethink.lawnchair.Workspace {
  public float getBackgroundAlpha();
  public void setBackgroundAlpha(float);
}

-keep class com.google.android.libraries.launcherclient.* {
  *;
}

-keep class fr.letmethink.lawnchair.DeferredHandler {
 *;
}

# Proguard will strip new callbacks in LauncherApps.Callback from
# WrappedCallback if compiled against an older SDK. Don't let this happen.
-keep class fr.letmethink.lawnchair.compat.** {
  *;
}
