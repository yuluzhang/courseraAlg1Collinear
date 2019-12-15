import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> ls = new ArrayList<>();
    public FastCollinearPoints(Point[] points)  {
        // finds all line segments containing 4 or more points
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
            List<Point> list = new ArrayList<>();
            for(int k = 0; k < pts.length; k++){
                if(k == i) continue;
                list.add(pts[k]);
            }
            Collections.sort(list, pts[i].slopeOrder());
            int j = 0;
            while(j < list.size()-2){
                double s1 = pts[i].slopeTo(list.get(j));
                double s2 = pts[i].slopeTo(list.get(j+1));
                double s3 = pts[i].slopeTo(list.get(j+2));

                Point[] temp = new Point[]{pts[i], list.get(j), list.get(j+1), list.get(j+2)};
                Arrays.sort(temp);
                if(s1 == s2 && s2 == s3 && temp[0].compareTo(pts[i]) == 0){
//                            pts[i].compareTo(list.get(j)) < 0 &&
//                            list.get(j).compareTo(list.get(j+1))<0 &&
//                            list.get(j+1).compareTo(list.get(j+2))<0 ){
                    ls.add(new LineSegment(temp[0], temp[3]));
                    break;
                } else j++;
            }
        }


//        for(int i = 0; i < pts.length; i++){
//            List<Point> list = new ArrayList<>();
//            for(int k = i+1; k < pts.length; k++){
//                list.add(pts[k]);
//            }
//            Collections.sort(list, pts[i].slopeOrder());
//            int j = 0;
//            while(j < list.size()){
//                int k = j+1;
//                while(k < list.size() && pts[i].slopeTo(list.get(j)) == pts[i].slopeTo(list.get(k))){
//                    k++;
//                }
//                if(k - j >=3){
//                    ls.add(new LineSegment(pts[i], list.get(k-1)));
//                    j = k;
//                    break;
//                }
//                j++;
//            }
//        }
    }
    public           int numberOfSegments()  {
        // the number of line segments
        return ls.size();
    }

    public LineSegment[] segments()  {
        // the line segments
        LineSegment[] res = new LineSegment[ls.size()];
        int i = 0;
        for(LineSegment entry: ls){
            res[i++] = entry;
        }
        return res;
    }
}
