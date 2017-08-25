package com.kpttech.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpttech.common.pojo.DataTableResult;
import com.kpttech.pagepojo.ReviewItem;

/**
 * solr访问接口
 * @author GJQ
 *
 */
@Service
public class SolrReviewService {

private static final Logger logger=Logger.getLogger(SolrReviewService.class);
	
	public static final Integer ROWS = 10;//每页记录数

    @Autowired
    private HttpSolrClient httpSolrClientReview;
    
    public String escapeQueryChars(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
          // These characters are part of the query syntax and must be escaped
          if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
            || c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
            || c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
            || Character.isWhitespace(c)) {
            sb.append('\\');
          }
          sb.append(c);
        }
        return sb.toString();
      }

	public DataTableResult search(String keyWords, Integer page) throws IOException {
		// TODO Auto-generated method stub
		DataTableResult dt=new DataTableResult();
		
		SolrQuery solrQuery = new SolrQuery(); // 构造搜索条件
//		solrQuery.setQuery("title:" + keyWords ); // 搜索关键词
		solrQuery.setQuery(keyWords ); // 搜索关键词,改成由前端构造条件"title:" + keyWords + " AND status:1"

		//logger.info(keyWords);
        //solrQuery.setQuery("title:" + keyWords + " AND status:1"); // 搜索关键词
        // 设置分页 start=0就是从0开始，，rows=5当前返回5条记录，第二页就是变化start这个值为5就可以了。
        solrQuery.setStart((Math.max(page, 1) - 1) * ROWS);
        solrQuery.setRows(ROWS);

        // 是否需要高亮
        boolean isHighlighting = !StringUtils.equals("*", keyWords) && StringUtils.isNotEmpty(keyWords);

        if (isHighlighting) {
            // 设置高亮
            solrQuery.setHighlight(true); // 开启高亮组件
            solrQuery.addHighlightField("title");// 高亮字段
            solrQuery.setHighlightSimplePre("<em>");// 标记，高亮关键字前缀
            solrQuery.setHighlightSimplePost("</em>");// 后缀
        }
        
        // 执行查询
        QueryResponse queryResponse;
		try {
			queryResponse = this.httpSolrClientReview.query(solrQuery);
			List<ReviewItem> items = queryResponse.getBeans(ReviewItem.class);
	        if (isHighlighting) {
	            // 将高亮的标题数据写回到数据对象中
	            Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
	            for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
	                for (ReviewItem item : items) {
	                    if (!highlighting.getKey().equals(item.getId().toString())) {
	                        continue;
	                    }
	                    //logger.info(item.getTitle());
	                    item.setTitle(StringUtils.join(highlighting.getValue().get("title"), ""));
	                    break;
	                }
	            }
	        }
	        
	        dt.setRecordsTotal(queryResponse.getResults().getNumFound());
	        dt.setRecordsFiltered(queryResponse.getResults().getNumFound());
	        dt.setData(items);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return dt;
	}

	public ReviewItem add(ReviewItem reviewItem){
		// TODO Auto-generated method stub
		try{
			logger.info(reviewItem);
           this.httpSolrClientReview.addBean(reviewItem);
           this.httpSolrClientReview.commit();
		}catch(Exception e){
			return null;
		}
      
      return reviewItem;
	}

	public ReviewItem update(ReviewItem reviewItem) {
		// TODO Auto-generated method stub
		try{
	           this.httpSolrClientReview.addBean(reviewItem);
	           this.httpSolrClientReview.commit();
			}catch(Exception e){
				return null;
			}
		return reviewItem;
	}

	/**
	 * ReviewItem传入ID
	 */
	public Integer delete(ReviewItem reviewItem) {
		// TODO Auto-generated method stub
		try{
			this.httpSolrClientReview.deleteById(reviewItem.getId());
	        this.httpSolrClientReview.commit();
	        return 1;
		}catch(Exception e){
			return 0;
		}
	}
}
