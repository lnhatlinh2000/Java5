/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.entity;

import java.util.Collection;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

/**
 *
 * @author ASUS
 */
@SqlResultSetMapping(
        name="customPB",
        columns={@ColumnResult(name="Departs", type=String.class),
            @ColumnResult(name = "Achievement", type=Long.class),
            @ColumnResult(name = "Discipline", type=Long.class),
            @ColumnResult(name = "Reward_Points", type=Long.class)})
@NamedNativeQueries({
        @NamedNativeQuery(
        name = "TTPhongBan",
        query = "EXEC sp_ThanhTichPhongBan",
        resultSetMapping = "customPB"
        )
    })
@Entity
@Table(name="Departs")
public class Depart {
    @Id
    private String Id;
    private String Name;
    
    @OneToMany(mappedBy="depart",fetch=FetchType.EAGER)
    private Collection<Staff> staff;

    public Depart() {
    }
    
    public Depart(String Id, String Name, Collection<Staff> staff) {
        this.Id = Id;
        this.Name = Name;
        this.staff = staff;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Collection<Staff> getStaff() {
        return staff;
    }

    public void setStaff(Collection<Staff> staff) {
        this.staff = staff;
    }
    
    
}

