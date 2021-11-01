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
import poly.entity.Depart;
import poly.entity.User;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class DepartsController {

    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "phongban", method = RequestMethod.GET)
    public String showPB(ModelMap model) {
//        if(!User.USER.getUsername().equals("truongphong")){
//            model.addAttribute("user", User.USER.getUsername()+" <br/>Không có quyền truy cập!");
//            return "Qly_Giaodien/Mainframe";
//        }
        model.addAttribute("user", User.USER.getUsername());
        model.addAttribute("execute", "Insert");
        model.addAttribute("Depart", new Depart());
        model.addAttribute("departs", findAllDepart());

        return "Qly_Giaodien/Phongban";
    }

    @ModelAttribute("allDepart")
    public List<Depart> findAllDepart() {
        Session session = factory.getCurrentSession();
        String sql = "FROM Depart";
        Query query = session.createQuery(sql);
        List<Depart> list = query.list();
        return list;
    }

    @RequestMapping(value = "function/departs", method = RequestMethod.POST)
    public String function(ModelMap model,
            @ModelAttribute("Depart") Depart depart,
            HttpServletRequest request) {
        String action = request.getParameter("action");
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        if (action.equals("Insert")) {
            try {
                session.save(depart);
                t.commit();
                depart.setId("");
                depart.setName("");
                model.addAttribute("message", "<h3 style='color:blue'>Thêm mới thành công!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("departs", findAllDepart());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Thêm mới thất bại!!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("departs", findAllDepart());
            } finally {
                session.close();
            }
        } else if (action.equals("Update")) {
            try {
                session.update(depart);
                t.commit();
                depart.setId("");
                depart.setName("");
                model.addAttribute("message", "<h3 style='color:blue'>Sửa thành công!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("hide", "false");
                model.addAttribute("execute", "Insert");
                model.addAttribute("departs", findAllDepart());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Sửa thất bại!!</h3>");
                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Update");
                model.addAttribute("departs", findAllDepart());
            } finally {
                session.close();
            }
        }
        return "Qly_Giaodien/Phongban";
    }

    @RequestMapping(value = "phongban/delete/{id}", method = RequestMethod.GET)
    public String Delete(ModelMap model,
            @PathVariable("id") String id,
            @ModelAttribute("Depart") Depart depart) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Depart wdepart = (Depart) session.get(Depart.class, id);
        try {
            session.delete(wdepart);
            t.commit();
            depart.setId("");
            depart.setName("");
            
            model.addAttribute("message", "<h3 style='color:blue'>Xóa thành công!</h3>");
            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("departs", findAllDepart());
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "<h3 style='color:red'>Xóa thất bại!!</h3>");
            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("departs", findAllDepart());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Phongban";
    }

    @RequestMapping(value = "phongban/edit/{id}")
    public String Edit(ModelMap model,
            @PathVariable("id") String id,
            @ModelAttribute("Depart") Depart depart) {
        Session session = factory.openSession();
        Depart showdepart = (Depart) session.get(Depart.class, id);
        try {
            depart.setId(id);
            depart.setName(showdepart.getName());
            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("departs", findAllDepart());
            model.addAttribute("hide", "true");
            model.addAttribute("execute", "Update");
        } catch (Exception e) {
            model.addAttribute("departs", findAllDepart());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Phongban";

    }
}
