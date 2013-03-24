package info.jrand0m.code.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import info.jrand0m.code.client.parser.Command;

import java.util.List;

public interface IntersectServiceAsync {
    // Sample interface method of remote interface
    void getMessage(String msg, AsyncCallback<String> async);

    // Sample interface method of remote interface
    void getIntersection(List<Command> figure1, List<Command> figure2, AsyncCallback<List<Command>> async);
}
