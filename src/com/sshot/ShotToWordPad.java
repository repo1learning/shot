package com.sshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class ShotToWordPad {

	public static void saveImageToWord(String documentName,ArrayList<String> screens) throws Exception, FileNotFoundException, IOException {

		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p = doc.createParagraph();
		XWPFRun xwpfRun = p.createRun();

		for (String imgFile : screens) {
			int format = XWPFDocument.PICTURE_TYPE_JPEG;
			xwpfRun.addBreak();
			xwpfRun.addPicture(new FileInputStream(imgFile), format, imgFile, Units.toEMU(400), Units.toEMU(200));
		}

		FileOutputStream out = new FileOutputStream(documentName);
		doc.write(out);
		out.close();
	}

	
	public static void takeScreeShot(String fileName) {

		try {
			Robot robot = new Robot();
			String format = "jpg";
			
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(fileName));
				} catch (AWTException | IOException ex) {
			System.err.println(ex);
		}
	}
}