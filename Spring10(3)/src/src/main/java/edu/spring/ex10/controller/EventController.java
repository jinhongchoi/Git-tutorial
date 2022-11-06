package edu.spring.ex10.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.spring.ex10.domain.EventVO;
import edu.spring.ex10.domain.ProductVO;
import edu.spring.ex10.pageutil.PageCriteria;
import edu.spring.ex10.pageutil.PageMaker;
import edu.spring.ex10.service.EventService;
import edu.spring.ex10.util.MediaUtil;

@Controller
@RequestMapping(value="/event")
public class EventController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	private EventService EventService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@GetMapping("/eventregister")
	public void registerGET() {
		logger.info("-----------eventregisterGET()ȣ��-----------");
	}//end registerGET()
	
	@PostMapping("/eventregister")
	public String registerPOST(EventVO vo, @RequestParam("files") MultipartFile[] files, RedirectAttributes reAttr) throws IOException{
		// RedirectAttributes
		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("-----------eventregisterPOST()ȣ��--"+vo.toString()+"---------");
				
		String img="";
		for(MultipartFile f: files) {
			img += saveUploadFile(f)+ "";
		}
		logger.info("���� ���� ���" + uploadPath);
		logger.info("img = " + img);
		vo.setEventUrl(img);
		
		int result =EventService.create(vo);
		
		logger.info(result+ "�� ����");
		 
		
		return "redirect:/event/eventlist";
		
	}//end registerPOST()
	
	@GetMapping("/eventlist")// jsp ���̶� �Ȱ��� ���� ��!
	public void list(Model model, Integer page, Integer numsPerPage, String productCate) {
		logger.info("-----------list()ȣ��-----------");
		logger.info("page = "+page + ", numsPerPage = "+ numsPerPage);
		

		
		PageCriteria criteria = new PageCriteria();
		if(page != null) {
			criteria.setPage(page);
		}
		
		if(numsPerPage != null) {
			criteria.setNumsPerPage(numsPerPage);
		}
		
		List<EventVO>listEvent = EventService.read(criteria);		
		model.addAttribute("listEvent", listEvent);	
				
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(criteria);
		pageMaker.setTotalCount(EventService.getTotalCount());
		pageMaker.setPageData();
		model.addAttribute("pageMaker", pageMaker);
						
	}//end list
	
	@GetMapping("/eventDetail") //detail�� �������°�  get ����̱� ������ GetMapping�� ����Ѵ�
	public void detail(Model model, Integer eventId) {
		logger.info("-----------detail()ȣ��: eventId= " + eventId +"-----------");
		EventVO vo = EventService.detailEvent(eventId);
		model.addAttribute("vo", vo);
		
		// model�� �̿��� jsp�� ������!
		// ������ servlet ���� request.setAttribute() �̰ſ� ���� -> request�� session�� ���� ����!
		
	}//end detail
	
	@GetMapping("/eventupdate") // �ϴ� �������� �ʿ��ؼ� get���� ������
	public void productpdateGET(Model model, Integer eventId, Integer page) {
		logger.info("-----------updateGET()ȣ��: eventId = "+eventId+"-----------");
		EventVO vo = EventService.detailEvent(eventId); //�������� ���� �������� boardService�� ���ؼ� �ʿ��� ������ �����´�.
		model.addAttribute("vo", vo);
		
	}//end updateGET
	
	@PostMapping("/eventupdate")
	public String productpdatePOST(EventVO vo, @RequestParam("files") MultipartFile[] files, RedirectAttributes reAttr) {
		logger.info("-----------updatePOST()ȣ��: vo = "+vo.toString()+"-----------");
		
		String img="";
		for(MultipartFile f: files) {
			img += saveUploadFile(f)+ "";
		}
		logger.info("���� ���� ���" + uploadPath);
		logger.info("img = " + img);
		vo.setEventUrl(img);
		
		
		int result = EventService.update(vo);
		if(result ==1) {
			
			return "redirect:/event/eventlist";
			// /board/list ��η� �̵�. get ���
			// get ����̹Ƿ� page�� ������ ��� ����
		}else {
			return "redirect:/event/eventupdate?event="+ vo.getEventId();
		}
		
	}//end updatePOST
	
	@PostMapping("/eventdelete")
	public String delete(Integer eventId, RedirectAttributes reAttr) {
		logger.info("-----------delete()ȣ��: productId = "+eventId+"-----------");
		int result = EventService.delete(eventId);
		if(result==1) {
			reAttr.addFlashAttribute("delete_result", "success"); 
			
			return "redirect:/event/eventlist";
			// reAttr.addFlashAttribute("insert_result", "success");  alert���� ������ insert �κ� ����!
			// ������ ������ ��ΰ� list�� ���⶧���� list.jsp�� ���� jquery�� �ۼ��Ѵ�  
		}else {
			return "redirect:/event/eventdetail";
		}
	}//end delete
	
	
	// display �Լ��� ȣ���ϸ� �������� �̹����� Ȯ���� �� ���� - ���� ��θ� �����ؾ� ��
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
	
	private String saveUploadFile(MultipartFile file) {
		// UUID: ���ε��ϴ� ������ �ߺ����� �ʵ���
		UUID uuid = UUID.randomUUID();		//uuid �̿��ؼ� �θ� �������
		String savedName = uuid + "_"+ file.getOriginalFilename(); 
		File target= new File(uploadPath, savedName); 
		
		try {
			FileCopyUtils.copy(file.getBytes(), target); // ���⼭ ���� �� ����
			logger.info("=====���� ���� ����=====");
			return savedName;
		} catch (IOException e) {
			logger.info("=====���� ���� ����=====");
			e.printStackTrace();
			return null;
		}
		
		
	}//end saveUploadFile

}
