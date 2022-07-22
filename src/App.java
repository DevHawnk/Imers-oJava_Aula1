import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

    public class App {

        private static final String TOP_MOVIES_URL = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";

        public static void main(String[] args) throws Exception {

            String topMoviesBody = doRequest(TOP_MOVIES_URL);

            List<Map<String, String>> topMoviesList = parseJsonToString(topMoviesBody);

            printImDbList("Top Movies List", topMoviesList);
                    }


        private static String doRequest(String url) throws IOException, InterruptedException {
            var address = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(address).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();
        }


        private static List<Map<String, String>> parseJsonToString(String body) {
            var parser = new JsonParser();
            List<Map<String, String>> jsonAttributesList = parser.parse(body);
            return jsonAttributesList;
        }


        private static void printImDbList(String title, List<Map<String, String>> imDbShowsList) {
                    System.out.println("\n\u001b[38;2;0;0;0;1m\u001b[48;2;42;122;228m ### " + title + " ###\u001b[m");
            for (Map<String,String> show : imDbShowsList) {
                double rating = Double.parseDouble(show.get("imDbRating"));


                System.out.println("Title:\u001b[1m" + show.get("title") + "\u001b[m"); //
                System.out.println("Poster Link:" + show.get("image"));
                System.out.println("\u001b[48;2;255;233;6mRating:" + rating + "\u001b[m");
                for (int i = 0; i <= (int)rating; i++) {
                    System.out.print("⭐️");
                }
                System.out.println("\n");
            }
        }
    }
