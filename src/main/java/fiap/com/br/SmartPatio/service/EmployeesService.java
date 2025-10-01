package fiap.com.br.SmartPatio.service;

import fiap.com.br.SmartPatio.domainmodel.User;

import java.util.List;

public interface EmployeesService {
    List<User> listBranchEmployees(Long filialId, Long gestorId);
}
