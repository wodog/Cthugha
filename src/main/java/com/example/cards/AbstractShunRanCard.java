package com.example.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.example.enums.CustomTags;
import com.example.helpers.ModHelper;
import com.example.orbs.YanZhiJing;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public abstract class AbstractShunRanCard extends AbstractRightClickCard {

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    protected boolean canShunRan = true;

    public AbstractShunRanCard(String arg0, String arg1, String arg2, int arg3, String arg4, CardType arg5,
            CardColor arg6, CardRarity arg7, CardTarget arg8) {
        super(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
        this.tags.add(CustomTags.Shun_Ran);
    }

    protected abstract void doShunRan(int size);

    public void atTurnStart() {
        this.tags.remove(CustomTags.Shun_Ran_Triggered);
    }

    @Override
    protected void onRightClick() {
        if (!canShunRan) {
            return;
        }

        if (this.hasTag(CustomTags.Shun_Ran_Triggered)) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX,
                    AbstractDungeon.player.dialogY, 3.0F, "使用过瞬燃了", true));
        }

        if (!this.hasTag(CustomTags.Shun_Ran_Triggered) && AbstractDungeon.player != null) {
            this.tags.add(CustomTags.Shun_Ran_Triggered);

            if (AbstractDungeon.player.hand.findCardById("Burn") == null) {
                doShunRan(0);
            } else {
                this.addToBot(
                        new SelectCardsInHandAction(10, uiStrings.TEXT[0], true, true,
                                card -> ModHelper.IsBurnCard(card),
                                abstractCards -> {
                                    int count = abstractCards.size();

                                    // 存在 复灼伤还 的情况
                                    int fuZhuoShangHuanCount = 0;
                                    for (AbstractCard card : abstractCards) {
                                        if (card.cardID == FuZhuoShangHuan.ID) {
                                            if (card.magicNumber > fuZhuoShangHuanCount) {
                                                fuZhuoShangHuanCount = card.magicNumber;
                                            }
                                        }
                                    }
                                    if (fuZhuoShangHuanCount != 0) {
                                        count = fuZhuoShangHuanCount;
                                    }

                                    for (AbstractCard card : abstractCards) {
                                        addToBot(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
                                    }

                                    doShunRan(count);
                                }));
            }

        }
    }

}
