package tsou.cn.hxgpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tsou.cn.lib_primissions.HxgPermissionFail;
import tsou.cn.lib_primissions.HxgPermissionHelper;
import tsou.cn.lib_primissions.HxgPermissionSuccess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUESE_CODE = 258;
    /**
     * 打电话
     */
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @HxgPermissionSuccess(requestCode = REQUESE_CODE)
    private void success() {
        Toast.makeText(this, "成功了", Toast.LENGTH_SHORT).show();
    }

    @HxgPermissionFail(requestCode = REQUESE_CODE)
    private void fail() {
        Toast.makeText(this, "失败了", Toast.LENGTH_SHORT).show();
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
        Log.e("huangxiaoguo","onRequestPermissionsResult");
        HxgPermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_main, new PlusOneFragment()).commit();
//        HxgPermissionHelper.with(this)
//                .requestCode(REQUESE_CODE)
//                .requestPermission(Manifest.permission.CALL_PHONE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.CAMERA)
//                .request();
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
