package com.example.cards;

import com.example.actions.BreakBlockDamageAction;
import com.example.actions.DecreaseMonsterMaxHealthAction;
import com.example.actions.EachBurnAction;
import com.example.enums.AbstractCardEnum;
import com.example.helpers.ModHelper;
import com.example.power.RiShiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class LianYing extends AbstractShunRanCard {

    public static final String ID = ModHelper.MakePath(LianYing.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/连营.png";
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private int initDamage;

    public LianYing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.damage = this.baseDamage = this.initDamage = 5;
        this.magicNumber = this.baseMagicNumber = 4;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeDamage(2);
            this.initDamage += 2;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage)));

        LianYing self = this;
        this.addToBot(new AbstractGameAction() {
            public void update() {
                self.baseDamage += self.magicNumber;
                self.applyPowers();
                for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                    if (c instanceof LianYing) {
                        c.baseDamage += c.magicNumber;
                        c.applyPowers();
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                    if (c instanceof LianYing) {
                        c.baseDamage += c.magicNumber;
                        c.applyPowers();
                    }
                }
                for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    if (c instanceof LianYing) {
                        c.baseDamage += c.magicNumber;
                        c.applyPowers();
                    }
                }
                this.isDone = true;
            }

        });
    }

    @Override
    public void atTurnStart() {
        this.baseDamage = initDamage;
    }

    @Override
    protected void doShunRan(int size) {
        if (size >= 2) {
            this.addToBot(new MakeTempCardInHandAction(this));
        }
    }

}
