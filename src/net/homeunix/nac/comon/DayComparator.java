package net.homeunix.nac.common;

import java.util.Comparator;

public class DayComparator<T> implements Comparator<T> {
	public enum DIRECTION { ASCEND, DESCEND };
	private DIRECTION direction;

	public DayComparator(DIRECTION direction) {
		this.direction = direction;
	}

	public int compare(T o1, T o2) {
		switch (this.direction) {
			case ASCEND:
				return constant.day.get(o1) - constant.day.get(o2);
			case DESCEND:
				return constant.day.get(o2) - constant.day.get(o1);
		}
		throw new RuntimeException("dead exception");
	}
}
