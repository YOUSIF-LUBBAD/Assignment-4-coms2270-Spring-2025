package hw4;


import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * DeadEndLink represents a spur that connects to exactly **one** path
 * and then stops—there is no exit point, so a train must reverse out.
 *
 * <p>By extending {@link AbstractLink} we inherit a default
 * {@code shiftPoints(PositionVector)} implementation.  Since
 * {@code getConnectedPoint} always returns {@code null}, that default
 * method simply leaves the {@code PositionVector} unchanged—exactly
 * what a dead-end should do.</p>
 *
 * @author Yousif Lubbad
 */
public class DeadEndLink extends AbstractLink implements Crossable
{
    /** There is only one distinct path. */
    @Override
    public int getNumPaths()
    {
        return 1;
    }

    /**
     * A dead-end never connects <em>through</em> to another point,
     * so the answer is always {@code null}.
     */
    @Override
    public Point getConnectedPoint(Point p)
    {
        return null;
    }

    /* Callbacks remain no-ops; nothing to lock or unlock. */
}
