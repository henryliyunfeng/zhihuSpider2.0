package comSpider.model;

public class FetchedPage {
	private String url;//�ӵ�ַ
	private String content;//ҳ������
	private int statusCode;//״̬��
	
	public FetchedPage(String url,String content,int statusCode){
		this.url=url;
		this.content=content;
		this.statusCode=statusCode;
	}
	//�ṩһϵ��set��get����
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
