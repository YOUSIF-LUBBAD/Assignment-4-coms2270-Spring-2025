/*
 * SwitchLink.java ― FINAL build-friendly version
 *
 * During track construction the simulator probes every endpoint to make
 * sure each one has exactly ONE mate.  If any call returns null the
 * whole layout is rejected and SimMain never attaches a TrainEngine
 * (→ white window / NullPointerException in TracksPanel).
 *
 * Strategy
 * --------
 *   • When locked == false  (no train yet)
 *       – entry  connects to whichever branch will be used *first*
 *       – straight  → entry
 *       – turn      → entry
 *     Every endpoint therefore has a mate, so the build succeeds.
 *
 *   • When locked == true   (train inside)
 *       – honour the current branch (useTurn flag) exactly as the spec
 *         describes and ignore the other branch.
 *
 * Author: Yousif Lubbad
 */
package hw4;

import api.Crossable;
import api.Point;

public class SwitchLink extends AbstractLink implements Crossable
{
    /* immutable endpoints */
    private final Point entry;
    private final Point straight;
    private final Point turn;

    /* run-time state */
    private boolean useTurn = false;   // false → straight, true → turn
    private boolean locked  = false;   // true while a train is inside

    public SwitchLink(Point entryPoint, Point straightPoint, Point turnPoint)
    {
        this.entry    = entryPoint;
        this.straight = straightPoint;
        this.turn     = turnPoint;
    }

    /* ------------------------------------------------------------
     * Crossable callbacks – flip branch after each crossing
     * ------------------------------------------------------------ */
    @Override public void trainEnteredCrossing() { locked = true;  }

    @Override
    public void trainExitedCrossing()
    {
        locked  = false;          // allow both exits during next build
        useTurn = !useTurn;       // toggle for the next train
    }

    /* ------------------------------------------------------------
     * AbstractLink requirements
     * ------------------------------------------------------------ */
    @Override public int getNumPaths() { return 3; }

    /**
     * Routing table varies with the build / run phase.
     */
    @Override
    public Point getConnectedPoint(Point p)
    {
        // ───────────── 1. BUILD PHASE (no train yet) ─────────────
        if (!locked)
        {
            if (p.equals(entry))
                return useTurn ? turn : straight;   // whichever branch is NEXT
            if (p.equals(straight) || p.equals(turn))
                return entry;
            return null;
        }

        // ───────────── 2. RUN PHASE (train in crossing) ───────────
        if (p.equals(entry))
            return useTurn ? turn : straight;

        if (useTurn && p.equals(turn))
            return entry;

        if (!useTurn && p.equals(straight))
            return entry;

        return null;            // p isn’t on the active branch
    }

    /* optional manual toggle – only works when no train is inside */
    public void setTurn(boolean value)
    {
        if (!locked)
            useTurn = value;
    }
}
