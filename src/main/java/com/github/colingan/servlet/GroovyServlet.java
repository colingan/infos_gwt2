package com.github.colingan.servlet;

import gdt.infra.cfg.ConfigEnv;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.colingan.client.login.InfosToken;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class GroovyServlet extends HttpServlet {

    private static String ENC = null;
    private static final long serialVersionUID = -4443020883314568918L;
    public final static Logger logger = LoggerFactory.getLogger("groovy");

  @Inject
  private Injector injector;
    private GroovyShell groovy;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        Binding bind = new Binding();
    bind.setVariable("guice", injector);
        groovy = new GroovyShell(GroovyServlet.class.getClassLoader(), bind);
        System.out.println("GroovyServlet start.");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

  private static String ROOT = ",ganjia,";

  private boolean isRoot(HttpServletRequest request) {
    if (!ConfigEnv.isProdEnv()) {
      return true;
    }
    String user = InfosToken.getUserName(request);
    return user != null && ROOT.contains(String.format(",%s,", user));
  }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String groovyInput = (String) request.getParameter("groovyInput");
        String groovyOutput = "please input and submit. you can use spring.getBean(name) method.";

        System.out.println("GroovyServlet groovyInput=" + groovyInput);

    if (isRoot(request)) {
            if (groovyInput != null && groovyInput.length() > 0) {
                try {
                    if (ENC != null) {
                        groovyInput = new String(groovyInput.getBytes(ENC), "utf8");
                    }
                    groovyOutput = String.valueOf(groovy.evaluate(groovyInput));
                } catch (Throwable e) {
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    pw.close();
                    groovyOutput = sw.toString();
                }
                if (groovyOutput == null) {
                    groovyOutput = "null";
                }
                logger.warn(String.format("[groovy] groovyInput: %s, groovyOutput: %s", groovyInput, groovyOutput));
            }
    } else {
      groovyOutput = "Sorry, You are not admin!";
    }
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(getText(groovyInput, groovyOutput));

    }

    protected String getText(String input, String output) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>");
        sb.append("<title>Hello Groovy</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<center>");
        sb.append("<form name=\"groovy\" action=\"groovy\" mothod=\"post\">");
        sb.append("<table width=\"60%\">");
        sb.append("<tr><td align=\"left\"></td></tr>");
        sb.append("<tr><td align='left'>").append(StringEscapeUtils.escapeHtml(output)).append("</td></tr>");
        sb.append("<tr><td align='left'><textarea name='groovyInput' type='textarea' cols='80' rows='20'>");
        if (input != null)
            sb.append(input);
        sb.append("</textarea></td></tr>");
        sb.append("<tr><td align='left'><input type='submit' value='groovy' /></td></tr>");
        sb.append("</table>");
        sb.append("</form>");
        sb.append("</center>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

}