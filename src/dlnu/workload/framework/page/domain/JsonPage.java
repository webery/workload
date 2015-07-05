package dlnu.workload.framework.page.domain;

import java.io.Serializable;
import java.util.List;

public class JsonPage implements Serializable {

	private static final long serialVersionUID = 1L;
	private PageList pageList = null;
	Paginator paginator = null;

	public JsonPage() {
	}

	public JsonPage(List list) {
		this.pageList = (PageList) list;
		this.pageList = pageList;
		this.paginator = pageList.getPaginator();
	}

	public PageList getPageList() {
		return pageList;
	}

	public void setPageList(PageList pageList) {
		this.pageList = pageList;
	}

	public Paginator getPaginator() {
		return paginator;
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

}
