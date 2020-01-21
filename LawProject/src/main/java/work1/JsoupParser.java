package work1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupParser {
	private static String storageDir = ".\\document";
	private String seedURL;
	private LinkedList<String> urlList;
	private Calendar cal;
	private DateFormat df;
	private Date date;
	/*
	 * storageDir = 저장 폴더 지정 변수(default = 상대경로이며 기준은  Project 최상위)
	 * seedURL = 시작 URL을 저장할 변수, 
	 * urlList = url을 통해 파싱하여, 얻은 앵커 링크를 저장함.
	 *
	 * cal, df, date = 날짜를 변환해주는 변수.(why? 참고 확인)
	 * 
	 * 1.parsing() = 시작URL로부터 링크를 얻어서 접속한 후, 기사명, 기사 본문을 파싱함.
	 * 1.1 saveFile() = 파싱한 기사명, 기사 본문을 텍스트 파일로 저장함.
	 * 
	 * 참고 : 현재 수집된 네이버뉴스는 url에서 sectionId,date에 규칙을 찾을 수 있음.
	 * 		- sectionId = 분야를 의미하며 텍스트 기사는 100~105가 있음.(정치, 경제, 사회, 생활/문화, 세계, IT/과학)
	 *  	- date = 한 분야에서 지정한 날짜의 인기있는 30개의 기사목록을 얻을 수 있음.
	 *  	- 이 값을 이용하면 다양한 분야에서 많은 기사 데이터를 얻어낼 수 있음.
	 *      - 따라서 현재 테스트는 정치와 IT/과학 분야에서 1800개를 수집한 상태.
	 * 
	 * 
	 *       
	 */
	public JsoupParser(int docNum) {
		this.seedURL = "https://news.naver.com/main/ranking/popularDay.nhn?rankingType=popular_day&sectionId=103&date=";
		this.urlList = new LinkedList<String>();
		cal = Calendar.getInstance();
        df = new SimpleDateFormat("yyyyMMdd");
        date = new Date();
        cal.setTime(date);
        
	}
	
	public void parsing() throws InterruptedException, IOException {
		for(int i=0; true; i++) {
			
			String url = seedURL+df.format(cal.getTime());
			cal.add(Calendar.DATE,-1);
			System.out.println("============================================================");
			System.out.println("url : "+url);
			System.out.println("============================================================");
			Document doc = null;
			
		    doc = Jsoup.connect(url).get();
		     
			 Elements element = doc.select("div.ranking div.ranking_thumb");       
			 for(Element e:element.select("a")) {
				 String temp = e.absUrl("href");
				 urlList.add(temp);
			 }
			
			 for(String str : urlList) {
				 Document newsDoc = Jsoup.connect(str).get();
				 Elements el = newsDoc.select("div.content");  
				 String title = el.select("#articleTitle").text();
				 String content = el.select("#articleBodyContents").text();
				 saveFile(content, title, "txt");
			 }
			 Thread.sleep(300);
		}
	       
	}
	
	public void saveFile(String content, String title, String ext) {
		String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		title = title.replaceAll(match,"");
    	String fname = storageDir + File.separator + title + "." + ext;
    	BufferedWriter writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname), "UTF8"));
	    	writer.write(content);
	    	writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
		
	public static void main(String[] args) {
		JsoupParser jp = new JsoupParser(1000);
		try {
			jp.parsing();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
