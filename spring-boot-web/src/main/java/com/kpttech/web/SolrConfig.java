package com.kpttech.web;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {
	
	@Value("${solr.url}")  
    private String url;  
	@Value("${solr.maxRetries}")  
    private int maxRetries;
	@Value("${solr.connectionTimeout}")  
    private int connectionTimeout;
	
	@Bean(name="httpSolrClientReview")
	public HttpSolrClient getSolrBean(){
		HttpSolrClient httpSolrClient=new HttpSolrClient(url);
		httpSolrClient.setMaxRetries(maxRetries);
		httpSolrClient.setSoTimeout(connectionTimeout);
		return httpSolrClient;
	}

}
