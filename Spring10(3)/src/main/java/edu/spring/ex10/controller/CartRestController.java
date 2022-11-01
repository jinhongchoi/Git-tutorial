package edu.spring.ex10.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.service.CartService;



@RestController
@RequestMapping(value="/carts")
public class CartRestController {
	
	private static final Logger logger=
			LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
	private CartService cartService;
	
	@PostMapping
	public ResponseEntity<Integer>createCart(@RequestBody CartVO vo){
		// @RequestBody
		// -Ŭ���̾�Ʈ���� ���۹��� json �����͸� �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("-----------createCarts() ȣ��: vo=" + vo.toString()+"-----------");

		// ResponseEntity<T> : Rest ��Ŀ��� �����͸� ������ �� ���̴� ��ü
		// -�����Ϳ� HttpStatus�� ����
		// - <T> : �������� �ϴ� ������ Ÿ��
		
		int result =cartService.create(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}
	
	@GetMapping("/cartlist")
	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
	public void list(HttpSession session, Model model) {
		logger.info("-----------cartlist()ȣ��-----------");
//		String userId=(String)session.getAttribute("userId");
		String userId="1";
		Map<String, Object>map = new HashMap<String, Object>();
		List<CartVO>list = cartService.readCart(userId); //��ٱ��� ����
		int sumMoney = cartService.sumMoney(userId);
		
		map.put("list", list);
		map.put("count", list.size());
		map.put("sumMoney", sumMoney);
		
		model.addAttribute("map", map);
		
		
	}//end list

}










