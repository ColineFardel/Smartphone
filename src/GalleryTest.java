/*
 * Project POO Smartphone
 * Author: Ismaël Moreno
 * Date creation: 08.06.2019
 * Date last modification: 09.06.2019
 */
import static org.junit.Assert.*;

import java.io.File;

import javax.swing.JPanel;

import org.junit.Test;

/**
 * This class is used to perform JUnit tests on Gallery class
 * @author Ismaël Moreno
 *
 */
public class GalleryTest {
	File filePictures = new File("./Images/Galerie/");
	String[] listPathPictures  = filePictures.list();
	JPanel screen = new JPanel();
	JPanel galleryPanel = new JPanel();
	
	/**
	 * This method is used to check if the reading of the file works correctly
	 */
	@Test
	public void matricePicturesTest() {
		Gallery gallery = new Gallery();
		gallery.initializeGallery();
		
		assertTrue(listPathPictures.length == 22);
	}
	/**
	 * This method allows to check if the galleryPanel is created and visible
	 */
	@Test
	public void galleryPanelTest() {
		Gallery gallery = new Gallery();
		gallery.configurateGalleryPanel();
		
		assertTrue(galleryPanel.isVisible());
	}
}
