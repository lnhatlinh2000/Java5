/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.entity;

import java.util.Date;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
        name="custom",
        columns={@ColumnResult(name="Id", type=String.class),
            @ColumnResult(name = "Name", type=String.class),
            @ColumnResult(name = "Departs", type=String.class),
            @ColumnResult(name = "Achievement_points", type = Long.class),
            @ColumnResult(name = "Photo", type=String.class)})
@NamedNativeQueries({
        @NamedNativeQuery(
        name = "Top10",
        query = "EXEC sp_Top10",
        resultSetMapping = "custom"
        )
    })
@Entity
@Table(name="Records")
public class Record {
    @Id
    @GeneratedValue
    private Integer Id;
    private Integer Type;
    private String Reason;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="MM/dd/yyyy")
    private Date Date;
    @ManyToOne
    @JoinColumn(name="StaffId")
    private Staff staff;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type) {
        this.Type = Type;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
}
