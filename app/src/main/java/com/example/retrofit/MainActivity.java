package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    List list;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_item);
        SearchView searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText) && TextUtils.isDigitsOnly(newText)) {
                    id = Integer.parseInt(newText);
                    getTodo(id);
                } else {
                    getTodos();
                }
                return false;
            }
        });

        //calling the method to display the Todos
        getTodos();



    }

    private void getTodo(int id) {
        Call<ObjectResponse<Todo>> call = RetrofitClient.getInstance().getApi().getTodo(id);
        (call).enqueue(new Callback<ObjectResponse<Todo>>() {
            @Override
            public void onResponse(Call<ObjectResponse<Todo>> call, Response<ObjectResponse<Todo>> response) {
                if(response.body() != null && response.isSuccessful()) {
                    ; //displaying the string array into
                    String[] todos = new String[]{String.valueOf(response.body().getId()), response.body().getTitle(), String.valueOf(response.body().getUserId())};
                    listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, todos));
                }
            }

            @Override
            public void onFailure(Call<ObjectResponse<Todo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }



    private void getTodos() {
        Call<List<Todo>> call = RetrofitClient.getInstance().getApi().getTodos();
        call.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                List<Todo> todoList = response.body();

                //Creating an String array for the ListView
                String[] todos = new String[todoList.size()];
                int[] todoIds = new int[todoList.size()];
                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < todoList.size(); i++) {
                    todos[i] = todoList.get(i).getTitle();
                    todoIds[i] = todoList.get(i).getId();
                }

                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, todos));
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}