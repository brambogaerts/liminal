package nl.brambogaerts.liminal.sorters;

import java.util.Comparator;
import nl.brambogaerts.liminal.tools.PixelTool;

public class LuminanceSorter implements Comparator<Integer> {
	public int compare(Integer p1, Integer p2) {
		Double l1 = PixelTool.luminance(p1);
		Double l2 = PixelTool.luminance(p2);
		return l2.compareTo(l1);
	}
}
