/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.util.Date;
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
import poly.entity.Record;
import poly.entity.Staff;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class RecordsController {

    @Autowired
    SessionFactory factory;

    @RequestMapping(value = "records", method = RequestMethod.GET)
    public String showUser(ModelMap model) {
//        if (!User.USER.getUsername().equals("admin")) {
//            model.addAttribute("user", User.USER.getUsername() + " <br/>Không có quyền truy cập!");
//            return "Qly_Giaodien/Mainframe";
//        }
        model.addAttribute("execute", "Insert");
        model.addAttribute("Record", new Record());
        model.addAttribute("records", findAllRecord());
//        model.addAttribute("user", User.USER.getUsername());

        return "Qly_Giaodien/Thanhtich_Kyluat";
    }
    

    @ModelAttribute("allRecord")
    public List<Record> findAllRecord() {
        Session session = factory.getCurrentSession();
        String sql = "FROM Record";
        Query query = session.createQuery(sql);
//        query.setFirstResult(10);
//        query.setMaxResults(20);
//        Long count = (Long) query.uniqueResult();
//        int lastPageNumber = (int) (Math.ceil(count/pageSize));
        List<Record> list = query.list();

        return list;
    }

//    @ModelAttribute("findRecord")
//    public List<Record> findRecord(Integer id) {
//        Session session = factory.getCurrentSession();
//        String sql = "FROM Record where Id='"+id+"'";
//        Query query = session.createQuery(sql);
//        List<Record> list = query.list();
//        return list;
//    }
    @ModelAttribute("staffs")
    public List<Staff> getStaffs() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Staff";
        Query query = session.createQuery(hql);
        List<Staff> list = query.list();
        return list;
    }

    @RequestMapping(value = "function/record", method = RequestMethod.POST)
    public String function(ModelMap model,
            @ModelAttribute("Record") Record record,
            HttpServletRequest request) {
        String action = request.getParameter("action");
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Staff staff = new Staff();
        if (action.equals("Insert")) {
            try {
                record.setDate(new Date());
                session.save(record);
                t.commit();
                record.setReason("");
                model.addAttribute("message", "<h3 style='color:blue'>Thêm mới thành công!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("records", findAllRecord());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Thêm mới thất bại!!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("records", findAllRecord());
            } finally {
                session.close();
            }
        } else if (action.equals("Update")) {
            try {
                record.setDate(new Date());
                session.update(record);
                t.commit();
                record.setReason("");
                record.setType(1);
                record.setStaff(getStaffs().set(0, staff));
                model.addAttribute("message", "<h3 style='color:blue'>Sửa thành công!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("hide", "false");
                model.addAttribute("execute", "Insert");
                model.addAttribute("records", findAllRecord());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Sửa thất bại!!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Update");
                model.addAttribute("records", findAllRecord());
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return "Qly_Giaodien/Thanhtich_Kyluat";
    }

    @RequestMapping(value = "record/delete/{id}", method = RequestMethod.GET)
    public String Delete(ModelMap model,
            @PathVariable("id") Integer id,
            @ModelAttribute("Record") Record record) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        record = (Record) session.get(Record.class, id);
        try {
            session.delete(record);
            t.commit();
            record.setReason("");
            model.addAttribute("message", "<h3 style='color:blue'>Xóa thành công!</h3>");
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("records", findAllRecord());
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "<h3 style='color:red'>Xóa thất bại!!</h3>");
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("records", findAllRecord());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Thanhtich_Kyluat";
    }

    @RequestMapping(value = "record/edit/{id}")
    public String Edit(ModelMap model,
            @PathVariable("id") Integer id,
            @ModelAttribute("Record") Record record) {
        Session session = factory.openSession();
        Record show = (Record) session.get(Record.class, id);
        try {
            show.setId(Integer.valueOf(id));
            record.setReason(show.getReason());
            record.setType(show.getType());
            record.setStaff(show.getStaff());
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("records", findAllRecord());
            model.addAttribute("hide", "true");
            model.addAttribute("execute", "Update");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("records", findAllRecord());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Thanhtich_Kyluat";
    }

    @RequestMapping(value = "record/search", method = RequestMethod.GET)
    public String Search(ModelMap model,
            @ModelAttribute("Record") Record record,
            HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("search"));
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        Record show = (Record) session.get(Record.class, id);
        try {
            show.setId(Integer.valueOf(id));
            record.setReason(show.getReason());
            record.setType(show.getType());
            record.setStaff(show.getStaff());
//            model.addAttribute("message", "<h3 style='color:blue'>Xóa thành công!</h3>");
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("records", findAllRecord());
        } catch (Exception e) {
            t.rollback();
//            model.addAttribute("message", "<h3 style='color:red'>Xóa thất bại!!</h3>");
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("records", findAllRecord());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Thanhtich_Kyluat";
    }
    
//    @ModelAttribute("allRecord")
//    public List<Record> Record(Integer a, Integer b) {
//        Session session = factory.getCurrentSession();
//        String sql = "FROM Record";
//        Query query = session.createQuery(sql);
//        query.setFirstResult(a);
//        query.setMaxResults(b);
////        Long count = (Long) query.uniqueResult();
////        int lastPageNumber = (int) (Math.ceil(count/pageSize));
//        List<Record> list = query.list();
//
//        return list;
//    }
    
    @RequestMapping(value= "record/next", method = RequestMethod.GET)
    public String next(HttpServletRequest request, ModelMap model){
        String next = request.getParameter("page");
        if(next.equals("1")){
            Session session = factory.getCurrentSession();
        String sql = "FROM Record";
        Query query = session.createQuery(sql);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List<Record> list = query.list();
            model.addAttribute("records",list);
        }else if(next.equals("2")){
            Session session = factory.getCurrentSession();
        String sql = "FROM Record";
        Query query = session.createQuery(sql);
        query.setFirstResult(10);
        query.setMaxResults(20);
        List<Record> list = query.list();
            model.addAttribute("records",list);
        }
        return "Qly_Giaodien/Thanhtich_Kyluat";
    }
}
