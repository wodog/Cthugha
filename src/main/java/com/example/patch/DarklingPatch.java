package com.example.patch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.example.object.AbstractSpirit;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;

@SpirePatch(clz = Darkling.class, method = "takeTurn")
public class DarklingPatch {
  public static SpireReturn Prefix(Darkling __instance) {
    if (__instance.maxHealth <= 0) {
      return SpireReturn.Return();
    }
    return SpireReturn.Continue();
  }
}
