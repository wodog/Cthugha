package com.example.cards;

import java.util.Iterator;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.example.actions.EachBurnAction;
import com.example.enums.AbstractCardEnum;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.example.power.RiShiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;

import basemod.abstracts.CustomCard;

public class QiSeGuBai extends CustomCard {

    public static final String ID = ModHelper.MakePath(QiSeGuBai.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/其色孤白.png";
    private static final int COST = 4;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    public QiSeGuBai() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.exhaust = true;

        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeBaseCost(3);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(
                new SelectCardsInHandAction(1, "移除", false, true,
                        card -> ModHelper.IsBurn(card) || ModHelper.IsDefendCard(card),
                        abstractCards -> {
                            System.out.println("5555555555555555555");
                            if (abstractCards.size() > 0) {
                                System.out.println("55555555555555555557777");
                                for (AbstractCard card : abstractCards) {
                                    // CardCrawlGame.sound.play("CARD_EXHAUST");
                                    AbstractDungeon.player.masterDeck.removeCard(card.cardID);
                                }
                            }
                        }));

    }

}
