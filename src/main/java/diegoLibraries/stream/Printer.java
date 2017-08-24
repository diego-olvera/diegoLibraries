package diegoLibraries.stream;

import diegoLibraries.commons.Util;

@FunctionalInterface
public interface Printer {
	void print(Object o);
	default void println(Object o) {
		this.print(o+Util.JUMP_LINE);
	}
}
