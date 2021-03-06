package meugeninua.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DrawablesActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton animationDrawableView;
    private ImageButton animatedVectorView;
    private ImageButton fadeViewsFirst;
    private ImageButton fadeViewsSecond;

    private AnimationDrawable animationDrawable;
    private AnimatedVectorDrawableCompat animatedVectorDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawables);

        animationDrawable = createAnimationDrawable(false);
        animatedVectorDrawable = createAnimatedVectorDrawable(false);

        animationDrawableView = findViewById(R.id.animation_drawable_view);
        animationDrawableView.setImageDrawable(animationDrawable);
        animationDrawableView.setOnClickListener(this);
        animatedVectorView = findViewById(R.id.animated_vector_view);
        animatedVectorView.setImageDrawable(animatedVectorDrawable);
        animatedVectorView.setOnClickListener(this);
        fadeViewsFirst = findViewById(R.id.fade_views_1_view);
        fadeViewsFirst.setOnClickListener(this);
        fadeViewsSecond = findViewById(R.id.fade_views_2_view);
        fadeViewsSecond.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.animation_drawable_view) {
            animateAnimationDrawable();
        } else if (id == R.id.animated_vector_view) {
            animateAnimatedVector();
        } else if (id == R.id.fade_views_1_view) {
            animateFadeViews(false);
        } else if (id == R.id.fade_views_2_view) {
            animateFadeViews(true);
        }
    }

    private void animateAnimationDrawable() {
        boolean isBack = Boolean.TRUE.equals(animationDrawableView.getTag());
        animationDrawable = createAnimationDrawable(isBack);
        animationDrawableView.setImageDrawable(animationDrawable);
        animationDrawableView.setTag(!isBack);
        animationDrawable.start();
    }

    private void animateAnimatedVector() {
        boolean isBack = Boolean.TRUE.equals(animatedVectorView.getTag());
        animatedVectorDrawable = createAnimatedVectorDrawable(isBack);
        animatedVectorView.setImageDrawable(animatedVectorDrawable);
        animatedVectorView.setTag(!isBack);
        animatedVectorDrawable.start();
    }

    private void animateFadeViews(boolean isBack) {
        final View fadeInView, fadeOutView;
        if (isBack) {
            fadeInView = fadeViewsFirst;
            fadeOutView = fadeViewsSecond;
        } else {
            fadeInView = fadeViewsSecond;
            fadeOutView = fadeViewsFirst;
        }

        fadeInView.setAlpha(0);
        fadeInView.setVisibility(View.VISIBLE);
        fadeOutView.setAlpha(1);
        fadeOutView.setVisibility(View.VISIBLE);

        long duration = getResources().getInteger(R.integer.fade_views_duration);
        fadeInView.animate()
                .alpha(1)
                .setDuration(duration)
                .setListener(null);
        fadeOutView.animate()
                .alpha(0)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        fadeOutView.setVisibility(View.GONE);
                    }
                });
    }

    private AnimationDrawable createAnimationDrawable(boolean isBack) {
        Resources resources = getResources();

        int[] items;
        if (isBack) {
            items = new int[] {
                    R.drawable.baseline_battery_full_96,
                    R.drawable.baseline_battery_charging_full_96,
                    R.drawable.baseline_battery_charging_90_96,
                    R.drawable.baseline_battery_charging_80_96,
                    R.drawable.baseline_battery_charging_60_96,
                    R.drawable.baseline_battery_charging_50_96,
                    R.drawable.baseline_battery_charging_30_96,
                    R.drawable.baseline_battery_charging_20_96 };
        } else {
            items = new int[] {
                    R.drawable.baseline_battery_charging_20_96,
                    R.drawable.baseline_battery_charging_30_96,
                    R.drawable.baseline_battery_charging_50_96,
                    R.drawable.baseline_battery_charging_60_96,
                    R.drawable.baseline_battery_charging_80_96,
                    R.drawable.baseline_battery_charging_90_96,
                    R.drawable.baseline_battery_charging_full_96,
                    R.drawable.baseline_battery_full_96 };
        }
        AnimationDrawable drawable = new AnimationDrawable();
        drawable.setOneShot(true);
        for (int item : items) {
            drawable.addFrame(
                    VectorDrawableCompat.create(resources, item, getTheme()),
                    resources.getInteger(R.integer.animation_drawable_duration));
        }
        return drawable;
    }

    private AnimatedVectorDrawableCompat createAnimatedVectorDrawable(boolean isBack) {
        int drawableId = isBack ? R.drawable.animated_call_down : R.drawable.animated_call_up;
        return AnimatedVectorDrawableCompat.create(this, drawableId);
    }
}
