package com.example.actions;

import com.example.power.HuoZhuoLianZiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ZhiLiaoAction extends AbstractGameAction {
    private AbstractGameAction action;
    private AbstractCard card;

    public ZhiLiaoAction(AbstractCard card, AbstractGameAction action) {
        this.card = card;
        this.action = action;
    }

    public void update() {
        boolean attacked = false;
        if (AbstractDungeon.player.hasPower(HuoZhuoLianZiPower.POWER_ID)) {

        } else {
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                if (c == this.card) {
                    continue;
                }
                if (c.type == AbstractCard.CardType.ATTACK) {
                    attacked = true;
                    break;
                }
            }
        }
        if (!attacked) {
            this.addToBot(this.action);

            if (!AbstractDungeon.player.hasPower(HuoZhuoLianZiPower.POWER_ID)) {
                this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new EntanglePower(AbstractDungeon.player), 0));
            }
        }

        this.isDone = true;
    }
}
