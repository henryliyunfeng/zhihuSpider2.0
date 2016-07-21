package comSpider.handler;

import comSpider.model.FetchedPage;
import comSpider.queue.UrlQueue;

public class ContentHandler {
	public boolean check(FetchedPage fetchedPage){
		// ���ץȡ��ҳ���������ȡ���ݣ��򽫵�ǰURL�������ȡ���У��Ա�������ȡ
		if(isAntiScratch(fetchedPage)){
			UrlQueue.addFirstElement(fetchedPage.getUrl());
			return false;
		}
		
		return true;
	}
	
	//ͨ��״̬���жϸ���ҳ��Ӧ�Ƿ���ȷ
	private boolean isStatusValid(int statusCode){
		if(statusCode >= 200 && statusCode < 400){
			return true;
		}
		return false;
	}
	//�Ƿ������������
	private boolean isAntiScratch(FetchedPage fetchedPage){
		// 403 forbidden
		if((!isStatusValid(fetchedPage.getStatusCode())) && fetchedPage.getStatusCode() == 403){
			return true;
		}
		
		// ҳ�����ݰ����ķ���ȡ����
		if(fetchedPage.getContent().contains("<div>��ֹ����</div>")){
			return true;
		}
		
		return false;
	}
}
