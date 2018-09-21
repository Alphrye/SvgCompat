# SvgCompat
VectorDrawable是Android中Svg的不完整支持（足够实现大部分功能）。
Android Lollipop时代提出，起初只支持Lollipop+的Android系统，没有得到广泛应用。
随着AppCompat 23.2版本的发布，将VectorDrawable兼容到API7，动画支持到API11。
比如在`ImageView`中可以去使用`app:srcCompat`属性完成对Lollipop前的系统版本兼容，但是还不足以满足开发中的一些需求。

## SvgCompatTextView
主要针对`TextView`中`android:drawableStart`，`android:drawableEnd`，`android:drawableTop`，`android:drawableBottom`属性做兼容(AppCompat中不支持)。
 此外还添加对这些Drawable的着色处理。
 ```
  <com.nexuslink.svgcompat.SvgCompatTextView
        android:text="@string/app_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:drawableStartCompat="@drawable/ic_android_black_24dp"
        app:drawableEndCompat="@drawable/ic_android_black_24dp"
	        app:drawableTopCompat="@drawable/ic_android_black_24dp"
        app:drawableBottomCompat="@drawable/ic_android_black_24dp"
        app:tintStartDrawable="@color/colorAccent"
        app:tintEndDrawable="@color/colorAccent"
        app:tintTopDrawable="@color/colorAccent"
        app:tintBottomDrawable="@color/colorAccent"/>
 ```
