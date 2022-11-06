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

import edu.spring.ex10.domain.ECartVO;
import edu.spring.ex10.service.ECartService;
import edu.spring.ex10.util.MediaUtil;

@Controller
@RequestMapping(value="/ecart")
public class ECartController {
	
	@GetMapping("/ecart")
	public void cart() {
		
	}
	private static final Logger logger =
			LoggerFactory.getLogger(ECartController.class);
	
	@Autowired
	private ECartService ecartservice;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@GetMapping("/ecartlist")
	// model �� modelAndView �� �������� ���ϰ��� ��� ǥ���ϳ��� ���̰� �ִ�!
	// ���� �ð��� ��� ��İ� ǥ����� model�̹Ƿ� �򰥸����ʰ� model ���!
	public void list(HttpSession session, Model model) {
		logger.info("-----------ecartlist()ȣ��-----------");
//		String userId=(String)session.getAttribute("userId");
		String userId="1";
		Map<String, Object>map = new HashMap<String, Object>();
		List<ECartVO>list = ecartservice.readECart(userId);
				
		map.put("list", list);
		map.put("count", list.size());		
		
		model.addAttribute("map", map);
		
		
	}//end list
	
	
	@PostMapping("/delete")
	public String delete(int ecartId, RedirectAttributes reAttr, Model model) {
		logger.info("-----------delete()ȣ��: ecartId = "+ecartId+"-----------");
		int result=ecartservice.delete(ecartId);
		if(result==1) {
			reAttr.addFlashAttribute("delete_result", "success"); 
			model.addAttribute("msg", "���� ����");
			return "redirect:/ecart/ecartlist";
			// reAttr.addFlashAttribute("insert_result", "success");  alert���� ������ insert �κ� ����!
			// ������ ������ ��ΰ� list�� ���⶧���� list.jsp�� ���� jquery�� �ۼ��Ѵ�  
		}else {
			model.addAttribute("msg", "���� ����");
			return "redirect:/ecart/ecartlist";
		}
	}//end delete
	
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
