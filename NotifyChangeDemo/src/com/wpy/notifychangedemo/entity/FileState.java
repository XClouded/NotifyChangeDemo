package com.wpy.notifychangedemo.entity;

/**
 * 
 * 项目名称：NotifyChangeDemo 类名称：FileState 类描述： 文件的实体类 创建人：wpy 创建时间：2014-9-11
 * 上午10:47:26
 * 
 */
public class FileState {
	String fileName;// 文件名字
	int completeSize;// 下载完成的长度
	boolean state;// 文件状态，true为下载完成，false为未完成

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getCompleteSize() {
		return completeSize;
	}

	public void setCompleteSize(int completeSize) {
		this.completeSize = completeSize;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

}
