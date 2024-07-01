package com.example.actions;

import com.example.effect.SpiritAttachedEffect;
import com.example.object.AbstractSpirit;
import com.example.object.GiveFire;
import com.example.patch.SpiritField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;

import java.util.ArrayList;
import java.util.function.Predicate;

public class RandomAttachSpiritAction extends AbstractGameAction {
    private final boolean hideVisual;

    private AbstractSpirit spirit;

    private Predicate<AbstractCard> predicate;

    private boolean fast;

    public RandomAttachSpiritAction(AbstractSpirit spirit, int amount, Predicate<AbstractCard> predicate,
            boolean hideVisual, boolean fast) {
        setValues(AbstractDungeon.player, AbstractDungeon.player, amount);
        this.spirit = spirit;
        this.predicate = predicate;
        this.duration = fast ? Settings.ACTION_DUR_FAST : Settings.ACTION_DUR_LONG;
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.hideVisual = hideVisual;
        this.fast = fast;
    }

    public void update() {
        if (this.duration == (this.fast ? Settings.ACTION_DUR_FAST : Settings.ACTION_DUR_LONG)) {
            ArrayList<AbstractCard> rngPool = new ArrayList<>();
            for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                if (this.predicate.test(card)) {
                    AbstractSpirit spirit = SpiritField.spirit.get(card);
                    if (spirit == null) {
                        rngPool.add(card);
                    } else {
                        if (spirit instanceof GiveFire) {
                            if (spirit.amount < 3) {
                                rngPool.add(card);
                            }
                        }
                    }
                }
            }
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (this.predicate.test(card)) {
                    AbstractSpirit spirit = SpiritField.spirit.get(card);
                    if (spirit == null) {
                        rngPool.add(card);
                    } else {
                        if (spirit instanceof GiveFire) {
                            if (spirit.amount < 3) {
                                rngPool.add(card);
                            }
                        }
                    }
                }
            }
            for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                if (this.predicate.test(card)) {
                    AbstractSpirit spirit = SpiritField.spirit.get(card);
                    if (spirit == null) {
                        rngPool.add(card);
                    } else {
                        if (spirit instanceof GiveFire) {
                            if (spirit.amount < 3) {
                                rngPool.add(card);
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < this.amount && rngPool.size() > 0; i++) {
                AbstractCard card = rngPool.get(AbstractDungeon.cardRandomRng.random(rngPool.size() - 1));

                AbstractSpirit spirit = SpiritField.spirit.get(card);
                if (spirit != null) {
                    spirit.amount++;
                } else {
                    this.spirit.makeCopy().attachToCard(card);
                }

                if (!this.hideVisual) {
                    AbstractDungeon.effectList.add(new SpiritAttachedEffect(card));
                }
                rngPool.remove(card);
            }
        }

        tickDuration();
    }
}
