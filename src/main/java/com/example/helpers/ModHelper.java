package com.example.helpers;

import com.example.enums.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ModHelper {
    public static String MakePath(String id) {
        return "Cthugha:" + id;
    }

    public static boolean IsBurn(AbstractCard card) {
        return card.cardID == "Burn";
    }

    public static boolean IsBurnCard(AbstractCard card) {
        return card.cardID == "Burn" || card.hasTag(CustomTags.Burn_Card);
    }

    public static boolean IsStrikeCard(AbstractCard card) {
        return card.hasTag(AbstractCard.CardTags.STRIKE);
    }

    public static boolean IsDefendCard(AbstractCard card) {
        return card.cardID == "Defend_R";
    }
}
