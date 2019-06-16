package tsou.cn.lib_primissions;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄家三少 on 2018/7/18.
 */

class HxgPermissionUtils {
    private HxgPermissionUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断版本6.0
     *
     * @return
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 执行成功方法
     *
     * @param object
     * @param code
     */
    public static void executeSuccessMethod(Object object, int code) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            HxgPermissionSuccess successMethod = method.getAnnotation(HxgPermissionSuccess.class);
            if (successMethod != null) {
                int methodCode = successMethod.requestCode();
                if (methodCode == code) {
                    executeMethod(object, method);
                }
            }
        }
    }

    /**
     * 反射 执行方法
     *
     * @param object
     * @param method
     */
    private static void executeMethod(Object object, Method method) {
        //第一个是传该方法属于哪个类，第二个是传参数....
        try {
            //允许执行私有方法
            method.setAccessible(true);
            method.invoke(object, new Object[]{});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取没有获取的权限
     *
     * @param object      Activity、Fragment
     * @param permissions
     * @return
     */
    public static List<String> getDeniedPermissions(Object object, String[] permissions) {
        List<String> deniedPermissons = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(getActivity(object), permission)
                    == PackageManager.PERMISSION_DENIED) {
                deniedPermissons.add(permission);
            }

        }
        return deniedPermissons;
    }

    /**
     * 获取Acticity
     *
     * @param object
     * @return
     */
    public static Activity getActivity(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        }
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        }
        return null;
    }
    /**
     * 获取Fragment
     *
     * @param object
     * @return
     */
    public static Fragment getFragment(Object object) {
        if (object instanceof Fragment) {
            return (Fragment) object;
        }
        return null;
    }
    /**
     * 执行失败的方法
     *
     * @param object
     * @param code
     */
    public static void executeFailMethod(Object object, int code) {
        Method[] methods = object.getClass().getDeclaredMethods();
        for (Method method : methods) {
            HxgPermissionFail failMethod = method.getAnnotation(HxgPermissionFail.class);
            if (failMethod != null) {
                int methodCode = failMethod.requestCode();
                if (methodCode == code) {
                    executeMethod(object, method);
                }
            }
        }
    }
}




