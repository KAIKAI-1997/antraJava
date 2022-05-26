import java.sql.*;

/**
 * jdbc issues
 * 1. dynamical query
 * 2. connection pool
 * 3. centralized query =>
 * 4. data / result set mapping
 * 5. cache
 * ..
 * ORM
 */
public class JdbcExample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/java";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "321456";

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement stmt = null;//防止sql注入

        try {
            //STEP 2: Register JDBC driver -> DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(false);//开启transaction
            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            String sql2 = "select * from java.employee;";
//            String sql3 = "SELECT .... first, last, age FROM Employees WHERE age .. And first = ";
//            String sql4 = "SELECT .... first, last, age FROM Employees WHERE age or first..";
//            Statement stmt = conn.createStatement();
            stmt = conn.prepareStatement(sql2);
            ResultSet rs = stmt.executeQuery();

            //STEP 5: Extract data from result set
            while(rs.next()){
                //Retrieve by column name
                String first = rs.getString("ID");
                String last = rs.getString("salary");

                //Display values
                System.out.print("First: " + first);
                System.out.println("Last: " + last);
            }
            conn.commit();
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }//end main
}