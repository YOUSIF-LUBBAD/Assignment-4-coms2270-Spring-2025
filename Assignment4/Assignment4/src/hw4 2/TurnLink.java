package hw4;

import api.Crossable;
import api.Point;
import api.PositionVector;
/**
 * A fixed 90-degree turn: a train entering at {@code entry} always
 * exits at {@code turn}.  The straight-ahead endpoint is present only
 * so the constructor signature matches {@link SwitchLink}.
 *
 * <p>The inherited {@code shiftPoints(PositionVector)} from
 * {@link AbstractLink} handles the interior stepping, so this class
 * supplies only the routing logic.</p>
 *
 * @author Yousif Lubbad
 */
public class TurnLink extends AbstractLink implements Crossable
{
    /* ------------------------------------------------------------------
     * Immutable endpoints (straight is ignored).
     * ------------------------------------------------------------------ */
    private final Point entry;
    private final Point straightUnused;
    private final Point turn;

    /**
     * @param entryPoint     common entry/exit endpoint
     * @param straightPoint  straight-ahead endpoint (ignored)
     * @param turnPoint      endpoint reached when turning
     */
    public TurnLink(Point entryPoint, Point straightPoint, Point turnPoint)
    {
        this.entry          = entryPoint;
        this.straightUnused = straightPoint;
        this.turn           = turnPoint;
    }

    /* ------------------------------------------------------------------
     * Link behaviour
     * ------------------------------------------------------------------ */

    /** Exactly two distinct paths participate: entry ↔ turn. */
    @Override
    public int getNumPaths()
    {
        return 2;
    }

    /**
     * Routing table:
     * <pre>
     * entry ↔ turn
     * others → null
     * </pre>
     */
    @Override
    public Point getConnectedPoint(Point p)
    {
        if (p.equals(entry))
            return turn;
        if (p.equals(turn))
            return entry;
        return null;
    }

    /* No locking or toggling needed for a fixed turn link. */
    @Override public void trainEnteredCrossing() { }
    @Override public void trainExitedCrossing()  { }
}
