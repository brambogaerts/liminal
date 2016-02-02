package nl.brambogaerts.liminal.tools;

public abstract class PixelTool {
	public static double red(int pixel){
		return (pixel & 0xFF0000) >> 16;
	}

	public static double green(int pixel){
		return (pixel & 0xFF00) >> 8;
	}

	public static double blue(int pixel){
		return pixel & 0xFF;
	}

	public static double brightness(int pixel){
		return (red(pixel) + green(pixel) + blue(pixel)) / 3;
	}

	public static double luminance(int pixel){
		return red(pixel) * .3 + green(pixel) * .59 + blue(pixel) * .11;
	}
}
