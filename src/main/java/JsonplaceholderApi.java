import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JsonplaceholderApi {

	public static void postUser(String url, String json) throws IOException {

//		Connection.Response executePost = Jsoup
//				.connect(url + "/users")
//				.header("Accept", "application/json")
//				.header("Content-Type", "application/json; charset=UTF-8")
//				.requestBody(json)
//				.method(Connection.Method.POST)
//				.ignoreContentType(true)
//				.ignoreHttpErrors(true)
//				.execute();
//		System.out.println("bodyPost = " + executePost.body()); //get answer "body" - new user with "idPost":0
//		System.out.println("executePost.statusCode() = " + executePost.statusCode());

		URL url1 = new URL(url + "/users");
		HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(Files.readAllBytes(new File("src/main/resources/json.json").toPath()));
		outputStream.flush();
		outputStream.close();

		System.out.println("POST response code: " + connection.getResponseCode());
		if (connection.getResponseCode() == HttpURLConnection.HTTP_CREATED) {
			BufferedReader in =
					new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
			StringBuffer response = new StringBuffer();
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response);
		} else {
			System.out.println("Error");
		}
	}

	public static void putUser(String url, String json) throws IOException {
		Connection.Response executePut = Jsoup
				.connect(url + "/users/1")
				.header("Accept", "application/json")
				.header("Content-Type", "application/json")
				.requestBody(json)
				.method(Connection.Method.PUT)
				.ignoreContentType(true)
				.execute();
		System.out.println("bodyPut = " + executePut.body());
		//get answer "body" - new user with "id":0
	}

	public static void deleteUser(String url) throws IOException {
		Connection.Response executeDelete = Jsoup
				.connect(url + "/users/1")
				.method(Connection.Method.DELETE)
				.ignoreContentType(true)
				.execute();
		System.out.println("statusCode delete = " + executeDelete.statusCode());
		// get code 200
	}

	public static void getAllUsers(String url) throws IOException {

		Connection.Response executeGetAllUsers = Jsoup
				.connect(url + "/users")
				.method(Connection.Method.GET)
				.ignoreContentType(true)
				.execute();
		File file = new File("src/main/resources/allUsers.txt");
		FileWriter allUsers = new FileWriter(file, false);
		allUsers.write(executeGetAllUsers.body());
		allUsers.close();
		//System.out.println("body = " + executeGetAllUsers.body());
	}

	public static void getUserById(String url) throws IOException {
		System.out.println("Enter user's id (from 1 to 10) - ");
		Scanner scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		//scanner.close();
		if (i > 0 && i <= 10){
			Connection.Response executeGetUserById = Jsoup.connect(url + "/users/" + i)
					.method(Connection.Method.GET)
					.ignoreContentType(true)
					.execute();
			System.out.println("executeGetUserId = " + executeGetUserById.body());
		}
		else System.out.println("error");
	}

	public static void getUserByName(String url) throws IOException {
		List<User> users = new ArrayList<>();

		for (int i = 1; i <= 10; i++) {
			String user = Jsoup.connect(url + "/users/" + i)
					.method(Connection.Method.GET)
					.ignoreContentType(true)
					.execute()
					.body();

			users.add(new Gson().fromJson(user, User.class));
		}

		System.out.println("Enter user's name  - ");
		Scanner scanner = new Scanner(System.in);
		String i = scanner.nextLine();
		//scanner.close();
		Optional<User> first = users.stream()
				.filter(obj -> obj.getName().equals(i))
				.findFirst();
		System.out.println("first = " + first);
	}

	public static void getUserByName(String url, String name) throws IOException {

		String jsonAllUsers = Jsoup
				.connect(url + "/users")
				.ignoreContentType(true)
				.execute()
				.body();

		Gson gson = new Gson();
		JsonArray usersArray = gson.fromJson(jsonAllUsers, JsonArray.class);

		for (int i = 0; i < usersArray.size(); i++) {
			if ((new Gson().fromJson(String.valueOf(usersArray.get(i)), User.class).getName()).equals(name)) {
				System.out.println(usersArray.get(i));
			}
		}
	}

	public static void getCommentsToLastPost(String url) throws IOException {

		//ask which user's comments we need
		System.out.println("Enter user's id (from 1 to 10) - ");
		Scanner scanner = new Scanner(System.in);
		int idUser = scanner.nextInt();
		//scanner.close();

		String posts = Jsoup
				.connect(url + "/users/" + idUser + "/posts")
				.ignoreContentType(true)
				.execute()
				.body();

		//get string with last post
		JsonArray postsArray = new Gson().fromJson(posts, JsonArray.class);
		JsonElement lastPost = postsArray.get(postsArray.size() - 1);
		String string = lastPost.toString();

		//get "id" last post
		String regex = "\"id\":" + "\\d+";
		Matcher matcher = Pattern.compile(regex).matcher(string);
		int idPost = 0;
		if (matcher.find()) {
			idPost = Integer.parseInt(matcher.group().replaceAll("\\D+", ""));
		}

		//get all comments to post's id
		String comments = Jsoup.connect(url + "/posts/" + idPost + "/comments")
				.ignoreContentType(true)
				.execute()
				.body();

		String fileName = "src/main/resources/user-" + idUser + "-post-" + idPost + "-comments.json";
		File file = new File(fileName);
		FileWriter allComments = new FileWriter(file, false);
		allComments.write(comments);
		allComments.close();
	}

	public static void getTodosNotCompleted(String url) throws IOException {

		//ask which user's comments we need
		System.out.println("Enter user's id (from 1 to 10) - ");
		Scanner scanner = new Scanner(System.in);
		int idUser = scanner.nextInt();
		scanner.close();

		String posts = Jsoup
				.connect(url + "/users/" + idUser + "/todos")
				.ignoreContentType(true)
				.execute()
				.body();

		JsonArray todosArray = new Gson().fromJson(posts, JsonArray.class);

		for (int i = 0; i < todosArray.size(); i++) {
			if (!(new Gson().fromJson(String.valueOf(todosArray.get(i)), Todos.class).getCompleted())) {
				System.out.println(todosArray.get(i));
			}
		}
	}
}
