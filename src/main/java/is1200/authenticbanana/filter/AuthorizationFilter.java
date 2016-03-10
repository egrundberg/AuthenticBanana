/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is1200.authenticbanana.filter;

/**
 * This class is used to filter unauthorized requests
 * @author Erik
 */
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author michelle
 */
//@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
//@WebFilter(filterName = "AuthFilter", servletNames = {"Faces Servlet"})
public class AuthorizationFilter implements Filter {

    /**
     *
     */
    public AuthorizationFilter() {
    }

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Handles requests and redirects the user if it is not allowed to visit the
     * requested page
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            if (reqURI.contains("/applicant/")) {
                if ((ses != null && ses.getAttribute("applicant") != null)) {
                    chain.doFilter(request, response);
                } else if ((ses != null && ses.getAttribute("recruiter") != null)) {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/recruiter/home.xhtml");
                } else {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/public/index.xhtml");
                }
            } else if (reqURI.contains("/recruiter/")) {
                if ((ses != null && ses.getAttribute("recruiter") != null)) {
                    chain.doFilter(request, response);
                } else if ((ses != null && ses.getAttribute("applicant") != null)) {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/applicant/home.xhtml");
                } else {
                    resp.sendRedirect(reqt.getContextPath() + "/faces/public/index.xhtml");
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException | ServletException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     */
    @Override
    public void destroy() {

    }
}
