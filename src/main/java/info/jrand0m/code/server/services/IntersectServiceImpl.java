package info.jrand0m.code.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import info.jrand0m.code.client.parser.Command;
import info.jrand0m.code.client.services.IntersectService;

import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.util.List;

public class IntersectServiceImpl extends RemoteServiceServlet implements IntersectService {
    public List<Command> getIntersection(List<Command> figure1, List<Command> figure2) {
        Area area1 = areaFromCommandList(figure1);
        Area area2 = areaFromCommandList(figure2);

        area1.intersect(area2);
        List<Command> result = commandsListFromArea(area1);
        return result;
    }

    private List<Command> commandsListFromArea(Area area1) {
        List<Command> result = null;
        return result;
    }

    //todo extract to command utils
    private Area areaFromCommandList(List<Command> commandList) {
        GeneralPath path = null;
        return new Area(path);
    }
}
