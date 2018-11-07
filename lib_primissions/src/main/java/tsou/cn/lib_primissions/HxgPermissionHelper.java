package tsou.cn.lib_primissions;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * Created by Administrator on 2018/7/18 0018.
 */

public class HxgPermissionHelper {

    private Object mObject;
    private int mRequestCode;
    private String[] mPermissions;

    private HxgPermissionHelper(Object object) {
        this.mObject = object;
    }

    /**
     * Activity中调用
     *
     * @param activity
     * @return
     */
    public static HxgPermissionHelper with(Activity activity) {
        return new HxgPermissionHelper(activity);
    }

    /**
     * Fragment中调用
     *
     * @param fragment
     * @return
     */
    public static HxgPermissionHelper with(Fragment fragment) {
        return new HxgPermissionHelper(fragment);
    }

    /**
     * 添加请求码
     *
     * @param requestCode
     * @return
     */
    public HxgPermissionHelper requestCode(int requestCode) {
        this.mRequestCode = requestCode;
        return this;
    }

    /**
     * 添加请求权限数组
     *
     * @param permissions
     * @return
     */
    public HxgPermissionHelper requestPermission(String... permissions) {
        this.mPermissions = permissions;
        return this;
    }

    /**
     * 发起请求权限
     */
    public void request() {
        if (!HxgPermissionUtils.isOverMarshmallow()) {
            //6.0以下直接执行方法
            HxgPermissionUtils.executeSuccessMethod(mObject, mRequestCode);
            return;
        }
        //得到没有被授予过的权限
        List<String> deniedPermissions = HxgPermissionUtils.getDeniedPermissions(mObject, mPermissions);

        if (deniedPermissions.size() == 0) {
            //权限全部授权
            HxgPermissionUtils.executeSuccessMethod(mObject, mRequestCode);
        } else {
            //申请权限
            ActivityCompat.requestPermissions(HxgPermissionUtils.getActivity(mObject),
                    deniedPermissions.toArray(new String[deniedPermissions.size()]),
                    mRequestCode);
        }
    }

    /**
     * 处理申请权限的回调
     *
     * @param object
     * @param requestCode
     * @param permissions
     */
    public static void requestPermissionsResult(Object object,
                                                int requestCode,
                                                String[] permissions) {
        //得到没有被授予过的权限
        List<String> deniedPermissions = HxgPermissionUtils
                .getDeniedPermissions(object, permissions);
        if (deniedPermissions.size()==0){
            //用户全部同意
            HxgPermissionUtils.executeSuccessMethod(object,requestCode);
        }else {
            //有部分权限用户不同意
            HxgPermissionUtils.executeFailMethod(object,requestCode);
        }
    }
}
