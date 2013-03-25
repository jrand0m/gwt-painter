package info.jrand0m.code.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface IntersectServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see info.jrand0m.code.client.services.IntersectService
     */
    void getIntersection( java.util.List<java.lang.String> figures, AsyncCallback<java.lang.String> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static IntersectServiceAsync instance;

        public static final IntersectServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (IntersectServiceAsync) GWT.create( IntersectService.class );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
