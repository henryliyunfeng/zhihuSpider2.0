package comSpider.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import comSpider.model.FetchedPage;
import comSpider.queue.UrlQueue;
import comSpider.queue.VisitedUrlQueue;
public class ContentParser {
	/**
	 * 解析提问页面内容，并把目标数据封装成ZhihuQuestions
	 * @param questionPage
	 * @return
	 */
	public synchronized ZhihuQuestions parse(FetchedPage questionPage){
		ZhihuQuestions targetZhihu =null;
		//抓取每一个提问链接内的目标数据，封装成ZhihuQuestions类
		targetZhihu=new ZhihuQuestions(questionPage.getUrl());
		// 把已经爬过的URL放入已爬取队列
		VisitedUrlQueue.addElement(questionPage.getUrl());
		
		// 根据当前页面和URL获取下一步爬取的URLs
		// TODO
		
		return targetZhihu; 
	}
	/**
	 * 解析出知乎发现首页上的所有提问的链接地址，加入待爬取队列UrlQueue容器里
	 * @param fetchedPage
	 */
	public void parseLinks(FetchedPage fetchedPage){
		//ArrayList<String> urlArr=new ArrayList<String>();
		//创建一个干净的html文档
		Document doc = Jsoup.parse(fetchedPage.getContent());
		//class等于question_link的a标签的集合,也就是提问的相对地址
		Elements links = doc.select("a.question_link");
		for(Element link:links){
			String linkHref = link.attr("href");//获取链接的相对路径
			String nextLink="https://www.zhihu.com"+linkHref;//绝对路径
			UrlQueue.addElement(nextLink);
		}
	}
}
