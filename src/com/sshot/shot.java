package com.sshot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class shot {

	protected Shell shlrdrockcreation;
	private Text text;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			shot window = new shot();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public static String getDate() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("ddmmhhmmss");
		return ft.format(dNow);
	}

	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlrdrockcreation.open();
		shlrdrockcreation.layout();
		while (!shlrdrockcreation.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlrdrockcreation = new Shell();
		shlrdrockcreation.setSize(324, 78);
		shlrdrockcreation.setText("3rdRockCreation");
		text = new Text(shlrdrockcreation, SWT.BORDER);

		text.setBounds(126, 22, 182, 21);
		ArrayList<String> screenshots = new ArrayList<String>();

		Button stopButton = new Button(shlrdrockcreation, SWT.NONE);
		stopButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				System.out.println(screenshots);
				try {
					ShotToWordPad.saveImageToWord(
							"C:/Users/Public/Pictures/Sample Pictures/" + text.getText() + "__" + getDate() + ".docx",
							screenshots);

					shlrdrockcreation.close();
					JOptionPane.showMessageDialog(null, "CHECK : C:/Users/Public/Pictures/Sample Pictures/", "Saved", JOptionPane.INFORMATION_MESSAGE);

				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		stopButton.setBounds(0, 0, 100, 21);
		stopButton.setText("STOP");

		Button takeScreenShotButton = new Button(shlrdrockcreation, SWT.NONE);
		takeScreenShotButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String fileName = "C:/Users/Public/Pictures/testingScreenshots" + getDate() + "-FullScreenshot.jpg";
				ShotToWordPad.takeScreeShot(fileName);
				screenshots.add(fileName);
			}
		});
		takeScreenShotButton.setText("CAPTURE");
		takeScreenShotButton.setBounds(102, 0, 105, 21);

		Button cancelScreenShotButton = new Button(shlrdrockcreation, SWT.NONE);
		cancelScreenShotButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try{
					screenshots.remove(screenshots.size() - 1);
				}
				catch(ArrayIndexOutOfBoundsException e){
					e.getMessage();
				}
			}
		});
		cancelScreenShotButton.setText("CANCEL");
		cancelScreenShotButton.setBounds(208, 0, 100, 21);
		Label lblDocument = new Label(shlrdrockcreation, SWT.NONE);
		lblDocument.setBounds(45, 25, 55, 15);
		lblDocument.setText("Document :");

	}
}
