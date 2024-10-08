package com.example.helpers;

import com.example.cards.Defend;
import com.example.cards.FuZhuoShangHuan;
import com.example.enums.CustomTags;
import com.example.relics.LieSiTaShuJian;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ModHelper {
    public static String MakePath(String id) {
        return "Cthugha:" + id;
    }

    // 灼伤
    public static boolean IsBurn(AbstractCard card) {
        return card.cardID == "Burn";
    }

    // 灼伤牌
    public static boolean IsBurnCard(AbstractCard card) {
        return card.cardID == "Burn" || card.hasTag(CustomTags.Burn_Card)
                || AbstractDungeon.player.hasRelic(LieSiTaShuJian.ID);
    }

    // 打击牌
    public static boolean IsStrikeCard(AbstractCard card) {
        return card.hasTag(AbstractCard.CardTags.STRIKE);
    }

    // 防御牌
    public static boolean IsDefendCard(AbstractCard card) {
        return card.cardID == Defend.ID;
    }
}
