package com.example.cards;

import com.example.enums.AbstractCardEnum;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class FuZhuoShangHuan extends CustomCard {

    public static final String ID = ModHelper.MakePath(FuZhuoShangHuan.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/135.png";
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public FuZhuoShangHuan() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.damage = this.baseDamage = 6;
        this.magicNumber = this.baseMagicNumber = 6;
        this.tags.add(CustomTags.Burn_Card);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeDamage(2);
            this.upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage)));
    }

    public void triggerOnExhaust() {
        FuZhuoShangHuan card = new FuZhuoShangHuan();
        if (this.upgraded) {
            card.upgrade();
        }
        card.baseDamage = card.baseDamage - (card.baseDamage - this.baseDamage) - 2;
        card.baseMagicNumber = card.baseMagicNumber - (card.baseMagicNumber - this.baseMagicNumber) - 2;
        if (card.baseDamage < 0) {
            card.baseDamage = 0;
        }
        if (card.baseMagicNumber < 0) {
            card.baseMagicNumber = 0;
        }

        if (this.baseMagicNumber > 2) {
            this.addToBot(new MakeTempCardInHandAction(card, 1));
        }
    }
}
