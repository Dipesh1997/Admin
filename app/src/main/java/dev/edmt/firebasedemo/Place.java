package dev.edmt.firebasedemo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;


public class Place extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        //Uri uri = Uri.parse("http://wallpaperswide.com/download/great_wall_of_rajasthan-wallpaper-960x540.jpg");
        //http://2.bp.blogspot.com/-mjXRtArnQcI/Ub7Fm7Z2EYI/AAAAAAAAOEs/2iMHk1nKb08/s1600/red_fort_delhi.jpg
        //http://www.hdwallpaperszing.com/wp-content/uploads/2015/08/HD-Goa-Beach-Wallpaper.jpg
        //https://wallpaperscraft.com/image/india_akshardham_temple_beautiful_top_view_panorama_85106_1920x1080.jpg
        Uri uri = Uri.parse("http://wallpaperswide.com/download/great_wall_of_rajasthan-wallpaper-960x540.jpg");

        SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
        draweeView.setImageURI(uri);
        Uri uri2 = Uri.parse("http://3.bp.blogspot.com/-Mny9xrIo_Yk/UpWCi1kkgoI/AAAAAAAANak/h9gH8p6BZX8/s1600/Taj+Mahal+hotel.jpg");

        SimpleDraweeView draweeView2 = (SimpleDraweeView) findViewById(R.id.my_image_view2);
        draweeView2.setImageURI(uri2);
        Uri uri3 = Uri.parse("https://wallpaperscraft.com/image/india_akshardham_temple_beautiful_top_view_panorama_85106_1920x1080.jpg");

        SimpleDraweeView draweeView3 = (SimpleDraweeView) findViewById(R.id.my_image_view3);
        draweeView3.setImageURI(uri3);
        Uri uri4 = Uri.parse("http://www.hdwallpaperszing.com/wp-content/uploads/2015/08/HD-Goa-Beach-Wallpaper.jpg");

        SimpleDraweeView draweeView4 = (SimpleDraweeView) findViewById(R.id.my_image_view4);
        draweeView4.setImageURI(uri4);
        Uri uri5 = Uri.parse("http://2.bp.blogspot.com/-mjXRtArnQcI/Ub7Fm7Z2EYI/AAAAAAAAOEs/2iMHk1nKb08/s1600/red_fort_delhi.jpg");

        SimpleDraweeView draweeView5 = (SimpleDraweeView) findViewById(R.id.my_image_view5);
        draweeView5.setImageURI(uri5);

    }
}
