package simulation;

import api.Crossable;
import api.Path;
import api.PointPair;
import hw4.CouplingLink;
import hw4.MultiFixedLink;
import hw4.StraightLink;

public class TestTrack3 extends Track {
	public TestTrack3() {
		Path path1 = addPathType(PathTypes.westToEast, 5, 5);
		Path path2 = addPathType(PathTypes.curveWestToNorth, 6, 5);
		Path path3 = addPathType(PathTypes.curveSouthToEast, 6, 4);
		Path path4 = addPathType(PathTypes.curveWestToSouth, 7, 4);
		Path path5 = addPathType(PathTypes.curveWestToNorth, 7, 5);
		Path path6 = addPathType(PathTypes.curveNorthToEast, 6, 5);
		Path path7 = addPathType(PathTypes.northToSouth, 6, 4);
		Path path8 = addPathType(PathTypes.northToSouth, 6, 3);
		Path path9 = addPathType(PathTypes.curveWestToSouth, 6, 2);
		Path path10 = addPathType(PathTypes.curveSouthToEast, 5, 2);
		Path path11 = addPathType(PathTypes.curveNorthToEast, 5, 3);
		Path path12 = addPathType(PathTypes.curveWestToSouth, 6, 3);
		Path path13 = addPathType(PathTypes.northToSouth, 6, 5);
		Path path14 = addPathType(PathTypes.northToSouth, 6, 6);
		Path path15 = addPathType(PathTypes.curveNorthToEast, 6, 7);
		Path path16 = addPathType(PathTypes.curveWestToNorth, 7, 7);
		Path path17 = addPathType(PathTypes.curveWestToSouth, 7, 6);
		Path path18 = addPathType(PathTypes.curveNorthToEast, 6, 6);
		Path path19 = addPathType(PathTypes.curveWestToSouth, 6, 4);
		Path path20 = addPathType(PathTypes.curveSouthToEast, 5, 4);
		Path path21 = addPathType(PathTypes.curveNorthToEast, 5, 5);

		Crossable link = new StraightLink(path2.getLowpoint(), path1.getHighpoint(), path21.getHighpoint());
		path2.setLowEndpointLink(link);
		path1.setHighEndpointLink(link);
		path21.setHighEndpointLink(link);
		
		PointPair[] points = {new PointPair(path2.getHighpoint(), path3.getHighpoint()),
				new PointPair(path6.getLowpoint(), path7.getHighpoint()),
				new PointPair(path7.getHighpoint(), path13.getLowpoint()),
				new PointPair(path13.getLowpoint(), path19.getLowpoint()),
				new PointPair(path19.getHighpoint(), path6.getHighpoint()),
				new PointPair(path3.getHighpoint(), path2.getHighpoint())};
		Crossable link2 = new MultiFixedLink(points);
		path2.setHighEndpointLink(link2);
		path3.setLowEndpointLink(link2);
		path6.setLowEndpointLink(link2);
		path7.setHighEndpointLink(link2);
		path13.setLowEndpointLink(link2);
		path19.setLowEndpointLink(link2);
		
		Crossable link3 = new CouplingLink(path3.getLowpoint(), path4.getHighpoint());
		path3.setLowEndpointLink(link3);
		path4.setHighEndpointLink(link3);
		
		Crossable link4 = new CouplingLink(path4.getLowpoint(), path5.getHighpoint());
		path4.setLowEndpointLink(link4);
		path5.setHighEndpointLink(link4);
		
		Crossable link5 = new CouplingLink(path5.getLowpoint(), path6.getHighpoint());
		path5.setLowEndpointLink(link5);
		path6.setHighEndpointLink(link5);

		Crossable link6 = new StraightLink(path7.getLowpoint(), path8.getHighpoint(), path12.getLowpoint());
		path7.setLowEndpointLink(link6);
		path8.setHighEndpointLink(link6);
		path12.setLowEndpointLink(link6);
		
		Crossable link7 = new CouplingLink(path8.getLowpoint(), path9.getLowpoint());
		path8.setLowEndpointLink(link7);
		path9.setLowEndpointLink(link7);

		Crossable link8 = new CouplingLink(path9.getHighpoint(), path10.getLowpoint());
		path9.setHighEndpointLink(link8);
		path10.setLowEndpointLink(link8);
		
		Crossable link9 = new CouplingLink(path10.getHighpoint(), path11.getLowpoint());
		path10.setHighEndpointLink(link9);
		path11.setLowEndpointLink(link9);

		Crossable link10 = new CouplingLink(path11.getHighpoint(), path12.getHighpoint());
		path11.setHighEndpointLink(link10);
		path12.setHighEndpointLink(link10);
		
		Crossable link11 = new StraightLink(path13.getHighpoint(), path14.getLowpoint(), path18.getLowpoint());
		path13.setHighEndpointLink(link11);
		path14.setLowEndpointLink(link11);
		path18.setLowEndpointLink(link11);

		Crossable link12 = new CouplingLink(path14.getHighpoint(), path15.getLowpoint());
		path14.setHighEndpointLink(link12);
		path15.setLowEndpointLink(link12);

		Crossable link13 = new CouplingLink(path15.getHighpoint(), path16.getLowpoint());
		path15.setHighEndpointLink(link13);
		path16.setLowEndpointLink(link13);
		
		Crossable link14 = new CouplingLink(path16.getHighpoint(), path17.getLowpoint());
		path16.setHighEndpointLink(link14);
		path17.setLowEndpointLink(link14);

		Crossable link15 = new CouplingLink(path17.getHighpoint(), path18.getHighpoint());
		path17.setHighEndpointLink(link15);
		path18.setHighEndpointLink(link15);

		Crossable link16 = new CouplingLink(path19.getHighpoint(), path20.getLowpoint());
		path19.setHighEndpointLink(link16);
		path20.setLowEndpointLink(link16);

		Crossable link17 = new CouplingLink(path20.getHighpoint(), path21.getLowpoint());
		path20.setHighEndpointLink(link17);
		path21.setLowEndpointLink(link17);
	}
}
