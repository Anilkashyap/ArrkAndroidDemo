package com.adi.arrk_demo.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.adi.arrk_demo.R;
import com.adi.arrk_demo.callback.OnClickCallback;
import com.adi.arrk_demo.databinding.WarsListItemBinding;
import com.adi.arrk_demo.service.model.Character;

import java.util.List;

public class StarWarsAdapter extends RecyclerView.Adapter<StarWarsAdapter.ArticleViewHolder> {

    List<? extends Character> characterList;

    public void setProjectList(final List<? extends Character> characterList) {
        if (this.characterList == null) {
            this.characterList = characterList;
            notifyItemRangeInserted(0, characterList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return StarWarsAdapter.this.characterList.size();
                }

                @Override
                public int getNewListSize() {
                    return characterList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return StarWarsAdapter.this.characterList.get(oldItemPosition).getName() ==
                            StarWarsAdapter.this.characterList.get(newItemPosition).getName();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Character newArticle = characterList.get(newItemPosition);
                    Character oldArticle = characterList.get(oldItemPosition);
                    return newArticle.getName().equals(oldArticle.getName())
                            && oldArticle.getMass().equals(newArticle.getMass());
                }
            });
            this.characterList = characterList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WarsListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.wars_list_item,
                        parent, false);

        binding.setCallback(new OnClickCallback());

        return new ArticleViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.binding.setCharacter(characterList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return characterList == null ? 0 : characterList.size();
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        final WarsListItemBinding binding;

        public ArticleViewHolder(WarsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
