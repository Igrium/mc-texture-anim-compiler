package org.metaversemedia.mctexture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

/**
 * Class that represents a sequence of images
 * @author Igrium
 *
 */
public class ImageSequence implements Iterable<BufferedImage> {
	private ArrayList<BufferedImage> images;
	
	public ImageSequence() {
		images = new ArrayList<BufferedImage>();
	}
	
	/**
	 * Gets the length of the image sequence in frames
	 * @return length
	 */
	public int length() {
		return images.size();
	}
	
	/**
	 * 
	 * Gets the image in a specific array of the sequence
	 * @param index
	 * @return
	 */
	public BufferedImage get(int index) {
		return images.get(index);
	}
	
	/**
	 * Load an image sequence from a folder
	 * @param directory Folder to load from
	 * @return Generated image sequence
	 * @throws IOException
	 */
	public static ImageSequence fromFolder(File directory) throws IOException {
		System.out.println("Loading directory: "+directory);
		
		// Get all files
		List<File> directoryListing = Arrays.asList(directory.listFiles());
		
		// Filter out pngs
		ArrayList<File> images = new ArrayList<File>();
		for (File f : directoryListing) {
			if (FilenameUtils.getExtension(f.getName()).matches(".png"));
			images.add(f);
		}
		
		// Sort into alphabetical order
		java.util.Collections.sort(images);
		
		// Add all images to image sequence
		ImageSequence imageSequence = new ImageSequence();
		for (File f : images) {
			System.out.println("Loading image: "+f.getName());
			
			imageSequence.images.add(ImageIO.read(f));
		}
		
		return imageSequence;
	}

	@Override
	public Iterator<BufferedImage> iterator() {
		// TODO Auto-generated method stub
		return images.iterator();
	}
	
}
