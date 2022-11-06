package edu.spring.ex10.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
	public String payinsertPOST(PayVO vo, RedirectAttributes reAttr) {
		// RedirectAttributes
		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("-----------payinsertPOST()ȣ��-----------");
		logger.info(vo.toString());
		int result= payservice.create(vo);
		logger.info(result+ "�� ����");
		if(result==1) {
			reAttr.addFlashAttribute("insert_result", "success");  // �� �ȶ��?
			// insert_result�� list.jsp�� ���� success��� ������ jsp�� ���޵Ǹ� jsp���� ó����
			return "redirect:../pay/paylistDetail"; // /pay/paylistDetail ��η� �̵�. get ���
		}else {
			return "redirect:/pay/payinsert";
		}
		
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
		
		
//		logger.info("-----------detail()ȣ��: productId= " + productId +"-----------");
//		logger.info("-----------detail()ȣ��: amount= " + amount +"-----------");
//		Map<String, Object>map2 = new HashMap<String, Object>();
//		ProductVO vo = payservice.detailProduct(productId);
//		map2.put("voId", vo.getProductId());
//		map2.put("voName", vo.getProductName());
//		map2.put("voUrl", vo.getProductUrl());
//		map2.put("voPrice", vo.getProductPrice());		
//		map2.put("amount", amount);
//		map2.put("voTotal", amount*vo.getProductPrice());
//		model.addAttribute("map2", map2);
		
		// model�� �̿��� jsp�� ������!
		// ������ servlet ���� request.setAttribute() �̰ſ� ���� -> request�� session�� ���� ����!
		
	}//end list
	
	
	@GetMapping("/paylistDetail")
	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
	public void listDetail(HttpSession session, Model model) {
		logger.info("-----------paylistDetail()ȣ��-----------");
//		String userId=(String)session.getAttribute("userId");
		String userId="1";
		Map<String, Object>map = new HashMap<String, Object>();
		List<PayVO>listDetail = payservice.listPay(userId); //��ٱ��� ����
				
		map.put("listDetail", listDetail);
		map.put("count", listDetail.size());		
		
		model.addAttribute("map2", map);		
		
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





