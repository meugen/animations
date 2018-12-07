package meugeninua.animations;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH> {

    private static final int VIEW_TYPES_COUNT = 1;

    public static final int DRAWABLES_POSITION = 0;

    private final LayoutInflater inflater;
    private final OnClickActivityListener listener;

    public MainAdapter(Context context, OnClickActivityListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup container, int viewType) {
        return new VH(inflater.inflate(R.layout.item_activity, container, false), this);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return VIEW_TYPES_COUNT;
    }

    private void callOnAdapterClick(int position) {
        listener.onClickActivity(position);
    }

    static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView titleView;
        private final WeakReference<MainAdapter> adapterRef;
        private int position;

        VH(View view, MainAdapter adapter) {
            super(view);
            this.adapterRef = new WeakReference<>(adapter);
            titleView = view.findViewById(R.id.title);
            titleView.setOnClickListener(this);
        }

        void bind(int position) {
            this.position = position;
            if (position == DRAWABLES_POSITION) {
                titleView.setText(R.string.title_drawables);
            }
        }

        @Override
        public void onClick(View v) {
            MainAdapter adapter = adapterRef.get();
            if (adapter == null) {
                return;
            }

            int id = v.getId();
            if (id == R.id.title) {
                adapter.callOnAdapterClick(position);
            }
        }
    }

    public interface OnClickActivityListener {

        void onClickActivity(int position);
    }
}
