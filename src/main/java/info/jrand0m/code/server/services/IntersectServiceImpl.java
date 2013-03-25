package info.jrand0m.code.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import info.jrand0m.code.client.services.IntersectService;
import info.jrand0m.code.server.Utils;

import java.awt.geom.Area;

public class IntersectServiceImpl extends RemoteServiceServlet implements IntersectService {
    public String getIntersection(String figure1, String figure2) {

        Area area1 = Utils.areaFromCommandString(figure1);
        Area area2 = Utils.areaFromCommandString(figure2);

        area1.intersect(area2);

        String result = Utils.commandsListFromArea(area1);
        return result;
    }


}
