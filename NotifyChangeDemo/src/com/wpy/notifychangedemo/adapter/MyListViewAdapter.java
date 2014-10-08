package com.wpy.notifychangedemo.adapter;

import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wpy.notifychangedemo.Downloader;
import com.wpy.notifychangedemo.R;
import com.wpy.notifychangedemo.entity.FileState;

public class MyListViewAdapter extends BaseAdapter {

	private List<FileState> list;
	private LayoutInflater inflater = null;
	private ListView listView;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == 1) {
				String name = (String) msg.obj;
				int length = msg.arg1;
				// 更新item里面的某一条目
				for (int i = 0; i < list.size(); i++) {
					FileState fileState = list.get(i);
					if (fileState.getFileName().equals(name)) {
						fileState.setCompleteSize(length);
						list.set(i, fileState);
						updateView(i);
						break;
					}
				}
				// notifyDataSetChanged();//更新所有条目
			}
		}
	};

	public MyListViewAdapter(List<FileState> list, Context context) {
		this.list = list;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.fileName = (TextView) convertView
					.findViewById(R.id.fileName);
			holder.progressBar = (ProgressBar) convertView
					.findViewById(R.id.down_progressBar);
			holder.percent = (TextView) convertView
					.findViewById(R.id.percent_text);
			holder.down = (ImageView) convertView.findViewById(R.id.down_view);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FileState fileState = list.get(position);
		final String name = fileState.getFileName();
		System.out.println(name + "---run getView");
		holder.fileName.setText(name);
		// 如果文件状态为已经下载
		if (fileState.isState() == true) {
			holder.progressBar.setVisibility(ProgressBar.INVISIBLE);
			holder.down.setVisibility(ImageView.INVISIBLE);
			holder.percent.setText("已下载");
		} else {
			holder.progressBar.setVisibility(ProgressBar.VISIBLE);
			holder.progressBar.setProgress(fileState.getCompleteSize());
			holder.percent.setText(fileState.getCompleteSize() + "%");
			holder.down.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Downloader downloader = new Downloader(name, handler);
					downloader.download();
				}
			});

			if (fileState.getCompleteSize() == 100) {
				holder.progressBar.setVisibility(ProgressBar.INVISIBLE);
				holder.down.setVisibility(ImageView.INVISIBLE);
				holder.percent.setText("已下载");
				fileState.setState(true);
				list.set(position, fileState);
			}
		}
		return convertView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	/**
	 * 用于更新要更新的item
	 * 
	 * @param itemIndex
	 *            要item的下标
	 */
	private void updateView(int itemIndex) {
		// 得到第1个可显示控件的位置,记住是第1个可显示控件。而不是第1个控件
		int visiblePosition = listView.getFirstVisiblePosition();
		// 得到你需要更新item的View
		View view = listView.getChildAt(itemIndex - visiblePosition);
		FileState fileState = list.get(itemIndex);
		final String name = fileState.getFileName();
		System.out.println(name + "---run updateView");
		if (fileState.isState() == false) {
			ViewHolder holderOne = new ViewHolder();
			// start:初始化
			holderOne.fileName = (TextView) view.findViewById(R.id.fileName);
			holderOne.progressBar = (ProgressBar) view
					.findViewById(R.id.down_progressBar);
			holderOne.percent = (TextView) view.findViewById(R.id.percent_text);
			holderOne.down = (ImageView) view.findViewById(R.id.down_view);
			// end
			holderOne.fileName.setText(fileState.getFileName());
			holderOne.progressBar.setVisibility(ProgressBar.VISIBLE);
			holderOne.progressBar.setProgress(fileState.getCompleteSize());
			holderOne.percent.setText(fileState.getCompleteSize() + "%");
			holderOne.down.setOnClickListener(new View.OnClickListener() {

				public void onClick(View v) {
					Downloader down = new Downloader(name, handler);
					down.download();
				}

			});
			if (fileState.getCompleteSize() == 100) {
				holderOne.progressBar.setVisibility(ProgressBar.INVISIBLE);
				holderOne.percent.setText("已下载");
				holderOne.down.setVisibility(ProgressBar.INVISIBLE);
				fileState.setState(true);
				list.set(itemIndex, fileState);
			}
		}
	}

	class ViewHolder {
		public TextView fileName;
		public ProgressBar progressBar;
		public TextView percent;
		public ImageView down;
	}
}
