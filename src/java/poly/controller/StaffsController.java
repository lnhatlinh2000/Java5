/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletContext;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import poly.entity.Depart;
import poly.entity.Staff;

/**
 *
 * @author ASUS
 */
@Transactional
@Controller
public class StaffsController {

    @Autowired
    SessionFactory factory;

    @Autowired
    ServletContext context;

    @RequestMapping(value = "nhanvien", method = RequestMethod.GET)
    public String showUser(ModelMap model) {
//        if (!User.USER.getUsername().equals("admin")) {
//            model.addAttribute("user", User.USER.getUsername() + " <br/>Không có quyền truy cập!");
//            return "Qly_Giaodien/Mainframe";
//        }
        model.addAttribute("execute", "Insert");
        model.addAttribute("Staff", new Staff());
        model.addAttribute("staffs", findAllStaff());
//        model.addAttribute("user", User.USER.getUsername());

        return "Qly_Giaodien/Nhanvien";
    }

    @ModelAttribute("allStaff")
    public List<Staff> findAllStaff() {
        Session session = factory.getCurrentSession();
        String sql = "FROM Staff";
        Query query = session.createQuery(sql);
//        query.setFirstResult(10);
//        query.setMaxResults(20);
//        Long count = (Long) query.uniqueResult();
//        int lastPageNumber = (int) (Math.ceil(count/pageSize));
        List<Staff> list = query.list();

        return list;
    }

    @ModelAttribute("departs")
    public List<Depart> getDeparts() {
        Session session = factory.getCurrentSession();
        String hql = "FROM Depart";
        Query query = session.createQuery(hql);
        List<Depart> list = query.list();
        return list;
    }

    void clear(@ModelAttribute("Staff") Staff staff) {
        Depart depart = new Depart();
        staff.setId("");
        staff.setName("");
        staff.setGender(true);
        staff.setBirthday(Date.valueOf(""));
        staff.setEmail("");
        staff.setDepart(getDeparts().set(0, depart));
        staff.setSalary(Float.valueOf(""));
        staff.setPhone("");
        staff.setPhoto("");
    }

    @RequestMapping(value = "function/staff", method = RequestMethod.POST)
    public String function(ModelMap model,
            @ModelAttribute("Staff") Staff staff,
            @RequestParam(value="photo", required = false) MultipartFile photo,
            HttpServletRequest request) {
        String action = request.getParameter("action");
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        if (action.equals("Insert")) {
            try {
                String fileName = photo.getOriginalFilename();
                System.err.println(fileName);
                String photoPath = context.getRealPath("/image/" + fileName);
                photo.transferTo(new File(photoPath));
                
                staff.setPhoto(fileName);
//                System.err.println(staff.getName());
//                System.err.println(staff.getPhoto());
                session.save(staff);
                t.commit();
//                clear(staff);
                model.addAttribute("message", "<h3 style='color:blue'>Thêm mới thành công!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("staffs", findAllStaff());
            } catch (Exception e) {
                e.printStackTrace();
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Thêm mới thất bại!!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Insert");
                model.addAttribute("staffs", findAllStaff());
                
            } finally {
                session.close();
            }
        } else if (action.equals("Update")) {
            try {
//                record.setDate(new Date());
                session.update(staff);
                t.commit();
                clear(staff);
                model.addAttribute("message", "<h3 style='color:blue'>Sửa thành công!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("hide", "false");
                model.addAttribute("execute", "Insert");
                model.addAttribute("staffs", findAllStaff());
            } catch (Exception e) {
                t.rollback();
                model.addAttribute("message", "<h3 style='color:red'>Sửa thất bại!!</h3>");
//                model.addAttribute("user", User.USER.getUsername());
                model.addAttribute("execute", "Update");
                model.addAttribute("staffs", findAllStaff());
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return "Qly_Giaodien/Nhanvien";
    }

    @RequestMapping(value = "staff/delete/{id}", method = RequestMethod.GET)
    public String Delete(ModelMap model,
            @PathVariable("id") String id,
            @ModelAttribute("Staff") Staff staff) {
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        staff = (Staff) session.get(Staff.class, id);
        try {
            session.delete(staff);
            t.commit();
//            record.setReason("");
            model.addAttribute("message", "<h3 style='color:blue'>Xóa thành công!</h3>");
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("staffs", findAllStaff());
        } catch (Exception e) {
            t.rollback();
            model.addAttribute("message", "<h3 style='color:red'>Xóa thất bại!!</h3>");
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("execute", "Insert");
            model.addAttribute("staffs", findAllStaff());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Nhanvien";
    }

    @RequestMapping(value = "staff/edit/{id}")
    public String Edit(ModelMap model,
            @PathVariable("id") String id,
//            @RequestParam("photo") MultipartFile photo,
            @ModelAttribute("Staff") Staff staff) {
        Session session = factory.openSession();
//        String fileName = photo.getOriginalFilename();
        Staff show = (Staff) session.get(Staff.class, id);
        try {
            show.setId(id);
            staff.setName(show.getName());
            staff.setGender(show.getGender());
            staff.setBirthday(show.getBirthday());
            staff.setEmail(show.getEmail());
            staff.setDepart(show.getDepart());
            staff.setSalary(show.getSalary());
            staff.setPhone(show.getPhone());
            staff.setPhoto(show.getPhoto());
            staff.setNotes(show.getNotes());
//            model.addAttribute("photo_name", fileName);
//            model.addAttribute("user", User.USER.getUsername());
            model.addAttribute("staffs", findAllStaff());
            model.addAttribute("hide", "true");
            model.addAttribute("execute", "Update");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("staffs", findAllStaff());
        } finally {
            session.close();
        }
        return "Qly_Giaodien/Nhanvien";
    }

//    @RequestMapping(value = "record/search", method = RequestMethod.GET)
//    public String Search(ModelMap model,
//            @ModelAttribute("Record") Record record,
//            HttpServletRequest request) {
//        Integer id = Integer.valueOf(request.getParameter("search"));
//        Session session = factory.openSession();
//        Transaction t = session.beginTransaction();
//        Record show = (Record) session.get(Record.class, id);
//        try {
//            show.setId(Integer.valueOf(id));
//            record.setReason(show.getReason());
//            record.setType(show.getType());
//            record.setStaff(show.getStaff());
////            model.addAttribute("message", "<h3 style='color:blue'>Xóa thành công!</h3>");
////            model.addAttribute("user", User.USER.getUsername());
//            model.addAttribute("execute", "Insert");
//            model.addAttribute("records", findAllRecord());
//        } catch (Exception e) {
//            t.rollback();
////            model.addAttribute("message", "<h3 style='color:red'>Xóa thất bại!!</h3>");
////            model.addAttribute("user", User.USER.getUsername());
//            model.addAttribute("execute", "Insert");
//            model.addAttribute("records", findAllRecord());
//        } finally {
//            session.close();
//        }
//        return "Qly_Giaodien/Thanhtich_Kyluat";
//    }
}
