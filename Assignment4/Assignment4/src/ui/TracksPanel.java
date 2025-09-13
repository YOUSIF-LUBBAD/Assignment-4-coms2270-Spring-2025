package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import api.Path;
import api.Point;
import api.PositionVector;
import api.Train;
import simulation.Simulation;

public class TracksPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Simulation simulation;
	private Stroke solid = new BasicStroke(3);
	private int width = 600;
	private int height = 600;
	private BufferedImage trainEngineImage;

	public TracksPanel(Simulation simulation) {
		this.simulation = simulation;
		Dimension dim = new Dimension(width, height);
		setBackground(Color.WHITE);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		String imagePath = "resources/train-engine.png";
		try {
			trainEngineImage = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		ArrayList<Path> paths = simulation.getTrack().getPaths();
		for (Path path : paths) {
			for (int i = 0; i < path.getNumPoints() - 1; i += 2) {
				Point pointA = path.getPointByIndex(i);
				Point pointB = path.getPointByIndex(i + 1);
				g2.setColor(Color.BLACK);
				g2.setStroke(solid);
				g2.drawLine((int) pointA.getX(), (int) pointA.getY(), (int) pointB.getX(), (int) pointB.getY());
			}
		}

		Train train = simulation.getTrain();
		PositionVector position = train.getPosition();
		double rad = Math.atan2(position.getPointB().getY() - position.getPointA().getY(),
				position.getPointB().getX() - position.getPointA().getX());
		double sin = Math.abs(Math.sin(rad));
		double cos = Math.abs(Math.cos(rad));
		int w = trainEngineImage.getWidth();
		int h = trainEngineImage.getHeight();
		int neww = (int) Math.floor(w * cos + h * sin);
		int newh = (int) Math.floor(h * cos + w * sin);
		BufferedImage rotated = new BufferedImage(neww, newh, trainEngineImage.getType());
		
		Graphics2D graphic = rotated.createGraphics();
		graphic.translate((neww - w) / 2, (newh - h) / 2);
		graphic.rotate(rad, w / 2, h / 2);
		graphic.drawRenderedImage(trainEngineImage, null);
		graphic.dispose();

		g2.drawImage(rotated, (int) position.getPointB().getX() - 11, (int) position.getPointB().getY() - 11, null);
	}
}
