/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.Dialect;

import java.sql.Types;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author ASUS
 */
public class SQLDialectCustom extends SQLServerDialect{
    public SQLDialectCustom(){
        super();
        registerHibernateType(Types.NVARCHAR,StandardBasicTypes.TEXT.getName());
    }
    
}
