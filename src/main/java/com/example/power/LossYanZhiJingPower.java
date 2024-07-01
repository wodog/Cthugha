package com.example.power;

import com.badlogic.gdx.graphics.Texture;
import com.example.Cthugha_Core;
import com.example.actions.DecreaseMonsterMaxHealthAction;
import com.example.actions.EachBurnAction;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.RemoveNextOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class LossYanZhiJingPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath(LossYanZhiJingPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public LossYanZhiJingPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture("cthughaResources/img/power/218_32.png");
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

    public void atEndOfTurn(boolean isPlayer) {
        System.out.println("6666666665");
        for (int i = 0; i < this.amount; i++) {
            AbstractDungeon.player.removeNextOrb();
            // this.addToTop(new RemoveNextOrbAction());
        }

        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

}
