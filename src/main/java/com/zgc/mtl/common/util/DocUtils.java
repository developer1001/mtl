package com.zgc.mtl.common.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 解析html文件
 * 
 * @date 2020-07-20 10:25:10
 * @author yang
 */
public class DocUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DocUtils.class);

	public static String getHtmlSubContent(String tag, String url)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		long start = System.currentTimeMillis();
		Document doc = null;
//		WebClient webclient = new WebClient();
//		webclient.getOptions().setJavaScriptEnabled(false);
//		webclient.getOptions().setCssEnabled(false);
//		HtmlPage page = webclient.getPage(url);
//		System.out.println("得到htmlpage耗时：" + (System.currentTimeMillis() - start));
//		long start1 = System.currentTimeMillis();
		doc= Jsoup.parse(new URL(url),6000);
//		doc = Jsoup.parse(page.asXml());
		System.out.println("html -> doc 耗时：" + (System.currentTimeMillis() - start));
		Elements input = doc.select("input").select("[type=hidden]");
		if(input != null && input.size() > 0)
		for(Element e : input) {
			e.remove();
		}
		doc.select("input.edit_btn").nextAll().remove();
		doc.select("input.edit_btn").remove();
		Elements body = doc.getElementsByTag(tag);
		String bodyHtml = body.html();
		System.out.println(bodyHtml);
		System.out.println("耗时：" + (System.currentTimeMillis() - start));
		return bodyHtml;
	}
	
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		getHtmlSubContent("body", "http://yx-card-cdn-dev.oss-cn-shenzhen.aliyuncs.com/wx23f7ed39e52f8914/headlines/contentMKT/console/template/html/view/20200803/918b20200803104450368020.html");
	}
}
