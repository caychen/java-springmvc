package org.com.cay.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.com.cay.entity.Address;
import org.com.cay.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/springmvc")
@SessionAttributes(value={"user"}, types={String.class})
public class SpringMVCTest {

	private static final String SUCCESS = "success";

	@RequestMapping("/testForward")
	public String testForward(){
		System.out.println("testForward");
		return "forward:/index.jsp";
	}
	
	@RequestMapping("/testRedirect")
	public String testRedirect(){
		System.out.println("testRedirect");
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/testCustomerView")
	public String testCustomerView(){
		System.out.println("testCustomerView");
		return "helloView";
	}
	
	@RequestMapping("/testViewAndViewResolver")
	public String testViewAndViewResolver(){
		System.out.println("testViewAndViewResolver");
		return SUCCESS;
	}
	/**
	 * 1、有@ModelAttribute标记的方法，会在每个目标方法执行之前被SpringMVC调用！
	 * 2、@ModelAttribute注解也可以用来修饰目标方法POJO类型的入参，其value值有以下几点作用：
	 * 		(1)、springmvc会使用value属性值在implicitModel中查找对应的对象,若存在则会直接传入到目标方法的入参中。
	 * 		(2)、springmvc会将key和value存入到request中
	 */
	@ModelAttribute
	public User getUser(@RequestParam(value="id",required=false) Integer id
			,Map<String, Object> maps){
		System.out.println("ModelAttribute method");
		if(id != null){
			//模拟从数据库中获取一个User对象
			User user = new User(1, "Cay", "123456", "1111@qq.com", 25);
			System.out.println("模拟从数据库中获取一个User对象");
			maps.put("user", user);
		}
		return null;
	}
	/**
	 * 执行流程：
	 * 	1、执行@ModelAttribute注解修饰的方法，从数据库中取出对象，把对象放入到map中，键为user
	 *  2、SpringMVC从map中取出user对象，并把表单的请求参数赋给该user对象的对象属性
	 *  3、SpringMVC把上述对象传入目标方法的参数
	 *  
	 * 注意：
	 * 	在@ModelAttribute修饰的方法中，放入到Map时的键需要跟目标方法入参类型的第一个字母小写的字符串一致  
	 * 
	 * 源码分析的流程：
	 * 	1、调用@ModelAttribute注解修饰的方法，实际上把@ModelAttribute方法中Map中的数据放在了implicitModel中
	 * 	2、解析请求处理中的目标参数，实际上该目标方法参数来自于WebDataBinder对象的target属性中
	 * 		(1)、创建WebDataBinder对象：
	 * 			i、确定objectName属性：若传入的attrName属性值为"",则objectName为类名 ，且第一个字母小写
	 * 				注意：attrName，若目标方法的POJO参数使用了@ModelAttribute来修饰，则attrName值即为@ModelAttribute的value属性值
	 * 			ii、确定target属性值：
	 * 				> 在implcitModel中查找attrName对应的属性值，若存在，
	 * 				> 若不存在，则验证当前Handler是否使用了@SessionAttributes进行修饰，若使用了，则尝试从Session中获取attrName所对应的属性值，若session中没有对应的属性值，则抛出异常。
	 * 				> 若Handler没有使用@SessionAttributes进行修饰，或者@SessionAttributes中没有使用value值指定的key和attrName相匹配，则通过反射创建POJO对象.
	 * 
	 *		(2)、springmvc把表单的请求参数赋值给了WebDataBinder的target对应的属性，
	 *		(3)、springmvc会把WebDataBinder的attrName和target给implicitModel，从而传到request域对象中。
	 *		(4)、把WebDataBinder的target作为参数传递给目标方法的入参。
	 *
	 *	springmvc确定目标方法的pojo类型入参的过程
	 *		1、确定一个key
	 *			(1)、若目标方法的pojo类型的参数未使用@ModelAttribute作为修饰，则key为POJO类名，且第一个字母小写
	 *			(2)、若使用了@ModelAttribute来作为修饰，则key为@ModelAttribute注解的value值。
	 *		2、在implicitModel中查找key对应的对象，若存在，则作为入参传入
	 *			(1)、若在@ModelAttribute标识的方法中的Map中保存过，且key和(1)中确定的key一致，则会获取到。
	 *		3、若implicitModel中不存在key对应的对象，则检查当前的Handler是否使用了@SessionAttributes注解修饰，
	 *			若使用了该注解，且@SessionAttributes注解的value属性值中包含了key，则会从HttpSession中来获取key所对用的value值，若存在则直接传入到目标方法的入参中，若不存在，则将抛出异常
	 *		4、若Handler没有标识@SessionAttributes注解或@SessionAttributes注解的value值不包含key，则会通过反射来创建pojo类型的参数，从而传入为目标方法的参数
	 *		5、springmvc会把key和value保存到implicitModel中，从而会保存到request中。
	 *
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("testModelAttribute: " + user);
		return SUCCESS;
	}
	
	/**
	 * @SessionAttributes除了可以通过属性名指定需要放到session会话中的属性外(实际上使用的是该注解的value属性值),
	 * 	 还可以通过模型属性的对象类型指定哪些模型属性需要放到session中(实际上使用该注解的types属性值)
	 * @SessionAttributes中的value表示该值需要放到session中
	 * @SessionAttributes中的types表示值类型为指定类型的值需要放到session中
	 * 
	 * 注意：
	 * 	该注解只能放在类上面，而不能放在方法上面
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> maps){
		Address addr = new Address();
		maps.put("address", addr);//只放入到request中
		
		User user = new User("Cay", "123456", "qqqq@qq.com", 16);
		maps.put("user", user);//既放入request，也放入session，通过value属性值
		maps.put("school", "school");//既放入request，也放入session，通过types属性值
		return SUCCESS;
	}
	
	/*
	 * 目标方法可以添加Map(实际上也可以是Model类型或者ModelMap类型)类型作为参数
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> maps){
		System.out.println(maps.getClass().getName());
		maps.put("names", Arrays.asList("Cay", "Amy", "Tom"));
		return SUCCESS;
	}
	
	/**
	 * 目标方法的返回值可以是ModelAndView类型
	 * 其中可以包含视图和模型信息
	 * SpringMVC会把ModelAndView的model中的数据放入到request域对象中。
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){
		ModelAndView mv = new ModelAndView();
		
		//添加模型数据到ModelAndView中
		mv.addObject("time", new Date());
		
		mv.setViewName(SUCCESS);
		return mv;
	}
	
	/**
	 * 可以使用Servlet原生的API作为目标方法的参数
	 * 具体支持以下类型：
	 * 		HttpServletRequest
	 * 		HttpServletResponse
	 * 		Principal
	 * 		Locale
	 * 		InputStream
	 * 		OutputStream
	 * 		Reader
	 * 		Writer
	 * 
	 */
	@RequestMapping("/testServletAPI")
	public String testServletAPI(HttpServletRequest request, HttpServletResponse response){
		System.out.println("testServletAPI" + request + "," + response);
		return SUCCESS;
	}
	
	/**
	 * SpringMVC会按请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性值，并且支持级联属性，如address.city等
	 * 对于未自动匹配填充的属性，按照默认值
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("testPojo :" + user);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue用来映射一个cookie值
	 * 
	 */
	@RequestMapping("/testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String sessionId){
		System.out.println("testCookieValue, sessionId: " + sessionId);
		return SUCCESS;
	}
	
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader("Accept-Language") String al){
		System.out.println("testRequestHeader: " + al);
		return SUCCESS;
	}
	
	/**
	 * @RequestParam来映射请求参数
	 * value 即请求参数的参数名
	 * required 该参数是否为必须，默认为true
	 * defaultValue 请求参数的默认值(String类型)
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam("username") String un,
			@RequestParam(value="age", required=false, defaultValue="10") int age){
		System.out.println("testRequestParam, username :" + un + ", age: " + age);
		return SUCCESS;
	}
	
	/**
	 * Rest风格的URL				传统URL
	 * 新增： /xxx POST
	 * 修改：/xxx/id PUT			update?id=1
	 * 获取：/xxx/id GET			get?id=1
	 * 删除：/xxx/id DELETE  		delete?id=1
	 * 
	 *	如何发送PUT和DELETE请求？
	 *	1、需要在web.xml配置org.springframework.web.filter.HiddenHttpMethodFilter过滤器
	 *	2、需要发送POST请求
	 *	3、需要在发送POST请求时携带一个隐藏域，name为_method， value为PUT或DELETE
	 *
	 *	在SpringMVC的目标方法中如何获取id呢？
	 *	使用@PathVariable
	 */
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.PUT)
	public String testRestPut(@PathVariable("id") int id){
		System.out.println("testRest Put: " +  id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.DELETE)
	public String testRestDelete(@PathVariable("id") int id){
		System.out.println("testRest Delete: " +  id);
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest", method=RequestMethod.POST)
	public String testRestPost(){
		System.out.println("testRest Post");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testRest/{id}", method=RequestMethod.GET)
	public String testRestGet(@PathVariable("id") int id){
		System.out.println("testRest Get: " +  id);
		return SUCCESS;
	}
	
	/**
	 * @PathVariable可以来映射url中的占位符到目标方法的参数中
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{uid}")
	public String testPathVariable(@PathVariable("uid") int id){
		System.out.println("testPathVariable, id: " + id);
		return SUCCESS;
	}
	
	/**
	 * ?代表一个任意字符
	 * 一个*代表一个通配符，表示任意多个字符，但是只能一层
	 * 两个**代表多层通配符，匹配多层路径
	 * @return
	 */
	@RequestMapping("/testAnt/*/xyz")
	public String testAnt(){
		System.out.println("testAnt");
		return SUCCESS;
	}
	
	//http://localhost:8080/springmvc-1/springmvc/testParamsAndHeaders?username=Cay&age=11		ok
	//http://localhost:8080/springmvc-1/springmvc/testParamsAndHeaders?username=Cay&age=10		error
	/**
	 * params和headers来更加精确地映射请求，支持简单的表达式
	 * xxx : 表示必须包含xxx
	 * !xxx : 表示不能包含xxx
	 * xxx=value : xxx的值必须等于value
	 * xxx!=value : xxx的值不能等于value
	 */
	//params表示请求参数必须有username和age，而且age的值不能为10
	@RequestMapping(value="/testParamsAndHeaders", params={"username","age!=10"})
	public String testParamsAndHeaders(){
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	@RequestMapping(value="/testMethod", method=RequestMethod.POST)
	public String testMethod(){
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping(){
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
}
