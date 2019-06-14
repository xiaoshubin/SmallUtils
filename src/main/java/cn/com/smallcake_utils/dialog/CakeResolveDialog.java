package cn.com.smallcake_utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.com.smallcake_utils.R;


/**
 * Created by Administrator on 2015/12/24
 * 决定选择弹出框
 */
public class CakeResolveDialog extends Dialog implements View.OnClickListener {
    private CharSequence msg;
    private OnOkListener listener;
    private OnCancleListener cancleListener;
    public CakeResolveDialog(Context context, CharSequence msg,OnOkListener listener) {
        super(context, R.style.Theme_Loading_Dialog);
        this.msg = msg;
        this.listener = listener;
    }
    public CakeResolveDialog(Context context, CharSequence msg,OnOkListener listener,OnCancleListener cancleListener) {
        super(context, R.style.Theme_Loading_Dialog);
        this.msg = msg;
        this.listener = listener;
        this.cancleListener = cancleListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resolve_dialog);
        this.setCancelable(false);//设置点击弹出框外部，无法取消对话框

        View btn_cancle = findViewById(R.id.btn_cancle);
        View btn_ok = findViewById(R.id.btn_ok);
        btn_cancle.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        TextView tvMsg =  findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

    }



    public interface OnOkListener{
        void onOkClick();
    }
    public interface OnCancleListener{
        void onCancleClick();
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_cancle) {
            if (cancleListener != null) cancleListener.onCancleClick();
            cancel();

        } else if (i == R.id.btn_ok) {
            if (listener != null) listener.onOkClick();
            cancel();

        }

    }

}
