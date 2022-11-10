package edu.spring.ex10.service;

import java.util.List;

import edu.spring.ex10.domain.BoardVO;
import edu.spring.ex10.pageutil.PageCriteria;



// CRUD(Create, Read, Update, Delete)
public interface BoardService {
		
	int create(BoardVO vo);
	
	List<BoardVO>read(PageCriteria criteria);
	
	BoardVO read(int boardId);
	
	int update(BoardVO vo);
	
	int delete(int boardId);
		
	int getTotalCount();
	
}
