package simulation;

import api.Crossable;
import api.Path;
import hw4.CouplingLink;
import hw4.StraightLink;

public class TestTrack2 extends Track {
	public TestTrack2() {
		Path path1 = addPathType(PathTypes.westToEast, 5, 5);
		Path path2 = addPathType(PathTypes.westToEast, 6, 5);
		Path path3 = addPathType(PathTypes.curveWestToNorth, 7, 5);
		Crossable link = new CouplingLink(path2.getHighpoint(), path3.getLowpoint());
		path2.setHighEndpointLink(link);
		path3.setLowEndpointLink(link);
		Path path4 = addPathType(PathTypes.northToSouth, 7, 4);
		link = new CouplingLink(path3.getHighpoint(), path4.getHighpoint());
		path3.setHighEndpointLink(link);
		path4.setHighEndpointLink(link);
		Path path5 = addPathType(PathTypes.curveWestToSouth, 7, 3);
		link = new CouplingLink(path4.getLowpoint(), path5.getLowpoint());
		path4.setLowEndpointLink(link);
		path5.setLowEndpointLink(link);
		Path path6 = addPathType(PathTypes.westToEast, 6, 3);
		link = new CouplingLink(path5.getHighpoint(), path6.getHighpoint());
		path5.setHighEndpointLink(link);
		path6.setHighEndpointLink(link);
		Path path7 = addPathType(PathTypes.curveSouthToEast, 5, 3);
		link = new CouplingLink(path6.getLowpoint(), path7.getLowpoint());
		path6.setLowEndpointLink(link);
		path7.setLowEndpointLink(link);
		Path path8 = addPathType(PathTypes.northToSouth, 5, 4);
		link = new CouplingLink(path7.getHighpoint(), path8.getLowpoint());
		path7.setHighEndpointLink(link);
		path8.setLowEndpointLink(link);
		Path path9 = addPathType(PathTypes.curveNorthToEast, 5, 5);
		link = new CouplingLink(path8.getHighpoint(), path9.getLowpoint());
		path8.setHighEndpointLink(link);
		path9.setLowEndpointLink(link);
		link = new StraightLink(path2.getLowpoint(), path1.getHighpoint(), path9.getHighpoint());
		path2.setLowEndpointLink(link);
		path1.setHighEndpointLink(link);
		path9.setHighEndpointLink(link);
	}
}
