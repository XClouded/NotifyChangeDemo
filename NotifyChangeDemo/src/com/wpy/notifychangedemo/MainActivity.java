package com.wpy.notifychangedemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.wpy.notifychangedemo.adapter.MyListViewAdapter;
import com.wpy.notifychangedemo.entity.FileState;

public class MainActivity extends Activity {

	private ListView listView;
	private List<FileState> list = new ArrayList<FileState>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initFile();
		initView();
	}

	/**
	 * 初始化数据
	 */
	private void initFile() {
		for (int i = 0; i < 8; i++) {
			FileState fileState = new FileState();
			fileState.setFileName(i + ".mp3");
			fileState.setCompleteSize(100);
			fileState.setState(true);
			list.add(fileState);
		}
		FileState f = new FileState();
		f.setFileName("8.mp3");
		f.setCompleteSize(0);
		f.setState(false);
		list.add(f);
		FileState f1 = new FileState();
		f1.setFileName("9.mp3");
		f1.setCompleteSize(0);
		f1.setState(false);
		list.add(f1);
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		listView = (ListView) this.findViewById(R.id.listView);
		MyListViewAdapter adapter = new MyListViewAdapter(list, this);
		listView.setAdapter(adapter);
		adapter.setListView(listView);
	}

}
