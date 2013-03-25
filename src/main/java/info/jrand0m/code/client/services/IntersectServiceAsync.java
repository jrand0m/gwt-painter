package info.jrand0m.code.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import info.jrand0m.code.shared.Command;

import java.util.List;

public interface IntersectServiceAsync {
    // Sample interface method of remote interface
    void getIntersection(List<Command> figure1, List<Command> figure2, AsyncCallback<List<Command>> async);
}
