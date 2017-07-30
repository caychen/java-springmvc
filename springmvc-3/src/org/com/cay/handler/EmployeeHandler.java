package org.com.cay.handler;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.com.cay.dao.DepartmentDao;
import org.com.cay.dao.EmployeeDao;
import org.com.cay.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private DepartmentDao departmentDao;

	@RequestMapping("/emps")
	public String list(Map<String, Object> map) {
		map.put("employees", employeeDao.getAll());
		return "list";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.GET)
	@ResponseBody
	public void input(HttpServletResponse response) throws IOException {
		// System.out.println(departmentDao.getDepartments());
		Gson gson = new Gson();
		String jsonStr = gson.toJson(departmentDao.getDepartments());
		response.getWriter().print(jsonStr);
		//return "input";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.POST)
	public String save(Employee employee) {
		System.out.println(employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable("id")Integer id){
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}", method=RequestMethod.GET)
	public String input(@PathVariable("id")Integer id, Map<String, Object>map){
		map.put("employee", employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value = "id", required=false)Integer id, Map<String, Object>map){
		if(id != null){
			map.put("employee", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String update(Employee employee){
		employeeDao.save(employee);
		return "redirect:/emps";
	}
}
