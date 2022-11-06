package edu.spring.ex10.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex10.domain.CartVO;
import edu.spring.ex10.domain.ECartVO;
import edu.spring.ex10.service.ECartService;

@RestController
@RequestMapping(value="/ecarts")
public class ECartRestController {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ECartRestController.class);
	
	@Autowired
	private ECartService ecartService;
	
	@PostMapping
	// ���� �߰�
	public ResponseEntity<Integer>createCart(@RequestBody ECartVO vo, Model model){
		// @RequestBody
		// -Ŭ���̾�Ʈ���� ���۹��� json �����͸� �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("-----------createCarts() ȣ��: vo=" + vo.toString()+"-----------");


		int result= 0; 
		try {
			
				int count = ecartService.countECart(vo.getEventId(), "1");
				//String userId=(String)session.getAttribute("userId");

				if(count ==0) {
					// ������ insert
					result =ecartService.create(vo);
				}else {
					model.addAttribute("msg", "�̹� �����ϴ� �����Դϴ�.");
					
				}		
	
//			result = cartService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // �̷��� �ϸ� ����� ���� 1 �ƴϸ� 0�� �Ǿ� ���´�
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}//end createECart

}
