package org.metaversemedia.mctexture;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Class that compiles an image sequence into a long BufferedImage
 * @author Igrium
 *
 */
public class TextureCompiler {
	/**
	 * Compile an image sequence into a texture that Minecraft can read
	 * @param imageSequence
	 * @return
	 */
	public static BufferedImage compileAnimTexture(ImageSequence imageSequence) {
		System.out.println("Compiling image...");
		// Get resolution (fh: frame height)
		int w = imageSequence.get(0).getWidth();
		int fh = imageSequence.get(0).getHeight();
		int h = fh*imageSequence.length();
		
		BufferedImage combined = new BufferedImage(w, h, imageSequence.get(0).getType());
		
		// Draw all images to target
		Graphics g = combined.getGraphics();
		for (int i = 0; i < imageSequence.length(); i++) {
			g.drawImage(imageSequence.get(i), 0, i*fh, null);
		}
		
		return combined;
	}
}
