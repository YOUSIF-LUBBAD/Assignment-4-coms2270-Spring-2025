package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;
/**
 * StraightLink connects two path endpoints in a
 * “straight-through” fashion.  A third point may be supplied by
 * the layout builder (to keep the signature parallel with
 * {@code TurnLink/SwitchLink}), but it is <em>ignored</em>.
 *
 * <p>By extending {@link AbstractLink} we inherit the default
 * {@code shiftPoints(PositionVector)} implementation, so no code
 * duplication is necessary.</p>
 *
 * @author Yousif Lubbad
 */
public class StraightLink extends AbstractLink implements Crossable
{
    /* ------------------------------------------------------------------
     * Immutable endpoints
     * ------------------------------------------------------------------ */
    private final Point endA;
    private final Point endB;

    /**
     * @param input    one endpoint of the straight path
     * @param output   the opposite endpoint of the straight path
     * @param unused   third point (ignored, kept for uniform ctor)
     */
    public StraightLink(Point input, Point output, Point unused)
    {
        this.endA = input;
        this.endB = output;
    }

    /* ------------------------------------------------------------------
     * Concrete behaviours required by AbstractLink
     * ------------------------------------------------------------------ */

    /** Exactly two distinct paths are involved. */
    @Override
    public int getNumPaths()
    {
        return 2;
    }

    /**
     * Returns the mate of {@code p} if it is one of the endpoints,
     * otherwise {@code null}.
     */
    @Override
    public Point getConnectedPoint(Point p)
    {
        if (p.equals(endA))
            return endB;
        if (p.equals(endB))
            return endA;
        return null;
    }

    /* No locking or toggling needed for a fixed straight link. */
    @Override public void trainEnteredCrossing() { }
    @Override public void trainExitedCrossing()  { }
}
