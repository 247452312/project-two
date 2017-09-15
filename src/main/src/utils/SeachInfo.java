package utils;

@SuppressWarnings("all")
public class SeachInfo {
//where ,sort,limit
// pageno

	private int maxrow=10;

	private String where,sort,limit;
	private int pageno,nextpage,prepage,pagecount;
	private String table,col,value,math="like",type="String";
	private String inTable;
	private boolean canpage=true;
	private long rowcount=0;
	public SeachInfo() {
	}



	public SeachInfo(String table, String col, String value, boolean canpage) {
		super();
		setTable(table);
		setCol(col);
		setValue(value);
		setCanpage(canpage);
	}



	public SeachInfo(boolean canpage) {
		super();
		this.canpage = canpage;
	}


	public String getCol() {
		return col;
	}
	public String getLimit() {
		if(!canpage) return "";
		if(pageno==0)pageno=1;
		int pos=(pageno-1)*maxrow;
		return " limit "+pos+","+maxrow;
	}
	public String getMath() {
		return math;
	}
	public int getNextpage() {
		return nextpage;
	}
	public int getPagecount() {
		return pagecount;
	}
	public int getPageno() {
		return pageno;
	}
	public int getPrepage() {
		return prepage;
	}
	public long getRowcount() {
		return rowcount;
	}
	public String getSort() {
		return sort;
	}
	public String getTable() {
		return table;
	}
	public String getValue() {
		return value;
	}
	public String getWhere() {
		if(where!=null&&where.length()>0)return where;
		else{
			if(table!=null&&table.length()>0){
				if(value==null||value.length()==0) return where;
				String v=value;
				if(math.equalsIgnoreCase("like")) v=" '%"+v+"%'";
				else if(math.equalsIgnoreCase("in"))v = " ("+v+")";
				else if(type.equalsIgnoreCase("string")) v="'"+v+"'";
				return " where "+table+"."+col+" "+math+" "+v+" ";
			}
		}
		return "";
	}
	public String getNoWhere(){
		if(where!=null&&where.length()>0)return where;
		else{
			if(table!=null&&table.length()>0){
				if(value.length()==0) return where;
				String v=value;
				if(math.equalsIgnoreCase("like")) v=" '%"+v+"%'";
				else if(math.equalsIgnoreCase("in"))v = " ("+v+")";
				else if(type.equalsIgnoreCase("string")) v="'"+v+"'";
				return table+"."+col+" "+math+" "+v+" ";
			}
		}
		return "";
	}
	public boolean isCanpage() {
		return canpage;
	}
	public void setCanpage(boolean canpage) {
		this.canpage = canpage;
	}
	public void setCol(String col) {
		this.col = col;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public void setMath(String math) {
		this.math = math;
	}
	public void setNextpage(int nextpage) {
		this.nextpage = nextpage;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public void setPageno(int pageno) {
		this.pageno = pageno;
	}
	public void setPrepage(int prepage) {
		this.prepage = prepage;
	}
	public void setRowcount(long rowcount) {
		this.rowcount = rowcount;
		if(rowcount==0){
			pagecount=1;
		}else{
			pagecount=((int)(rowcount-1)/maxrow)+1;
		}
		if(pageno==0)pageno=1;
		if(pageno<pagecount)nextpage=pageno+1;
		else nextpage=pageno;

		if(pageno>2)prepage=pageno-1;
		else prepage=1;

	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setWhere(String where) {
		this.where = where;
	}


	public String getInTable() {
		return inTable;
	}


	public void setInTable(String inTable) {
		this.inTable = inTable;
	}



	public int getMaxrow() {
		return this.maxrow;
	}



	public void setMaxrow(int maxrow) {
		this.maxrow = maxrow;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


}
