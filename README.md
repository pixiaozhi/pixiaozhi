# pixiaozhi
1.0.0仅仅测试是否正常可以依赖（TextViewUtil）

gradle依赖

Step 1.Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.pixiaozhi:pixiaozhi:1.0.0'
	}

------------------------------------------------------------------------
已上传文件（暂未打包发布）
TextDrawable 可设置图片+文字的TextView，在布局文件中设置文字左边图宽高，同理可设置rightDrawable，topDrawable，bottomDrawable

	<com.ryx.widget.TextDrawable
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="@string/app_name"
        app:leftDrawable="@mipmap/ic_launcher_round"
        app:leftDrawableHeight="20dp"
        app:leftDrawableWidth="20dp"/>
