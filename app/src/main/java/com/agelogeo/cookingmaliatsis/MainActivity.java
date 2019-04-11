package com.agelogeo.cookingmaliatsis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction transaction;
    SQLiteDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("");
        setMyDatabase();

        Cursor c = myDatabase.rawQuery("SELECT * FROM episodes",null);
        if(c.getCount()>0){
            c.moveToFirst();
            do{
                Episode episode = new Episode();
                episode.setId(c.getInt(0));
                episode.setTitle(c.getString(1));
                episode.setVideo_id(c.getString(2));
                episode.addOnEpisodeScenes(new Scene(c.getString(2)));
                SavedSettings.addOnStaticAllEpisodes(episode);
                c.moveToNext();
            }while(!c.isAfterLast());
        }else{
            Log.i("DATABASE","No Result...");
        }
        c.close();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SwitchCompat drawerSwitch = (SwitchCompat) navigationView.getMenu().findItem(R.id.switch_item).getActionView();
        drawerSwitch.setChecked(true);
        drawerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SavedSettings.setShowThumbnails(isChecked);
            }
        });



    }

    public void setMyDatabase(){
        myDatabase = this.openOrCreateDatabase("MaliatsisDB",MODE_PRIVATE,null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS episodes (id INT(6), title VARCHAR , video_id VARCHAR )");
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS scenes (id INT(6), title VARCHAR , favorite BOOLEAN , timestamp INT(6) , episode_id INT(6), FOREIGN KEY (episode_id) REFERENCES episodes(id))");

        Cursor c = myDatabase.rawQuery("SELECT * FROM episodes",null);
        if(c.getCount()==0){
            Log.i("DATABASE","Initialize database..");
            initiateDatabase();
        }
        c.close();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.all) {
            setFragment(new AllFragment());
        } else if (id == R.id.favorites) {
            setFragment(new FavoritesFragment());
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(Fragment fragment) {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_main_layout,fragment);
        transaction.commit();
    }
//(1,'Cooking Maliatsis - 01 - Φτερούγες κοτόπουλου με cola','https://img.youtube.com/vi/K99mRKZxPRc/hqdefault.jpg','https://www.youtube.com/watch?v=K99mRKZxPRc')
    public void initiateDatabase(){
        myDatabase.execSQL("INSERT INTO episodes (id, title, video_id ) VALUES " +
                        "(1,'Cooking Maliatsis - 01 - Φτερούγες κοτόπουλου με cola','K99mRKZxPRc'),"+
                        "(2,'Cooking Maliatsis - 02 - Κάτι σαν ομελέτα (ο Θεός να την κάνει)','-LIYTu4nDkc')," +
                        "(3,'Cooking Maliatsis - 03 - Μπισκοτατάκια με πραλίνα','KSZIMQWNhng')," +
                        "(4,'Cooking Maliatsis - 04 - Κοτο(τσα)μπουκιές με nachos','6H3brlrLiBQ')," +
                        "(5,'Cooking Maliatsis - 05 - Πίτα του Ποπάυ','KSZIMQWNhng')," +
                        "(6,'Cooking Maliatsis - 06 - Στριφτή Μανιταρόπιτα','RM8xI3Co8-g')," +
                        "(7,'Cooking Maliatsis - 07 - Quiche Lorraine','qJorwkgXffo')," +
                        "(8,'Cooking Maliatsis - 08 - Το απόλυτο σάντουιτς','yIZOjzFfZN0')," +
                        "(9,'Cooking Maliatsis - 09 - Κρέπες','5QytMNrIKVs')," +
                        "(10,'Cooking Maliatsis - 10 - Ποπ κορν','zVfg-TdwbzE')," +
                        "(11,'Cooking Maliatsis - 11 - Ωχ, τα πλευρά μου','CAu-TwP6gTo')," +
                        "(12,'Cooking Maliatsis - 12 - Πανσέτες Pancho Villa','QP7dQvGmfoY')," +
                        "(13,'Cooking Maliatsis - 13 - Το παλαμάρι του βαρκάρη','ohrbmP0dGpI')," +
                        "(14,'Cooking Maliatsis - 14 - Μελομακαρομπιέδες και κουραμπιεκάρονα','r_7UtyPEhHM')," +
                        "(15,'Cooking Maliatsis - 15 - Βασιλόπιτα','XNcukPQmRB8')," +
                        "(16,'Cooking Maliatsis - 16 - Μουσακόγυρος','tIHuokZfU1g')," +
                        "(17,'Cooking Maliatsis - 17 - Enrique: πέστροφα σε κρούστα αμυγδάλου','vOP4yFXB6Gc')," +
                        "(18,'Cooking Maliatsis - 18 - Scotch Eggs','axx-pqsX78U')," +
                        "(19,'Cooking Maliatsis - 19 - Κρεμ Μπρουλέ','HyMIwPazi5o')," +
                        "(20,'Cooking Maliatsis - 20 - Κινέζικο Γ.Τ.Π.','cpbm3xW3DJc')," +
                        "(21,'Cooking Maliatsis - 21 - Shepherd\''s Chilli','K9Ma9B-1KPM')," +
                        "(22,'Cooking Maliatsis - 22 - Προφιτερόλ','hRuvGyvbwwg')," +
                        "(23,'Cooking Maliatsis - 23 - Λαχανοντολμάδες αυγολέμονο','1YrpSjHl2tU')," +
                        "(24,'Cooking Maliatsis - 24 - Διπλό Τζακπότ','B-bAutz5dAI')," +
                        "(25,'Cooking Maliatsis - 25 - Μοσχαρίσια τηγανιά με πουρέ γλυκοπατάτας','82N7y--Zz_E')," +
                        "(26,'Cooking Maliatsis - 26 - Cheesecake Brownies','gTmwHU9PRNs')," +
                        "(27,'Cooking Maliatsis - 27 - Burger Φιδέλ Κάστρο','Il-IYctdWEw')," +
                        "(28,'Cooking Maliatsis - 28 - Χταπόδι με κοφτό μακαρονάκι','ndnwH3z-dTM')," +
                        "(29,'Cooking Maliatsis - 29 - Και κουλούρι και τυρί','yvSsGosXCeU')," +
                        "(30,'Cooking Maliatsis - 30 - Σουσι-κώνεται;','mVs7klyup3g')," +
                        "(31,'Cooking Maliatsis - 31 - Χοχλιοί μπουμπουριστοί','698H630xgWs')," +
                        "(32,'Cooking Maliatsis - 32 - (Μας τα \''κανες) τσουρέκια','mEMYql14nvE')," +
                        "(33,'Cooking Maliatsis - 33 - Μαγειρίτσα','IDK-JKwNP10')," +
                        "(34,'Cooking Maliatsis - 34 - Κοντορέτσι/Κοκοσούβλι','Xc_1TflsTUE')," +
                        "(35,'Cooking Maliatsis - 35 - Αγκινάρες α λα πολίτα','qZ6OnXpaXQo')," +
                        "(36,'Cooking Maliatsis - 36 - Η σούπα της αγάπης','YPB1vOnmyys')," +
                        "(37,'Cooking Maliatsis - 37 - Κανταΐφι και έθιμα','jgOzIl0bM_o')," +
                        "(38,'Cooking Maliatsis - 38 - Πίτσα κέικ με γύρο','IIgbkjEAZiU')," +
                        "(39,'Cooking Maliatsis - 39 - Μπιφτέκπληξη','svSBKIVaQxE')," +
                        "(40,'Cooking Maliatsis - 40 - Γαριδοαστακομακαρονάδα','fD8-FRSyZsY')," +
                        "(41,'Cooking Maliatsis - 41 - Παγωτό κόλλυβα','DH7i75ASq6Y')," +
                        "(42,'Cooking Maliatsis - 42 - Μοριακό σουβλάκι','r3uFn3VPF48')," +
                        "(43,'Cooking Maliatsis - 43 - Τούρτα εKλαίρ','MYQg6yXuoVk')," +
                        "(44,'Cooking Maliatsis - 44 - Ψαρονέφρι γεμιστό με πατάτα ακορντεόν','l-piTdJnsTc')," +
                        "(45,'Cooking Maliatsis - 45 - Vegan Mac\''n\''Cheese με vegeκεφτεδάκια','HEoHdtqDNIo')," +
                        "(46,'Cooking Maliatsis - 46 - Φεστιβαλ χοληστερίνης','7O4yI4Vp86c')," +
                        "(47,'Cooking Maliatsis - 47 - Κολοκύθα τούμπανο','ElQr3xWtjOI')," +
                        "(48,'Cooking Maliatsis - 48 - Μπουγιαμπέσα με μούτσο','Z2HTOPzNvY0')," +
                        "(49,'Cooking Maliatsis - 49 - Γεμιστές χοιρινές μπριζόλες','rp0BQJXMQkY')," +
                        "(50,'Cooking Maliatsis - 50 - Αρνάκι (άσπρο και παχύ της μάνας του) με κάρι','RymudG45k9E')," +
                        "(51,'Cooking Maliatsis - 51 - Φιλέτο Wellington ft. Δημήτρης Σκαρμούτσος','PmLuuGQ7n-U')," +
                        "(52,'Cooking Maliatsis - 52 - Γιουβαρλάκια','f6-kA6OfglE')," +
                        "(53,'Cooking Maliatsis - 53 - Μπανανόφι','OUNWQ-OlPDY')," +
                        "(54,'Cooking Maliatsis - 54 - Στιφάδο','AWDls2_zfuc')," +
                        "(55,'Cooking Maliatsis - 55 - Γηρωικό σάντουιτς','FxJsUwo5_4I')," +
                        "(56,'Cooking Maliatsis - 56 - Τούρτα πίτσα','XYhgCBQivL0')," +
                        "(57,'Cooking Maliatsis - 57 - Νιόκι με κρέμα απάκι','_N9Lwn7b5DA')," +
                        "(58,'Cooking Maliatsis - 58 - Φασόλια χάντρες με λουκάνικο και μπέικον','lYIpYZDTSIo')," +
                        "(59,'Cooking Maliatsis - 59 - Πάπια πορτοκάλι','VqTBUb3xivU')," +
                        "(60,'Cooking Maliatsis - 60 - Κανελόνια γεμιστά','AZzUgij_wqI')," +
                        "(61,'Cooking Maliatsis - 61 - Τάρτα σοκολάτα φιστικοβούτυρο','edfIVo-M4fo')," +
                        "(62,'Cooking Maliatsis - 62 - Θράψαλο γεμιστό','gj1NCbk8MSs')," +
                        "(63,'Cooking Maliatsis - 63 - Χειροποίητα ζυμαρικά','c9bmKFatRMw')," +
                        "(64,'Cooking Maliatsis - 64 - İçli Köfte','y1wtsIKG8fc')," +
                        "(65,'Cooking Maliatsis - 65 - Μου έφυγε ο tacos','yQ-1g98VoB8')," +
                        "(66,'Cooking Maliatsis - 66 - Κρεατοκουλούρα','5zYzzbtr9nQ')," +
                        "(67,'Cooking Maliatsis - 67 - Ρώσικη ρουλέτα','rqtf25Skd-4')," +
                        "(68,'Cooking Maliatsis - 68 - Αρνί γεμιστό + Bonus','fnqCfEh5HMg')," +
                        "(69,'Cooking Maliatsis - 69 - Γαλακτομπούρεκο','HMaph-4zErc')," +
                        "(70,'Cooking Maliatsis - 70 - Cinammon Buns','6-i4yzp0LMg')," +
                        "(71,'Cooking Maliatsis - 71 - Κοτόπουλο Παρμεζάναξ Ft. Μάρκος Σεφερλης','LU6zEzsSDyk')," +
                        "(72,'Cooking Maliatsis - 72 - Πεϊνιρλί','E8DKIfz0WMA')," +
                        "(73,'Cooking Maliatsis - 73 - Πάβλοβα','5r2wxbWY1uQ')," +
                        "(74,'Cooking Maliatsis - 74 - Despastitsio','DP6E-Wun1Nk')," +
                        "(75,'Cooking Maliatsis - 75 Burger Mac and Cheese','90K4l7InCkg')," +
                        "(76,'Cooking Maliatsis - 76 - Φιλέτο γλώσσας με μελιτζανοσαλάτα','RkV2ANwf0nc')," +
                        "(77,'Cooking Maliatsis - 77 - Chimichanga τούμπανο','jbgtZ1BByaM')," +
                        "(78,'Cooking Maliatsis - 78 - Τραγική Τριλογία','EEe2u2Nzz4U')," +
                        "(79,'Cooking Maliatsis - 79 - Γαρίδες κανταΐφι με ριζότο παντζαριού','76HhWfQR-B0')," +
                        "(80,'Cooking Maliatsis - 80 - Σαπιομπουρδελάκατος Πρασοσέλινο','dboF-LMEOh8')," +
                        "(81,'Cooking Maliatsis - 81 - Τάρτα με συκωτάκια πουλιών','Wef0OEGZT_I')," +
                        "(82,'Cooking Maliatsis - 82 - Rixtetointernet Πίτσα','jl5_qIhk6n0')," +
                        "(83,'Cooking Maliatsis - 83 - Σουπιές με σπανάκι + Πίτα έκπληξη','DV5sxxe-BUA')," +
                        "(84,'Cooking Maliatsis - 84 - Thai κοτόπουλο με πράσινο κάρρυ','RYyeEVPMcV0')," +
                        "(85,'Cooking Maliatsis - 85 - Λουκάνικααααα','t3aHyhuXEA4')," +
                        "(86,'Cooking Maliatsis - 86 - Βασιλοπιτά-πιτα & Γαλοπουλομπάμπουσκα','027wrBETjN4')," +
                        "(87,'Cooking Maliatsis - 87 - Χέλια στο φούρνο & σπάθα στο τηγάνι','_xL2yjN1quw')," +
                        "(88,'Cooking Maliatsis - 88 - Βουρ στον πατσά / πατσα-βουρ-όπιτα','5n4pdH-Q6II')," +
                        "(89,'Cooking Maliatsis - 89 - Γαμήλια Τούρτα (ft. Λυδία Παπαϊωάννου)','Mq3LTYvjWWU')," +
                        "(90,'Cooking Maliatsis - 90 - Βατραχοπόδαρα & αμελέτητα','LGEDc28Jy2A')," +
                        "(91,'Cooking Maliatsis - 91 - Σαλάτα σε ζελέ','MyLycdgSuTk')," +
                        "(92,'Cooking Maliatsis - 92 - Πιτσόγυρο','UkGatpNNa-Q')," +
                        "(93,'Cooking Maliatsis - 93 - Απολύομαι ψαρούκλες','5HSYtgdzfT0')," +
                        "(94,'Cooking Maliatsis - 94 - Godzilla Steak','jI5zAqmOX2o')," +
                        "(95,'Cooking Maliatsis - 95 - Κοτόπουλο με σάλτσα σοκολάτας (mole poblano)','LzGL4qcaGjs')," +
                        "(96,'Cooking Maliatsis - 96 - Donuts με γαλαξιακό γλάσο','q6SV1d2Ppcg')," +
                        "(97,'Cooking Maliatsis - 97 - Σαλάτα με παγωτό τυρί','6ewUA-qDMK0')," +
                        "(98,'Cooking Maliatsis - 98 - Ο Πύργος της Κολάσεως!','De2abch0Ax0')," +
                        "(99,'Cooking Maliatsis - 99 - Μπακλαβάς Cheesecake','8fYlUekpNKw')," +
                        "(100,'Cooking Maliatsis - Πάσχα στο Χωριό 2018 Special','WymzcFL_s3o')," +
                        "(101,'Cooking Maliatsis - 100 - Γεμιστά με γύρο','Lby2yat8boc')," +
                        "(102,'Cooking Maliatsis - 101 - Stargazy Pie','u6dCYy1YukE')," +
                        "(103,'Cooking Maliatsis - 102 - Χωνακονιονκεφτέδες της οργής','ayq4ikbgwr8')," +
                "(104,'Cooking Maliatsis - 103 - Το SPAM του αιώνα','mm25peTPo2c')," +
                "(105,'Cooking Maliatsis - 104 - Πίτσα κουνουπίδι','50aNUFqjJcI')," +
                "(106,'Cooking Maliatsis - 105 - Ντόπα γλυκό με Baileys','G_1UtzpOtwo')," +
                "(107,'Cooking Maliatsis - 106 - Τροπικό χοιρινό με μπανάνα ft. Ευρυδίκη Βαλαβάνη','OYtcKfH88-s')," +
                "(108,'Cooking Maliatsis - 107 - Deep Fried Cheeseburger','28VCnmE3K0k')," +
                "(109,'Cooking Maliatsis - 108 - Χταπόδι φρικασέ και-φτέδες','yZ0y95jAF4U')," +
                "(110,'Cooking Maliatsis - 109 - Brownies με 3 σοκολάτες και παντζάρι','0ObhDXXyUCA')," +
                "(111,'Cooking Maliatsis - 110 - Ramen ft. Σωτήρης Κοντιζάς','EVzaw5rFY3Q')," +
                "(112,'Cooking Maliatsis - 111 - ΤΟΥΜΠΑΝΟΟΟΟ (speaks fluent Italian)','tQyudI2GBMQ')," +
                "(113,'Cooking Maliatsis - 112 - Κουνέλι κοκορέτσι','9psbqMpI-mw')," +
                "(114,'Cooking Maliatsis - 113 - Flying spaghetti κιμάνστερ','sQP67PAQRUQ')," +
                "(115,'Cooking Maliatsis - 114 - Πρόχειρο διαγώνισμα: Scotch Eggs','BybNrB9n6XA')," +
                "(116,'Cooking Maliatsis - 115 - Πορτσέτα με μπύρα','tchGGkelGc0')," +
                "(117,'Cooking Maliatsis - 116 - Ο τιτλος του $eΧ tape σου','vx8tb2xBhUs')," +
                "(118,'Cooking Maliatsis - 117 - Επικά μπισκοτομπράουνις','2vYpgR645jM')," +
                "(119,'Cooking Maliatsis - 118 - Ποιος γ@μ@ει ποιος χήνα','RHTH57bYsWI')," +
                "(120,'Cooking Maliatsis - 119 - Μπιφτεκολαζάνια με κιμά','5eerj2Fby0w')," +
                "(121,'Cooking Maliatsis - 120 - Κεφαλάκι αρνίσιο','x4gm4CLGJyU')," +
                "(122,'Cooking Maliatsis - 121 - Κύβος ΤυΡούμπικ ft. Φάνης Λαμπρόπουλος','HITaLSa21rk')," +
                "(123,'Cooking Maliatsis - 122 - Πορτοκαλοπιτα με παγωτό','cLreTY58n5k')," +
                "(124,'Cooking Maliatsis - 123 - Παέγια','TSV0J0qS1i8')," +
                "(125,'Cooking Maliatsis - 124 - Μους σοκολάτα honeycomb','EU5IPEeCOoo')," +
                "(125,'Cooking Maliatsis - 125 - Kizomba Hash Browns με Γύρο','d8mRhcqwu5Y')");


    }
}
