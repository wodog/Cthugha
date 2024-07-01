package com.example.power;

import com.badlogic.gdx.graphics.Texture;
import com.example.actions.DecreaseMonsterMaxHealthAction;
import com.example.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HeiYanPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath(HeiYanPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static int factor = 10; // 10%
    public static int baseFactor = 10;

    public HeiYanPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture("cthughaResources/img/power/202_32.png");

        this.type = com.megacrit.cardcrawl.powers.AbstractPower.PowerType.DEBUFF;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1] + factor
                + powerStrings.DESCRIPTIONS[2];
    }

    public void onInitialApplication() {
        this.addToBot(new DecreaseMonsterMaxHealthAction(owner, this.amount));
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.addToBot(new DecreaseMonsterMaxHealthAction(owner, stackAmount));
    }

    public void atStartOfTurn() {
        this.flashWithoutSound();

        float percentConversion = factor / 100.0F;
        int amountToLose = (int) (this.amount * percentConversion);
        this.addToBot(new LoseHPAction(owner, owner, amountToLose));
        this.addToBot(new DecreaseMonsterMaxHealthAction(owner, amountToLose));
    }

}
