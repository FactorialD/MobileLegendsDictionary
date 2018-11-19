package ua.factoriald.mydictmlbb0001;

import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import static java.lang.Integer.parseInt;


public class HeroMainActivity extends AppCompatActivity {

    public String hName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Log.d("Hero Class Main", "yes");


        TextView heroName = (TextView) findViewById(R.id.hero_name);
        ImageView heroBigIcon = (ImageView) findViewById(R.id.hero_main_big_icon);
        ImageView heroTypeIcon = (ImageView) findViewById(R.id.hero_type_icon);
        TextView heroType = (TextView) findViewById(R.id.hero_type);
        TextView heroSpecialization = (TextView) findViewById(R.id.hero_specialization);

        TextView heroSkills = (TextView) findViewById(R.id.hero_skills);
        heroSkills.setOnClickListener(e -> {

            Intent intent = new Intent(this, HeroSkills.class);
            intent.putExtra("name", hName);
            startActivity(intent);
        });

        ProgressBar heroDurability = (ProgressBar) findViewById(R.id.hero_durability);
        //heroDurability.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        //heroDurability.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        //heroDurability.getIndeterminateDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);


        ProgressBar heroOffence = (ProgressBar) findViewById(R.id.hero_offence);
        //heroOffence.getIndeterminateDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        //heroOffence.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);


        ProgressBar heroAbilityEffects = (ProgressBar) findViewById(R.id.hero_ability_effects);
        //heroAbilityEffects.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
        //heroAbilityEffects.getProgressDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);


        ProgressBar heroDifficulty = (ProgressBar) findViewById(R.id.hero_difficulty);
        //heroDifficulty.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
        //heroDifficulty.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);


        Intent intent = getIntent();



        //String name = intent.getExtras().getString("name");
        //heroName.setText(name);

        String str;

        int position = intent.getExtras().getInt("position");
        str = String.valueOf(position);

        heroName.setText(str);

        XmlPullParser parser = getResources().getXml(R.xml.all_heroes);

        // массивы данных
        try {
            for (int i = -1; (parser.getEventType()!= XmlPullParser.END_DOCUMENT) && (i <= position); ) {

                if ((parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("Hero"))) {
                    i++;
                    if( i == position ){//если попали на нужного героя

                        str = parser.getAttributeValue(0);
                        hName  = str;
                        heroName.setText(str);

                        heroBigIcon.setImageResource(getResources().getIdentifier(parser.getAttributeValue(2), "drawable", getPackageName()));

                        str = parser.getAttributeValue(3);
                        if(str.contains("Tank")){
                            if(str.contains("Fighter")) heroTypeIcon.setImageResource(R.drawable.tank_fighter_icon);                //Tank Fighter
                            else if (str.contains("Support")) heroTypeIcon.setImageResource(R.drawable.support_tank_icon);          //Tank Support
                            else heroTypeIcon.setImageResource(R.drawable.tank_icon);                                               //Tank

                        }
                        else if (str.contains("Support")){
                            heroTypeIcon.setImageResource(R.drawable.support_icon);                                                 //Support
                        }
                        else if (str.contains("Fighter")){
                            if(str.contains("Assassin")) heroTypeIcon.setImageResource(R.drawable.assassin_fighter_icon);           //Fighter Assassin
                            else if (str.contains("Marksman")) heroTypeIcon.setImageResource(R.drawable.fighter_marksman_icon);     //Fighter Marksman
                            else heroTypeIcon.setImageResource(R.drawable.fighter_icon);                                               //Fighter

                        }
                        else if (str.contains("Assassin")){
                            if(str.contains("Mage")) heroTypeIcon.setImageResource(R.drawable.assassin_mage_icon);                  //Assassin Mage
                            else if (str.contains("Marksman")) heroTypeIcon.setImageResource(R.drawable.marksman_assassin_icon);    //Assassin Marksman
                            else heroTypeIcon.setImageResource(R.drawable.assassin_icon);                                           //Assassin

                        }
                        else if (str.contains("Mage")){
                            heroTypeIcon.setImageResource(R.drawable.mage_icon);                                                    //Mage
                        }
                        else if (str.contains("Marksman")){
                            heroTypeIcon.setImageResource(R.drawable.marksman_icon);                                                //Marksman
                        }

                        heroType.setText(parser.getAttributeValue(3));

                        heroSpecialization.setText(parser.getAttributeValue(4));

                        heroDurability.setProgress( parseInt(parser.getAttributeValue(5)) );
                        heroOffence.setProgress( parseInt(parser.getAttributeValue(6)) );
                        heroAbilityEffects.setProgress( parseInt(parser.getAttributeValue(7)) );
                        heroDifficulty.setProgress( parseInt(parser.getAttributeValue(8)) );


                    }
                }
                parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
