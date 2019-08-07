package org.metaversemedia.mctexture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: mc-texture-anim-compiler <input directory> [output file]");
			return;
		}
		
		File inputFolder = new File(args[0]);
		String targetPath = null;
		
		// Get target filename
		if (args.length < 2) {
			targetPath = Paths.get(inputFolder.getParent(),"animated_texture.png").toString();
		} else {
			// Append file extension
			if (args[1].substring(args[1].length() - 4).matches(".png")) {
				targetPath = args[1];
			} else {
				targetPath = args[1]+".png";
			}
		}
		
		// Load image sequence
		ImageSequence imageSequence = null;
		try {
			imageSequence = ImageSequence.fromFolder(inputFolder);
		} catch (IOException e) {
			System.out.println("Error reading image sequence!");
			e.printStackTrace();
			return;
		}
		
		// Compile texture
		BufferedImage compiledTexture = TextureCompiler.compileAnimTexture(imageSequence);
		
		// Write compiled texture to file
		try {
			System.out.println("Writing compiled image to: "+targetPath);
			ImageIO.write(compiledTexture, "PNG", new File(targetPath));
		} catch (IOException e) {
			System.out.println("Error writing image file!");
			e.printStackTrace();
			return;
		}

	}

}
