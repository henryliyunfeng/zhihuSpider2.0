package comSpider.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import comSpider.parser.ZhihuQuestions;

public class DataStorage {
	private File dir;
	private File file;
	static volatile boolean isCreatedSucceed=false;
	FileWriter fw;
	public DataStorage(){
		createDirFile();
		try {
			fw=new FileWriter(file,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 存储所有提问到本地文件out/questions.txt
	 * @param data
	 * @return
	 */
	public synchronized void store(ZhihuQuestions data){
		// store to DB
		// TODO
		
		
		//store to File
		//创建待存储文本
		//createDirFile();
		//写数据到文件中
		try{
			//多个线程各自开启一个FileWriter写入同一个文件,会产生串行现象
			BufferedWriter writer=new BufferedWriter(fw);//在构造FileWriter时，第二个参数设置为true
			writer.write(data.toString());				
			writer.flush();
			//writer.close();
			//System.out.println("had stored");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 创建一个out/questions.txt文件
	 * @param 
	 * @return
	 */
	private void createDirFile(){	
		try{
			//只创建一次文件,没有则创建，有则不创建
			if(!isCreatedSucceed){
				//创建文件夹
				dir=new File("out");
				dir.mkdirs();
				//再创建文件
				file=new File("out/questions.txt");//创建抽象路径名
				file.createNewFile();
				isCreatedSucceed=true;//已经创建文件
				System.out.println("create \"out/question.txt\" file succeed." );
			}
//			else
//				System.out.println("create \"out/question.txt\" file failed." );

		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
