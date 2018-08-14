package com.smallcake.utils.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.List;

public abstract class CakeBaseAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> listDatas;

	protected int layoutId;


	public CakeBaseAdapter(List<T> listDatas, int layoutId) {
		this.listDatas = listDatas;
		this.layoutId = layoutId;
	}



    @Override
	public int getCount() {
		if (listDatas!=null&&listDatas.size()>0) {
			return listDatas.size();
		}else {
				return 0;
		}
		
	}

	@Override
	public T getItem(int position) {
		if (listDatas!=null&&listDatas.size()>0) {
			return listDatas.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = ViewHolder.get(convertView, parent, layoutId, position);
		try {
			convert(holder, getItem(position));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		mContext = parent.getContext();
		return holder.getConverView();
		
	}

	public abstract void convert(ViewHolder holder, T t) throws JSONException;

	/**
	 * ViewHolder
	 */
	protected static class ViewHolder{
		//SparseArray is better than HashMap
		private SparseArray<View> mViews;

		private int mPosition;
		private View mConverView;

		public int getPosition() {
			return mPosition;
		}

		public ViewHolder(ViewGroup parent, int layoutId,
						  int position) {
			this.mViews = new SparseArray<>();
			this.mPosition = position;
			mConverView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent,
					false);
			mConverView.setTag(this);
		}
		public  static ViewHolder get(View converView,
									 ViewGroup parent, int layoutId, int position) {
			if (converView == null) {
				return new ViewHolder( parent, layoutId, position);
			} else {
				ViewHolder holder = (ViewHolder) converView.getTag();
				holder.mPosition = position;// update position
				return holder;
			}
		}

		/**
		 * get view from viewId
		 *
		 * @param viewId
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public <T extends View> T getView(int viewId) {
			View view = mViews.get(viewId);
			if (view == null) {
				view = mConverView.findViewById(viewId);
				mViews.put(viewId, view);

			}
			return (T) view;
		}

		public View getConverView() {
			return mConverView;
		}

		public ViewHolder setText(int viewId,String text) {
			TextView tv = getView(viewId);
			tv.setText(text);
			return this;
		}
		public ViewHolder setImg(int viewId,int resId) {
			ImageView imgView = getView(viewId);
			imgView.setImageResource(resId);
			return this;
		}
		public ViewHolder setOnClickListener( View.OnClickListener onClickListener) {
			mConverView.setOnClickListener(onClickListener);
			return this;
		}
	}
}
