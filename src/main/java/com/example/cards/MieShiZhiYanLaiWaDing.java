package com.example.cards;

import com.example.enums.AbstractCardEnum;
import com.example.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class MieShiZhiYanLaiWaDing extends AbstractShunRanCard {

    public static final String ID = ModHelper.MakePath(MieShiZhiYanLaiWaDing.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/107.png";
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    public MieShiZhiYanLaiWaDing() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            public void update() {
                int toAdd = 10 - AbstractDungeon.player.hand.size();
                if (toAdd > 0) {
                    this.addToBot(new MakeTempCardInHandAction(new Burn(), toAdd));
                }

                this.isDone = true;
            }
        });
    }

    @Override
    protected void doShunRan(int size) {
        if (size >= 3) {
            int damage = 16;
            if (this.upgraded) {
                damage = 22;
            }
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, damage),
                    AttackEffect.BLUNT_HEAVY));
        }

        if (size >= 9) {
            int damage = 132;
            if (this.upgraded) {
                damage = 158;
            }
            this.addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, damage, DamageType.NORMAL,
                    AttackEffect.BLUNT_HEAVY));
        }
    }

}
