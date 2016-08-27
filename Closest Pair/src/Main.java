import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static final double INF = 20000.0;
	static Point[] list = {new Point(0,2),new Point(6,67),new Point(43,71),new Point(39,107),new Point(189,140)};
	/* 0 2
	 * 6 67
	 * 43 71
	 * 39 107
	 * 189 140
	 */
	static double find(int start,int end) {
		if(start == end || end - start == 1)	return INF;
		int mid = (start + end) / 2;
		double min = Math.min(find(start,mid),find(mid,end));
		double line = (list[mid-1].x + list[mid].x) / 2.0;
		double ans = INF;
		for(int i = mid-1;i >= start && list[i].x > line-min;i--) {
			for(int j = mid;j < end && list[j].x < line+min;j++) {
				ans = Math.min(ans,Point.distance(list[i],list[j]));
			}
		}
		return Math.min(min,ans);
	}
	public static void main(String args[]) {
		Arrays.sort(list,new Comparator<Point>() {
			public int compare(Point a,Point b) {
				if(a.x == b.x)	return Double.compare(a.y,b.y);
				return Double.compare(a.x,b.x);
			}
		});
		System.out.printf("%.4f",find(0,list.length));
	}
	static class Point {
		public double x,y;
		public Point(double x,double y) {this.x = x; this.y = y;}
		public static double distance(Point a,Point b) {return Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y,2));}
	}
}
