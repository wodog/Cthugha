package com.example.power;

import com.badlogic.gdx.graphics.Texture;
import com.example.Cthugha_Core;
import com.example.actions.EachBurnAction;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ShengMingFanHuanPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath(ShengMingFanHuanPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public ShengMingFanHuanPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        // this.loadRegion("thorns");
        this.img = new Texture("cthughaResources/img/power/215_32.png");

        this.type = PowerType.DEBUFF;
        
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

}
