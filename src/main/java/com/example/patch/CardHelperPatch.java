package com.example.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.example.cards.YiChiRuXi;
import com.example.relics.HuoTiHuoYan;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.Omamori;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;

// public class CardHelperPatch {
//     @SpirePatch(clz = CardHelper.class, method = "obtain")
//     public static class FastCardObtainEffectaa {
//         @SpirePostfixPatch
//         public static void Postfix(String key, AbstractCard.CardRarity rarity, AbstractCard.CardColor color) {
//             // if (card.color == AbstractCard.CardColor.CURSE &&
//             // AbstractDungeon.player.hasRelic("Omamori") &&
//             // (AbstractDungeon.player.getRelic("Omamori")).counter != 0) {
//             // ((Omamori) AbstractDungeon.player.getRelic("Omamori")).use();
//             // this.duration = 0.0F;
//             // this.isDone = true;
//             // }

//             AbstractCard  card = CardLibrary.getCard(key);
//             // if (card.cardID == "Burn") {
//                 System.out.println("==========------------===========");
//                 if (card != null) {

//                     System.out.println(key);
//                     System.out.println(card.upgraded);
//                 } else {
//                     System.out.println("card null");
//                 }
//             // AbstractCard card = AbstractDungeon.player.masterDeck.findCardById(YiChiRuXi.ID);
//             // System.out.println(card.cardID);
//             // System.out.println(card.magicNumber);
//             // if (key == YiChiRuXi.ID) {
//             //     for (int i = 0; i < card.magicNumber; i++) {
//             //         AbstractDungeon.player.masterDeck.removeCard("Burn");
//             //     }
//             // }
//         }
//     }
// }

