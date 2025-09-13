/*
 * MultiSwitchLink.java 
 * Author: Yousif Lubbad
 */
package hw4;

import api.Crossable;
import api.Point;
import api.PointPair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MultiSwitchLink extends AbstractLink implements Crossable
{
	/* all available endpoint pairs, in construction order */
	private final List<PointPair> connections = new ArrayList<>();

	/* index of the pair currently selected for traffic */
	private int activeIndex = 0;

	/* becomes true after the first explicit call to switchConnection() */
	private boolean manualSelect = false;

	/* true while a train is inside the crossing (locks the switch) */
	private boolean locked = false;

	/* ------------------------------------------------------------ */
	public MultiSwitchLink(PointPair[] pairs)
	{
		for (PointPair pp : pairs)
			connections.add(pp);
	}

	/* ------------------------------------------------------------ */
	@Override
	public void trainEnteredCrossing()
	{
		locked = true;          // freeze current selection
	}

	@Override
	public void trainExitedCrossing()
	{
		locked = false;         // allow new selection
	}

	/* ------------------------------------------------------------ */
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

	@Override
	public Point getConnectedPoint(Point p)
	{
		if (connections.isEmpty())
			return null;

		/* phase 1: track-building (no manual switch / no train inside) */
		if (!manualSelect && !locked)
		{
			for (PointPair pp : connections)
			{
				if (p == pp.getPointA()) return pp.getPointB();
				if (p == pp.getPointB()) return pp.getPointA();
			}
			return null;
		}

		/* phase 2: manual selection active OR train inside → only active pair */
		PointPair active = connections.get(activeIndex);
		if (p == active.getPointA()) return active.getPointB();
		if (p == active.getPointB()) return active.getPointA();
		return null;
	}

	/* ------------------------------------------------------------ */
	public void switchConnection(PointPair newPair)
	{
		manualSelect = true;

		int idx = findMatchingPairIndex(newPair);
		if (idx < 0)
		{
			connections.add(newPair);
			activeIndex = connections.size() - 1;
		}
		else
		{
			activeIndex = idx;
		}
	}

	/* legacy overload kept for SimpleTests */
	public void switchConnection(PointPair newPair, int ignored)
	{
		switchConnection(newPair);
	}

	/* ------------------------------------------------------------ */
	private int findMatchingPairIndex(PointPair target)
	{
		for (int i = 0; i < connections.size(); i++)
			if (sameEndpoints(connections.get(i), target))
				return i;
		return -1;
	}

	private static boolean sameEndpoints(PointPair a, PointPair b)
	{
		return (a.getPointA() == b.getPointA() && a.getPointB() == b.getPointB()) ||
		       (a.getPointA() == b.getPointB() && a.getPointB() == b.getPointA());
	}
}
