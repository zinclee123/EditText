# EditText-Android
继承自AppCompatEditText，支持清除所有

## 示例(Demo)
<p><img src="https://github.com/zinclee123/EditText-Android/blob/master/img/Demo.gif?raw=true?raw=true" width="320" alt="Screenshot"/></p>

## 用法(Usage)
### Step 1.
在项目的根build.gradle文件中加入<br/>
```
allprojects {
   repositories {
      ...
      maven { url 'https://jitpack.io' }
   }
}
   ```
### Step 2.
在你需要使用该控件的module的build.gradle中加入依赖<br/>
```
dependencies {
  compile 'com.github.zinclee123:EditText:v0.1'
}
```
### Step 3.
在布局文件中使用</br>
```xml
<pers.zinclee123.edittext.EditText
        android:id="@+id/et_test1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_marginTop="24dp"/>
```
EditText有三个自定义属性分别是</br>
1.et_showClearIcon,表示是否显示清除标志</br>
2.et_clearIcon,指定清除标志的图片资源id</br>
3.et_clearIconColor,指定清除标志的颜色，可以是字符串可以是color资源id</br>
也可以通过代码设置如下</br>
```java
//设置图标颜色
et1.setClearIconColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary));
//设置图标
et1.setClearIcon(R.mipmap.custom_clear_text_icon);
//设置图标
Drawable d = ContextCompat.getDrawable(MainActivity.this, R.mipmap.custom_clear_text_icon);
                DrawableCompat.setTint(d, Color.parseColor("#ff85a1"));
                et1.setClearIcon(d);
//设置是否显示清除图标
et1.setShowClearIcon(show);
```
### 注意(Notice)
可能会和setErro产生某些冲突，未测试，有问题提issue哈



