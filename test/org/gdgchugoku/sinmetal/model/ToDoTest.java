package org.gdgchugoku.sinmetal.model;

import org.slim3.tester.AppEngineTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ToDoTest extends AppEngineTestCase {

    private ToDo model = new ToDo();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}
