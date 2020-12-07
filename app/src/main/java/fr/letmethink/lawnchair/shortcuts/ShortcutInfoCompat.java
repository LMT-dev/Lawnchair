/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.letmethink.lawnchair.shortcuts;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

import fr.letmethink.lawnchair.ItemInfo;
import fr.letmethink.lawnchair.Utilities;
import fr.letmethink.lawnchair.compat.LauncherActivityInfoCompat;
import fr.letmethink.lawnchair.compat.UserManagerCompat;

/**
 * Wrapper class for {@link android.content.pm.ShortcutInfo}, representing deep shortcuts into apps.
 * <p>
 * Not to be confused with {@link fr.letmethink.lawnchair.ShortcutInfo}.
 */
public class ShortcutInfoCompat {
    private static final String INTENT_CATEGORY = "fr.letmethink.lawnchair.DEEP_SHORTCUT";
    public static final String EXTRA_SHORTCUT_ID = "shortcut_id";
    private String packageName;
    private String id;
    private CharSequence shortLabel;
    private CharSequence longLabel;
    private Intent activity;
    private UserHandle userHandle;
    private int rank;
    private boolean enabled;
    private CharSequence disabledMessage;
    private Drawable icon;

    private ShortcutInfo mShortcutInfo;

    public ShortcutInfoCompat(ShortcutInfo shortcutInfo) {
        mShortcutInfo = shortcutInfo;
    }

    public ShortcutInfoCompat(String packageName, String id, CharSequence shortLabel, CharSequence longLabel,
                              Intent activity, UserHandle userHandle, int rank, boolean enabled, CharSequence disabledMessage, Drawable icon) {
        this.packageName = packageName;
        this.id = id;
        this.shortLabel = shortLabel;
        this.longLabel = longLabel;
        this.activity = activity;
        this.userHandle = userHandle;
        this.rank = rank;
        this.enabled = enabled;
        this.disabledMessage = disabledMessage;
        this.icon = icon;
    }

    public Intent makeIntent(Context context) {
        long serialNumber = UserManagerCompat.getInstance(context)
                .getSerialNumberForUser(getUserHandle());
        Intent intent;
        if (useNative()) {
            intent = new Intent(Intent.ACTION_MAIN);
            intent.setComponent(getActivity());
        } else {
            intent = activity;
        }
        return intent
                .addCategory(INTENT_CATEGORY)
                .setPackage(getPackage())
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)
                .putExtra(ItemInfo.EXTRA_PROFILE, serialNumber)
                .putExtra(EXTRA_SHORTCUT_ID, getId());
    }

    public ShortcutInfo getShortcutInfo() {
        return mShortcutInfo;
    }

    public String getPackage() {
        if (useNative()) {
            return mShortcutInfo.getPackage();
        } else {
            return packageName;
        }
    }

    public String getId() {
        if (useNative()) {
            return mShortcutInfo.getId();
        } else {
            return id;
        }
    }

    public CharSequence getShortLabel() {
        if (useNative()) {
            return mShortcutInfo.getShortLabel();
        } else {
            return shortLabel;
        }
    }

    public CharSequence getLongLabel() {
        if (useNative()) {
            return mShortcutInfo.getLongLabel();
        } else {
            return longLabel;
        }
    }

    public long getLastChangedTimestamp() {
        if (useNative()) {
            return mShortcutInfo.getLastChangedTimestamp();
        } else {
            return 0;
        }
    }

    public ComponentName getActivity() {
        if (useNative()) {
            return mShortcutInfo.getActivity();
        } else {
            return activity.getComponent();
        }
    }

    public UserHandle getUserHandle() {
        if (useNative()) {
            return mShortcutInfo.getUserHandle();
        } else {
            return userHandle;
        }
    }

    public boolean hasKeyFieldsOnly() {
        if (useNative()) {
            return mShortcutInfo.hasKeyFieldsOnly();
        } else {
            return false;
        }
    }

    public boolean isPinned() {
        if (useNative()) {
            return mShortcutInfo.isPinned();
        } else {
            return false;
        }
    }

    public boolean isDeclaredInManifest() {
        if (useNative()) {
            return mShortcutInfo.isDeclaredInManifest();
        } else {
            return true;
        }
    }

    public boolean isEnabled() {
        if (useNative()) {
            return mShortcutInfo.isEnabled();
        } else {
            return enabled;
        }
    }

    public boolean isDynamic() {
        if (useNative()) {
            return mShortcutInfo.isDynamic();
        } else {
            return false;
        }
    }

    public int getRank() {
        if (useNative()) {
            return mShortcutInfo.getRank();
        } else {
            return rank;
        }
    }

    public CharSequence getDisabledMessage() {
        if (useNative()) {
            return mShortcutInfo.getDisabledMessage();
        } else {
            return disabledMessage;
        }
    }

    public Drawable getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        if (useNative()) {
            return mShortcutInfo.toString();
        } else {
            return super.toString();
        }
    }

    public boolean useNative() {
        return Utilities.isNycMR1OrAbove() && mShortcutInfo != null;
    }

    public LauncherActivityInfoCompat getActivityInfo(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .setComponent(getActivity());
        return LauncherActivityInfoCompat.create(context, getUserHandle(), intent);
    }
}
