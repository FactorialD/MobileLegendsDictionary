package ua.factoriald.mydictmlbb0001;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;



public class HeroSkills extends AppCompatActivity {

    String heroName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_skills);
        heroName = getIntent().getExtras().getString("name");

        WebView mWebView;

        mWebView = (WebView) findViewById(R.id.skill_web_view);
        // включаем поддержку JavaScript
        //mWebView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        Log.v("load url", "load url");
        boolean mn = (mWebView == null);
        Log.v("mwebview is:", new Boolean(mn).toString());
        //mWebView.loadUrl("file:///android_asset/pages/" + heroName.toLowerCase() + "/index.html");
        mWebView.loadUrl("file:///android_asset/index.html");
        //mWebView.loadUrl("https://lordoftime1327.github.io/DimaS/");


        Log.v("url loaded", "url loaded");

        // For API level below 18 (This method was deprecated in API level 18)
        if (Build.VERSION.SDK_INT >= 19) {
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        //ScrollView sv = (ScrollView) findViewById(R.id.skills_scroll);
        //ImageView iv = new ImageView();
        //sv.addView(iv);
//        ConstraintLayout bl = (ConstraintLayout) findViewById(R.id.blank_skills);
//        try{
//            sv.addView(bl);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        TextView skillText = (TextView) findViewById(R.id.skill_text);
//        skillText.setText(new HtmlSpanner().fromHtml(getString(R.string.article_main)));
//        skillText.setLinksClickable(true);
//        skillText.setMovementMethod(new LinkMovementMethod());


        //setArticle("article_main");


    }
}
