package com.smallcake.utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.smallcake.utils.R;


/**
 * Created by Administrator on 2015/12/24.
 */
public class CakeShowDialog extends Dialog implements View.OnClickListener {
    private String msg;
    private boolean finishActivity =false;
    private Context mContext;
    public CakeShowDialog(Context context, String msg) {
        super(context, R.style.Theme_Ios_Dialog);
        this.msg = msg;
    }
    public CakeShowDialog(Context context, String msg,boolean finishActivity) {
        super(context, R.style.Theme_Ios_Dialog);
        this.msg = msg;
        mContext = context;
        this.finishActivity = finishActivity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake_show_dialog_layout);
        this.setCancelable(false);//设置点击弹出框外部，无法取消对话框
        View btn_ok = findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        TextView tvMsg = (TextView) findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

    }


    @Override
    public void onClick(View v) {
        cancel();
        if (finishActivity)((Activity)mContext).finish();
    }

}
