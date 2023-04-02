package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modal.Passport;
import utility.DatabaseConnection;

public class PassportsDAO {

    public ArrayList<Passport> getAllPassportDetails() {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Passport> passportList = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "SELECT * FROM passport");

            rs = ps.executeQuery();
            while (rs.next()) {
                Passport passport = new Passport(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("age"));

                passportList.add(passport);
            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getAllPassportDetails()");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return passportList;

    }

    public void deleteAllPassportDetails() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "DELETE FROM passport");

            ps.execute();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteAllPassportDetails()");
            sqle.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Passport getPassportById(String id) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Passport passport = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "SELECT * FROM passport WHERE id = " + id);

            rs = ps.executeQuery();

            while (rs.next()) {
                passport = new Passport(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("age"));
                

            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getPassportById()");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return passport;

    }

    public void deletePassportDetailsById(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "DELETE FROM passport WHERE id = " + id);

            ps.execute();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deletePassportDetailsById()");
            sqle.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public ArrayList<Passport> getPassportByCountry(String country) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Passport passport = null;
        ArrayList<Passport> passportList = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "SELECT * FROM passport WHERE country = '" + country + "'");

            rs = ps.executeQuery();

            while (rs.next()) {
                passport = new Passport(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("country"),
                        rs.getString("age"));
                passportList.add(passport);
            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in getPassportByCountry()");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return passportList;

    }

    public void deletePassportDetailsByCountry(String country) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "DELETE FROM passport WHERE country = '" + country + "'");

            ps.execute();

        } catch (SQLException sqle) {
            System.err.println("SQLException in deleteAllPassportDetailsByCountry()");
            sqle.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int addPassport(Passport passport) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            if (checkForDuplicatePassport(passport.getName())) {
                return -1;
            } else {
                conn = DatabaseConnection.getConnection();

                ps = conn.prepareStatement(
                        "INSERT INTO passport "
                        + "(name, country, age) "
                        + "VALUES (?,?,?)");

                ps.setString(1, passport.getName());
                ps.setString(2, passport.getCountry());
                ps.setString(3, passport.getAge());
                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("SQLException in addPassport()");
            sqle.printStackTrace();
            return -1;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

    public boolean checkForDuplicatePassport(String name) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        boolean isDuplicate = false;
        try {
            conn = DatabaseConnection.getConnection();

            ps = conn.prepareStatement(
                    "SELECT name FROM passport");

            rs = ps.executeQuery();

            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(name)) {
                    isDuplicate = true;
                    break;
                }
            }

        } catch (SQLException sqle) {
            System.err.println("SQLException in checkForDuplicatePassport()");
            sqle.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isDuplicate;
    }

    public int updatePassport(Passport passport) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            if (checkForDuplicatePassport(passport.getName())) {
                return -1;
            } else {
                conn = DatabaseConnection.getConnection();

                ps = conn.prepareStatement(
                        "UPDATE passport "
                        + "SET name=?, country=?, age=?"
                        + "WHERE id=?");

                ps.setString(1, passport.getName());
                ps.setString(2, passport.getCountry());
                ps.setString(3, passport.getAge());

                ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            System.err.println("SQLException in updatePassport()");
            sqle.printStackTrace();
            return -1;
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

}
