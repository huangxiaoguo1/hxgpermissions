package tsou.cn.hxgpermissions;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tsou.cn.lib_primissions.HxgPermissionFail;
import tsou.cn.lib_primissions.HxgPermissionHelper;
import tsou.cn.lib_primissions.HxgPermissionSuccess;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int CALL_PHONE_REQUESE_CODE = 258;
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

    @SuppressWarnings("ResourceType")
    @HxgPermissionSuccess(requestCode = CALL_PHONE_REQUESE_CODE)
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:13273026553");
        intent.setData(data);
        startActivity(intent);
    }

    @HxgPermissionFail(requestCode = CALL_PHONE_REQUESE_CODE)
    private void callPhoneFail() {
        Toast.makeText(this, "失败了", Toast.LENGTH_SHORT).show();
    }

    /**
     * 使用时放在BaseActivity中
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HxgPermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }

    private void initView() {
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                HxgPermissionHelper.with(this)
                        .requestCode(CALL_PHONE_REQUESE_CODE)
                        .requestPermission(Manifest.permission.CALL_PHONE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA)
                        .request();
                break;
        }
    }
}
