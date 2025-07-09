import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonplaceholderApiTest {
	public static void main(String[] args) throws IOException {
		String url = "https://jsonplaceholder.typicode.com";
		String json = new String(Files.readAllBytes(Paths.get("src/main/resources/json.txt")));

		JsonplaceholderApi.postUser(url, json);
//		JsonplaceholderApi.putUser(url, json);
//		JsonplaceholderApi.deleteUser(url);
//		JsonplaceholderApi.getAllUsers(url);
//		JsonplaceholderApi.getUserById(url);
//		JsonplaceholderApi.getUserByName(url);
//		JsonplaceholderApi.getUserByName(url, "Mrs. Dennis Schulist");
//		JsonplaceholderApi.getCommentsToLastPost(url);
//		JsonplaceholderApi.getTodosNotCompleted(url);

	}
}
