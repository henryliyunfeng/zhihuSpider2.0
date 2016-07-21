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
	 * ��������ҳ�����ݣ�����Ŀ�����ݷ�װ��ZhihuQuestions
	 * @param questionPage
	 * @return
	 */
	public synchronized ZhihuQuestions parse(FetchedPage questionPage){
		ZhihuQuestions targetZhihu =null;
		//ץȡÿһ�����������ڵ�Ŀ�����ݣ���װ��ZhihuQuestions��
		targetZhihu=new ZhihuQuestions(questionPage.getUrl());
		// ���Ѿ�������URL��������ȡ����
		VisitedUrlQueue.addElement(questionPage.getUrl());
		
		// ���ݵ�ǰҳ���URL��ȡ��һ����ȡ��URLs
		// TODO
		
		return targetZhihu; 
	}
	/**
	 * ������֪��������ҳ�ϵ��������ʵ����ӵ�ַ���������ȡ����UrlQueue������
	 * @param fetchedPage
	 */
	public void parseLinks(FetchedPage fetchedPage){
		//ArrayList<String> urlArr=new ArrayList<String>();
		//����һ���ɾ���html�ĵ�
		Document doc = Jsoup.parse(fetchedPage.getContent());
		//class����question_link��a��ǩ�ļ���,Ҳ�������ʵ���Ե�ַ
		Elements links = doc.select("a.question_link");
		for(Element link:links){
			String linkHref = link.attr("href");//��ȡ���ӵ����·��
			String nextLink="https://www.zhihu.com"+linkHref;//����·��
			UrlQueue.addElement(nextLink);
		}
	}
}
