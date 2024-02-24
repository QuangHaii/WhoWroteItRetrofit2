package com.example.whowroteitretrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;

import com.example.whowroteitretrofit2.model.BookInfo;
import com.example.whowroteitretrofit2.model.Item;
import com.example.whowroteitretrofit2.model.ListViewAdapter;
import com.example.whowroteitretrofit2.remote.APIService;
import com.example.whowroteitretrofit2.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<Item> books = null;
    private ListViewAdapter listViewAdapter;
    private Button searchButton;
    private TextView bookInput;
    private ListView listView;
    private TextView instruction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.searchButton);
        bookInput = findViewById(R.id.bookInput);
        listView = findViewById(R.id.listView);
        instruction = findViewById(R.id.instructions);
    }

    public void searchBooks(View view){
        // Get the search string from the input field.
        String queryString = bookInput.getText().toString();

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        // If the network is available, connected, and the search field
        // is not empty, start a BookLoader AsyncTask.
        if (networkInfo != null && networkInfo.isConnected()
                && queryString.trim().length() > 0) {
            searchQuery(queryString,listView);
            instruction.setText(R.string.instructions);
        }
        // Otherwise update the TextView to tell the user there is no
        // connection, or no search term.
        else {
            if (queryString.length() == 0) {
                instruction.setText(R.string.no_search_term);
            } else {
                instruction.setText(R.string.no_network);
            }
        }
    }

    public void searchQuery(String query,ListView listView){
        APIService service = RetrofitClient.getClient().create(APIService.class);
        Call<BookInfo> callSync = service.getBook(query,10,"books");
        callSync.enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                BookInfo bookInfo = response.body();
                books = bookInfo.getItems();
                listViewAdapter = new ListViewAdapter(books);
                listView.setAdapter(listViewAdapter);
            }
            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {

            }
        });
    }
}