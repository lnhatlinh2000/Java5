/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author ASUS
 */

@SqlResultSetMapping(
        name="customCN",
        columns={@ColumnResult(name="Staffs", type=String.class),
            @ColumnResult(name = "Achievement", type=Long.class),
            @ColumnResult(name = "Discipline", type=Long.class),
            @ColumnResult(name = "Reward_Points", type=Long.class)})
@NamedNativeQueries({
        @NamedNativeQuery(
        name = "TTCaNhan",
        query = "EXEC sp_ThanhTichCaNhan",
        resultSetMapping = "customCN"
        )
    })

@Entity
@Table(name = "Staffs")
public class Staff{

    @Id
    private String id;
    private String name;
    private Boolean gender;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date birthday;
    private String photo;
    private String email;
    private String phone;
    private Float salary;
    private String notes;

    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER)
    private Collection<Record> record;

    @ManyToOne
    @JoinColumn(name = "DepartId")
    private Depart depart;

    public Staff() {
    }

    public Staff(String id, String name, Boolean gender, Date birthday, String photo, String email, String phone, Float salary, String notes, Collection<Record> record, Depart depart) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.notes = notes;
        this.record = record;
        this.depart = depart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Collection<Record> getRecord() {
        return record;
    }

    public void setRecord(Collection<Record> record) {
        this.record = record;
    }
    
}
