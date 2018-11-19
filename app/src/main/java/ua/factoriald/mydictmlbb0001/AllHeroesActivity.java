package ua.factoriald.mydictmlbb0001;


import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AllHeroesActivity extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView allHeroesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("allheroesactivity", "created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_heroes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Heroes");
        }



        XmlPullParser parser = getResources().getXml(R.xml.all_heroes);

        ArrayList<HeroListItem> listhero = new ArrayList<>();
        int imgbuf;


        //заполняем массивы с именами и иконками с xml файла
        try {
            while (parser.getEventType()!= XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("Hero")) {
                    HeroListItem h = new HeroListItem(parser.getAttributeValue(0), parser.getAttributeValue(1));
                    listhero.add(h);

                }
                parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // упаковываем данные в понятную для адаптера структуру
        //ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(listname.size());
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(listhero.size());
        Map<String, Object> m;
        for (int i = 0; i < listhero.size(); i++) {
            imgbuf = getResources().getIdentifier(listhero.get(i).img, "drawable", "ua.factoriald.mydictmlbb0001");


            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, listhero.get(i).heroName);
            m.put(ATTRIBUTE_NAME_IMAGE, imgbuf);
            //m.put(ATTRIBUTE_NAME_IMAGE, ("assets\\hero_little_icon\\" + listhero.get(i).img));

            data.add(m);
        }

        // массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_IMAGE };
        // массив ID View-компонентов, в которые будут вставлять данные
        int[] to = { R.id.list_item_hero_name, R.id.list_item_hero_icon };

        // создаем адаптер
        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.list_item,
                from, to);

        // определяем список и присваиваем ему адаптер
        allHeroesList = (ListView) findViewById(R.id.all_heroes_list);
        allHeroesList.setAdapter(sAdapter);



        allHeroesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                intent = new Intent(AllHeroesActivity.this, HeroMainActivity.class);

                TextView name = (TextView) findViewById(R.id.hero_name);
                //TextView tv = (TextView) parent.findViewById(R.id.list_item_hero_name);
                //String name = tv.getText().toString();
                //Log.v("df", name.getText().toString());

                ////parent.getItemAtPosition(position)

                //intent.putExtra("name", name.getText().toString());
                intent.putExtra("position", position);

                startActivity(intent); //Запускаем активность
            }
            });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar Up/Home button
            case android.R.id.home:
                onBackPressed();
                //NavUtils.navigateUpFromSameTask(this);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
