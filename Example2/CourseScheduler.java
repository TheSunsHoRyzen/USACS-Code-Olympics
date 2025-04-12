import java.util.*;

public class CourseScheduler {
    public static List<String> findCourseOrder(List<String> courses, List<List<String>> prerequisites) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        List<String> result = new ArrayList<>();

        for (String course : courses) {
            graph.put(course, new ArrayList<>());
            visited.put(course, false);
        }

        for (List<String> pair : prerequisites) {
            String course = pair.get(0);
            String prereq = pair.get(1);
            graph.get(course).add(prereq);
        }

        for (String course : courses) {
            if (!visited.get(course)) {
                dfs(course, graph, visited, result);
            }
        }

        return result;
    }

    private static void dfs(String course, Map<String, List<String>> graph,
                            Map<String, Boolean> visited, List<String> result) {
        visited.put(course, true);
        for (String neighbor : graph.get(course)) {
            if (!visited.get(neighbor)) {
                dfs(neighbor, graph, visited, result);
            }
        }
        result.add(course);
    }

    public static void main(String[] args) {

    //test case starts here 
        List<String> courses = Arrays.asList("CS101", "CS102", "CS103", "CS104");

        List<List<String>> prerequisites = Arrays.asList(
            Arrays.asList("CS101", "CS102"),
            Arrays.asList("CS102", "CS103"),
            Arrays.asList("CS103", "CS104")
        );

    //test case ends here

        List<String> order = findCourseOrder(courses, prerequisites);
        System.out.println(order);
    }
}
