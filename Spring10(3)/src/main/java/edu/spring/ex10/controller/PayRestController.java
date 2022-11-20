package edu.spring.ex10.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.spring.ex10.domain.PayDetailVO;
import edu.spring.ex10.service.PayService;

@RestController
@RequestMapping(value="/pays")
public class PayRestController {
	
	private static final Logger logger=
			LoggerFactory.getLogger(CartRestController.class);
	
	@Autowired
	private PayService payservice;
	
	@PostMapping
	public ResponseEntity<Integer>payinsertPOST(@RequestBody PayDetailVO vo){
		// @RequestBody
		// -Ŭ���̾�Ʈ���� ���۹��� json �����͸� �ڹ� ��ü�� ��ȯ���ִ� annotation
		logger.info("-----------payinsertPOST() ȣ��: vo=" + vo.toString()+"-----------");


		int result= 0; 
		try {
			
			result=payservice.create2(vo);
	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // �̷��� �ϸ� ����� ���� 1 �ƴϸ� 0�� �Ǿ� ���´�
		
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
		
	}//end createCart
	

}
