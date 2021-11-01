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
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import poly.entity.User;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class UsersController {

    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String showUser(ModelMap model) {
        if (!User.USER.getUsername().equals("admin")) {
            model.addAttribute("user", User.USER.getUsername() + " <br/>Không có quyền truy cập!");
            return "Qly_Giaodien/Mainframe";
        }
        model.addAttribute("execute", "Insert");
        model.addAttribute("User", new User());
        model.addAttribute("users", findAllUser());
        model.addAttribute("user", User.USER.getUsername());
        return "Qly_Giaodien/Nguoidung";
    }

    @ModelAttribute("allUser")
    public List<User> findAllUser() {
        Session session = factory.getCurrentSession();
        String sql = "FROM User";
        Query query = session.createQuery(sql);
        List<User> list = query.list();
        return list;
    }

    @RequestMapping(value = "function/nguoidung", method = RequestMethod.POST)
    public String function(ModelMap model,
            @ModelAttribute("User") User user,
            HttpServletRequest request) {
        String action = request.getParameter("action");
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        if (action.equals("Insert")) {
            try {
                session.save(user);
                t.commit();
                user.setUsername("");
                user.setPassword("");
                user.setFullname("");
                model.addAttribute("message", "<h3 style='color:blue'>Thêm mới thành công!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("users", findAllUser());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Thêm mới thất bại!!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("users", findAllUser());
            } finally {
                session.close();
            }
        } else if (action.equals("Update")) {
            try {
                session.update(user);
                t.commit();
                user.setUsername("");
                user.setPassword("");
                user.setFullname("");
                model.addAttribute("message", "<h3 style='color:blue'>Sửa thành công!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("hide", "false");
                model.addAttribute("execute", "Insert");
                model.addAttribute("users", findAllUser());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Sửa thất bại!!</h3>");
                model.addAttribute("user", User.USER.getUsername());

                model.addAttribute("execute", "Update");
                model.addAttribute("users", findAllUser());
            } finally {
                session.close();
            }
        }
        return "Qly_Giaodien/Nguoidung";
    }

    @RequestMapping(value = "nguoidung/delete/{username}", method = RequestMethod.GET)
    public String Delete(ModelMap model,
            @PathVariable("username") String username,
            @ModelAttribute("User") User user) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        user = (User) session.get(User.class, username);
        try {
            session.delete(user);
            t.commit();
            user.setUsername("");
            user.setPassword("");
            user.setFullname("");
            model.addAttribute("message", "<h3 style='color:blue'>Xóa thành công!</h3>");
            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("users", findAllUser());
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "<h3 style='color:red'>Xóa thất bại!!</h3>");
            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("users", findAllUser());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Nguoidung";
    }

    @RequestMapping(value = "nguoidung/edit/{username}")
    public String Edit(ModelMap model,
            @PathVariable("username") String username,
            @ModelAttribute("User") User user) {
        Session session = factory.openSession();
        User show = (User) session.get(User.class, username);
        try {
            user.setUsername(username);
            user.setPassword(show.getPassword());
            user.setFullname(show.getFullname());
            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("users", findAllUser());
            model.addAttribute("hide", "true");
            model.addAttribute("execute", "Update");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("users", findAllUser());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Nguoidung";

    }
}
