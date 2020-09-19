/*
 * Copyright (c) Gustavo Claramunt (AnderWeb) 2014.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mardous.discreteseekbar.internal.compat;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import com.mardous.discreteseekbar.internal.drawable.AlmostRippleDrawable;

/**
 * Wrapper compatibility class to call some API-Specific methods
 * And offer alternate procedures when possible
 */
public class SeekBarCompat {
    /**
     * Our DiscreteSeekBar implementation uses a circular drawable on API < 21
     * because we don't set it as Background, but draw it ourselves
     *
     * @param colorStateList The ripple colors.
     * @return The ripple drawable.
     */
    public static Drawable getRipple(ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new RippleDrawable(colorStateList, null, null);
        } else {
            return new AlmostRippleDrawable(colorStateList);
        }
    }

    /**
     * Sets the color of the seekbar ripple
     *
     * @param drawable The ripple drawable.
     * @param colorStateList The ColorStateList the track ripple will be changed to.
     */
    public static void setRippleColor(@NonNull Drawable drawable, ColorStateList colorStateList) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((RippleDrawable) drawable).setColor(colorStateList);
        } else {
            ((AlmostRippleDrawable) drawable).setColor(colorStateList);
        }
    }

    /**
     * As our DiscreteSeekBar implementation uses a circular drawable on API < 21
     * we want to use the same method to set its bounds as the Ripple's hotspot bounds.
     *
     * @param drawable The drawable to modify.
     */
    public static void setHotspotBounds(Drawable drawable, int left, int top, int right, int bottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //We don't want the full size rect, Lollipop ripple would be too big
            int size = (right - left) / 8;
            DrawableCompat.setHotspotBounds(drawable, left + size, top + size, right - size, bottom - size);
        } else {
            drawable.setBounds(left, top, right, bottom);
        }
    }
}
