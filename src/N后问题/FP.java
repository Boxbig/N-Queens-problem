package N������;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FP {
	public ArrayList<Integer> result; // �洢�����result[i]=k ��ʾ�� i+1 �ŵ� k+1 ��λ�ðڸ��ʺ�
	int currRow; // ��ǰ��¼�Ľ�����˵ڼ��� : 1~n

	FP ResultAfterPutHere(int pos, FP rtn) {
		int row, i;
		FP r;
		for (row = 0; row < currRow; row++) {
			if (currRow - row == pos - result.get(row) // ͬ��������б��
					|| currRow - row == -pos +result.get(row) // ͬ��������б��
			) // Ҳ���Ǵ��ڳ�ͻ�Ļʺ�
			{
				return null; // ��ô�ͷ��ؿ�
			}
		}
		// �����ڳ�ͻ�Ļʺ��ڴ˴�����һ���ʺ󣬷����µĽ��
		r = new FP();
		r.result = new ArrayList<>();
		r.currRow = currRow + 1;
		for (i = 0; i <currRow; i++) {// ����ǰ��Ļʺ�ڷ����
			r.result.add(rtn.result.get(i));
		}
		r.result.add(currRow, pos);// �����µĻʺ�
		return r;
	}

	public static  ArrayList<Integer> diaoyong(int n) {
		ArrayList<Integer> arraylist = new ArrayList<>();
		Queue<FP> list = new LinkedList<>();
		FP rtn, rtnNew;
		for (int i = 0; i < n; i++) {// �ѵ�һ�ŵ�n�ְڷ����Ž�����
			rtn = new FP();
			rtn.result = new ArrayList<>();
			rtn.result.add(i);
			rtn.currRow = 1;
			list.add(rtn);
		}
		
		// ����ÿһ�������ֱ���ҳ����
		while (!list.isEmpty()) {
			int j=0;
			rtn = list.peek(); // ȡ��������ǰ��ļһ�
			list.poll();
			for (int i = 0; i <n; i++) {
				if(rtn.result.contains(i)&&j<=rtn.currRow){
					j++;
					continue;	
				}
				rtnNew = rtn.ResultAfterPutHere(i, rtn);
				if (rtnNew != null) {// ����ͻ
					if (rtnNew.currRow == n) // ���Ƿ��Ѿ�������N�ʺ�
					{// ������
						for (i = 0; i < rtnNew.currRow; i++)
							arraylist.add(rtnNew.result.get(i) + 1);
						if(arraylist.size()==n*2){
							
								return arraylist;
							
						}
					}
					// û�а���ͽ����м�����
					list.add(rtnNew);
				}
			}
			//rtn = null;
		}
		return arraylist;
	}

}



