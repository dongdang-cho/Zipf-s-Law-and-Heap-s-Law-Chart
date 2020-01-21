package work1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class Statistician {
	private HashMap<String, Integer> tokenMap;
	private ArrayList<Entry<String, Integer>> orderList; 
	private int tokenNum;
	private ArrayList<Double> xList, yList;
	
	/*
	 * tokenMap = <토큰,빈도수>가 저장되는  HashMap 자료구조.
	 * orderList = <토큰,빈도수>객체를 내림차순하여 저장되는 자료구조.
	 * 1. frequencyCheck() = 외부로부터 ArrayList<토큰>을 받아 토큰의 빈도수를 체크하여 tokenMap에 저장하는 메소드.
	 * 2. rankTerms() = tokenMap의 빈도수를 기준으로 하여 순서가 있는 자료구조 orderList에 내림차순으로 정렬하여 저장.
	 * 3. showZipfChart() = Zipf's Law를 증명하는 그래프를 출력.(x=랭킹,y=발생확률)
	 * 4. showHeapsChart() = Heap's Law를 증명하는 그래프를 출력.(x=수집된 단어수, y=중복제거된 단어수)
	 * 메소드 실행순서는 1~4이다.
	 */
	public Statistician() {
		// TODO Auto-generated constructor stub
		this.tokenMap = new HashMap<String, Integer>();
		this.tokenNum = 0;
	}
	
	public HashMap<String, Integer> frequencyCheck (ArrayList<String> list) {
		xList = new ArrayList<Double>();
		yList = new ArrayList<Double>();
		for(String token: list) {
			token = token.toLowerCase();
			if(token.length()==0) continue;
		
			Integer freq = tokenMap.get(token);
			if(freq == null) {
				tokenMap.put(token, 1);
				if(xList.isEmpty()) yList.add(1.0);
				yList.add(yList.get(yList.size()-1)+1);
			}
			else {
				tokenMap.replace(token, freq+1);
				yList.add(yList.get(yList.size()-1));
			}
			tokenNum++;
			if(xList.isEmpty()) xList.add(1.0);
			xList.add(xList.get(xList.size()-1)+1);
		}
		return tokenMap;
	}
	
	public ArrayList<Entry<String, Integer>> rankTerms() {
		orderList = new ArrayList<Entry<String, Integer>>(this.tokenMap.entrySet());
		orderList.sort(Entry.comparingByValue(Comparator.reverseOrder()));
					
		return orderList;
	}
	
	public void showZipfChart() {
		ArrayList<Double> list = new ArrayList<Double>();
		for(int i=0; i<orderList.size(); i++) { 
			Entry<String, Integer> entry = orderList.get(i);
			double value = ((double)entry.getValue())/tokenNum;
			list.add(value);
		}
		LineChartEx ex = new LineChartEx("Zipf's Law","Rank","Probability",null,list);
        ex.setVisible(true);
	}
	public void showHeapsChart() {
		
		LineChartEx ex = new LineChartEx("Heap's Law","Words in Collection","Words in Vocabulary",xList,yList);
        ex.setVisible(true);
	}
	public int getTokenNum() {
		return tokenNum;
	}

	public HashMap<String, Integer> getTokenMap() {
		return tokenMap;
	}
	
}
