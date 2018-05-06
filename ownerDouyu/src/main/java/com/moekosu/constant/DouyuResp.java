package com.moekosu.constant;

import java.util.List;

public class DouyuResp {

	private String error;

	private List<DouyuRoom> data;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<DouyuRoom> getData() {
		return data;
	}

	public void setData(List<DouyuRoom> data) {
		this.data = data;
	}
}
