/*
 * Project POO Smartphone
 * Author: Ismaël Moreno
 * Date creation: 08.06.2019
 * Date last modification: 08.06.2019
 */
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class GalleryTest {
	File filePictures = new File("./Images/Galerie/");
	String[] listPathPictures  = filePictures.list();
	
	@Test
	public void matricePicturesTest() {
		Gallery gallery = new Gallery();
		gallery.initializeGallery();
		
		assertTrue(listPathPictures.length == 22);
	}
}
