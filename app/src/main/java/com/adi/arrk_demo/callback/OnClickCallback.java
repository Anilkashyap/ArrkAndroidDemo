package com.adi.arrk_demo.callback;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.adi.arrk_demo.service.model.Character;
import com.adi.arrk_demo.ui.CharacterDetailActivity;
import com.adi.arrk_demo.utility.Constants;

public class OnClickCallback {
    public void onClick(View view, Character character) {
        Context context = view.getContext();
        Intent intent = new Intent(context, CharacterDetailActivity.class);
        intent.putExtra(Constants.KEY_CHARACTER, character);
        context.startActivity(intent);
    }
}
