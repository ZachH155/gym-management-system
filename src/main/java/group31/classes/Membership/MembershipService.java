package group31.classes.Membership;

import java.sql.SQLException;
import java.util.List;


public class MembershipService {
    public MembershipDAO DAO;

    // constructor
    public MembershipService(MembershipDAO DAO) {
        this.DAO = DAO;
    }

    // methods
    public void addMembership(Membership membership) throws SQLException {
        if (membership == null) {
            System.out.println("Membership obj null");
            return;
        }

        Membership newMembership = new Membership(membership.getCustomer());
        DAO.addMembership(newMembership);
    }

    public Membership getMembership(String customer) throws SQLException {
        if (customer == null) {
            System.out.println("Enter a customers username");
            return null;
        }
        return DAO.getMembership(customer);
    }

    public List<Membership> getAllMemberships() throws SQLException {
        return DAO.getAllMemberships();
    }

    public void deleteMembership(String customer) throws SQLException {
        DAO.deleteMembership(customer);
    }

};
