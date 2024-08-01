package com.example.cards;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.evacipated.cardcrawl.mod.stslib.patches.bothInterfaces.OnReceivePowerPatch.ApplyPower;
import com.example.actions.ResetShunRanAction;
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
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
import com.megacrit.cardcrawl.potions.PoisonPotion;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import basemod.abstracts.CustomCard;

public class ZhongKuJianPo extends CustomCard {

    public static final String ID = ModHelper.MakePath(ZhongKuJianPo.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/103.png";
    private static final int COST = 2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public ZhongKuJianPo() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeBaseCost(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInHandAction(new Burn(), 1));

        this.addToBot(new AbstractGameAction() {
            public void update() {
                int count = 0;
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (ModHelper.IsBurnCard(card)) {
                        count++;
                    }
                }

                // 依次给予
                for (int i = 0; i < count; i++) {
                    if (i % 3 == 0) {
                        this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(p, 2, false)));
                    } else if (i % 3 == 1) {
                        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(p, 2, false)));
                    } else if (i % 3 == 2) {
                        this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, 10)));
                    }
                }

                // // 随机给予
                // for (int i = 0; i < count; i++) {
                //     int kind = MathUtils.random(0, 2);
                //     if (kind == 0) { // 2层易伤
                //         this.addToBot(new ApplyPowerAction(m, p, new VulnerablePower(p, 2, false)));
                //     } else if (kind == 1) { // 2层虚弱
                //         this.addToBot(new ApplyPowerAction(m, p, new WeakPower(p, 2, false)));
                //     } else if (kind == 2) { // 10层中毒
                //         this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, 10)));
                //     }
                // }

                this.isDone = true;
            }
        });

    }

}
