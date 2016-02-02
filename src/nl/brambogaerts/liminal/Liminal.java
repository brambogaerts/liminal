package nl.brambogaerts.liminal;


import java.util.Date;

import nl.brambogaerts.liminal.config.Configuration;
import nl.brambogaerts.liminal.image.Image;
import nl.brambogaerts.liminal.transform.SortTransformer;
import nl.brambogaerts.liminal.transform.BlurTransformer;
import nl.brambogaerts.liminal.tools.CLITool;

public class Liminal {
	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "true");

		Configuration.add("input", "undefined");
		Configuration.add("output", new Date().getTime() + ".png");
		Configuration.add("threshold", "180");
		Configuration.add("blurX", "80");
		Configuration.add("blurY", "10");
		Configuration.add("luminance", "true");
		Configuration.add("sort", "true");
		Configuration.add("blur", "true");

		Configuration.use(
			CLITool.parseArguments(args)
		);

		Configuration.print();

		Image img = new Image(
			Configuration.get("input")
		);

		if(Configuration.getBoolean("sort")){
			img.transform(
				new SortTransformer(
					Configuration.getDouble("threshold"),
					Configuration.getBoolean("luminance")
				)
			);
		}

		if(Configuration.getBoolean("blur")){
			img.transform(
				new BlurTransformer(
					Configuration.getDouble("blurX"),
					Configuration.getDouble("blurY")
				)
			);
		}

		img.save(
			Configuration.get("output")
		);
	}
}
