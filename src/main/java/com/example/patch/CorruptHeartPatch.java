package com.example.patch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.example.object.AbstractSpirit;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;

@SpirePatch(clz = CorruptHeart.class, method = "usePreBattleAction")
public class CorruptHeartPatch {
  public static void Postfix(CorruptHeart __instance) {
    CardCrawlGame.music.fadeOutTempBGM();
    CardCrawlGame.music.playTempBgmInstantly("Cthugha-USAO.mp3");
  }
}
