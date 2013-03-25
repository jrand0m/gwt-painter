package info.jrand0m.code.server;


import info.jrand0m.code.client.services.IntersectService;
import info.jrand0m.code.server.services.IntersectServiceImpl;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class IntersectServiceTest {
    @Test
    public void testIntegration() throws Exception {
        String square1 = "M5,5 L5,15 L15,15 L15,5 L5,5 Z";
        String square2 = "M10,5 L10,15 L20,15 L20,5 L10,5 Z";
        ArrayList<String> list = new ArrayList<String>(2);
        list.add(square1);
        list.add(square2);
        IntersectService service = new IntersectServiceImpl();
        String result = service.getIntersection(list);
        assertThat(result, equalTo("M10.0,5.0 L10.0,15.0 L15.0,15.0 L15.0,5.0 Z"));
    }


}
