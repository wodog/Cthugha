package com.example.actions;

import com.example.cards.AbstractRightClickCard;
import com.example.cards.AbstractShunRanCard;
import com.example.enums.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.AbstractMonster.EnemyType;
import com.megacrit.cardcrawl.powers.BufferPower;

public class ResetShunRanAction extends AbstractGameAction {

    public ResetShunRanAction() {
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof AbstractShunRanCard) {
                c.tags.remove(CustomTags.Shun_Ran_Triggered);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof AbstractShunRanCard) {
                c.tags.remove(CustomTags.Shun_Ran_Triggered);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof AbstractShunRanCard) {
                c.tags.remove(CustomTags.Shun_Ran_Triggered);
            }
        }

        this.isDone = true;
    }

}
