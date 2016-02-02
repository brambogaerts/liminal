package nl.brambogaerts.liminal.transform;

import nl.brambogaerts.liminal.progress.ProgressBar;
import nl.brambogaerts.liminal.sorters.LuminanceSorter;
import nl.brambogaerts.liminal.sorters.BrightnessSorter;
import nl.brambogaerts.liminal.tools.PixelTool;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class SortTransformer extends Transformer {
	double threshold;

	public SortTransformer(double threshold){
		super();
		this.threshold = threshold;
	}

	@Override
	public void transform(int[] pixels, int w, int h){
		ProgressBar pb = new ProgressBar("Phase 1: Sorting", 100);

		List<Integer> transformed = Arrays.stream(pixels).boxed().collect(Collectors.toList());
		List<Integer> liminals = new ArrayList<Integer>();

		int change = 0;
		int pLuminance = 0;

		for(int i=0; i<pixels.length; i++){
			pb.setProgress(Math.round((double) i / pixels.length * 100));
			int luminance = (int) PixelTool.luminance(pixels[i]);

			if(i % w != 0 && Math.abs(change) > this.threshold || i % w == w - 1){
				liminals.add(i);
			}

			change += luminance - pLuminance;
			pLuminance = luminance;

			if(i % w == w - 1){
				change = 0;
				pLuminance = 0;
			}
		}

		int pLiminal = 0;
		for(Integer i: liminals){
			Collections.sort(transformed.subList(pLiminal, i), new LuminanceSorter());
			pLiminal = i;
		}

		System.arraycopy(transformed.stream().mapToInt((i) -> i).toArray() , 0, pixels, 0, pixels.length);
	}
}
