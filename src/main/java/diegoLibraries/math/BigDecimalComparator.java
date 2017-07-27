package diegoLibraries.math;

import java.math.BigDecimal;
import java.util.Comparator;

public class BigDecimalComparator {
	
	public static Comparator<BigDecimal> getAscendent() {
		return new Comparator<BigDecimal>() {
			@Override
			public int compare(BigDecimal o1, BigDecimal o2) {
				return o1.compareTo(o2);
			}
		};
	}
	public static Comparator<BigDecimal> getDescendent() {
		return new Comparator<BigDecimal>() {
			@Override
			public int compare(BigDecimal o1, BigDecimal o2) {
				return o2.compareTo(o1);
			}
		};
	}
}
