public class QuickFindUF {
	private int[] id;
	//set id of each object to itself (N array accesses)
	public QuickFindUF(int N)
	{
		id = new int[N];
		for (int i = 0; i<N; i++)
			id[i] = i;
	}
	
	public boolean connected(int p, int q)
	{ 
		return id[p] == id[q];
	}
	
	//change all entries with id[p] to id[q] (at most 2N+2 array accesses)
	public void union(int p, int q)
	{
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i<id.length; i++)
			if (id[i] == pid) id[i] = qid;
	}
	
	public static void main(String[] args) {
		QuickFindUF q = new QuickFindUF(10);
		        q.union(3, 8);
		        q.union(5, 2);
		        q.union(2, 3);
		        q.union(9, 1);
		        q.union(7, 4);
		        q.union(3, 9);
		for (int i = 0; i < 10; i++) 
		System.out.print(q.id[i] + " ");
		    }
}

