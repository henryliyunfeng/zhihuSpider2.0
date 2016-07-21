package comSpider.parser;
/**
 * 设计一个ZhihuQuestion封装类，来存储所有抓取到的对象
 * 5个字段：问题标题、问题的描述、问题对应的链接、评论者的名字、所有的评论信息
 *getRealUrl(String url)方法
 */
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import comSpider.model.FetchedPage;

import comSpider.fetcher.PageFetcher;

import java.util.regex.Matcher;  

public class ZhihuQuestions {  
    public String question;// 问题的标题 
    public String questionDescription;//对问题的描述
    public String zhihuUrl;// 问题对应的链接  
    public ArrayList<String> answersName;//所有评论者的名字
    public ArrayList<String> answers;// 存储所有回答的数组  
  
    // 带参数(String url)的构造器
    public ZhihuQuestions(String url) {  
        question = "";  
        questionDescription="";
        zhihuUrl = "";  
        answersName=new ArrayList<String>();
        answers = new ArrayList<String>();  
        
        if(getRealUrl(url)){
        	System.out.println("该问题页面地址="+zhihuUrl);
        	//根据该问题所在的页面地址，爬虫子页面所有内容
        	FetchedPage allContent=PageFetcher.getContentFromUrl(zhihuUrl);
        	Document doc=Jsoup.parse(allContent.getContent());
        	//抓取问题
        	//带zm-editable-content属性的span标签，文本内容
        	question=doc.select("span.zm-editable-content").text();
        	//System.out.println(question);
        	//抓去问题描述
        	questionDescription=doc.select("div[id=zh-question-detail]").text();
        	//System.out.println(questionDescription);
        	//抓取评论者的姓名	
        	//带有data-author-name属性的div元素集合
        	Elements allName=doc.select("div[data-author-name]");
        	for(Element name:allName){
        		//每个元素的data-author-name属性，就是评论者的名字
        		String nameText=name.attr("data-author-name");
        		answersName.add(nameText);
        	}
        	//抓取所有回答
        	Elements allAns=doc.select("div[class=zm-editable-content clearfix]");
        	//取得div标签下class=zm-editable-content clearfix的所有内容
        	//DOM遍历,只要文本信息
        	for(Element ans:allAns){
        		String ansText=ans.text();
        		answers.add(ansText);
        	}
        }
    } 
    /**
     * 把链接：https://www.zhihu.com/question/46727998/answer/108099200
     * 转变成：https://www.zhihu.com/question/46727998
     * 后者才是所有回答的网页真实地址
     * @param url 假地址
     * @return true 由假地址转换成真地址成功
     */
    public boolean getRealUrl(String url){
    	Pattern linkPattern=Pattern.compile("question/(.+?)/");
    	Matcher linkMatcher=linkPattern.matcher(url);
    	if(linkMatcher.find()){
    		zhihuUrl="https://www.zhihu.com/question/"+linkMatcher.group(1);
    		return true;
    	}
    	return false;    		
    }
  
    @Override  
    public String toString() {
    	StringBuffer contents=new StringBuffer();
    	contents.append("问题的链接："+zhihuUrl+"\r\n");
    	contents.append("问题的标题："+question+"\r\n");
    	contents.append("问题的描述："+questionDescription+"\r\n");
    	contents.append("评论者："+answersName+"\r\n");
    	contents.append("回答：");
    	for(int i=0;i<answers.size();i++){
    		int num=i+1;
    		contents.append("[No."+num+"="+answers.get(i)+"]\r\n");
    	}
    	return contents.toString();
    } 
    
}  
