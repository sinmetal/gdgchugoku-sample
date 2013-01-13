package org.gdgchugoku.sinmetal.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.gdgchugoku.sinmetal.meta.ToDoMeta;
import org.gdgchugoku.sinmetal.model.ToDo;
import org.gdgchugoku.sinmetal.service.ToDoManagerService;
import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ToDoController extends Controller {

    private final Logger logger = Logger.getLogger(ToDoController.class
        .getName());

    private final ToDoManagerService toDoManagerService =
        new ToDoManagerService();

    @Override
    public Navigation run() throws Exception {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            final Map<String, String> responseMap =
                new HashMap<String, String>();
            responseMap.put("loginUrl", userService.createLoginURL(basePath));
            final JSONObject jsonObject = new JSONObject(responseMap);
            response.getWriter().println(jsonObject.toString());
            response.flushBuffer();
            return null;
        }

        if ("POST".equals(request.getMethod())) {
            return doPost(user);
        } else {
            return doGet(user);
        }
    }

    private Navigation doPost(User user) {
        final String memo = asString("memo");
        logger.info("memo=" + memo);
        toDoManagerService.put(user, memo);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        return null;
    }

    private Navigation doGet(User user) throws IOException {
        final List<ToDo> list = toDoManagerService.get(user);
        final String json = ToDoMeta.get().modelsToJson(list);

        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().println(json);
        response.flushBuffer();
        return null;
    }
}
