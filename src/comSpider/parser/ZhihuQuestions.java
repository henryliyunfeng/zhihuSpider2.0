package comSpider.parser;
/**
 * ���һ��ZhihuQuestion��װ�࣬���洢����ץȡ���Ķ���
 * 5���ֶΣ�������⡢����������������Ӧ�����ӡ������ߵ����֡����е�������Ϣ
 *getRealUrl(String url)����
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
    public String question;// ����ı��� 
    public String questionDescription;//�����������
    public String zhihuUrl;// �����Ӧ������  
    public ArrayList<String> answersName;//���������ߵ�����
    public ArrayList<String> answers;// �洢���лش������  
  
    // ������(String url)�Ĺ�����
    public ZhihuQuestions(String url) {  
        question = "";  
        questionDescription="";
        zhihuUrl = "";  
        answersName=new ArrayList<String>();
        answers = new ArrayList<String>();  
        
        if(getRealUrl(url)){
        	System.out.println("������ҳ���ַ="+zhihuUrl);
        	//���ݸ��������ڵ�ҳ���ַ��������ҳ����������
        	FetchedPage allContent=PageFetcher.getContentFromUrl(zhihuUrl);
        	Document doc=Jsoup.parse(allContent.getContent());
        	//ץȡ����
        	//��zm-editable-content���Ե�span��ǩ���ı�����
        	question=doc.select("span.zm-editable-content").text();
        	//System.out.println(question);
        	//ץȥ��������
        	questionDescription=doc.select("div[id=zh-question-detail]").text();
        	//System.out.println(questionDescription);
        	//ץȡ�����ߵ�����	
        	//����data-author-name���Ե�divԪ�ؼ���
        	Elements allName=doc.select("div[data-author-name]");
        	for(Element name:allName){
        		//ÿ��Ԫ�ص�data-author-name���ԣ����������ߵ�����
        		String nameText=name.attr("data-author-name");
        		answersName.add(nameText);
        	}
        	//ץȡ���лش�
        	Elements allAns=doc.select("div[class=zm-editable-content clearfix]");
        	//ȡ��div��ǩ��class=zm-editable-content clearfix����������
        	//DOM����,ֻҪ�ı���Ϣ
        	for(Element ans:allAns){
        		String ansText=ans.text();
        		answers.add(ansText);
        	}
        }
    } 
    /**
     * �����ӣ�https://www.zhihu.com/question/46727998/answer/108099200
     * ת��ɣ�https://www.zhihu.com/question/46727998
     * ���߲������лش����ҳ��ʵ��ַ
     * @param url �ٵ�ַ
     * @return true �ɼٵ�ַת�������ַ�ɹ�
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
    	contents.append("��������ӣ�"+zhihuUrl+"\r\n");
    	contents.append("����ı��⣺"+question+"\r\n");
    	contents.append("�����������"+questionDescription+"\r\n");
    	contents.append("�����ߣ�"+answersName+"\r\n");
    	contents.append("�ش�");
    	for(int i=0;i<answers.size();i++){
    		int num=i+1;
    		contents.append("[No."+num+"="+answers.get(i)+"]\r\n");
    	}
    	return contents.toString();
    } 
    
}  
