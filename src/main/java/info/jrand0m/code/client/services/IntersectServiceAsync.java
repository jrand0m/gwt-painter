package info.jrand0m.code.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IntersectServiceAsync {
    // Sample interface method of remote interface
    void getIntersection(String figure1, String figure2, AsyncCallback<String> async);
}
