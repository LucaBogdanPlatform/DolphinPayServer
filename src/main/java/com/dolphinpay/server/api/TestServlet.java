/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dolphinpay.server.api;

import com.dolphinpay.server.hibernate.HibernateUtils;
import com.dolphinpay.server.hibernate.models.StandsOrdersState;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.List;

public class TestServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, URISyntaxException {
        try (PrintWriter out = response.getWriter()) {
            out.println("aaaa");
            try (Session session = HibernateUtils.getSessionFactory().openSession()) {
                out.println("ciaoooo");
                List<StandsOrdersState> students = session.createQuery("from stands_orders_states", StandsOrdersState.class).list();
 
                out.println(students.size());
            } catch (Exception e) {
                out.println("ERROR" + e.toString()); 
            }
        }

        /*
        String token = "eXyUcS9WPVI:APA91bHORz_rH-T_DONu7EGbjwAS3mxombbrYotyWC4gmubxoEtBAOJWF8SaMg5TlOtQLrXyK9bGf2Fx3xU5ZszgLK5DVJLmJ0KymVoUKCSNLwqLFU7uVLqT20Y0ODzs0AFaNHWMvIcv";

        try {
            FirebaseMessaging.getInstance().send(Message.builder().setToken(token).build());
        } catch (FirebaseMessagingException ex) {
            Logger.getLogger(TestServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        */


    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
