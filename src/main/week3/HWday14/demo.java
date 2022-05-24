package HWday14;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.*;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

/**
 * department_id(DepartmentName, DepartmentID)
 * Employee(ID, DepartmentID, Salary)
 */

public class demo {
    private DataSource getDataSource(){
        Properties props = new Properties();
        FileInputStream fis = null;
        MysqlDataSource mysqlDS = new MysqlDataSource();
        mysqlDS.setURL(props.getProperty("jdbc:mysql://localhost:3306/java"));
        mysqlDS.setUser(props.getProperty("root"));
        mysqlDS.setPassword(props.getProperty("321456"));

        return mysqlDS;
    }

    private Properties getProperties() {
        final Properties properties = new Properties();
        properties.put( "hibernate.dialect", "org.hibernate.dialect.mysqldialect" );
        properties.put( "hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver" );
//        properties.put("hibernate.show_sql", "true");
        return properties;
    }

    private EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com/example/java92022/week3/orm/demo3");
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "demo-unit" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();
        return em.getObject();
    }

    private static void insertToemployee(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        employee s = new employee();
        s.setID("Jame");
        s.setDepartmentID("3");
        s.setSalary("90000");
        em.merge(s);
        tx.commit();
    }

    private static void insertTodepartment_id(EntityManager em){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        department_id s = new department_id();
        s.setDepartmentName("Software");
        s.setDepartmentId("1");
        em.merge(s);
        tx.commit();
    }

    private static void getDepartmentByStudentId(EntityManager em) {
        Query query = em.createQuery("select department_id from employee join department_id on employee.departmentId = department_id.departmentId where ID = ?1");
        query.setParameter(1, "kyle");
        department_id s = (department_id)query.getSingleResult();
        System.out.println(s);
    }

    private static void getStudentsByDepartmentId(EntityManager em) {
        Query query = em.createQuery("select * from employee join department_id on employee.departmentId = department_id.departmentId where employee.departmentId = ?1");
        query.setParameter(1, "3");
        List<employee> s = (List<employee>)query.getResultList();
        for(Object x:s){
            System.out.println(x);
        }
    }

    private static void removeDepartmentOnly(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select * from department_id where departmentID = ?1");
        query.setParameter(1, "3");
        department_id s = (department_id)query.getSingleResult();
        em.remove(s);
        tx.commit();
    }

    private static void removeDepartmentPlusEMployees(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery("select * from employee join department_id on employee.departmentId = department_id.departmentId where department_id.departmentId = ?1");
        query.setParameter(1, "3");
        List<employee> s = query.getResultList();
        department_id dep = em.find(department_id.class, "3");
        for(Object x: s){
            em.remove(x);
        }
        em.remove(dep);
        tx.commit();
    }

    public static void main(String[] args) {
        demo jpaDemo = new demo();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        PersistenceUnitUtil unitUtil = entityManagerFactory.getPersistenceUnitUtil();
        insertToemployee(em);
    }


}
