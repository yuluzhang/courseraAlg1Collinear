import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private List<LineSegment> ls = new ArrayList<>();
    public BruteCollinearPoints(Point[] points) {
        // finds all line segments containing 4 points
        if(points == null) throw new IllegalArgumentException();
        for(Point p : points){
            if(p == null) throw new IllegalArgumentException();
        }
        for(int i = 0; i < points.length; i++){
            for(int j = i+1; j < points.length; j++){
                if(points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }
        Point[] pts = new Point[points.length];
        int idx = 0;
        for(Point p: points){
            pts[idx++] = p;
        }

        Arrays.sort(pts);
        for(int i = 0; i < pts.length; i++){
            for(int j = 0; j < pts.length; j++){
                if(i == j) continue;
                double sl1 = pts[i].slopeTo(pts[j]);
                for(int k = 0; k < pts.length; k++){
                    if(k == j || k == i) continue;
                    double sl2 = pts[i].slopeTo(pts[k]);
                    if(sl1 != sl2) continue;
                    for(int l = 0; l < pts.length; l++){
                        if(l == k || l == j || l == i) continue;
                        double sl3 = pts[i].slopeTo(pts[l]);
                        if(sl3 == sl2){
//                            Point[] temp = new Point[]{pts[i], pts[j],pts[k], pts[l]};
//                            Arrays.sort(temp);
                            if(i < j && j < k && k < l) ls.add(new LineSegment(pts[i], pts[l]));
                        }
                    }
                }
            }
        }


//        for(int i = 0; i < pts.length-3; i++){
//            boolean added = false;
//            for(int j = i+1; j < pts.length-2; j++) {
//                double s1 = pts[i].slopeTo(pts[j]);
//                for (int k = j + 1; k < pts.length - 1; k++) {
//                    double s2 = pts[i].slopeTo(pts[k]);
//                    if (s1 != s2) continue;
//                    for (int l = k + 1; l < pts.length; l++) {
//                        double s3 = pts[i].slopeTo(pts[l]);
//                        if (s1 == s3) {
//                            ls.add(new LineSegment(pts[i], pts[l]));
//                            added = true;
//                            break;
//                        }
//                    }
//                    if (added) break;
//                }
//                if(added) break;
//            }
//        }
    }
    public int numberOfSegments() {
        // the number of line segments
        return ls.size();
    }
    public LineSegment[] segments()     {
        // the line segments
        LineSegment[] res = new LineSegment[ls.size()];
        int i = 0;
        for(LineSegment entry: ls){
            res[i++] = entry;
        }
        return res;
    }
}
