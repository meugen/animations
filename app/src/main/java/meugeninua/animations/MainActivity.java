package meugeninua.animations;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.image);
        animationDrawable = createAnimationDrawable();
        imageView.setImageDrawable(animationDrawable);
    }

    @Override
    protected void onStart() {
        super.onStart();
        animationDrawable.start();
    }

    private AnimationDrawable createAnimationDrawable() {
        Resources resources = getResources();

        int[] items = new int[] {
                R.drawable.baseline_battery_charging_20_96,
                R.drawable.baseline_battery_charging_30_96,
                R.drawable.baseline_battery_charging_50_96,
                R.drawable.baseline_battery_charging_60_96,
                R.drawable.baseline_battery_charging_80_96,
                R.drawable.baseline_battery_charging_90_96,
                R.drawable.baseline_battery_charging_full_96,
                R.drawable.baseline_battery_full_96};
        AnimationDrawable drawable = new AnimationDrawable();
        drawable.setOneShot(false);
        for (int item : items) {
            drawable.addFrame(
                    VectorDrawableCompat.create(resources, item, getTheme()),
                    resources.getInteger(R.integer.short_duration));
        }
        return drawable;
    }
}
