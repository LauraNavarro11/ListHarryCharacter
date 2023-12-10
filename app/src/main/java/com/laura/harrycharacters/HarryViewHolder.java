package com.laura.harrycharacters;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.laura.harrycharacters.databinding.ItemCharactersBinding;

public class HarryViewHolder extends RecyclerView.ViewHolder {
    private final ItemCharactersBinding binding;

    public HarryViewHolder(View view) {
        super(view);
        binding = ItemCharactersBinding.bind(view);
    }

    public void bind(CharactersResponse characterResponse, HarryAdapter.OnItemSelectedListener onItemSelectedListener) {
        binding.tvNameCharacter.setText(characterResponse.getName());
        binding.tvHouseCharacter.setText(characterResponse.getHouse());

        // Utilizando Glide para cargar la imagen (puedes ajustar según tus necesidades)
        Glide.with(binding.tvHouseCharacter.getContext())
                .load(characterResponse.getImage())
                .into(binding.ivCharacters);

        binding.root.setOnClickListener(v -> onItemSelectedListener.onItemSelected(characterResponse.getId()));
    }

}
