package org.com.cay.conversion;

import org.com.cay.entity.Department;
import org.com.cay.entity.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String arg0) {
		// TODO Auto-generated method stub
		String[] args = arg0.split(",");
		Employee employee = new Employee();
		employee.setLastName(args[0]);
		employee.setEmail(args[1]);
		employee.setGender(Integer.parseInt(args[2]));
		Department dept = new Department();
		dept.setId(Integer.parseInt(args[3]));
		employee.setDepartment(dept);
		return employee;
	}

}
