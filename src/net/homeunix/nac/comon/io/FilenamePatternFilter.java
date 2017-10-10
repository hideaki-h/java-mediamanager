package net.homeunix.nac.common.io;

import java.io.*;
import java.util.regex.*;

public class FilenamePatternFilter implements FilenameFilter {
	protected Pattern pattern;

	public FilenamePatternFilter(Pattern pattern) throws NullPointerException {
		this.setPattern(pattern);
	}

	public void setPattern(Pattern pattern) {
		if (pattern == null) { throw new NullPointerException(); }
		this.pattern = pattern;
	}

	public boolean accept(File file, String name) {
		return this.pattern.matcher(name).matches();
	}
}
