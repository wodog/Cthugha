package com.example.actions;

import java.util.Iterator;

import com.example.Cthugha_Core;
import com.example.enums.CustomTags;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class YanBaoAction extends AbstractGameAction {

    private AbstractGameAction action;
    private AbstractCard card;

    public YanBaoAction(AbstractCard card, AbstractGameAction action) {
        this.action = action;
        this.card = card;
    }

    @Override
    public void update() {
        if (canYanBao()) {
            this.addToTop(this.action);
            card.tags.add(CustomTags.Yan_Bao_Triggered);
        } else {
            card.tags.remove(CustomTags.Yan_Bao_Triggered);
        }
        this.isDone = true;
    }

    // 是否能 炎爆
    private boolean canYanBao() {
        int matchNums = 0;
        int size = AbstractDungeon.player.orbs.size();
        for (int i = 0; i < size; i++) {
            AbstractOrb orb = AbstractDungeon.player.orbs.get(i);
            if (orb.ID == YanZhiJing.ORB_ID) {
                matchNums++;
            }
        }

        // Iterator<AbstractOrb> orbs = AbstractDungeon.player.orbs.iterator();
        // while (orbs.hasNext()) {
        // AbstractOrb orb = orbs.next();
        // System.out.println("===========================" + orb.ID);
        // CTHUGHA_Core.logger.info("info");
        // if (orb.ID == YanZhiJing.ORB_ID) {
        // flag = true;
        // } else {
        // return false;
        // }
        // }
        if (matchNums >= 6) {
            return true;
        }
        return false;
    }
}
