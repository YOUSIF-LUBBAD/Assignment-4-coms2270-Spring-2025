package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;

/**
 * CouplingLink joins the <i>high</i> endpoint of one path to the
 * <i>low</i> endpoint of a second path (and vice-versa).
 *
 * It is fixed – no switching or locking – so the only behaviour we
 * need in addition to {@code getConnectedPoint} is a correct
 * {@code shiftPoints} implementation.
 *
 * @author Yousif Lubbad
 */
public class CouplingLink extends AbstractLink implements Crossable
{
    /* ------------------------------------------------------------------ */
    private final Point path1High;
    private final Point path2Low;

    /**
     * @param path1High  high-endpoint of the first path
     * @param path2Low   low-endpoint  of the second path
     */
    public CouplingLink(Point path1High, Point path2Low)
    {
        this.path1High = path1High;
        this.path2Low  = path2Low;
    }

    /* ------------------------------------------------------------------ */
    @Override
    public int getNumPaths()
    {
        return 2;
    }

    /**
     * Returns the mate of p if p is one of the two endpoints,
     * otherwise {@code null}.
     */
    @Override
    public Point getConnectedPoint(Point p)
    {
        if (p == path1High) return path2Low;
        if (p == path2Low)  return path1High;
        return null;
    }

    /* ------------------------------------------------------------------
     *  Traversable – move the PositionVector onto the next path
     * ------------------------------------------------------------------ */
    @Override
    public void shiftPoints(PositionVector pv)
    {
        // current end-point (point B) determines where we go next
        Point entry = getConnectedPoint(pv.getPointB());
        if (entry == null) {
            // we’re not at an endpoint that belongs to this link – do nothing
            return;
        }

        int idx   = entry.getPointIndex();
        int last  = entry.getPath().getNumPoints() - 1;
        int step  = (idx == 0)      ? 1     // low end → step forward
                  : (idx == last)   ? -1    // high end → step back
                  : 0;                     // interior – invalid for a link

        if (step == 0) {
            // link endpoints are always path ends; interior index means error
            return;
        }

        pv.setPointA(entry);
        pv.setPointB(entry.getPath().getPointByIndex(idx + step));
        // relative distance stays exactly the same, per assignment spec
    }

    /* link never locks or toggles, so callbacks stay as no-ops */
    @Override public void trainEnteredCrossing() { }
    @Override public void trainExitedCrossing()  { }
}
