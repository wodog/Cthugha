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
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class CanYangPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath(CanYangPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public CanYangPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = -1;
        this.updateDescription();
        this.img = new Texture("cthughaResources/img/power/204_32.png");
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    public void atStartOfTurnPostDraw() {
        this.flash();

        this.addToBot(new ChangeStanceAction("Divinity"));
        int toDraw = 10 - AbstractDungeon.player.hand.size();
        if (toDraw > 0) {
            this.addToTop(new DrawCardAction(AbstractDungeon.player, toDraw));
        }
    }

    public void onInitialApplication() {
        this.flash();

        this.addToBot(new ChangeStanceAction("Divinity"));
        int toDraw = 10 - AbstractDungeon.player.hand.size();
        if (toDraw > 0) {
            this.addToTop(new DrawCardAction(AbstractDungeon.player, toDraw));
        }
    }

}
