package com.example.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.example.enums.AbstractCardEnum;
import com.example.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public class HuoYanHuXi extends AbstractShunRanCard {

    public static final String ID = ModHelper.MakePath(HuoYanHuXi.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;
    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    private static final String IMG_PATH = "cthughaResources/img/card/119.png";
    private static final int COST = -2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;

    public static final Texture BurnAttack = ImageMaster.loadImage("cthughaResources/img/card/灼伤_p.png");
    public static final TextureAtlas.AtlasRegion BurnAttackReigon = new TextureAtlas.AtlasRegion(BurnAttack, 0, 0,
            BurnAttack.getWidth(), BurnAttack.getHeight());

    public static final TextureAtlas cardAtlas = new TextureAtlas(Gdx.files.internal("cards/cards.atlas"));
    public static final TextureAtlas.AtlasRegion BurnReigon = cardAtlas.findRegion("status/burn");

    public HuoYanHuXi() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();

            this.selfRetain = true;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    // public boolean canUse(AbstractPlayer p, AbstractMonster m) {
    // return false;
    // }

    private void changeBurnAttackTypeCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (ModHelper.IsBurn(c)) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
                this.changeBurn(c);
            }
        }
    }

    public static void changeBurn(AbstractCard c) {
        if (c.type == CardType.STATUS) {
            c.type = CardType.ATTACK;
            c.target = CardTarget.ENEMY;

            c.baseDamage = c.baseMagicNumber;
            c.rawDescription = "造成 !D! 点伤害。";
            c.initializeDescription();
            // c.applyPowers();

            c.jokePortrait = c.portrait = BurnAttackReigon;
        } else if (c.type == CardType.ATTACK) {
            c.type = CardType.STATUS;
            c.target = CardTarget.NONE;

            c.rawDescription = CardCrawlGame.languagePack.getCardStrings("Burn").DESCRIPTION;
            if (c.upgraded) {
                c.rawDescription = CardCrawlGame.languagePack.getCardStrings("Burn").UPGRADE_DESCRIPTION;
            }
            c.initializeDescription();

            c.jokePortrait = c.portrait = BurnReigon;
        }
    }

    public static AbstractCard getAttckBurn() {
        AbstractCard tmp = new Burn();
        HuoYanHuXi.changeBurn(tmp);
        return tmp;
    }

    protected void doShunRan(int size) {
        if (size >= 0) {
            changeBurnAttackTypeCardsInGroup(AbstractDungeon.player.hand);
            changeBurnAttackTypeCardsInGroup(AbstractDungeon.player.drawPile);
            changeBurnAttackTypeCardsInGroup(AbstractDungeon.player.discardPile);
            changeBurnAttackTypeCardsInGroup(AbstractDungeon.player.exhaustPile);

            CardCrawlGame.music.fadeOutTempBGM();
            CardCrawlGame.music.playTempBgmInstantly("Cthugha-USAO.mp3");
        }
    }
}
