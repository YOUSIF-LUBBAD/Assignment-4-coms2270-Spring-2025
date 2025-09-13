package hw4;

import api.Crossable;
import api.Path;
import api.Point;
import api.PositionVector;

/**
 * Shared engine for every concrete link.
 * <p>
 * – Implements the generic “hop to the next pair of points” logic<br>
 * – Supplies no-op locking callbacks so subclasses override only if needed
 *
 * @author Yousif Lubbad
 */
public abstract class AbstractLink implements Crossable
{
	/**
	 * Move the {@code PositionVector} from the endpoint we just reached
	 * to the first interior segment on the path that follows.
	 * <p>
	 * If {@code getConnectedPoint()} returns {@code null} (dead-end),
	 * the vector is left unchanged so the train can reverse.
	 */
	@Override
	public void shiftPoints(PositionVector pv)
	{
		Point here = pv.getPointB();          // endpoint just reached
		Point mate = getConnectedPoint(here); // where this link leads

		if (mate == null)
			return;                           // dead-end → stay put

		Path nextPath = mate.getPath();
		int  idx      = mate.getPointIndex();
		int  nextIdx  = (idx == 0) ? 1
		             : (idx == nextPath.getNumPoints() - 1) ? idx - 1
		             : idx + 1;               // (safety for interior pts)

		pv.setPointA(here);
		pv.setPointB(nextPath.getPointByIndex(nextIdx));
	}

	/* ------------------------------------------------------------
	 * Default no-ops so subclasses override only if they need locks
	 * ------------------------------------------------------------ */
	@Override public void trainEnteredCrossing() { }
	@Override public void trainExitedCrossing()  { }
}
