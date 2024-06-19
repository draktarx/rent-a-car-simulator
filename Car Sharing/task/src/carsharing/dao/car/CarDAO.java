package carsharing.dao.car;

import carsharing.model.Company;

import java.util.Map;

public interface CarDAO {

    Map<Integer, String> allCarsByCompany(int id);

    boolean addCarToCompany(Company company, String carName);

}
