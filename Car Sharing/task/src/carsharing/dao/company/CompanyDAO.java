package carsharing.dao.company;

import carsharing.model.Company;

import java.util.Map;

public interface CompanyDAO {

    Company find(int id);

    boolean addCompany(String name);

    Map<Integer, String> list();

}
