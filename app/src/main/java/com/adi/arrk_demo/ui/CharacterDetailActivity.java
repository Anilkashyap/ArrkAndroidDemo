package com.adi.arrk_demo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.adi.arrk_demo.R;
import com.adi.arrk_demo.databinding.ActivityCharacterDetailsBinding;
import com.adi.arrk_demo.service.model.Character;
import com.adi.arrk_demo.utility.Constants;

public class CharacterDetailActivity extends AppCompatActivity {

    private ActivityCharacterDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Character item = getIntent().getParcelableExtra(Constants.KEY_CHARACTER);
        setContentView(R.layout.activity_character_details);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details);
        binding.setCharacter(item);
    }

}
