package N������;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FZ {
	public int[] q; // ��ʾ�� i+1 �ŵ� q[i] ��λ�ðڸ��ʺ�
	public int currentRow; // ��ǰ��¼�Ľ�����˵ڼ��� : 1~n

	FZ ResultAfterPutHere(int col, FZ temp) {
		int row, i;
		FZ fz;
		for (row = 0; row < currentRow; row++) {
			if (col == q[row] || Math.abs(currentRow - row) == Math.abs(col - q[row])) {// ���ڳ�ͻ�Ļʺ�
				return null; // ��ô�ͷ��ؿ�
			}
		}
		// �����ڳ�ͻ�Ļʺ��ڴ˴�����һ���ʺ󣬷����µĽ��
		fz = new FZ();
		fz.q = new int[temp.q.length];
		fz.currentRow = currentRow + 1;
		for (i = 0; i < currentRow; i++) {// ����ǰ��Ļʺ�ڷ����
			fz.q[i] = temp.q[i];
		}
		fz.q[currentRow] = col;// �����µĻʺ�
		return fz;
	}

	ArrayList<Integer> place(int n){
		ArrayList<Integer> als = new ArrayList<>();
		Queue<FZ> queue = new LinkedList<>();
		FZ fz, fzNew;
		for (int i = 0; i < n; i++) {// �ѵ�һ�ŵ�n�ְڷ����Ž�����
			fz = new FZ();
			fz.q = new int[n];
			fz.q[0] = i;
			fz.currentRow = 1;
			queue.add(fz);
		}
		
		while (!queue.isEmpty()) {// ����ÿһ�������ֱ���ҳ����
			fz = queue.peek(); // ȡ��������ǰ���
			queue.poll();
			for (int i = 0; i < n; i++) {
				fzNew = fz.ResultAfterPutHere(i, fz);
				if (fzNew != null) {// ����ͻ
					if (fzNew.currentRow == n){ // ���Ƿ��Ѿ�������N�ʺ�,����ڷ����
						for (i = 0; i < fzNew.currentRow; i++){
							als.add(fzNew.q[i] + 1);
						}
						if(als.size()==2*n){
							return als;
						}
					}else{
						queue.add(fzNew);// û�а���ͽ����м�����
					}
				}
			}
			//fz = null;
		}
		return als;
	}
}