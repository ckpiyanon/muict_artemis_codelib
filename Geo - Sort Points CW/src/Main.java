import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String args[]) {
		Point[] list = {new Point(0,2),new Point(6,67),new Point(43,71),new Point(39,107),new Point(189,140)};
//		Point[] list = {new Point(-2,-2),new Point(-2,2),new Point(2,-2),new Point(2,2)};
		double avgX = 0,avgY = 0;
		for(Point p:list) {avgX += p.x; avgY += p.y;}
		avgX /= list.length; avgY /= list.length;
		Point center = new Point(avgX,avgY);
		Arrays.sort(list,new Comparator<Point>() {
			private int quadrant(Point p) {
				double dx = p.x - center.x;
				double dy = p.y - center.y;
				if(dx > 0 && dy > 0)	return 1;
				if(dx < 0 && dy > 0)	return 2;
				if(dx < 0 && dy < 0)	return 3;
				if(dx > 0 && dy < 0)	return 4;
				return 0;
			}
			public int compare(Point a,Point b) {
				int q1 = quadrant(a),q2 = quadrant(b);
				if(q1 != q2)	return q1 - q2;
				double degA = Math.atan((a.y - center.y) / (a.x - center.x));
				double degB = Math.atan((b.y - center.y) / (b.x - center.x));
				return Double.compare(degA,degB);
			}
		});
		System.out.println(Arrays.toString(list));
	}
	static class Point {
		public double x,y;
		public Point(double x,double y) {this.x = x; this.y = y;}
		public static double distance(Point a,Point b) {return Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y,2));}
		public String toString() {return "(" + x + "," + y + ")";}
	}
}
