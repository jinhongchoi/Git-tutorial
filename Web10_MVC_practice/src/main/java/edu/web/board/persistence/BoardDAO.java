package edu.web.board.persistence;

import java.util.List;

import edu.web.board.domain.BoardVO;
import edu.web.util.PageCriteria;

public interface BoardDAO {
	
	public abstract int insert(BoardVO vo);
	
	public abstract List<BoardVO>select();
	
	public abstract BoardVO select(int boardId);
	
	public abstract int update(BoardVO vo);
	
	public abstract int delete(int boardId);

	public abstract List<BoardVO>select(PageCriteria criteria);
	
	public abstract int getTotalCounts();
}
