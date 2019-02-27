package com.library.page;

import java.util.List;

/**
 * 
 * @author 刘鸿伟
 *
 */
public class PageResult {
	// 数据库查询值
	private List<?> listData; // 每页的内容 结果集
	private int recordTotal; // 总条数
	// 页面传入值
	private int currentPage; // 当前页
	private int pageSize; // 每页大小
	// 计算值
	private int previousPage; // 前一页
	private int nextPage; // 下一页
	private int firstPage = 1; // 第一页 固定为1
	private int lastPage; // 最后一页
	private int totalPage; // 总页数
	// 引用谷歌算法 分页
	private int beginPage; // 开始页
	private int endPage; // 结束页
	private int totalIndexCount = 5; // 显示的页数个数 默认为5个

	public PageResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param listData
	 *            结果集
	 * @param currentPage
	 *            当前页
	 * @param pageSize
	 *            每页条数
	 * @param recordTotal
	 *            总条数
	 */
	public PageResult(List<?> listData, int currentPage, int pageSize, int recordTotal) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.recordTotal = recordTotal;
		this.listData = listData;

		// 总页数
		this.totalPage = this.recordTotal % this.pageSize > 0 ? this.recordTotal / this.pageSize + 1
				: this.recordTotal / this.pageSize;

		// 最后一页==总页数
		this.lastPage = this.totalPage;

		// 前一页
		if (this.currentPage > 1) {
			this.previousPage = this.currentPage - 1;
		} else {
			this.previousPage = this.firstPage;
		}

		// 下一页
		if (this.currentPage < this.lastPage) {
			this.nextPage = this.currentPage + 1;
		} else {
			this.nextPage = this.lastPage;
		}

		/**
		 * 首页 上页 [1] 2 3 4 5 6 7 8 9 10 下页 末页 当前第1/18页一共53条记录 每页5 条
		 * 
		 * @param totalIndexCount
		 *            总索引数 (要显示的页数个数:3 4 5 6 7 为5个)
		 * @param currentPage
		 *            当前页
		 * @param totalPage
		 *            总页数
		 * @return
		 */
		this.beginPage = currentPage - (totalIndexCount % 2 == 0 ? totalIndexCount / 2 - 1 : totalIndexCount / 2);
		this.endPage = currentPage + totalIndexCount / 2;

		if (this.beginPage < 1) {
			this.beginPage = 1;
			if (totalPage >= totalIndexCount)
				this.endPage = totalIndexCount;
			else
				this.endPage = totalPage;
		}
		if (this.endPage > totalPage) {
			this.endPage = totalPage;
			if ((this.endPage - totalIndexCount) > 0)
				this.beginPage = this.endPage - totalIndexCount + 1;
			else
				this.beginPage = 1;
		}

	}

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	public int getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotalIndexCount() {
		return totalIndexCount;
	}

	public void setTotalIndexCount(int totalIndexCount) {
		this.totalIndexCount = totalIndexCount;
	}

	@Override
	public String toString() {
		return "PageResult [listData=" + listData + ", recordTotal=" + recordTotal + ", currentPage=" + currentPage
				+ ", pageSize=" + pageSize + ", previousPage=" + previousPage + ", nextPage=" + nextPage
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", totalPage=" + totalPage + ", beginPage="
				+ beginPage + ", endPage=" + endPage + ", totalIndexCount=" + totalIndexCount + "]";
	}

}
