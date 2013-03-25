package info.jrand0m.code.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import info.jrand0m.code.client.services.IntersectService;
import info.jrand0m.code.server.CommandUtils;
import info.jrand0m.code.shared.Command;

import java.awt.geom.Area;
import java.util.List;

public class IntersectServiceImpl extends RemoteServiceServlet implements IntersectService {
    public List<Command> getIntersection(List<Command> figure1, List<Command> figure2) {

        Area area1 = CommandUtils.areaFromCommandList(figure1);
        Area area2 = CommandUtils.areaFromCommandList(figure2);

        area1.intersect(area2);

        List<Command> result = CommandUtils.commandsListFromArea(area1);
        return result;
    }


}
