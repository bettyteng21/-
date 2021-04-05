import java.util.Map;
import java.util.HashMap;
import java.util.Set;
public class MaxPointsOnALine {
	public static int maxPoints(Point[] points) {
		if(points.length < 2) return points.length;
		int pointCount = points.length;
		int globalMaxCount = 1;
		Map<Double, Integer> hm = new HashMap<Double, Integer>();
		for(int i = 0; i < pointCount; i++){
			hm.clear();
			int samePointCount = 0;
			double slope;
			int maxCount = 0;
			for(int j = i + 1; j < pointCount; j++){
				if(points[i].x == points[j].x && points[i].y == points[j].y){
					samePointCount++;
					continue;
				} else if(points[i].x == points[j].x && points[i].y != points[j].y){
					slope = Double.MAX_VALUE;
				} else if(points[i].y == points[j].y){
					slope = 0;
				} else {
					slope = (double)(points[i].y - points[j].y) / (double)(points[i].x - points[j].x);
				}

				if(hm.containsKey(slope)){
					int tmp = hm.get(slope) + 1;
					hm.put(slope, tmp);

					if(tmp > maxCount) maxCount = tmp;

				} else {
					hm.put(slope, 1);
					if(maxCount == 0) maxCount = 1;
				}

			} // end inside for loop

			if(globalMaxCount < maxCount + samePointCount + 1) globalMaxCount = maxCount + samePointCount + 1;

		} // end outside for loop

		return globalMaxCount;
	}

	public static void main(String[] args){
		Point[] p = new Point[]{new Point(2,3),new Point(3,3),new Point(-5,3) };
		System.out.println(maxPoints(p));
	}
}

class Point {
	int x;
	int y;
	Point() { x = 0; y = 0; }
	Point(int a, int b) { x = a; y = b; }
}