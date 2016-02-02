package nl.brambogaerts.liminal.transform;

import nl.brambogaerts.liminal.progress.ProgressBar;

public class BlurTransformer extends Transformer {
	double blurX, blurY;

	public BlurTransformer(double blurX, double blurY){
		super();
		this.blurX = blurX;
		this.blurY = blurY;
	}

	@Override
	public void transform(int[] pixels, int w, int h){
		ProgressBar pb = new ProgressBar("Phase 2: Blurring", 100);

		for(int i=0; i<pixels.length; i++){
			pb.setProgress(Math.round((double) i / pixels.length * 100));

			int x = i % w;
			int y = (int) Math.floor(i/w);

			int randomX = (int) Math.min(w - 1, Math.max(0, x + Math.random() * blurX * 2 - blurX));
			int randomY = (int) Math.min(h - 1, Math.max(0, y + Math.random() * blurY * 2 - blurY));

			int newIndex = randomY * w + randomX;
			int temp = pixels[i];

			pixels[i] = pixels[newIndex];
			pixels[newIndex] = temp;
		}
	}
}
