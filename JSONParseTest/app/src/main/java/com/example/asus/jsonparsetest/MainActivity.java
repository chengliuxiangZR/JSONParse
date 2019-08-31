package com.example.asus.jsonparsetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Person> persons;
    private String json="[\n" +
            "    { \"id\":\"1\",\"name\":\"基神\",\"age\":\"18\" },\n" +
            "    { \"id\":\"2\",\"name\":\"B神\",\"age\":\"18\"  },\n" +
            "    { \"id\":\"3\",\"name\":\"曹神\",\"age\":\"18\" }\n" +
            "]";
    private Person person_example=new Person("4","龙浩晨","15");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        persons=new ArrayList<>();
    }
    public void myClick(View view){
        parseEasyJson(json);
        String s=allString();
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }
    public void myClick_2(View view){
        String s=fast_json(json);
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }
    public void myClick_3(View view){
        String text=JSON.toJSONString(person_example);
        Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();

    }
    private String fast_json(String json){
        StringBuilder stringBuilder=new StringBuilder();
        List<Person> personList= JSON.parseArray(json,Person.class);
        for(int i=0;i<personList.size();i++){
            Person person=personList.get(i);
            stringBuilder.append(person.toString()+"\n");
        }
        return stringBuilder.toString();
    }
    private String allString(){
        StringBuilder stringBuilder=new StringBuilder();
        for(Person person:persons){
            stringBuilder.append(person.toString()+"\n");
        }
        return stringBuilder.toString();
    }
    private void parseEasyJson(String json){
        try {
            JSONArray jsonArray=new JSONArray(json);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=(JSONObject) jsonArray.get(i);
                Person person=new Person();
                person.setId(i+"");
                person.setName(jsonObject.getString("name"));
                person.setAge(jsonObject.getString("age"));
                persons.add(person);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
