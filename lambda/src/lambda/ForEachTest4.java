package lambda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class ForEachTest4 {
	public static void main(String[] args) {
		String strArr[]= {"���","�ٳ���","����","����","�޷�"};
		
		//�迭�� ���� �ٷ� �� �ֵ��� ���� => Arrays
		Stream<String> stream = Arrays.stream(strArr);
		stream.forEach(s->System.out.println(s));
		
		//���丮���� ��Ʈ�� ������
		Path path=Paths.get("c:\\upload\\2021\\06\\18");
//		Stream<Path> stream1=null;
//		try {
//			stream1 =Files.list(path);
//			stream1.forEach(f->System.out.println(f.getFileName()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}finally {
//			stream1.close();
//		}
//		
		
		try (Stream<Path> stream1=Files.list(path)){
			//���丮���� ��Ʈ�� ������
			stream1.forEach(f->System.out.println(f.getFileName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
