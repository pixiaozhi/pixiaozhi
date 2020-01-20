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
	
标签列表控件。可以设置标签的选中效果。 可以设置标签的选中类型：不可选中、单选、限数量多选和不限数量多选等， 并支持设置必选项、单行显示、最大显示行数等功能。

	<com.ryx.widget.LabelsView
       android:id="@+id/labels"
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       app:labelBackground="@drawable/label_bg"     //标签的背景
       app:labelTextColor="@drawable/label_text_color" //标签的字体颜色 可以是一个颜色值
       app:labelTextSize="14sp"      //标签的字体大小
       app:labelTextPaddingBottom="5dp"   //标签的上下左右边距
       app:labelTextPaddingLeft="10dp"
       app:labelTextPaddingRight="10dp"
       app:labelTextPaddingTop="5dp"
       app:lineMargin="10dp"   //行与行的距离
       app:wordMargin="10dp"   //标签与标签的距离
       app:selectType="SINGLE"   //标签的选择类型 有单选(可反选)、单选(不可反选)、多选、不可选四种类型
       app:maxLines="3"    // 设置最大显示行数，小于等于0则不限行数。
       app:maxSelect="5"   //标签的最大选择数量，只有多选的时候才有用，0为不限数量
       app:minSelect="1"   //标签的最少选择数量，只有多选的时候才有用，0为不限数量
       app:isIndicator="true" />   //设置为指示器模式，不能手动改变标签的选中状态

	<!-- 其他属性 -->
	app:labelTextWidth="wrap_content"  // 标签项宽
	app:labelTextHeight="wrap_content"  // 标签项高
	app:labelGravity="center"  // 标签项的文本显示方向
	app:labelTextPadding="5dp"  // 标签的Padding
	app:singleLine="true"  // 单行显示，默认false
