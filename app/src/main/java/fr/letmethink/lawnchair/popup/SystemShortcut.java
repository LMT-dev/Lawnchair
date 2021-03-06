package fr.letmethink.lawnchair.popup;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;

import fr.letmethink.lawnchair.AbstractFloatingView;
import fr.letmethink.lawnchair.EditAppDialog;
import fr.letmethink.lawnchair.InfoDropTarget;
import fr.letmethink.lawnchair.ItemInfo;
import fr.letmethink.lawnchair.Launcher;
import fr.letmethink.lawnchair.R;
import fr.letmethink.lawnchair.compat.LauncherActivityInfoCompat;
import fr.letmethink.lawnchair.util.PackageUserKey;
import fr.letmethink.lawnchair.util.Themes;
import fr.letmethink.lawnchair.widget.WidgetsBottomSheet;

public abstract class SystemShortcut {
    private final int mIconResId;
    private final int mLabelResId;

    public static class AppInfo extends SystemShortcut {
        public AppInfo() {
            super(R.drawable.ic_info_no_shadow, R.string.app_info_drop_target_label);
        }

        @Override
        public OnClickListener getOnClickListener(final Launcher launcher, final ItemInfo itemInfo) {
            return new OnClickListener() {
                @Override
                public void onClick(View view) {
                    InfoDropTarget.startDetailsActivityForInfo(itemInfo, launcher, null, launcher.getViewBounds(view), launcher.getActivityLaunchOptions(view));
                }
            };
        }
    }

    public static class Widgets extends SystemShortcut {
        public Widgets() {
            super(R.drawable.ic_widget, R.string.widget_button_text);
        }

        @Override
        public OnClickListener getOnClickListener(final Launcher launcher, final ItemInfo itemInfo) {
            if (launcher.getWidgetsForPackageUser(new PackageUserKey(itemInfo.getTargetComponent().getPackageName(), itemInfo.user)) == null) {
                return null;
            }
            return new OnClickListener() {
                @Override
                public void onClick(View view) {
                    AbstractFloatingView.closeAllOpenViews(launcher);
                    ((WidgetsBottomSheet) launcher.getLayoutInflater().inflate(R.layout.widgets_bottom_sheet, launcher.getDragLayer(), false)).populateAndShow(itemInfo);
                }
            };
        }
    }

    public static class Edit extends SystemShortcut {
        public Edit() {
            super(R.drawable.ic_edit_no_shadow, R.string.edit_drop_target_label);
        }

        @Override
        public OnClickListener getOnClickListener(final Launcher launcher, final ItemInfo itemInfo) {
            return new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_MAIN).setComponent(itemInfo.getTargetComponent());
                    LauncherActivityInfoCompat laic = LauncherActivityInfoCompat.create(launcher, itemInfo.user, i);
                    fr.letmethink.lawnchair.AppInfo appInfo = new fr.letmethink.lawnchair.AppInfo(launcher, laic, itemInfo.user, launcher.getIconCache());
                    launcher.openDialog(new EditAppDialog(launcher, appInfo, launcher));
                }
            };
        }
    }

    public abstract OnClickListener getOnClickListener(Launcher launcher, ItemInfo itemInfo);

    public SystemShortcut(int i, int i2) {
        this.mIconResId = i;
        this.mLabelResId = i2;
    }

    public Drawable getIcon(Context context, int i) {
        Drawable mutate = context.getResources().getDrawable(this.mIconResId, context.getTheme()).mutate();
        mutate.setTint(Themes.getAttrColor(context, i));
        return mutate;
    }

    public String getLabel(Context context) {
        return context.getString(this.mLabelResId);
    }
}