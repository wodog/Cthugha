package com.example.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.example.object.AbstractSpirit;
import com.example.object.GiveFire;
import com.example.power.TaoHuoShiYanPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AbstractCardPatch {

    @SpirePatch(clz = AbstractCard.class, method = "triggerOnExhaust")
    public static class MeteorStrikePatch {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard _inst) {
            AbstractSpirit spirit = SpiritField.spirit.get(_inst);
            if (spirit != null) {
                if (spirit instanceof GiveFire) {
                    spirit.onUse();
                }
            }
        }
    }
}
