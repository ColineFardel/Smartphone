/*
 * Project POO Smartphone
 * Author: Ismaël Moreno
 * Date creation: 08.06.2019
 * Date last modification: 08.06.2019
 */
import static org.junit.Assert.*;

import javax.swing.JPanel;

import org.junit.Test;

public class GalleryTest2 {
	JPanel screen = new JPanel();
	JPanel galleryPanel = new JPanel();
	 
	@Test
	public void galleryPanelTest() {
		Gallery gallery = new Gallery();
		gallery.configurateGalleryPanel();
		
		assertTrue(galleryPanel.isVisible());
	}
}
