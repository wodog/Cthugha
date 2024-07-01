package com.example.actions;

import java.util.Iterator;

import com.example.Cthugha_Core;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.RemoveNextOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class EachBurnAction extends AbstractGameAction {

    private AbstractGameAction action;
    private boolean needRemove;

    public EachBurnAction(AbstractGameAction action) {
        this.action = action;
        // this.needRemove = needRemove;
    }

    @Override
    public void update() {
        // int size = AbstractDungeon.player.hand.group.size();
        // for (int i = 0; i < size; i++) {
        //     AbstractCard card = AbstractDungeon.player.hand.group.get(i);
        //     Cthugha_Core.logger.info("==========------- atEndOfTurnPreEndTurnCards ------=================" + card.cardID);
        //     if (card.cardID == "Burn") {
                
        //         this.addToBot(this.action);
        //         this.addToBot(this.action);
        //         this.addToBot(this.action);
        //         // if (this.needRemove) {
        //         // this.addToBot(new RemoveNextOrbAction());
        //         // }
        //     }
        // }

        this.isDone = true;
    }

}
