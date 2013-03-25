package info.jrand0m.code.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

@RemoteServiceRelativePath("IntersectService")
public interface IntersectService extends RemoteService {
    // Sample interface method of remote interface
    String getIntersection(List<String> figures);
}

