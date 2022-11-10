package edu.spring.ex10.controller;



import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.domain.PayDetailVO;
import edu.spring.ex10.domain.PayProductVO;
import edu.spring.ex10.domain.PayVO;
import edu.spring.ex10.domain.ProductVO;
import edu.spring.ex10.service.CartService;
import edu.spring.ex10.service.PayService;
import edu.spring.ex10.service.ProductService;
import edu.spring.ex10.util.MediaUtil;

@Controller
@RequestMapping(value="/pay")
public class PayController {
	
	@GetMapping("/pay")
	public void cart() {
		
	}
	
	private static final Logger logger =
			LoggerFactory.getLogger(PayController.class);
	
	@Autowired
	private PayService payservice;
	
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	
	@GetMapping("/payinsert")
	public void payinsertGET() {
		logger.info("-----------payinsertGET()ȣ��-----------");
	}//end registerGET()
	
	@PostMapping("/payinsert")
	public String payinsertPOST(PayVO vo,PayDetailVO vo2, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("-----------payinsertPOST()ȣ��-----------");
		logger.info(vo.toString());
//		int result= payservice.create(vo);
		int result=payservice.create2(vo2);
		logger.info(result+ "�� ����");
		
		return "redirect:../pay/paylistDetail";
				
	}//end registerPOST()
	
	@PostMapping("/payinsert2")
	public String payinsert2POST(PayVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		String userId="1";
		String userName="1";
		String userTell="1";
		logger.info("-----------payinsert2POST()ȣ��-----------");
		logger.info(vo.toString());
		vo.setUserId(userId);
		vo.setUserTell(userTell);
		vo.setUserName(userName);
		// ���� 3�� jsp���� �޴°ŷ� ����!
		
//		int result= payservice.create(vo);
		int result=payservice.create(vo);
		logger.info(result+ "�� ����");
		
		return "redirect:../pay/paylistDetail";
				
	}//end registerPOST()
	
	
	@GetMapping("/paylist")
	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
	public void list(HttpSession session, Model model, Integer productId, Integer amount) {
		logger.info("-----------paylist()ȣ��-----------");
//		String userId=(String)session.getAttribute("userId");
		String userId="1";
		Map<String, Object>map = new HashMap<String, Object>();
		List<CartVO>list = payservice.readCart(userId); //��ٱ��� ����
		int sumMoney = payservice.sumMoney(userId);
		
		map.put("list", list);
		map.put("count", list.size());
		map.put("sumMoney", sumMoney);
				
		model.addAttribute("map", map);		
		
		
	}//end list
	
	// ��ٱ��� �������
	@GetMapping("/paylistDetail")
	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
	public void listPayDetail(HttpSession session, Model model, PayDetailVO vo, Integer amount) {
		logger.info("-----------paylist()ȣ��-----------");
//		String userId=(String)session.getAttribute("userId");
		String userId="1";
//		Map<String, Object>map = new HashMap<String, Object>();
//		List<CartVO>list = payservice.readCart(userId); //��ٱ��� ����
		
		vo.setUserId(userId);
		
		List<PayDetailVO>listPayDetail=payservice.listPayDetail(vo);
		model.addAttribute("listPayDetail", listPayDetail);		
		
		payservice.cartAllDelete(userId);		
				
	}//end list
	
	
	
	
	//���� ���������� �ҷ�����
	@GetMapping("/paylist2")
	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
	public void list2(HttpSession session, Model model, Model model2, Integer productId, HttpServletRequest request) {
		logger.info("-----------paylist2()ȣ��-----------");
//		String userId=(String)session.getAttribute("userId");
		String userId="1";
		
		ProductVO vo= payservice.detailProduct(productId);	
				
		model.addAttribute("vo", vo);
		
	}//end list
	
	
	
	@GetMapping("/display")
	public ResponseEntity<byte[]>display(String fileName)throws Exception{
		logger.info("=====display() ȣ��");
		ResponseEntity<byte[]>entity = null;
		InputStream in = null;
		
		String filePath = uploadPath + fileName; // uploadPath -> �����ߴ� ���
		in= new FileInputStream(filePath);
		
		//���� Ȯ����
		String extension =
				filePath.substring(filePath.lastIndexOf(".")+1);
		logger.info(extension);
		
		// ���� ���(response header) �� Content-Type ����
		HttpHeaders httpHeaders = new HttpHeaders(); //org.springframework.http.HttpHeaders; �̰� ����Ʈ
		httpHeaders.setContentType(MediaUtil.geMediaType(extension));
		
		// ������ ����
		entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(in),// org.apache.commons.io.IOUtils; �̰� ����Ʈ
					httpHeaders,
					HttpStatus.OK
				);
		return entity;
		//http://localhost:8080/ex05/display?fileName=/unnamed.jpg �̰� �˻��ؼ� �ҷ��ͼ� �ִ��� Ȯ��
	}
	
}





