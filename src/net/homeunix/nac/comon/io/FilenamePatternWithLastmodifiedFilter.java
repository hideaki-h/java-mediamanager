package net.homeunix.nac.common.io;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FilenamePatternWithLastmodifiedFilter extends FilenamePatternFilter {
	private Date before= new Date();
	private Date after = new Date(this.before.getTime()-24*60*60*1000);

	public FilenamePatternWithLastmodifiedFilter() {
		super(java.util.regex.Pattern.compile(".*"));
	}

	public FilenamePatternWithLastmodifiedFilter(Pattern pattern) {
		super(pattern);
	}

	public FilenamePatternWithLastmodifiedFilter(Pattern pattern, int after) {
		super(pattern);
		this.after = this.getCurrentOffsetDate(after);
	}
	public FilenamePatternWithLastmodifiedFilter(Pattern pattern, int after, int before) {
		super(pattern);
		this.after = getCurrentOffsetDate(after);
		this.before = getCurrentOffsetDate(before);
	}

	private Date getCurrentOffsetDate(int date) {
		return new Date(System.currentTimeMillis()+(long)date*24*60*60*1000);
	}

	public void setUpdateBefore(int before) {
		this.before = this.getCurrentOffsetDate(before);
	}

	public void setUpdateAfter(int after) {
		this.after = this.getCurrentOffsetDate(after);
	}

	public boolean accept(File base, String name) {
		//ディレクトリーは以下に該当ファイルがある場合のみaccept
		if (base.isDirectory()) {
			List<File> files = java.util.Arrays.asList(base.listFiles());
			for (File file : files) {
				if (this.accept(file, file.getName())) return true;
			}
			return false;
		}
		//ファイルはパターンマッチと最終更新が一致する場合のみaccept
		if (this.pattern.matcher(name).matches()) {
			Date date = new Date(base.lastModified());
			if ((date.after(this.after)) & (date.before(this.before))) return true;
		}
		return false;
	}
}
