package com.example.cards;

import com.badlogic.gdx.math.MathUtils;
import com.example.actions.DrawSpecificCardAction;
import com.example.enums.AbstractCardEnum;
import com.example.helpers.ModHelper;
import com.example.object.AbstractSpirit;
import com.example.object.GiveFire;
import com.example.patch.SpiritField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;

public class XinJinHuoChuan extends CustomCard {

    public static final String ID = ModHelper.MakePath(XinJinHuoChuan.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/113.png";
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    public XinJinHuoChuan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        SpiritField.spirit.set(this, new GiveFire(3));
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // this.addToBot(new ApplyPowerAction(p, p, new YinCangPower(p)));

        // this.addToBot(new AbstractGameAction() {
        //     public void update() {
        //         int toAdd = 10 - AbstractDungeon.player.hand.size();
        //         if (toAdd > 0) {
        //             this.addToBot(new DrawSpecificCardAction(card -> {
        //                 AbstractSpirit spirit = SpiritField.spirit.get(card);
        //                 if (spirit != null) {
        //                     if (spirit instanceof GiveFire) {
        //                         return true;
        //                     }
        //                 }
        //                 return false;
        //             }, toAdd));
        //         }

        //         this.isDone = true;
        //     }
        // });
    }

}
