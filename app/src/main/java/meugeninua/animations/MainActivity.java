package meugeninua.animations;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnClickActivityListener {

    private ImageView androidView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidView = findViewById(R.id.android);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new MainAdapter(this, this));
    }

    @Override
    public void onClickActivity(int position) {
        if (position == MainAdapter.DRAWABLES_POSITION) {
            openDrawables();
        }
    }

    private void openDrawables() {
        Intent intent = new Intent(this, DrawablesActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, androidView, "android");
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
