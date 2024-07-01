package com.example.power;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.example.Cthugha_Core;
import com.example.actions.EachBurnAction;
import com.example.actions.RetainBurnAction;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

public class XuanCaoQiYanPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath(XuanCaoQiYanPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");

    public XuanCaoQiYanPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = powerStrings.NAME;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.img = new Texture("cthughaResources/img/power/214_32.png");
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[1];
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {

                // addToBot((AbstractGameAction) new RetainBurnAction(this.owner, this.amount));

                this.addToBot(
                    new SelectCardsInHandAction(this.amount, uiStrings.TEXT[0], true, true,
                            card -> (ModHelper.IsBurn(card)),
                            abstractCards -> {
                                for (AbstractCard card : abstractCards) {
                                    if (!card.isEthereal) {
                                        card.retain = true;

                                        this.addToBot(new AbstractGameAction() {
                                            public void update() {
                                                card.dontTriggerOnUseCard = true;
                                                card.use(null, null);
                                                card.dontTriggerOnUseCard = false;
                                               
                                                this.isDone = true;
                                            }
                                        });
                                    }
                                }

                                this.addToBot(new AbstractGameAction() {
                                    public void update() {
                                        for (AbstractCard card : AbstractDungeon.player.hand.group) {
                                            if (ModHelper.IsBurn(card) && !card.retain) {
                                                card.dontTriggerOnUseCard = true;
                                                card.use(null, null);
                                                card.dontTriggerOnUseCard = false;
                                            }
                                        }
                                        
                                        this.isDone = true;
                                    }
                                });

                            }
                    )
                );
        }
    }

}
