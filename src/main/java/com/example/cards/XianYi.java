package com.example.cards;

import com.example.actions.EachBurnAction;
import com.example.enums.AbstractCardEnum;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.example.power.RiShiPower;
import com.example.power.XianYiPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import basemod.abstracts.CustomCard;

public class XianYi extends AbstractShunRanCard {

    public static final String ID = ModHelper.MakePath(XianYi.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/128.png";
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;

    public XianYi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.exhaust = true;

        this.canShunRan = false;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.canShunRan = true;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int toDraw = 10 - AbstractDungeon.player.hand.size();
                if (toDraw > 0) {
                    this.addToTop(new DrawCardAction(AbstractDungeon.player, toDraw));
                }
                this.isDone = true;
            }
        });

        this.addToBot(new ApplyPowerAction(p, p, new XianYiPower(p, 1)));

    }

    @Override
    protected void doShunRan(int size) {
        if (size >= 8) {
            this.addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    int toDraw = 10 - AbstractDungeon.player.hand.size();
                    if (toDraw > 0) {
                        this.addToTop(new DrawCardAction(AbstractDungeon.player, toDraw));
                    }
                    this.isDone = true;
                }
            });

            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new IntangiblePlayerPower(AbstractDungeon.player, 1)));
        }
    }

}
