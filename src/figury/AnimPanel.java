package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	int width, height;

	boolean trace = false;

	private ArrayList<Figura> FigureList;

	private int delay = 30;

	private Timer timer;

	private static int numer = 0;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
		FigureList = new ArrayList<>();
	}

	public void initialize() {
		width = getWidth();
		height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setBackground(Color.WHITE);
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void Update(){
		Image old = image;
		initialize();
		buffer.setColor(Color.WHITE);
		buffer.fillRect(0,0,width,height);
		buffer.drawImage(old, 0,0,null);
		for(Iterator<Figura> iterator = FigureList.iterator(); iterator.hasNext();){
			Figura fig = iterator.next();
			if (fig.isOutOfBounds()) {
				timer.removeActionListener(fig);
				iterator.remove();
			}
			else
				fig.Update(buffer, width, height);
		}

		if (!timer.isRunning())
			device.drawImage(image, 0, 0, null);
	}

	void addFig() {
		Figura fig;
		switch(numer++ % 3){
			case 0: fig = new Kwadrat(buffer, delay, width, height);
				timer.addActionListener(fig);
				new Thread(fig).start();
				FigureList.add(fig);
				break;
			case 1: fig = new Elipsa(buffer, delay, width, height);
				timer.addActionListener(fig);
				new Thread(fig).start();
				FigureList.add(fig);
				break;
			case 2: fig = new Pentagon(buffer, delay, width, height);
				timer.addActionListener(fig);
				new Thread(fig).start();
				FigureList.add(fig);
				break;
		}
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	public boolean getTrace(){
		return trace;
	}

	public void setTrace(boolean t){
		trace = t;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		if(!trace)
			buffer.clearRect(0, 0, getWidth(), getHeight());
	}
}
