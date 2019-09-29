package com.softline.entityTemp;

public class ExcelMergedRegin {
	//是否是合并的格子
	private boolean isMergedRegin;
	//是否忽视（忽视的原因是这个格子是合并单元格里面的格子且不是左上角的那个）
	private boolean needignore;
	private int startrow;
	private int startcol;
	private int endrow;
	private int endcol;
	private String value;
	
	public boolean isNeedignore() {
		return needignore;
	}
	public void setNeedignore(boolean needignore) {
		this.needignore = needignore;
	}
	public boolean isMergedRegin() {
		return isMergedRegin;
	}
	public void setMergedRegin(boolean isMergedRegin) {
		this.isMergedRegin = isMergedRegin;
	}
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getStartcol() {
		return startcol;
	}
	public void setStartcol(int startcol) {
		this.startcol = startcol;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	public int getEndcol() {
		return endcol;
	}
	public void setEndcol(int endcol) {
		this.endcol = endcol;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ExcelMergedRegin() {
		super();
	}
	public ExcelMergedRegin(boolean isMergedRegin, int startrow, int startcol,
			int endrow, int endcol, String value,boolean needignore) {
		super();
		this.isMergedRegin = isMergedRegin;
		this.startrow = startrow;
		this.startcol = startcol;
		this.endrow = endrow;
		this.endcol = endcol;
		this.value = value;
		this.needignore=needignore;
	}
	
}
