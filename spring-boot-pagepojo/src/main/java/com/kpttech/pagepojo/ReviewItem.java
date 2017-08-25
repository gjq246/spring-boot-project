/**
 * solr数据项
 */

package com.kpttech.pagepojo;

import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewItem {
	
	@Field("id")
	private String id;//id一般是各个表的主键，而在推优管理中有两个评语，所以推优评语的主键可以是id+2,然后表名是tscore2

	/**
	 * title对应review
	 */
	@Field("title")
	private String title;//对应review
	
	@Field("tpclass")
	private String tpclass;//来自topic
	@Field("grade")
	private String grade;

	@Field("tname")
	private String tname;
	@Field("tid")
	private String tid;//public是公用评语
	
	@Field("tbname")
	private String tbname;//表名作为关联 

	
	/**
	 * 页面参数增删改可以不用传
	 */
	private Integer page;//用来传页面参数，不做搜索
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getTbname() {
		return tbname;
	}
	public void setTbname(String tbname) {
		this.tbname = tbname;
	}
	public String getTpclass() {
		return tpclass;
	}
	public void setTpclass(String tpclass) {
		this.tpclass = tpclass;
	}
	
	
	

}
