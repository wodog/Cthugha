package com.example.cards;

import com.example.actions.YanBaoAction;
import com.example.enums.AbstractCardEnum;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import basemod.abstracts.CustomCard;

public class LiuXingBao extends CustomCard {

    public static final String ID = ModHelper.MakePath(LiuXingBao.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/123.png";
    private static final int COST = -1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    public LiuXingBao() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.exhaust = true;
        this.damage = this.baseDamage = 4;
        this.magicNumber = this.baseMagicNumber = 1;

        this.tags.add(CustomTags.Yan_Bao);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if (this.upgraded) {
            effect++;
        }
        int count = effect;

        this.addToBot(new YanBaoAction(this, new AbstractGameAction() {
            public void update() {
                for (int i = 0; i < count; i++) {
                    this.addToTop(new DamageAllEnemiesAction(p, 20, DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                }
                this.isDone = true;
            }
        }));


        for (int i = 0; i < count; i++) {
            this.addToBot(new DamageAllEnemiesAction(p, 5, DamageType.NORMAL, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
        for (int i = 0; i < count; i++) {
            this.addToBot(new ChannelAction(new YanZhiJing()));
        }


        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        super.canUse(p, m);

        // EnergyPanel.totalCount: 面板的能量
        // p.energy.energy: 实际的能量
        if (EnergyPanel.totalCount == p.energy.energy) {
            return true;
        }
        return false;
    }

}
