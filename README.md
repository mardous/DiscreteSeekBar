# DiscreteSeekBar

[![](https://jitpack.io/v/mardous/DiscreteSeekBar.svg)](https://jitpack.io/#mardous/DiscreteSeekBar)

## Description
DiscreteSeekbar is my poor attempt to develop an android implementation of the [Discrete Slider] component from the Google Material Design Guidelines.

## Prologe
I really hope Google provides developers with a better (and official) implementation ;)

## Warning
After a bunch of hours trying to replicate the exact feel of the Material's Discrete Seekbar, with a beautiful stuff-that-morphs-into-other-stuff animation I was convinced about releasing the current code.

```java
android.util.Log.wtf("WARNING!! HACKERY-DRAGONS!!");
```
I've done a few bit of hacky cede and a bunch of things I'm not completely proud of, so use under your sole responsibility (or help me improve it via pull-requests!)

## Implementation details
This thing runs on API 16+.

The base SeekBar is pretty simple. Just 3 drawables for the track, progress and thumb. Some touch event logic to drag, some key event logic to move, and that's all.

It supports custom ranges (custom min/max), even for negative values.

The bubble thing **DOESN'T USE** [VectorDrawableMagic] . I was not really needed for such a simple morph. It uses instead an [Animatable Drawable] for the animation with a lot of hackery for callbacks, drawing and a bunch of old simple math.

>For this to work (and sync with events, etc) I've written a fair amount of shit questionable code...

The material-floating-thing is composed into the WindowManager (like the typical overflow menus) to be able to show it over other Views without needing to set the SeekBar big enough to account for the (variable) size of he floating thing.

>For this I'm not sure about the amounts of things I've copied from [PopupWindow] and the possible issues.

## Download
You can download this library using JitPack:

```groovy
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation 'com.github.mardous:DiscreteSeekBar:1.1.3'
}
```

## Usage
Once imported into your project, you just need to put them into your layous like:

```xml
<com.mardous.discreteseekbar.DiscreteSeekBar
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:dsb_min="2"
        app:dsb_max="15"/>
```

#### Parameters
You can tweak a few things of the DiscreteSeekbar:

* **dsb_min**: minimum value
* **dsb_max**: maximum value
* **dsb_value**: current value
* **dsb_mirrorForRtl**: reverse the DiscreteSeekBar for RTL locales
* **dsb_allowTrackClickToDrag**: allows clicking outside the thumb circle to initiate drag. Default TRUE
* **dsb_indicatorFormatter**: a string [Format] to apply to the value inside the bubble indicator.
* **dsb_indicatorPopupEnabled**: choose if the bubble indicator will be shown. Default TRUE 
* **dsb_animateEnabledState**: indicates if the DiscreteSeekBar should toggle its "Enabled" state with a smooth animation. Default TRUE
* **dsb_autoAdjustIndicatorTextColor**: indicates whether the color of the indicator text should automatically change based on the indicator color. For example, make the text dark when the indicator is light. Default TRUE

#### Design
 
* **dsb_progressColor**: color/colorStateList for the progress bar and thumb drawable
* **dsb_trackColor**: color/colorStateList for the track drawable
* **dsb_indicatorTextAppearance**: TextAppearance for the bubble indicator
* **dsb_indicatorColor**: color/colorStateList for the bubble shaped drawable
* **dsb_indicatorElevation**: related to android:elevation. Will only be used on API level 21+
* **dsb_rippleColor**: color/colorStateList for the ripple drawable seen when pressing the thumb. (Yes, it does a kind of "ripple" on API levels lower than 21 and a real RippleDrawable for 21+.
* **dsb_trackHeight**: dimension for the height of the track drawable.
* **dsb_scrubberHeight**: dimension for the height of the scrubber (selected area) drawable.
* **dsb_thumbSize**: dimension for the size of the thumb drawable.
* **dsb_indicatorSeparation**: dimension for the vertical distance from the thumb to the indicator. 
* **dsb_autoIndicatorTextColorLight**: used when the parameter "dsb_autoAdjustIndicatorTextColor" is TRUE. Defines the color to use when a light text color is required.
* **dsb_autoIndicatorTextColorDark**: used when the parameter "dsb_autoAdjustIndicatorTextColor" is TRUE. Defines the color to use when a dark text color is required.

You can also use the attribute **discreteSeekBarStyle** on your themes with a custom Style to be applied to all the DiscreteSeekBars on your app/activity/fragment/whatever.

[Discrete Slider]:http://www.google.com/design/spec/components/sliders.html#sliders-discrete-slider
[VectorDrawableMagic]:https://developer.android.com/reference/android/graphics/drawable/AnimatedVectorDrawable.html
[Animatable Drawable]:https://developer.android.com/reference/android/graphics/drawable/Animatable.html
[PopupWindow]:https://developer.android.com/reference/android/widget/PopupWindow.html
[Format]:https://developer.android.com/reference/java/util/Formatter.html
