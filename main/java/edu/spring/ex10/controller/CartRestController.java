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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		int result= 0; 
		try {
			
				int count = cartService.countCart(vo.getProductId(), "1");
				//String userId=(String)session.getAttribute("userId");
//				vo.setUserId(userId);
//				// ��ٱ��Ͽ� ���� ��ǰ�� �ִ��� �˻�
//				int count = cartservice.countCart(vo.getProductId(), userId)
//				������ session���� userid�� �޾ƿͼ� ���� userId ���� �� �ڸ�! "1" ���!				
				
				
				
//				==0 ? cartService.updateCart(vo) : cartService.create(vo);
				
				if(count ==0) {
					// ������ insert
					result =cartService.create(vo);
				}else {
					// ������ update
					result =cartService.updateCart(vo);
				}		
		
	
//			result = cartService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // �̷��� �ϸ� ����� ���� 1 �ƴϸ� 0�� �Ǿ� ���´�
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}//end createCart



}










