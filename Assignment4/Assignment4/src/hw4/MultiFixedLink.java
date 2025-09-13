package hw4;

import api.Crossable;
import api.Point;
import api.PointPair;
import api.PositionVector; // (needed only if you add custom logic later)
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * MultiFixedLink connects several pairs of path endpoints.
 * <p>
 * – The connections never change at runtime.  
 * – Each pair is bidirectional.  
 * – We inherit {@code shiftPoints()} from {@link AbstractLink}.
 *
 * @author Yousif Lubbad
 */
public class MultiFixedLink extends AbstractLink  implements Crossable
{
    /** Immutable list of endpoint pairs. */
    private final List<PointPair> connections;

    /**
     * Constructs a fixed link from the given pairs.
     *
     * @param pairs array of connected endpoint pairs
     *              (order does not matter, duplicates are ignored)
     */
    public MultiFixedLink(PointPair[] pairs)
    {
        connections = new ArrayList<>();
        for (PointPair pp : pairs)
        {
            connections.add(pp);
        }
    }

    /**
     * Returns the number of <b>distinct</b> paths represented
     * by all pairs (the spec counts each path only once).
     */
    @Override
    public int getNumPaths()
    {
        Set<api.Path> unique = new HashSet<>();
        for (PointPair pp : connections)
        {
            unique.add(pp.getPointA().getPath());
            unique.add(pp.getPointB().getPath());
        }
        return unique.size();
    }

    /**
     * If {@code p} is one endpoint in any pair, return the mate;
     * otherwise return {@code null}.
     */
    @Override
    public Point getConnectedPoint(Point p)
    {
        for (PointPair pp : connections)
        {
            if (p.equals(pp.getPointA()))
                return pp.getPointB();
            if (p.equals(pp.getPointB()))
                return pp.getPointA();
        }
        return null; // no connection → dead-end behaviour
    }

    /* These links do not lock or toggle, so the callbacks stay empty. */
    @Override public void trainEnteredCrossing() { }
    @Override public void trainExitedCrossing()  { }
}
