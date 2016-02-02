package nl.brambogaerts.liminal.transform;

import nl.brambogaerts.liminal.progress.ProgressBar;
import nl.brambogaerts.liminal.sorters.LuminanceSorter;
import nl.brambogaerts.liminal.sorters.BrightnessSorter;
import nl.brambogaerts.liminal.tools.PixelTool;

import java.util.Comparator;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SortTransformer extends Transformer {
	double threshold;
	boolean useLuminance;

	public SortTransformer(double threshold, boolean useLuminance){
		super();
		this.threshold = threshold;
		this.useLuminance = useLuminance;
	}

	@Override
	public void transform(int[] pixels, int w, int h){
		ProgressBar pb = new ProgressBar("Phase 1: Sorting", 100);

		List<Integer> transformed = Arrays.stream(pixels).boxed().collect(Collectors.toList());
		List<Integer> liminals = new ArrayList<Integer>();

		int change = 0;
		int pValue = 0;

		for(int i=0; i<pixels.length; i++){
			pb.setProgress(Math.round((double) i / pixels.length * 100));

			int value = useLuminance ? (int) PixelTool.luminance(pixels[i]) : (int) PixelTool.brightness(pixels[i]);

			if(i % w != 0 && Math.abs(change) > this.threshold || i % w == w - 1){
				liminals.add(i);
			}

			change += value - pValue;
			pValue = value;

			if(i % w == w - 1){
				change = 0;
				pValue = 0;
			}
		}

		int pLiminal = 0;
		for(Integer i: liminals){
			Comparator<Integer> sorter = useLuminance ? new LuminanceSorter() : new BrightnessSorter();
			Collections.sort(transformed.subList(pLiminal, i), sorter);
			pLiminal = i;
		}

		System.arraycopy(transformed.stream().mapToInt((i) -> i).toArray() , 0, pixels, 0, pixels.length);
	}
}
