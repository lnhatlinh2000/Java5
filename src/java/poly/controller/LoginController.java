/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.entity.User;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class LoginController {

    @Autowired
    SessionFactory factory;
    
    @RequestMapping("login")
    public String showLogin(HttpServletRequest request) {
        return "Qly_Giaodien/Login";
    }

    @RequestMapping("login_form")
    public String loginForm(HttpServletRequest request) {
        Session session = factory.openSession();
        String id = request.getParameter("user");
        String pw = request.getParameter("pwd");
        boolean check = this.check(id, pw);
        User username = (User) session.get(User.class, id);
        if (check) {
            User.USER=username;
            request.setAttribute("user", User.USER.getUsername());
            return "Qly_Giaodien/Mainframe";
        }
        request.setAttribute("message", "<p style='color:red'>Sai thông tin đăng nhập</p>");
        return "Qly_Giaodien/Login";
    }

    public boolean check(String user, String pass) {
        List<User> list = null;
        Session session = factory.getCurrentSession();
        String sql = "from User where Username = '" + user + "' and Password = '" + pass + "'";
        Query query = session.createQuery(sql);
        list = query.list();
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
}
