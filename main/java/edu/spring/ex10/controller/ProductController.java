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


import edu.spring.ex10.domain.ProductVO;
import edu.spring.ex10.pageutil.PageCriteria;
import edu.spring.ex10.pageutil.PageMaker;
import edu.spring.ex10.service.ProductService;
import edu.spring.ex10.util.FileUploadUtil;
import edu.spring.ex10.util.MediaUtil;


@Controller
@RequestMapping(value="/product")
public class ProductController {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService ProductService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@GetMapping("/productregister")
	public void registerGET() {
		logger.info("-----------productregisterGET()ȣ��-----------");
	}//end registerGET()
	
	@PostMapping("/productregister")
	public String registerPOST(ProductVO vo, @RequestParam("files") MultipartFile[] files, RedirectAttributes reAttr) throws IOException{
		// RedirectAttributes
		// - ���ο� ��� ��ġ�� �Ӽ����� �����ϴ� ��ü
		logger.info("-----------productregisterPOST()ȣ��--"+vo.toString()+"---------");
		
		
		String img="";
		for(MultipartFile f: files) {
			img += saveUploadFile(f)+ "";
		}
		logger.info("���� ���� ���" + uploadPath);
		logger.info("img = " + img);
		vo.setProductUrl(img);
		
		int result =ProductService.create(vo);
		
		logger.info(result+ "�� ����");
		 
		
		return "redirect:/product/productlist";
		
	}//end registerPOST()
	
	@GetMapping("/productlist")// jsp ���̶� �Ȱ��� ���� ��!
	public void list(Model model, Integer page, Integer numsPerPage, String productCate) {
		logger.info("-----------list()ȣ��-----------");
//		logger.info("page = "+page + ", numsPerPage = "+ numsPerPage);

//		PageCriteria criteria = new PageCriteria();
//		if(page != null) {
//			criteria.setPage(page);
//		}
//		
//		if(numsPerPage != null) {
//			criteria.setNumsPerPage(numsPerPage);
//		}
	
		productCate ="����";		
		
		List<ProductVO>listProduct = ProductService.listProduct(productCate);
		
		model.addAttribute("listProduct", listProduct);	
		
		productCate ="����";		
		
		List<ProductVO>listProduct2 = ProductService.listProduct(productCate);
		
		model.addAttribute("listProduct2", listProduct2);	
		
		productCate ="����";		
		
		List<ProductVO>listProduct3 = ProductService.listProduct(productCate);
		
		model.addAttribute("listProduct3", listProduct3);	
//		model.addAttribute("listProduct", listProduct);	
//		
//		PageMaker pageMaker = new PageMaker();
//		pageMaker.setCriteria(criteria);
//		pageMaker.setTotalCount(ProductService.getTotalCount());
//		pageMaker.setPageData();
//		model.addAttribute("pageMaker", pageMaker);
		
				
	}//end list
	
	@GetMapping("/productlistDetail")// jsp ���̶� �Ȱ��� ���� ��!
	public void list(Model model, String productCate) {
		logger.info("-----------listDetail()ȣ��-----------");
		

		List<ProductVO>listProduct = ProductService.listProduct(productCate);
		model.addAttribute("listProduct", listProduct);	
		
				
	}//end list
	
	@GetMapping("/productDetail") //detail�� �������°�  get ����̱� ������ GetMapping�� ����Ѵ�
	public void detail(Model model, Integer productId) {
		logger.info("-----------detail()ȣ��: productId= " + productId +"-----------");
		ProductVO vo = ProductService.detailProduct(productId);
		model.addAttribute("vo", vo);
		
		// model�� �̿��� jsp�� ������!
		// ������ servlet ���� request.setAttribute() �̰ſ� ���� -> request�� session�� ���� ����!
		
	}//end detail
	
	@GetMapping("/productupdate") // �ϴ� �������� �ʿ��ؼ� get���� ������
	public void productpdateGET(Model model, Integer productId, Integer page) {
		logger.info("-----------updateGET()ȣ��: productId = "+productId+"-----------");
		ProductVO vo = ProductService.detailProduct(productId); //�������� ���� �������� boardService�� ���ؼ� �ʿ��� ������ �����´�.
		model.addAttribute("vo", vo);
		
	}//end updateGET
	
	@PostMapping("/productupdate")
	public String productpdatePOST(ProductVO vo, @RequestParam("files") MultipartFile[] files, RedirectAttributes reAttr) {
		logger.info("-----------updatePOST()ȣ��: vo = "+vo.toString()+"-----------");
		
		String img="";
		for(MultipartFile f: files) {
			img += saveUploadFile(f)+ "";
		}
		logger.info("���� ���� ���" + uploadPath);
		logger.info("img = " + img);
		vo.setProductUrl(img);
		
		
		int result = ProductService.update(vo);
		if(result ==1) {
			
			return "redirect:/product/productlist";
			// /board/list ��η� �̵�. get ���
			// get ����̹Ƿ� page�� ������ ��� ����
		}else {
			return "redirect:/product/productupdate?product="+ vo.getProductId();
		}
		
	}//end updatePOST
	
	@PostMapping("/productdelete")
	public String delete(Integer productId, RedirectAttributes reAttr) {
		logger.info("-----------delete()ȣ��: productId = "+productId+"-----------");
		int result = ProductService.delete(productId);
		if(result==1) {
			reAttr.addFlashAttribute("delete_result", "success"); 
			
			return "redirect:/product/productlist";
			// reAttr.addFlashAttribute("insert_result", "success");  alert���� ������ insert �κ� ����!
			// ������ ������ ��ΰ� list�� ���⶧���� list.jsp�� ���� jquery�� �ۼ��Ѵ�  
		}else {
			return "redirect:/product/productdetail";
		}
	}//end delete
		
	
	@GetMapping("/upload-ajax")
	public void uploadAjaxGET() {
		logger.info("=====uploadAjaxGET() ȣ��=====");
	}
	
	@PostMapping("/upload-ajax")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile[] files) throws IOException{
		logger.info("=====uploadAjaxPOST() ȣ��=====");
		
		// ���� �ϳ��� ����
		String result = null; // result : ���� ��� �� ����� �̹��� �̸�
		result = FileUploadUtil.saveUploadedFile(uploadPath, 
				files[0].getOriginalFilename(), 
				files[0].getBytes());
		logger.info(result);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
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







