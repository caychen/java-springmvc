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
	 * 1����@ModelAttribute��ǵķ���������ÿ��Ŀ�귽��ִ��֮ǰ��SpringMVC���ã�
	 * 2��@ModelAttributeע��Ҳ������������Ŀ�귽��POJO���͵���Σ���valueֵ�����¼������ã�
	 * 		(1)��springmvc��ʹ��value����ֵ��implicitModel�в��Ҷ�Ӧ�Ķ���,���������ֱ�Ӵ��뵽Ŀ�귽��������С�
	 * 		(2)��springmvc�Ὣkey��value���뵽request��
	 */
	@ModelAttribute
	public User getUser(@RequestParam(value="id",required=false) Integer id
			,Map<String, Object> maps){
		System.out.println("ModelAttribute method");
		if(id != null){
			//ģ������ݿ��л�ȡһ��User����
			User user = new User(1, "Cay", "123456", "1111@qq.com", 25);
			System.out.println("ģ������ݿ��л�ȡһ��User����");
			maps.put("user", user);
		}
		return null;
	}
	/**
	 * ִ�����̣�
	 * 	1��ִ��@ModelAttributeע�����εķ����������ݿ���ȡ�����󣬰Ѷ�����뵽map�У���Ϊuser
	 *  2��SpringMVC��map��ȡ��user���󣬲��ѱ����������������user����Ķ�������
	 *  3��SpringMVC������������Ŀ�귽���Ĳ���
	 *  
	 * ע�⣺
	 * 	��@ModelAttribute���εķ����У����뵽Mapʱ�ļ���Ҫ��Ŀ�귽��������͵ĵ�һ����ĸСд���ַ���һ��  
	 * 
	 * Դ����������̣�
	 * 	1������@ModelAttributeע�����εķ�����ʵ���ϰ�@ModelAttribute������Map�е����ݷ�����implicitModel��
	 * 	2�������������е�Ŀ�������ʵ���ϸ�Ŀ�귽������������WebDataBinder�����target������
	 * 		(1)������WebDataBinder����
	 * 			i��ȷ��objectName���ԣ��������attrName����ֵΪ"",��objectNameΪ���� ���ҵ�һ����ĸСд
	 * 				ע�⣺attrName����Ŀ�귽����POJO����ʹ����@ModelAttribute�����Σ���attrNameֵ��Ϊ@ModelAttribute��value����ֵ
	 * 			ii��ȷ��target����ֵ��
	 * 				> ��implcitModel�в���attrName��Ӧ������ֵ�������ڣ�
	 * 				> �������ڣ�����֤��ǰHandler�Ƿ�ʹ����@SessionAttributes�������Σ���ʹ���ˣ����Դ�Session�л�ȡattrName����Ӧ������ֵ����session��û�ж�Ӧ������ֵ�����׳��쳣��
	 * 				> ��Handlerû��ʹ��@SessionAttributes�������Σ�����@SessionAttributes��û��ʹ��valueֵָ����key��attrName��ƥ�䣬��ͨ�����䴴��POJO����.
	 * 
	 *		(2)��springmvc�ѱ������������ֵ����WebDataBinder��target��Ӧ�����ԣ�
	 *		(3)��springmvc���WebDataBinder��attrName��target��implicitModel���Ӷ�����request������С�
	 *		(4)����WebDataBinder��target��Ϊ�������ݸ�Ŀ�귽������Ρ�
	 *
	 *	springmvcȷ��Ŀ�귽����pojo������εĹ���
	 *		1��ȷ��һ��key
	 *			(1)����Ŀ�귽����pojo���͵Ĳ���δʹ��@ModelAttribute��Ϊ���Σ���keyΪPOJO�������ҵ�һ����ĸСд
	 *			(2)����ʹ����@ModelAttribute����Ϊ���Σ���keyΪ@ModelAttributeע���valueֵ��
	 *		2����implicitModel�в���key��Ӧ�Ķ��������ڣ�����Ϊ��δ���
	 *			(1)������@ModelAttribute��ʶ�ķ����е�Map�б��������key��(1)��ȷ����keyһ�£�����ȡ����
	 *		3����implicitModel�в�����key��Ӧ�Ķ������鵱ǰ��Handler�Ƿ�ʹ����@SessionAttributesע�����Σ�
	 *			��ʹ���˸�ע�⣬��@SessionAttributesע���value����ֵ�а�����key������HttpSession������ȡkey�����õ�valueֵ����������ֱ�Ӵ��뵽Ŀ�귽��������У��������ڣ����׳��쳣
	 *		4����Handlerû�б�ʶ@SessionAttributesע���@SessionAttributesע���valueֵ������key�����ͨ������������pojo���͵Ĳ������Ӷ�����ΪĿ�귽���Ĳ���
	 *		5��springmvc���key��value���浽implicitModel�У��Ӷ��ᱣ�浽request�С�
	 *
	 * @return
	 */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user){
		System.out.println("testModelAttribute: " + user);
		return SUCCESS;
	}
	
	/**
	 * @SessionAttributes���˿���ͨ��������ָ����Ҫ�ŵ�session�Ự�е�������(ʵ����ʹ�õ��Ǹ�ע���value����ֵ),
	 * 	 ������ͨ��ģ�����ԵĶ�������ָ����Щģ��������Ҫ�ŵ�session��(ʵ����ʹ�ø�ע���types����ֵ)
	 * @SessionAttributes�е�value��ʾ��ֵ��Ҫ�ŵ�session��
	 * @SessionAttributes�е�types��ʾֵ����Ϊָ�����͵�ֵ��Ҫ�ŵ�session��
	 * 
	 * ע�⣺
	 * 	��ע��ֻ�ܷ��������棬�����ܷ��ڷ�������
	 * @return
	 */
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> maps){
		Address addr = new Address();
		maps.put("address", addr);//ֻ���뵽request��
		
		User user = new User("Cay", "123456", "qqqq@qq.com", 16);
		maps.put("user", user);//�ȷ���request��Ҳ����session��ͨ��value����ֵ
		maps.put("school", "school");//�ȷ���request��Ҳ����session��ͨ��types����ֵ
		return SUCCESS;
	}
	
	/*
	 * Ŀ�귽���������Map(ʵ����Ҳ������Model���ͻ���ModelMap����)������Ϊ����
	 * @return
	 */
	@RequestMapping("/testMap")
	public String testMap(Map<String, Object> maps){
		System.out.println(maps.getClass().getName());
		maps.put("names", Arrays.asList("Cay", "Amy", "Tom"));
		return SUCCESS;
	}
	
	/**
	 * Ŀ�귽���ķ���ֵ������ModelAndView����
	 * ���п��԰�����ͼ��ģ����Ϣ
	 * SpringMVC���ModelAndView��model�е����ݷ��뵽request������С�
	 * @return
	 */
	@RequestMapping("/testModelAndView")
	public ModelAndView testModelAndView(){
		ModelAndView mv = new ModelAndView();
		
		//���ģ�����ݵ�ModelAndView��
		mv.addObject("time", new Date());
		
		mv.setViewName(SUCCESS);
		return mv;
	}
	
	/**
	 * ����ʹ��Servletԭ����API��ΪĿ�귽���Ĳ���
	 * ����֧���������ͣ�
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
	 * SpringMVC�ᰴ�����������POJO�����������Զ�ƥ�䣬�Զ�Ϊ�ö����������ֵ������֧�ּ������ԣ���address.city��
	 * ����δ�Զ�ƥ���������ԣ�����Ĭ��ֵ
	 */
	@RequestMapping("/testPojo")
	public String testPojo(User user){
		System.out.println("testPojo :" + user);
		return SUCCESS;
	}
	
	/**
	 * @CookieValue����ӳ��һ��cookieֵ
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
	 * @RequestParam��ӳ���������
	 * value ����������Ĳ�����
	 * required �ò����Ƿ�Ϊ���룬Ĭ��Ϊtrue
	 * defaultValue ���������Ĭ��ֵ(String����)
	 */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam("username") String un,
			@RequestParam(value="age", required=false, defaultValue="10") int age){
		System.out.println("testRequestParam, username :" + un + ", age: " + age);
		return SUCCESS;
	}
	
	/**
	 * Rest����URL				��ͳURL
	 * ������ /xxx POST
	 * �޸ģ�/xxx/id PUT			update?id=1
	 * ��ȡ��/xxx/id GET			get?id=1
	 * ɾ����/xxx/id DELETE  		delete?id=1
	 * 
	 *	��η���PUT��DELETE����
	 *	1����Ҫ��web.xml����org.springframework.web.filter.HiddenHttpMethodFilter������
	 *	2����Ҫ����POST����
	 *	3����Ҫ�ڷ���POST����ʱЯ��һ��������nameΪ_method�� valueΪPUT��DELETE
	 *
	 *	��SpringMVC��Ŀ�귽������λ�ȡid�أ�
	 *	ʹ��@PathVariable
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
	 * @PathVariable������ӳ��url�е�ռλ����Ŀ�귽���Ĳ�����
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{uid}")
	public String testPathVariable(@PathVariable("uid") int id){
		System.out.println("testPathVariable, id: " + id);
		return SUCCESS;
	}
	
	/**
	 * ?����һ�������ַ�
	 * һ��*����һ��ͨ�������ʾ�������ַ�������ֻ��һ��
	 * ����**������ͨ�����ƥ����·��
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
	 * params��headers�����Ӿ�ȷ��ӳ������֧�ּ򵥵ı��ʽ
	 * xxx : ��ʾ�������xxx
	 * !xxx : ��ʾ���ܰ���xxx
	 * xxx=value : xxx��ֵ�������value
	 * xxx!=value : xxx��ֵ���ܵ���value
	 */
	//params��ʾ�������������username��age������age��ֵ����Ϊ10
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
