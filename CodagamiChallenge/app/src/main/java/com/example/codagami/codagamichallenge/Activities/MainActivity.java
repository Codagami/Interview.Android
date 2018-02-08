package com.example.codagami.codagamichallenge.Activities;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.codagami.codagamichallenge.CustomLayouts.CustomListAdapter;
import com.example.codagami.codagamichallenge.Objects.Person;
import com.example.codagami.codagamichallenge.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String CODAGAMI_URL =
            "https://codagami.blob.core.windows.net/interviews/codagami_challenge_android.json";
    private static final String CODAGAMI_CHARSET = "UTF-8";

    private ListView peopleList;
    private CustomListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // NOTE: You may not need this depending on your network settings.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        peopleList = (ListView) findViewById(R.id.peopleList);

        List<Person> personList = readPersonList(this);
        if (personList.isEmpty()) {
            Log.e("Empty Person List", "Could not read person list from Codagami URL");
            // TODO: Can probably show some sort of UI to user to let them know we failed to read
            // the person list too.
        } else {
            listAdapter = new CustomListAdapter(this, 0, personList);
            peopleList.setAdapter(listAdapter);
        }
    }

    private static @Nullable List<Person> readPersonList(Context context) {
        JSONObject json = maybeReadJson(CODAGAMI_URL);
        if (json == null) {
            return null;
        }

        List<Person> personList = new ArrayList<>();
        try {
            JSONArray peopleArray = json.getJSONArray("people");
            for (int i = 0; i < peopleArray.length(); i++) {
                JSONObject peopleJson = peopleArray.getJSONObject(i);
                String firstName = peopleJson.getString("firstName");
                String lastName = peopleJson.getString("lastName");
                String age = peopleJson.getString("age");
                personList.add(new Person(firstName, lastName, age));
            }
        } catch (JSONException e) {
            Log.e("JSONException", "While trying to parse 'people' JSON Array");
        } finally {
            return personList;
        }
    }

    private static @Nullable JSONObject maybeReadJson(String url) {
        try {
            JSONObject jsonObject = readJson(CODAGAMI_URL);
            return jsonObject;
        } catch (IOException e) {
            Log.e("IOException", "While trying to read Codagami URL");
            return null;
        } catch (JSONException e) {
            Log.e("JSONException", "While trying to read Codagami URL");
            return null;
        }
    }

    private static JSONObject readJson(String url) throws IOException, JSONException {
        InputStream inputStream = new URL(url).openStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, Charset.forName(CODAGAMI_CHARSET)));
            JSONObject jsonObject = new JSONObject(read(bufferedReader));
            return jsonObject;
        } finally {
            inputStream.close();
        }
    }

    private static String read(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int c;
        while ((c = bufferedReader.read()) != -1) {
            stringBuilder.append((char) c);
        }
        return stringBuilder.toString();
    }
}
