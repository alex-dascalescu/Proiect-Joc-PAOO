package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Connection c;
    private Statement stmt;
    private ResultSet rs;

    Database() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            //c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "CREATE TABLE POSITION " +
                    "(ID INT PRIMARY KEY NOT NULL," +
                    "X INT NOT NULL, " +
                    "Y INT NOT NULL)";
            stmt.execute(sql);
            sql = "INSERT INTO POSITION (ID, X, Y) " +
                    "VALUES (1, 41, 22);";
            stmt.executeUpdate(sql);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void update(int x, int y) {
        try {
            String sql = "UPDATE POSITION set X = " + x + "where ID=1;";
            stmt.executeUpdate(sql);
            sql = "UPDATE POSITION set Y = " + y + "where ID=1;";
            stmt.executeUpdate(sql);
            c.commit();
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public int getX() {
        int x=0;
        try {
            rs = stmt.executeQuery("SELECT * FROM POSITION;");
            while (rs.next()) {
                int id = rs.getInt("id");
                x = rs.getInt("X");
            }
        } catch(Exception e) {
            System.out.println("getx");
        }
        return x;
    }

    public int getY() {
        int y=0;
        try {
            rs = stmt.executeQuery("SELECT * FROM POSITION;");
            while (rs.next()) {
                int id = rs.getInt("id");
                y = rs.getInt("Y");
            }
        } catch(Exception e) {
            System.out.println("gety");
        }
        return y;
    }

    public void close(){
        try {
            stmt.close();
            c.close();
            rs.close();
        } catch(Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
