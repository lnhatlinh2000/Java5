/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class MainframeController {

    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "mainframe", method = RequestMethod.GET)
    public String showMainframe(ModelMap model) {
        model.addAttribute("top5D", loadTop5D());
        model.addAttribute("top5C", loadTop5C());

//        model.addAttribute("user", User.USER.getUsername());

        return "Qly_Giaodien/Mainframe";
    }
    
    public List<Object[]> loadTop5D() {
        Session session = factory.getCurrentSession();
        Query query = session.getNamedQuery("Top10");
//        query.setFirstResult(0);
//        query.setMaxResults(5);
        List<Object[]> list = query.list();
        ArrayList<Object[]> arr = new ArrayList<>();
        arr.add(list.get(0));
        arr.add(list.get(1));
        arr.add(list.get(2));
        arr.add(list.get(3));
        arr.add(list.get(4));
        return arr;
    }
        public List<Object[]> loadTop5C() {
        Session session = factory.getCurrentSession();
        Query query = session.getNamedQuery("Top10");
//        query.setFirstResult(0);
//        query.setMaxResults(5);
        List<Object[]> list = query.list();
        ArrayList<Object[]> arr = new ArrayList<>();
        arr.add(list.get(5));
        arr.add(list.get(6));
        arr.add(list.get(7));
        arr.add(list.get(8));
        arr.add(list.get(9));
        return arr;
    }
}
