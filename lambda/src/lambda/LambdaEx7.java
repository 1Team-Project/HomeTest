package lambda;

import java.util.function.IntBinaryOperator;

public class LambdaEx7 {
	
	public static int[] scores= {92, 05, 87};
	
	public static int maxOrMin(IntBinaryOperator operator) {
		int result=scores[0];
		for(int score:scores) {
			result = operator.applyAsInt(result, score);
		}
		return result;
	}

	public static void main(String[] args) {
		//Operator : �Ű����� ���ϰ� ��� ����, �ַ� �Ű����� �����ϰ� ����� ����
		int max=maxOrMin(
				(a,b)->{
					if(a>=b) return a;
					else return b;
				}
			);
		System.out.println("�ִ밪 : "+max);
		int min=maxOrMin(
				(a,b)->{
					if(a<=b) return a;
					else return b;
				}
			);
		System.out.println("�ּҰ� : "+min);
	}

}
