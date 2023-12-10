package com.laura.harrycharacters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.laura.harrycharacters.HarryAdapter.OnItemSelectedListener;


public class HarryAdapter extends RecyclerView.Adapter<HarryViewHolder> {

    private List<CharactersResponse> listCharacter;
    private OnItemSelectedListener onItemSelected;

    public HarryAdapter(OnItemSelectedListener onItemSelected) {
        this.onItemSelected = onItemSelected;
    }

    public void updateList(List<CharactersResponse> listCharacter) {
        this.listCharacter = listCharacter;
        notifyDataSetChanged();
    }

    @Override
    public HarryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_characters, parent, false);
        return new HarryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listCharacter != null ? listCharacter.size() : 0;
    }

    @Override
    public void onBindViewHolder(HarryViewHolder holder, int position) {
        if (listCharacter != null) {
            CharactersResponse item = listCharacter.get(position);
            holder.bind(item, onItemSelected);
        }
    }

    public interface OnItemSelectedListener {
        void onItemSelected(String itemId);
    }
}

