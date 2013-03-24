package info.jrand0m.code.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import info.jrand0m.code.client.parser.Command;

import java.util.List;

@RemoteServiceRelativePath("IntersectService")
public interface IntersectService extends RemoteService {
    // Sample interface method of remote interface
    List<Command> getIntersection(List<Command> figure1, List<Command> figure2);


}

