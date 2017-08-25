package com.kpttech.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kpttech.common.pojo.DataTableResult;
import com.kpttech.common.pojo.Json;
import com.kpttech.pagepojo.ReviewItem;
import com.kpttech.service.SolrReviewService;

@Controller
public class SolrController {
	
	@Autowired
	private SolrReviewService solrReviewService;
	
	private static final Logger logger = Logger.getLogger(SolrController.class);
	
	/* solr测试 */
	@RequestMapping("/doNotNeedSession/solrtest.action")
	@ResponseBody
	public Json solrTest(HttpServletRequest request) {
		Json j = new Json();
		try {
			
			ReviewItem item=new ReviewItem();
			item.setTitle("测试评语");
			item.setId("1001");
			solrReviewService.add(item);
			logger.info("添加测试通过");
			solrReviewService.delete(item);
			logger.info("删除测试通过");
			String keyWords="全面";
			DataTableResult dt=solrReviewService.search("title:" + keyWords + " AND grade:优秀", 1);
			List<ReviewItem> items=(List<ReviewItem>) dt.getData();
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			for(ReviewItem topicItem:items){
				logger.info(topicItem.getTitle());
			}
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

			j.setSuccess(true);
			j.setObj("成功");
			j.setMsg("成功");

			// }
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
