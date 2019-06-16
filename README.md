# hxgpermissions

#### 引入方式

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
 	dependencies {
	        implementation 'com.github.huangxiaoguo1:hxgpermissions:1.0.2'
	}
```

###### 在manifest中选择需要的权限

###### 申请权限（例如在点击事件中）

```
HxgPermissionHelper.with(this)
                        .requestCode(REQUESE_CODE)
                        .requestPermission(Manifest.permission.CALL_PHONE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA)
                        .request();
```

###### 成功和失败的方法（依靠requestCode判断对应权限）

```
    @HxgPermissionSuccess(requestCode = REQUESE_CODE)
    private void success() {
        Toast.makeText(this, "成功了", Toast.LENGTH_SHORT).show();
    }

    @HxgPermissionFail(requestCode = REQUESE_CODE)
    private void fail() {
        Toast.makeText(this, "失败了", Toast.LENGTH_SHORT).show();
    }
```

###### 回调函数（一般写在BaseActivity或者BaseFragment中）


```

    /**
     * 使用时放在BaseActivity中或BaseFragment中
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HxgPermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }


```

######混淆
```
-keepattributes *Annotation*
# keep annotated by HxgPermissionSuccess,HxgPermissionFail
-keep @tsou.cn.lib_primissions.HxgPermissionSuccess class * {*;}
-keep @tsou.cn.lib_primissions.HxgPermissionFail class * {*;}
-keep class * {
        @tsou.cn.lib_primissions.HxgPermissionSuccess <fields>;
}
-keepclassmembers class * {
        @tsou.cn.lib_primissions.HxgPermissionSuccess <methods>;
}
-keep class * {
        @tsou.cn.lib_primissions.HxgPermissionFail <fields>;
}
-keepclassmembers class * {
        @tsou.cn.lib_primissions.HxgPermissionFail <methods>;
}
```






