package nl.brambogaerts.liminal.sorters;

import java.util.Comparator;
import nl.brambogaerts.liminal.tools.PixelTool;

public class BrightnessSorter implements Comparator<Integer> {
	public int compare(Integer p1, Integer p2) {
		Double b1 = PixelTool.brightness(p1);
		Double b2 = PixelTool.brightness(p2);
		return b2.compareTo(b1);
	}
}
