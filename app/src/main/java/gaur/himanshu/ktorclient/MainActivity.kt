package gaur.himanshu.ktorclient

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gaur.himanshu.ktorclient.client.KtorClient
import gaur.himanshu.ktorclient.model.Post
import gaur.himanshu.ktorclient.ui.theme.KTORClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var posts by remember { mutableStateOf(emptyList<Post>()) }
            LaunchedEffect(Unit) {
                posts = KtorClient().getPosts()

                val postPost = KtorClient().postPost(
                    Post(
                        body = "body",
                        id = 1,
                        title = "title",
                        userId = 3
                    )
                )
                val patch = KtorClient().patch(
                    mapOf("title" to "fjaskljflafafasfajslfk"),
                    1
                )
                val put = KtorClient().put(
                    Post(
                        body = "body put",
                        id = 1,
                        title = "title put",
                        userId = 3
                    ),
                    1
                )
                val delete = KtorClient().delete(1)
                val comments = KtorClient().getComments(1)
                Log.d("TAGGGGGGGG", "onCreate COMMENTS: ${comments}")
                Log.d("TAGGGGGGG", "onCreate DELETE: ${delete.status.value}")
                Log.d("TAGGGGGGGGGGG", "onCreate PATCH: ${patch}")
                Log.d("TAGGGGGGGGGGG", "onCreate PUT: ${put}")
                Log.d("TAGGGGGGGGG", "onCreate: ${postPost}")

            }
            KTORClientTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        list = posts
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier, list: List<Post>) {
    LazyColumn(modifier.fillMaxSize()) {
        items(list) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                Text(text = it.id.toString())
                Spacer(Modifier.height(8.dp))
                Text(text = it.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(8.dp))
                Text(text = it.body, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

