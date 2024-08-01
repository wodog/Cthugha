package com.example.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnReceivePowerPatch.ApplyPower;
import com.example.actions.YanBaoAction;
import com.example.enums.AbstractCardEnum;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.example.power.BeiLuoShiMenXieDingPower;
import com.example.power.HeiYanDaoRenPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class ChangZhou extends AbstractShunRanCard {

    public static final String ID = ModHelper.MakePath(ChangZhou.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/136.png";
    private static final int COST = -2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    public ChangZhou() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeDamage(6);
            this.upgradeMagicNumber(1);
        }
    }

    // public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    //     if (this.upgraded) {
    //         return super.canUse(p, m);
    //     }

    //     return false;
    // }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    protected void doShunRan(int size) {
        if (size >= 0) {
            this.addToBot(new DrawCardAction(this.magicNumber, new AbstractGameAction() {
                public void update() {
                    for (AbstractCard c : DrawCardAction.drawnCards) {
                        if (c.costForTurn >= 0) {
                            c.costForTurn += 1;
                        }
                    }
                    this.isDone = true;
                    return;
                };
            }));
        }
    }

}
