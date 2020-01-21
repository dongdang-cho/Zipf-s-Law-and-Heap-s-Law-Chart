package work1;

import java.util.ArrayList;
import java.util.Arrays;

public class MyTokenizer {
	private String expression;
	private ArrayList<String> outputList;
	
	/*
	 * expression = 토큰분리 표현식
	 * outputList = 토큰분리가 완료된 ArrayList<토큰> 자료구조
	 * tokenizing = 표현식에 의해 토큰을 분리하여 String(단어) -> String[](토큰) -> outputList<토큰>  메소드
	 */
	
	public MyTokenizer(String expression) {
		this.expression = expression;
		outputList = new ArrayList<String>();
	}
	
	public ArrayList<String>tokenizing(String data) {
		outputList.addAll(Arrays.asList(data.split(expression)));
		return outputList;
	}

	public ArrayList<String> getOutputList() {
		return outputList;
	}

	
}
