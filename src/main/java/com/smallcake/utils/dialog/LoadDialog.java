package com.smallcake.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.smallcake.utils.R;


public class LoadDialog extends Dialog {
	String text;
	Context mContext;
	public LoadDialog(Context context) {
		this(context, "");
	}
	public LoadDialog(Context context, String text) {
		super(context, R.style.Theme_Loading_Dialog);
		this.text = text;
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smallcake_utils_loading_dialog);
		setCanceledOnTouchOutside(false);
		((TextView)findViewById(R.id.tv_load_dialog)).setText(TextUtils.isEmpty(text)?mContext.getString(R.string.loading):text);
	}
	
}
