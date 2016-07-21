package comSpider.model;

public class FetchedPage {
	private String url;//子地址
	private String content;//页面内容
	private int statusCode;//状态码
	
	public FetchedPage(String url,String content,int statusCode){
		this.url=url;
		this.content=content;
		this.statusCode=statusCode;
	}
	//提供一系列set和get方法
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	@Override
	public String toString(){
		String result=url+"...statusCode="+statusCode+"\n"+content;
		return result;
	}
}
