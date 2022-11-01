package edu.spring.ex10.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.service.CartService;

@Controller
//@RequestMapping(value="/cart")
public class CartController {
	@GetMapping("/cart")
	public void carts() {
		
	}
//	private static final Logger logger =
//			LoggerFactory.getLogger(ProductController.class);
//	
//	@Autowired
//	private CartService cartservice;
//	
//	@GetMapping("/cartinsert")
//	public void insertGET() {
//		logger.info("-----------insertGET()ȣ��-----------");
//	}//end registerGET()
//	
//	@PostMapping("/cartinsert")
//	public String insertPOST(CartVO vo, HttpSession session) {
//		// RedirectAttributes
//		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
//		logger.info("-----------insertPOST()ȣ��-----------");
//		logger.info(vo.toString());
//		
//		String userId=(String)session.getAttribute("userId");
//		vo.setUserId(userId);
//		// ��ٱ��Ͽ� ���� ��ǰ�� �ִ��� �˻�
//		int count = cartservice.countCart(vo.getProductId(), userId)
//		==0 ? cartservice.updateCart(vo) : cartservice.create(vo);
//		
//		if(count ==0) {
//			// ������ insert
//			cartservice.create(vo);
//		}else {
//			// ������ update
//			cartservice.updateCart(vo);
//		}		
//		
//		return "redirect:/cart/list";
//		
//	}//end registerPOST()
//	
//	@GetMapping("/cartlist")
//	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
//	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
//	public void list(HttpSession session, Model model) {
//		logger.info("-----------cartlist()ȣ��-----------");
////		String userId=(String)session.getAttribute("userId");
//		String userId="1";
//		Map<String, Object>map = new HashMap<String, Object>();
//		List<CartVO>list = cartservice.readCart(userId); //��ٱ��� ����
//		int sumMoney = cartservice.sumMoney(userId);
//		
//		map.put("list", list);
//		map.put("count", list.size());
//		map.put("sumMoney", sumMoney);
//		
//		model.addAttribute("map", map);
//		
//		
//	}//end list
//	
//	@PostMapping("/delete")
//	public String delete(int cartId, RedirectAttributes reAttr) {
//		logger.info("-----------delete()ȣ��: cartId = "+cartId+"-----------");
//		int result=cartservice.delete(cartId);
//		if(result==1) {
//			reAttr.addFlashAttribute("delete_result", "success"); 
//			
//			return "redirect:/cart/list";
//			// reAttr.addFlashAttribute("insert_result", "success");  alert���� ������ insert �κ� ����!
//			// ������ ������ ��ΰ� list�� ���⶧���� list.jsp�� ���� jquery�� �ۼ��Ѵ�  
//		}else {
//			return "redirect:/cart/list";
//		}
//	}//end delete
//	
//	@GetMapping("/update") // �ϴ� �������� �ʿ��ؼ� get���� ������
//	public void updateGET() {
//		logger.info("-----------updateGET()ȣ��----------");		
//		
//	}//end updateGET
//	
//	@PostMapping("/update")
//	public String updatePOST(int[]amount, int[]productId, HttpSession session) {
//		logger.info("-----------updatePOST()ȣ��-----------");
//		String userId= (String) session.getAttribute("userId");
//		
//		CartVO vo= new CartVO(); //�̰� �����ϰ� for������ �ٽ� ��´�!
//		for(int i=0; i<productId.length; i++) {
//			vo.setUserId(userId);
//			vo.setAmount(amount[i]);
//			vo.setProductId(productId[i]);
//			cartservice.updateCart(vo);
//		}
//		
//		return "redirect:/cart/list";
//		
//	}//end updatePOST
	
	
}












