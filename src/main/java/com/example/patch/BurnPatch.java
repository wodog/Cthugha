package com.example.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.example.power.FenJiPower;
import com.example.power.TaoHuoShiYanPower;
import com.example.relics.HuoTiHuoYan;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.MeteorStrike;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class BurnPatch {

    @SpirePatch(clz = Burn.class, method = SpirePatch.CONSTRUCTOR)
    public static class MeteorStrikePatch {
        @SpirePostfixPatch
        public static void Postfix(Burn _inst) {
            if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(TaoHuoShiYanPower.POWER_ID)) {
                _inst.upgrade();
            }
        }
    }

    @SpirePatch(clz = Burn.class, method = "triggerOnEndOfTurnForPlayingCard")
    public static class Renpi_player11 {
        @SpirePrefixPatch
        public static SpireReturn Prefix(AbstractCard _inst) {
            if (_inst.type != CardType.STATUS) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch(clz = Burn.class, method = "use", paramtypez = { AbstractPlayer.class, AbstractMonster.class })
    public static class Renpi_player {
        public static void Replace(AbstractCard _inst, AbstractPlayer p, AbstractMonster m) {
            if (AbstractDungeon.player.hasPower(FenJiPower.POWER_ID)) {
                AbstractPower power = AbstractDungeon.player.getPower(FenJiPower.POWER_ID);
                _inst.magicNumber += power.amount;
                power.flash();
            }

            if (_inst.type == CardType.ATTACK) { // 攻击牌
                if (AbstractDungeon.player.hasRelic(HuoTiHuoYan.ID)) {
                    _inst.magicNumber += 3;
                }
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m,
                                new DamageInfo(AbstractDungeon.player, _inst.magicNumber,
                                        DamageInfo.DamageType.NORMAL),
                                AbstractGameAction.AttackEffect.NONE));
            } else { // 自动打出
                if (_inst.dontTriggerOnUseCard) {
                    if (AbstractDungeon.player.hasRelic(HuoTiHuoYan.ID)) {
                        _inst.magicNumber += 3;
                        AbstractDungeon.actionManager.addToBottom(
                                new DamageRandomEnemyAction(
                                        new DamageInfo(AbstractDungeon.player, _inst.magicNumber,
                                                DamageInfo.DamageType.THORNS),
                                        AbstractGameAction.AttackEffect.FIRE));
                    } else {
                        AbstractDungeon.actionManager
                                .addToBottom(new DamageAction(AbstractDungeon.player,
                                        new DamageInfo(AbstractDungeon.player, _inst.magicNumber,
                                                DamageInfo.DamageType.THORNS),
                                        AbstractGameAction.AttackEffect.FIRE));
                    }
                }
            }

        }
    }

}
