package group31.classes.Membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MembershipDAO {
    public Connection connection;

    //constructor
    public MembershipDAO(Connection connection) {
        this.connection = connection;
    }


    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships(customer, cost, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, membership.getCustomer());
            statement.setInt(2, membership.getCost());
            statement.setString(3, membership.getDescription());
            statement.executeUpdate();
            System.out.println("Membership purchased successfully");
        }  catch (Exception e) {
            System.err.println("Database connection failed");
        }
    }

    public Membership getMembership(String customer) throws SQLException {
        Membership currentMembership = new Membership();
        String sql = "SELECT * FROM memberships";
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {
            while (result.next()) {
                if (result.getString("customer").equals(customer)) {
                    currentMembership = new Membership(result.getString("customer"));
                    break;
                }
            }
            return currentMembership;
        } catch (Exception e) {
            System.err.println("Database connection failed");
        }
        return currentMembership;
    }

    public void deleteMembership(String customer) throws SQLException {
        String sql = "DELETE FROM memberships WHERE customer = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, customer);
            statement.executeUpdate();
        }
    }

    public List<Membership> getAllMemberships() {
        String sql = "SELECT * FROM memberships";
        List<Membership> membershipList = new ArrayList<>();
        try (var statement = connection.createStatement();
            var result = statement.executeQuery(sql)) {

                while (result.next()) {
                    Membership listMembership = new Membership(result.getString("customer"));
                    membershipList.add(listMembership);
                }

            return membershipList;
        } catch (Exception e) {
            System.err.println("Database connection failed");  
            return membershipList; 
        }
    }
}
