# KTOR Client Demo

Welcome to the **KTOR Client Demo** repository! This project demonstrates how to use the KTOR client in Kotlin to perform HTTP requests such as `GET`, `POST`, `PUT`, `PATCH`, and `DELETE`. The video tutorial also covers how to handle headers and logging.

## YouTube Video 📹

Watch the complete tutorial on my YouTube channel:

[![Ktor Client Tutorial](https://img.youtube.com/vi/YOUR_VIDEO_ID/maxresdefault.jpg)](https://www.youtube.com/watch?v=YOUR_VIDEO_ID)

## API Used

We are using the API endpoints provided by [JSONPlaceholder](https://jsonplaceholder.typicode.com/). These endpoints are ideal for testing and learning HTTP requests.

### API Endpoints

- **GET**: `https://jsonplaceholder.typicode.com/posts`
- **POST**: `https://jsonplaceholder.typicode.com/posts`
- **PUT**: `https://jsonplaceholder.typicode.com/posts/{id}`
- **PATCH**: `https://jsonplaceholder.typicode.com/posts/{id}`
- **DELETE**: `https://jsonplaceholder.typicode.com/posts/{id}`

## Features Covered

1. **GET Request**: Fetching data from the server.
2. **POST Request**: Sending data to the server.
3. **PUT Request**: Updating existing data entirely.
4. **PATCH Request**: Partially updating data.
5. **DELETE Request**: Deleting data from the server.
6. **Headers**: Adding and managing custom headers.
7. **Logging**: Setting up logging for better debugging.

## Prerequisites

- **Kotlin**: Ensure you have Kotlin set up on your system.
- **Gradle**: The project uses Gradle for dependency management.

## Getting Started

Clone this repository to your local machine:

```bash
git clone https://github.com/himanshuGaur684/KTOR-Client.git
```

Navigate to the project directory:

```bash
cd KTOR-Client
```

## Setup

Add the following dependencies to your `build.gradle.kts`:

```kotlin
dependencies {
    implementation("io.ktor:ktor-client-core:2.x.x")
    implementation("io.ktor:ktor-client-cio:2.x.x")
    implementation("io.ktor:ktor-client-logging:2.x.x")
}
```

## How to Run

1. Open the project in your favorite IDE (e.g., IntelliJ IDEA).
2. Build and run the application.
3. Check the console for HTTP request responses and logs.

## Code Examples

### GET Request

```kotlin
val client = HttpClient(CIO)

suspend fun getPosts() {
    val response: List<Post> = client.get("https://jsonplaceholder.typicode.com/posts")
    response.forEach { println(it) }
}
```

### POST Request

```kotlin
suspend fun createPost() {
    val response: Post = client.post("https://jsonplaceholder.typicode.com/posts") {
        contentType(ContentType.Application.Json)
        body = Post(userId = 1, title = "New Post", body = "This is a new post.")
    }
    println(response)
}
```

For more examples, refer to the source code in this repository.

## Contributing

Contributions are welcome! Feel free to fork this repository and submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Happy coding! 🚀
