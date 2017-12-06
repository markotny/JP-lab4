package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class Pentagon extends Figura {

    private final int x1 = (int)(20*Math.cos(2*Math.PI/5));
    private final int x2 = (int)(20*Math.cos(Math.PI/5));
    private final int y1 = (int)(20*Math.sin(2*Math.PI/5));
    private final int y2 = (int)(20*Math.sin(4*Math.PI/5));

    public Pentagon(Graphics2D buffer, int delay, int width, int height) {
        super(buffer,delay,width,height);
        Polygon p = new Polygon();

        p.addPoint(20,0);
        p.addPoint(x1,y1);
        p.addPoint(-x2,y2);
        p.addPoint(-x2,-y2);
        p.addPoint(x1,-y1);

        shape = p;
        aft = new AffineTransform();
        area = new Area(shape);
    }

}
