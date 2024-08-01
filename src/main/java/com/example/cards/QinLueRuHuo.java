package com.example.cards;

import com.example.Cthugha_Core;
import com.example.actions.EachYanZhiJingDamageAction;
import com.example.actions.YanBaoAction;
import com.example.enums.AbstractCardEnum;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.RemoveNextOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

import basemod.abstracts.CustomCard;

public class QinLueRuHuo extends CustomCard {

    public static final String ID = ModHelper.MakePath(QinLueRuHuo.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/140.png";
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public QinLueRuHuo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.damage = this.baseDamage = 0;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            // this.upgradeDamage(4);
            this.upgradeMagicNumber(1);
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;

        int count = 0;
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (ModHelper.IsBurnCard(card)) {
                count++;
            }
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (ModHelper.IsBurnCard(card)) {
                count++;
            }
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (ModHelper.IsBurnCard(card)) {
                count++;
            }
        }
        this.baseDamage = this.baseDamage + this.magicNumber * count;

        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void applyPowers() {
        int realBaseDamage = this.baseDamage;

        int count = 0;
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (ModHelper.IsBurnCard(card)) {
                count++;
            }
        }
        for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
            if (ModHelper.IsBurnCard(card)) {
                count++;
            }
        }
        for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
            if (ModHelper.IsBurnCard(card)) {
                count++;
            }
        }
        this.baseDamage = this.baseDamage + this.magicNumber * count;

        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage)));
    }

}
