package tsou.cn.hxgpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import tsou.cn.lib_primissions.HxgPermissionFail;
import tsou.cn.lib_primissions.HxgPermissionHelper;
import tsou.cn.lib_primissions.HxgPermissionSuccess;

public class PlusOneFragment extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * 我是Fragment
     */
    private Button mBtn;
    private static final int REQUESE_CODE = 259;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mBtn = (Button) view.findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        HxgPermissionHelper.with(this)
                .requestCode(REQUESE_CODE)
                .requestPermission(Manifest.permission.CALL_PHONE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .request();
    }

    @HxgPermissionSuccess(requestCode = REQUESE_CODE)
    private void success() {
        Log.e("huangxiaoguo","HxgPermissionSuccess--fragment");
        Toast.makeText(getContext(), "成功了", Toast.LENGTH_SHORT).show();
    }

    @HxgPermissionFail(requestCode = REQUESE_CODE)
    private void fail() {
        Toast.makeText(getContext(), "失败了", Toast.LENGTH_SHORT).show();
    }

    /**
     * 使用时放在BaseActivity中或BaseFragment中
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("huangxiaoguo","onRequestPermissionsResult-------------");
        HxgPermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                HxgPermissionHelper.with(this)
                        .requestCode(REQUESE_CODE)
                        .requestPermission(Manifest.permission.CALL_PHONE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA)
                        .request();
                break;
        }
    }
}
