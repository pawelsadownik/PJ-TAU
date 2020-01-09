
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GeometricFigure {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();

        try {

            for (String i : args) {
                list.add(Integer.parseInt(i));
            }
            System.out.println(checkFigure(list));

        }
        catch (Exception e) {
            System.out.println("nierozpoznano");

        }
    }

    public static String checkFigure(List<Integer> segments) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i : segments) {
            list.add(i);
        }

        Collections.sort(list);
        if (list.size() < 3 || list.size() > 4) {
            return "nierozpoznano";
        }
        if (list.size() == 3) {
            if (segments.get(2) >= segments.get(0) + segments.get(1)) {
                return "nierozpoznano";
            } else {
                return triangleCheck(list);
            }
        }

        if (list.size() == 4) {
            if (list.get(3) < list.stream().mapToInt(Integer::intValue).sum() / 2) {
                return quattroCheck(list);
            }

        }
        return "nierozpoznano";
    }

    private static String quattroCheck(List<Integer> list) {
        if (list.get(0) == list.get(1) && list.get(0) == list.get(2) && list.get(0) == list.get(3)) {
            return "Kwadrat";
        }
        if ((list.get(0) == list.get(1) && list.get(2) == list.get(3)) ||
                (list.get(0) == list.get(3) && list.get(2) == list.get(1)) ||
                (list.get(0) == list.get(2) && list.get(3) == list.get(2)))

        {
            return "prostokąt";
        }

        return "czworobok";

    }

    public static String triangleCheck(List<Integer> segments) {
        if (segments.get(0) == segments.get(1) && segments.get(1) == segments.get(2)) {
            return "Trójkąt równoboczny";
        }

        if (segments.get(1) == segments.get(2) || segments.get(0) == segments.get(1) || segments.get(2) == segments.get(0)) {
            return "Trojkąt równoramienny";
        }
        return "trojkąt różnoboczny";

    }
}
