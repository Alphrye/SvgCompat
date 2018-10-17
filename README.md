# SvgCompat
VectorDrawable是Android中Svg的不完整支持（足够实现大部分功能）。
Android Lollipop时代提出，起初只支持Lollipop+的Android系统，没有得到广泛应用。
随着AppCompat 23.2版本的发布，将VectorDrawable兼容到API7，动画支持到API11。
比如在`ImageView`中可以去使用`app:srcCompat`属性完成对Lollipop前的系统版本兼容，但是还不足以满足开发中的一些需求。

## SvgCompatTextView
主要针对`TextView`中`android:drawableStart`，`android:drawableEnd`，`android:drawableTop`，`android:drawableBottom`属性做兼容(AppCompat中不支持)。
 此外还添加对这些Drawable的着色处理。
 
 ```java
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
## SvgImage
### 使用
```java
new SvgImage.Builder()
		.bind(mIvFavor) //绑定视图
		.drawable(R.drawable.common_icon_collect_24) //指定icon资源（vectorDrawable）
		.tint(R.color.white) //设置填充颜色
		.build();
```
## SvgText
### 使用
```java
new SvgText.Builder()
		.bind(mBackView)
		.leftDrawable(R.drawable.common_icon_back_24) //指定左边icon资源(vectorDrawable)
		.leftTint(R.color.white) //填充左边icon颜色
		.rightDrawable(R.drawable.common_icon_back_24) //指定右边icon资源(vectorDrawable)
		.rightTint(R.color.white)//填充右边icon颜色
		.topDrawable(R.drawable.common_icon_back_24) //指定上部icon资源(vectorDrawable)
		.topTint(R.color.white) //填充上部icon颜色
		.bottomDrawable(R.drawable.common_icon_back_24) //指定底部icon资源(vectorDrawable)
		.bottomTint(R.color.white)//填充底部icon颜色
		.padding(DimenHelper.dp2px(4.0f)) //设置icon与文本边距
		.build();
```

 
 
 
