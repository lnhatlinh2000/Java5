/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.entity.User;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class StatisticController {

    @Autowired
    SessionFactory factory;
    
    @RequestMapping("thanhtichCN")
    public String showCanhan(ModelMap model) {
//        model.addAttribute("user", User.USER.getUsername());
        model.addAttribute("CaNhan", loadTTCN());
        return "Qly_Giaodien/TTCanhan";
    }
    
    public List<Object[]> loadTTCN() {
        Session session = factory.getCurrentSession();
        Query query = session.getNamedQuery("TTCaNhan");
//        query.setFirstResult(1);
//        query.setMaxResults(5);
        List<Object[]> list = query.list();

        return list;
    }

    @RequestMapping("thanhtichPB")
    public String showPhongban(ModelMap model) {
//        model.addAttribute("user", User.USER.getUsername());
        model.addAttribute("PhongBan", loadTTPB());

        return "Qly_Giaodien/TTPhongban";
    }
    
        public List<Object[]> loadTTPB() {
        Session session = factory.getCurrentSession();
        Query query = session.getNamedQuery("TTPhongBan");
//        query.setFirstResult(1);
//        query.setMaxResults(5);
        List<Object[]> list = query.list();

        return list;
    }
}
