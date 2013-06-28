package matchat.tests;
import javax.activation.MimetypesFileTypeMap; 

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import java.util.Random;

import javax.imageio.ImageIO;
public class MessageBoxTests  {
	String imagefile1 ;
	
	@Test 
	public void initialTest () {
		
	}
	
	@Test 
	public void wrapImageGetData () {
		try {
			byte[] imageData = extractImageBytes(imagefile1) ;
		} catch (IOException e) {
			e.printStackTrace();
			fail("Could not open imagefile1");
		}
	}
	
	@Test
	public void savedFileTest() {
	}	
	
	private byte[] extractImageBytes (String ImageName) throws IOException {
		  MimetypesFileTypeMap Mimer = new MimetypesFileTypeMap() ;
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage.getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );	
	}
}
