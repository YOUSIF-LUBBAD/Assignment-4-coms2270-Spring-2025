package simulation;

import api.Crossable;
import api.Path;
import hw4.DeadEndLink;

public class TestTrack1 extends Track {
	public TestTrack1() {
		Path path = addPathType(PathTypes.westToEast, 5, 5);
		Crossable link = new DeadEndLink();
		path.setHighEndpointLink(link);
	}
}
