package info.jrand0m.code.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import info.jrand0m.code.client.services.IntersectService;
import info.jrand0m.code.server.Utils;

import java.awt.geom.Area;
import java.util.List;

public class IntersectServiceImpl extends RemoteServiceServlet implements IntersectService {
    public String getIntersection(List<String> figures) {
        if (figures.size()>0){
            Area base = Utils.areaFromCommandString(figures.get(0));
            for (int i=1;i<figures.size();i++){
                Area nextArea = Utils.areaFromCommandString(figures.get(i));
                base.intersect(nextArea);
            }
            return Utils.commandStringFromArea(base);
        }
        return "";
    }


}
