package com.zgc.mtl.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
/**
 * 主要用来构建RestHighLevelClient客户端
 * @date 2019-07-12 16:47:15
 * @author yang
 */
@Configuration
public class EsTemplateConfig {
	@Value("${spring.data.sst.hostlist}")
	private String hostlist;

	static {
		/**
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");
	}
	@Bean
	public ElasticsearchTemplate elasticsearchTemplate(Client client) {
		ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client);
//		elasticsearchTemplate.putMapping(Product.class);
		return elasticsearchTemplate;
	}

	@Bean
	public RestHighLevelClient restHighLevelClient() {
		// 解析hostlist配置信息
		String[] split = hostlist.split(",");
		// 创建HttpHost数组，其中存放es主机和端口的配置信息
		HttpHost[] httpHostArray = new HttpHost[split.length];
		for (int i = 0; i < split.length; i++) {
			String item = split[i];
			httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
		}
		// 创建RestHighLevelClient客户端
		RestClientBuilder builder = RestClient.builder(httpHostArray);
		return new RestHighLevelClient(builder);
	}

}