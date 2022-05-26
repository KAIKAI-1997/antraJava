package damain;

import com.mysql.cj.jdbc.MysqlDataSource;
import controller.Exceptions.BusinessException;
import controller.Exceptions.SystemException;
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
public class db {
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

    private synchronized static EntityManager EntityManagerCreate(){
        db jpaDemo = new db();
        DataSource dataSource = jpaDemo.getDataSource();
        Properties properties = jpaDemo.getProperties();
        EntityManagerFactory entityManagerFactory = jpaDemo.entityManagerFactory(dataSource, properties);
        EntityManager em = entityManagerFactory.createEntityManager();
        return em;
    }

    public static boolean insertToemployee(employee e){
        try {
            EntityManager em = EntityManagerCreate();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(e);
            tx.commit();
            return true;
        }
        catch (Exception exception){
            throw new BusinessException(500, "insert failure", exception);
        }
    }

    public static boolean insertTodepartment_id(departmentIndex depart){
        try{
            EntityManager em = EntityManagerCreate();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(depart);
            tx.commit();
            return true;
        }
        catch (Exception exception){
            throw new BusinessException(500, "insert failure", exception);
        }
    }

    public static departmentIndex getDepartmentsById(String neme) {
        try {
            EntityManager em = EntityManagerCreate();
            Query query = em.createQuery("select department_id from employee join department_id on employee.departmentId = department_id.departmentId where ID = ?1");
            query.setParameter(1, neme);
            departmentIndex s = (departmentIndex)query.getSingleResult();
            return s;
        }
        catch (Exception exception){
            throw new BusinessException(500, "fetch failure", exception);
        }
    }

    public static employee getEmployeeById(String ID){
        try{
            EntityManager em = EntityManagerCreate();
            Query query = em.createQuery("select * from employee where employee.ID = ?1");
            query.setParameter(1, ID);
            return (employee)query.getSingleResult();
        }
        catch (Exception exception){
            throw new BusinessException(500, "fetch failure", exception);
        }
    }

    public static boolean modifyEmployee(employee employee) {
        try{
            EntityManager em = EntityManagerCreate();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Query query = em.createQuery("update java Set ID=?1, salary=?2, department_id=?3");
            query.setParameter(1, employee.getID());
            query.setParameter(2, employee.getSalary());
            query.setParameter(3, employee.getDepartmentID());
            departmentIndex s = (departmentIndex)query.getSingleResult();
            em.merge(s);
            tx.commit();
            return true;

        }
        catch (Exception exception){
            throw new BusinessException(500, "update failure", exception);
        }
    }

    public static List<employee> getAllEmployees() {
        try{
            EntityManager em = EntityManagerCreate();
            Query query = em.createQuery("select * from employee");
            return (List<employee>)query.getResultList();
        }
        catch (Exception exception){
            throw new BusinessException(500, "fetch failure", exception);
        }
    }

    public static List<departmentIndex> getAllDepartments() {
        try{
            EntityManager em = EntityManagerCreate();
            Query query = em.createQuery("select * from department_id");
            return (List<departmentIndex>)query.getResultList();
        }
        catch (Exception exception){
            throw new BusinessException(500, "fetch failure", exception);
        }
    }

    public static List<employee> getStudentsByDepartmentId(String name) {
        try {
            EntityManager em = EntityManagerCreate();
            Query query = em.createQuery("select * from employee join department_id on employee.departmentId = department_id.departmentId where employee.departmentId = ?1");
            query.setParameter(1, name);
            List<employee> s = (List<employee>)query.getResultList();
            return s;
        }
        catch (Exception exception){
            throw new BusinessException(500, "fetch failure", exception);
        }
    }

    public static boolean removeEmployee(String ID) {
        try {
            EntityManager em = EntityManagerCreate();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Query query = em.createQuery("select * from employee where ID = ?1");
            query.setParameter(1, ID);
            departmentIndex s = (departmentIndex)query.getSingleResult();
            em.remove(s);
            tx.commit();
            return true;
        }
        catch (Exception exception){
            throw new BusinessException(500, "delete failure", exception);
        }
    }

    public static boolean removeDepartmentOnly(String neme) {
        try{

            EntityManager em = EntityManagerCreate();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Query query = em.createQuery("select * from department_id where departmentID = ?1");
            query.setParameter(1, neme);
            departmentIndex s = (departmentIndex)query.getSingleResult();
            em.remove(s);
            tx.commit();
            return true;
        }
        catch (Exception exception){
            throw new BusinessException(500, "delete failure", exception);
        }
    }

    public static boolean removeDepartmentPlusEMployees() {
        try{
            EntityManager em = EntityManagerCreate();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Query query = em.createQuery("select * from employee join department_id on employee.departmentId = department_id.departmentId where department_id.departmentId = ?1");
            query.setParameter(1, "3");
            List<employee> s = query.getResultList();
            departmentIndex dep = em.find(departmentIndex.class, "3");
            for(Object x: s){
                em.remove(x);
            }
            em.remove(dep);
            tx.commit();
            return true;
        }
        catch (Exception exception){
            throw new BusinessException(500, "delete failure", exception);
        }
    }
}
