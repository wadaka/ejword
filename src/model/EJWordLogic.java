package model;

import java.util.List;

import dao.WordDAO;

public class EJWordLogic {
	public void execute(EJWord ejw) {
		WordDAO dao= new WordDAO();
		int total=dao.getCount(ejw.getSearchWord(), ejw.getMode());
		ejw.setTotal(total);
		List<Word>list = dao.getListBySearchWord(ejw.getSearchWord(),ejw.getMode(),ejw.getLimit(),(ejw.getPageNo()-1)*ejw.getLimit() );
		ejw.setList(list);
		//pagination
		if(total > ejw.getLimit()) {
			int pageCount= (total-1)/ejw.getLimit() +1;
			String[][] pager;
			if(pageCount < 20) {
				pager = new String[pageCount] [];
				for(int i=0;i<pageCount;i++) {
					pager[i]=new String[] {ejw.getPageNo()==i+1?"active":"",i+1+"",i+1+""};
				}
			}else {
				int before = Math.min(ejw.getPageNo()-1, 5);
				int after = Math.min(pageCount-ejw.getPageNo(), 5);
				int len= 1+before+1+after+1;
				pager=new String[len][];
				pager[0] = new String[] {ejw.getPageNo()==1?"disabled":"",1+"","«"};
				for(int i=1,page=ejw.getPageNo()-before;i<len-1;i++,page++) {
					//String.valueOf(page)
					pager[i] = new String[] {page==ejw.getPageNo()?"active":"",page+"",page+""};
				}
				pager[len-1] = new String[] {ejw.getPageNo()==pageCount? "disabled":"",pageCount+"","»"};
			}
			ejw.setPager(pager);
		}
	}
}
